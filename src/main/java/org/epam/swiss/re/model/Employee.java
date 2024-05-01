package org.epam.swiss.re.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The Employee class represents an employee in a company.
 * It is an abstract class that holds common properties and methods for all types of employees.
 * Each employee has an id, first name, last name, salary, list of subordinates, average salary of subordinates, and a level in the company hierarchy.
 */
public abstract class Employee {

    protected Long id;
    protected String firstName;
    protected String lastName;
    protected BigDecimal salary;
    protected List<Subordinate> subordinates = new ArrayList<>();
    protected BigDecimal subordinatesAverageSalary = BigDecimal.ZERO;
    protected int level;

    /**
     * Assigns a subordinate to this employee and updates the average salary of subordinates.
     *
     * @param subordinate the subordinate to be assigned
     */
    public void assignSubordinate(Subordinate subordinate) {
        this.subordinates.add(subordinate);
        this.subordinatesAverageSalary = this.subordinatesAverageSalary.add(subordinate.getSalary());
    }

    /**
     * Returns the list of subordinates of this employee.
     *
     * @return the list of subordinates
     */
    public List<Subordinate> getSubordinates() {
        return subordinates;
    }

    /**
     * Returns the average salary of the subordinates of this employee.
     *
     * @return the average salary of the subordinates
     */
    public BigDecimal getSubordinatesAverageSalary() {
        return subordinatesAverageSalary.divide(new BigDecimal(subordinates.size()), RoundingMode.HALF_UP);
    }

    /**
     * Returns the id of this employee.
     *
     * @return the id of this employee
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the level of this employee in the company hierarchy.
     *
     * @return the level of this employee
     */
    public int getLevel() {
        return level;
    }

    /**
     * Returns the salary of this employee.
     *
     * @return the salary of this employee
     */
    public BigDecimal getSalary() {
        return salary;
    }

    /**
     * Returns the first name of this employee.
     *
     * @return the first name of this employee
     */
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Employee employee = (Employee) o;
        return level == employee.level &&
                Objects.equals(id, employee.id) &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(salary, employee.salary) &&
                Objects.equals(subordinates, employee.subordinates) &&
                Objects.equals(subordinatesAverageSalary, employee.subordinatesAverageSalary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, salary, subordinates, subordinatesAverageSalary, level);
    }
}
