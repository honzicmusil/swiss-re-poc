package org.epam.swiss.re.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.epam.swiss.re.model.Ceo;
import org.epam.swiss.re.model.Employee;
import org.epam.swiss.re.model.Subordinate;

/**
 * The CsvEmployeeLoader class is responsible for loading employee data from a CSV file.
 * It reads the file line by line, parses each line into an Employee object, and stores them in a Map.
 * The key of the Map is the unique ID of the employee.
 * If an employee has a manager (indicated by a manager ID in the CSV file), the employee is created as a Subordinate object.
 * The first employee without a manager ID is created as a Ceo object.
 * The class supports a maximum of 1000 employees. If the CSV file contains more than 1000 lines (excluding the header), the rest will be skipped.
 */
public class CsvEmployeeLoader {

    /**
     * Loads employees from a CSV file.
     *
     * @param fileName The name of the CSV file.
     * @return A map of employees, keyed by their unique ID.
     */
    public Map<Long, Employee> loadEmployeesFromCsv(String fileName) {
        Path pathToEmployeeFile = Path.of(fileName);
        return parseFile(pathToEmployeeFile);
    }

    /**
     * Parses a CSV file into a map of employees.
     *
     * @param pathToEmployeeFile The path to the CSV file.
     * @return A map of employees, keyed by their unique ID.
     */
    private Map<Long, Employee> parseFile(Path pathToEmployeeFile) {
        List<String> lines = readFile(pathToEmployeeFile);
        Map<Long, Employee> employees = new HashMap<>();
        for (int i = 1; i < lines.size(); i++) { // Skip header
            if (i > 1000) {
                System.out.println("Only 1000 employees are supported. Skipping the rest.");
                break;
            }
            final String[] parsedValues = parseLine(lines.get(i));
            Employee employee = createEmployee(parsedValues, employees);
            employees.put(employee.getId(), employee);
        }
        return employees;
    }

    /**
     * Reads all lines from a file.
     *
     * @param pathToEmployeeFile The path to the file.
     * @return A list of all lines in the file.
     */
    private List<String> readFile(Path pathToEmployeeFile) {
        try {
            return Files.readAllLines(pathToEmployeeFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates an Employee object from a parsed line of a CSV file.
     *
     * @param parsedValues The parsed values from a line of the CSV file.
     * @param employees The map of already created employees.
     * @return The created Employee object.
     */
    private Employee createEmployee(String[] parsedValues, Map<Long, Employee> employees) {
        if (parsedValues.length > 4 && !parsedValues[4].isEmpty()) {

            if (employees.get(Long.parseLong(parsedValues[4])) == null) {
                throw new RuntimeException("Manager with id " + parsedValues[4] + " not found for employee with id " + parsedValues[0]);
            }

            final Subordinate subordinate = new Subordinate(
                    Long.parseLong(parsedValues[0]),
                    parsedValues[1],
                    parsedValues[2],
                    new BigDecimal(parsedValues[3]),
                    Long.parseLong(parsedValues[4]),
                    employees.get(Long.parseLong(parsedValues[4])).getLevel() + 1
            );

            employees.get(Long.parseLong(parsedValues[4])).assignSubordinate(subordinate);

            return subordinate;
        } else {
            return new Ceo(
                    Long.parseLong(parsedValues[0]),
                    parsedValues[1],
                    parsedValues[2],
                    new java.math.BigDecimal(parsedValues[3])
            );
        }
    }

    /**
     * Parses a line of a CSV file into an array of strings.
     *
     * @param line The line to parse.
     * @return An array of parsed values.
     */
    private String[] parseLine(String line) {
        return line.split(",");
    }
}
