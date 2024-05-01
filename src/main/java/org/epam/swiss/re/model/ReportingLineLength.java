package org.epam.swiss.re.model;

import java.util.Objects;

/**
 * The ReportingLineLength class represents the length of the reporting line for an employee.
 * It holds a reference to the employee and the excess length of the reporting line.
 * <p>
 * The excess length is the number of levels in the reporting line that exceed a certain threshold.
 * For example, if the threshold is 5 and an employee has 7 people in their reporting line, the excess length is 2.
 */
public class ReportingLineLength {

    private final Employee employee;
    private final int excessLength;

    /**
     * Constructor for the ReportingLineLength class.
     *
     * @param employee     The employee for whom the reporting line length is being calculated.
     * @param excessLength The excess length of the reporting line.
     */
    public ReportingLineLength(Employee employee, int excessLength) {
        this.employee = employee;
        this.excessLength = excessLength;
    }

    /**
     * Returns the employee for whom the reporting line length is being calculated.
     *
     * @return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    @Override
    public String toString() {
        return "ReportingLineLength{" +
                "employee=" + employee.toString() +
                ", excessLengthBy=" + excessLength +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportingLineLength that = (ReportingLineLength) o;
        return excessLength == that.excessLength &&
                Objects.equals(employee, that.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee, excessLength);
    }
}
