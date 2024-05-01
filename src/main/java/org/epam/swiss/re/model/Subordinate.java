package org.epam.swiss.re.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * The Subordinate class represents a subordinate in a company.
 * It extends the Ceo class and holds additional properties specific to a subordinate.
 * Each subordinate has a managerId and a level in the company hierarchy.
 */
public class Subordinate extends Ceo {

    private final Long managerId;

    /**
     * Constructor for the Subordinate class.
     *
     * @param id         The id of the subordinate.
     * @param firstName  The first name of the subordinate.
     * @param lastName   The last name of the subordinate.
     * @param salary     The salary of the subordinate.
     * @param managerId  The id of the manager of the subordinate.
     * @param level      The level of the subordinate in the company hierarchy.
     */
    public Subordinate(Long id,
            String firstName,
            String lastName,
            BigDecimal salary,
            Long managerId,
            int level
    ) {
        super(id, firstName, lastName, salary);
        this.managerId = managerId;
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subordinate that = (Subordinate) o;
        return id.equals(that.id) &&
                level == that.level &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(salary, that.salary) &&
                Objects.equals(subordinates, that.subordinates) &&
                Objects.equals(subordinatesAverageSalary, that.subordinatesAverageSalary) &&
                Objects.equals(managerId, that.managerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, salary, subordinates, subordinatesAverageSalary, level, managerId);
    }
}
