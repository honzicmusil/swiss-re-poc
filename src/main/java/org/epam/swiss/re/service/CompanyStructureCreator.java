package org.epam.swiss.re.service;

import java.util.Map;

import org.epam.swiss.re.model.Ceo;
import org.epam.swiss.re.model.CompanyStructure;
import org.epam.swiss.re.model.Employee;

/**
 * The CompanyStructureCreator class is responsible for creating the company structure.
 * It uses the CsvEmployeeLoader to load employees from a CSV file and then finds the CEO among them.
 * The CEO is then used to create a new CompanyStructure.
 */
public class CompanyStructureCreator {

    private final CsvEmployeeLoader csvEmployeeLoader;

    /**
     * Constructor for the CompanyStructureCreator class.
     *
     * @param csvEmployeeLoader The CsvEmployeeLoader to be used for loading employees.
     */
    public CompanyStructureCreator(CsvEmployeeLoader csvEmployeeLoader) {
        this.csvEmployeeLoader = csvEmployeeLoader;
    }

    /**
     * Creates a new CompanyStructure using the employees loaded from a CSV file.
     *
     * @param fileName The name of the CSV file.
     * @return A new CompanyStructure.
     */
    public CompanyStructure createCompanyStructure(String fileName) {
        Map<Long, Employee> employees = csvEmployeeLoader.loadEmployeesFromCsv(fileName);
        Ceo ceo = findCeo(employees);
        return new CompanyStructure(ceo);
    }

    /**
     * Finds the CEO among the employees.
     *
     * @param employees The employees to search through.
     * @return The CEO.
     * @throws RuntimeException If no CEO is found.
     */
    private Ceo findCeo(Map<Long, Employee> employees) {
        for (Employee employee : employees.values()) {
            // this sucks, but instanceof does not work for inheritance
            if (Ceo.class.getName().equals(employee.getClass().getName())) {
                return (Ceo) employee;
            }
        }
        throw new RuntimeException("No CEO found in the employees map. Check your csv file.");
    }
}
