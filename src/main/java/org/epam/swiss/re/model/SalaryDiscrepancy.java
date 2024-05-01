package org.epam.swiss.re.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * The SalaryDiscrepancy class represents the discrepancy in salary between a manager and their subordinates.
 * It holds a reference to the manager, the average salary of the subordinates, and the salary of the manager.
 * <p>
 * The discrepancy is calculated as the difference between the manager's salary and the average salary of the subordinates.
 * The percentage difference is also calculated.
 */
public class SalaryDiscrepancy {

    private final Employee manager;
    private final BigDecimal averageSubordinateSalary;
    private final BigDecimal managerSalary;

    /**
     * Constructor for the SalaryDiscrepancy class.
     *
     * @param manager                 The manager for whom the salary discrepancy is being calculated.
     * @param averageSubordinateSalary The average salary of the subordinates.
     * @param managerSalary           The salary of the manager.
     */
    public SalaryDiscrepancy(Employee manager, BigDecimal averageSubordinateSalary, BigDecimal managerSalary) {
        this.manager = manager;
        this.averageSubordinateSalary = averageSubordinateSalary;
        this.managerSalary = managerSalary;
    }

    /**
     * Returns the manager for whom the salary discrepancy is being calculated.
     *
     * @return the manager
     */
    public Employee getManager() {
        return manager;
    }

    @Override
    public String toString() {
        BigDecimal difference = managerSalary.subtract(averageSubordinateSalary);
        BigDecimal percentageDifference = difference.divide(averageSubordinateSalary, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));

        return "SalaryDiscrepancy{" +
                "manager=" + manager.toString() +
                ", averageSubordinateSalary=" + averageSubordinateSalary +
                ", managerSalary=" + managerSalary +
                ", percentageDifference=" + percentageDifference + "%" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SalaryDiscrepancy that = (SalaryDiscrepancy) o;
        return Objects.equals(manager, that.manager) &&
                Objects.equals(averageSubordinateSalary, that.averageSubordinateSalary) &&
                Objects.equals(managerSalary, that.managerSalary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manager, averageSubordinateSalary, managerSalary);
    }

}
