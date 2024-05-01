package org.epam.swiss.re.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

import org.epam.swiss.re.model.Employee;
import org.junit.jupiter.api.Test;

class CsvEmployeeLoaderTest {

    @Test
    public void testLoadEmployeesFromCsv() {
        final Map<Long, Employee> employees = getLongEmployeeMap();

        assertEquals(5, employees.size());
        assertEquals("Joe", employees.get(123L).getFirstName());
        assertEquals("Martin", employees.get(124L).getFirstName());
        assertEquals("Bob", employees.get(125L).getFirstName());
        assertEquals("Alice", employees.get(300L).getFirstName());
        assertEquals("Brett", employees.get(305L).getFirstName());
    }

    @Test
    public void testLoadEmployeesFromCsvFileNotPresent() {
        CsvEmployeeLoader loader = new CsvEmployeeLoader();

        assertThrows(RuntimeException.class, () -> loader.loadEmployeesFromCsv("nonexistent.csv"));
    }

    @Test
    public void testLoadEmployeesFromCsvFile1000Exceeded() {
        CsvEmployeeLoader loader = new CsvEmployeeLoader();
        // Redirect System.out to a ByteArrayOutputStream
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Call the method
        loader.loadEmployeesFromCsv("src/test/resources/csv/1001exceeded.csv");

        // Restore System.out to its original stream
        System.setOut(originalOut);

        // Check the output
        assertTrue(outContent.toString().contains("Only 1000 employees are supported. Skipping the rest."));
    }


    private Map<Long, Employee> getLongEmployeeMap() {
        CsvEmployeeLoader loader = new CsvEmployeeLoader();
        return loader.loadEmployeesFromCsv("src/test/resources/csv/official.csv");
    }
}
