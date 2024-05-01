package org.epam.swiss.re.service;

import org.epam.swiss.re.model.CompanyStructure;
import org.epam.swiss.re.model.Employee;
import org.epam.swiss.re.model.ReportingLineLength;
import org.epam.swiss.re.model.SalaryDiscrepancy;
import org.epam.swiss.re.model.Subordinate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The CompanyStructureResultProcessor class is responsible for analyzing the company structure.
 * It identifies salary discrepancies and long reporting lines.
 */
public class CompanyStructureResultProcessor {

    // Threshold for identifying long reporting lines
    public static final int LEVEL_EXCEEDED_THRESHOLD = 5;

    // Coefficients for identifying salary discrepancies
    public static final double MINIMUM_SALARY_THRESHOLD_COEFFICIENT = 1.2;
    public static final double MAXIMUM_SALARY_THRESHOLD_COEFFICIENT = 1.5;

    // The company structure to be analyzed
    private final CompanyStructure companyStructure;

    /**
     * Constructor for the CompanyStructureResultProcessor class.
     *
     * @param companyStructure The company structure to be analyzed.
     */
    public CompanyStructureResultProcessor(CompanyStructure companyStructure) {
        this.companyStructure = companyStructure;
    }

    /**
     * Analyzes the company structure for salary discrepancies.
     *
     * @return A list of salary discrepancies.
     */
    public List<SalaryDiscrepancy> analyzeSalaryDiscrepancies() {
        List<SalaryDiscrepancy> salaryDiscrepancies = new ArrayList<>();
        analyzeSalaryDiscrepanciesRecursive(companyStructure.ceo(), salaryDiscrepancies);
        return salaryDiscrepancies;
    }

    /**
     * Recursive helper method for analyzing salary discrepancies.
     *
     * @param manager The manager whose salary is to be compared with their subordinates.
     * @param salaryDiscrepancies The list of identified salary discrepancies.
     */
    private void analyzeSalaryDiscrepanciesRecursive(Employee manager, List<SalaryDiscrepancy> salaryDiscrepancies) {
        if (!manager.getSubordinates().isEmpty()) {
            BigDecimal averageSubordinateSalary = manager.getSubordinatesAverageSalary();
            BigDecimal minManagerSalary = averageSubordinateSalary.multiply(BigDecimal.valueOf(MINIMUM_SALARY_THRESHOLD_COEFFICIENT));
            BigDecimal maxManagerSalary = averageSubordinateSalary.multiply(BigDecimal.valueOf(MAXIMUM_SALARY_THRESHOLD_COEFFICIENT));
            if (manager.getSalary().compareTo(minManagerSalary) < 0 || manager.getSalary().compareTo(maxManagerSalary) > 0) {
                salaryDiscrepancies.add(new SalaryDiscrepancy(manager, averageSubordinateSalary, manager.getSalary()));
            }
        }
        for (Subordinate subordinate : manager.getSubordinates()) {
            analyzeSalaryDiscrepanciesRecursive(subordinate, salaryDiscrepancies);
        }
    }

    /**
     * Identifies long reporting lines in the company structure.
     *
     * @return A list of long reporting lines.
     */
    public List<ReportingLineLength> identifyLongReportingLines() {
        List<ReportingLineLength> reportingLineLengths = new ArrayList<>();
        identifyLongReportingLinesRecursive(companyStructure.ceo(), reportingLineLengths);
        return reportingLineLengths;
    }

    /**
     * Recursive helper method for identifying long reporting lines.
     *
     * @param employee The employee whose reporting line length is to be checked.
     * @param reportingLineLengths The list of identified long reporting lines.
     */
    private void identifyLongReportingLinesRecursive(Employee employee, List<ReportingLineLength> reportingLineLengths) {
        if (employee.getLevel() > LEVEL_EXCEEDED_THRESHOLD) { // 4 managers between them and the CEO means the employee is at level 6 or higher
            reportingLineLengths.add(new ReportingLineLength(employee, employee.getLevel() - LEVEL_EXCEEDED_THRESHOLD));
        }
        for (Subordinate subordinate : employee.getSubordinates()) {
            identifyLongReportingLinesRecursive(subordinate, reportingLineLengths);
        }
    }
}
