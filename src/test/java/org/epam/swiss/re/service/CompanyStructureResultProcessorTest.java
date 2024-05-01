package org.epam.swiss.re.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.epam.swiss.re.model.Ceo;
import org.epam.swiss.re.model.CompanyStructure;
import org.epam.swiss.re.model.Employee;
import org.epam.swiss.re.model.ReportingLineLength;
import org.epam.swiss.re.model.SalaryDiscrepancy;
import org.epam.swiss.re.model.Subordinate;
import org.junit.jupiter.api.Test;

class CompanyStructureResultProcessorTest {


    @Test
    void testAnalyzeSalaryDiscrepancies() {
        final CompanyStructure companyStructure = createSalaryDiscrepanciesStructure();

        CompanyStructureResultProcessor processor = new CompanyStructureResultProcessor(companyStructure);

        List<SalaryDiscrepancy> salaryDiscrepancies = processor.analyzeSalaryDiscrepancies();

        assertEquals(1, salaryDiscrepancies.size());
        assertEquals(companyStructure.ceo(), salaryDiscrepancies.getFirst().getManager());
    }

    @Test
    void testIdentifyLongReportingLines() {
        Map<Long, Employee> employees = new HashMap<>();
        Ceo ceo = new Ceo(123L, "Joe", "Doe", BigDecimal.valueOf(60000));
        employees.put(123L, ceo);

        Subordinate subordinate1 = new Subordinate(124L, "Martin", "Chekov", BigDecimal.valueOf(45000), 123L, 5);
        employees.put(124L, subordinate1);
        ceo.assignSubordinate(subordinate1);

        Subordinate subordinate2 = new Subordinate(125L, "Bob", "Ronstad", BigDecimal.valueOf(47000), 123L, 7);
        employees.put(125L, subordinate2);
        ceo.assignSubordinate(subordinate2);

        CompanyStructure companyStructure = new CompanyStructure(ceo);
        CompanyStructureResultProcessor processor = new CompanyStructureResultProcessor(companyStructure);

        List<ReportingLineLength> reportingLineLengths = processor.identifyLongReportingLines();

        assertEquals(1, reportingLineLengths.size());
        assertEquals(subordinate2, reportingLineLengths.getFirst().getEmployee());
    }

    private static CompanyStructure createSalaryDiscrepanciesStructure() {
        Map<Long, Employee> employees = new HashMap<>();
        Ceo ceo = new Ceo(123L, "Joe", "Doe", BigDecimal.valueOf(60000));
        employees.put(123L, ceo);

        Subordinate subordinate1 = new Subordinate(124L, "Martin", "Chekov", BigDecimal.valueOf(25000), 123L, 2);
        employees.put(124L, subordinate1);
        ceo.assignSubordinate(subordinate1);

        Subordinate subordinate2 = new Subordinate(125L, "Bob", "Ronstad", BigDecimal.valueOf(47000), 123L, 2);
        employees.put(125L, subordinate2);
        ceo.assignSubordinate(subordinate2);

        return new CompanyStructure(ceo);
    }
}
