package org.epam.swiss.re;

import org.epam.swiss.re.model.CompanyStructure;
import org.epam.swiss.re.service.CompanyStructureCreator;
import org.epam.swiss.re.service.CompanyStructureResultProcessor;
import org.epam.swiss.re.service.CsvEmployeeLoader;

/**
 * Main class for the Company Structure Analyzer application.
 * This class is responsible for initiating the analysis process.
 */
public class BigCompanyStructureOptimiserMain {

    /**
     * The main method which is the entry point of the application.
     * It expects the path to the CSV file as a command-line argument.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the Company Structure Analyzer!");
        if (args.length == 0) {
            System.out.println("Please provide the path to the CSV file as a command-line argument.");
            return;
        }

        System.out.println("Analyzing the company structure...");
        final CompanyStructureResultProcessor processor = performAnalysis(args);

        System.out.println(processor.analyzeSalaryDiscrepancies());
        System.out.println(processor.identifyLongReportingLines());

        System.out.println("Analysis complete!");
    }

    /**
     * Performs the analysis of the company structure.
     * It loads the employees from the CSV file, creates the company structure,
     * and then processes the results.
     *
     * @param args Command-line arguments.
     * @return The result processor with the analysis results.
     */
    private static CompanyStructureResultProcessor performAnalysis(String[] args) {
        CsvEmployeeLoader csvEmployeeLoader = new CsvEmployeeLoader();
        CompanyStructureCreator companyStructureCreator = new CompanyStructureCreator(csvEmployeeLoader);
        CompanyStructure companyStructure = companyStructureCreator.createCompanyStructure(args[0]);
        return new CompanyStructureResultProcessor(companyStructure);
    }
}
