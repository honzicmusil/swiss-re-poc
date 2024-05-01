package org.epam.swiss.re.service.utils;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Random;

public class CsvGenerator {
    public static void main(String[] args) {
        String fileName = "employees.csv";
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println("id,firstName,lastName,salary,managerId");

            // CEO
            writer.println("1,CEO,CEO,100000,");

            // Employees
            Random random = new Random();
            for (int i = 2; i <= 1001; i++) {
                int salary = 30000 + random.nextInt(30000);
                writer.println(i + ",Employee" + i + ",Lastname" + i + "," + salary + ",1");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}
