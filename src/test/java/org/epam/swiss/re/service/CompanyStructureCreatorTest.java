package org.epam.swiss.re.service;

import static org.junit.jupiter.api.Assertions.*;

import org.epam.swiss.re.model.CompanyStructure;
import org.junit.jupiter.api.Test;

class CompanyStructureCreatorTest {

    @Test
    void testCreateCompanyStructure() {
        CsvEmployeeLoader csvEmployeeLoader = new CsvEmployeeLoader();
        CompanyStructureCreator creator = new CompanyStructureCreator(csvEmployeeLoader);

        CompanyStructure companyStructure = creator.createCompanyStructure("src/test/resources/csv/official.csv");

        assertEquals("Joe", companyStructure.ceo().getFirstName());
    }

    @Test
    public void testCreateCompanyStructureNoCeo() {
        CsvEmployeeLoader csvEmployeeLoader = new CsvEmployeeLoader();
        CompanyStructureCreator creator = new CompanyStructureCreator(csvEmployeeLoader);

        assertThrows(RuntimeException.class, () -> creator.createCompanyStructure("src/test/resources/csv/noCeo.csv"));
    }

}
