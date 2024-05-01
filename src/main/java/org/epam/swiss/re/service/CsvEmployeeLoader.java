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

public class CsvEmployeeLoader {

    public Map<Long, Employee> loadEmployeesFromCsv(String fileName) {
        Path pathToEmployeeFile = Path.of(fileName);
        return parseFile(pathToEmployeeFile);
    }

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

    private List<String> readFile(Path pathToEmployeeFile) {
        try {
            return Files.readAllLines(pathToEmployeeFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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

    private String[] parseLine(String line) {
        return line.split(",");
    }
}
