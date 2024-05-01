# Swiss Re POC

This project is a proof of concept for a company structure creation and analysis system. It is implemented in Java and uses Maven for dependency management.

## Implementation

The project consists of several key components:

1. `CsvEmployeeLoader`: This class is responsible for loading employee data from a CSV file. It parses the file, creates `Employee` objects, and stores them in a `Map` for easy access.

2. `CompanyStructureCreator`: This class uses the `CsvEmployeeLoader` to create a `CompanyStructure` object. The `CompanyStructure` represents the entire company, with the CEO at the top and all other employees as subordinates.

3. `CompanyStructureResultProcessor`: This class takes a `CompanyStructure` and performs various analyses on it. Currently, it can analyze salary discrepancies and identify long reporting lines.

4. `Main`: This is the entry point of the application. It takes a path to a CSV file as a command-line argument, creates a `CompanyStructure` from the file, and then performs the analyses.

## How to Run

To run the application, you first need to build it. This can be done using Maven:

```bash
mvn clean package
```
This will create a JAR file in the target directory. You can then run this JAR file using the Java command-line tool:

```bash
java -jar target/swiss-re-poc-1.0.0-SNAPSHOT.jar path/to/your/csv/file.csv
```

Replace `path/to/your/csv/file.csv` with the actual path to the CSV file you want to analyze.  Please note that the CSV file should be formatted correctly for the application to work. Each line should represent an employee, with the following fields in order: id, first name, last name, salary, and manager ID (if applicable).
Also, we expect that the ceo and managers are present in the csv file before the employees.
