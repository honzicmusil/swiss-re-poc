package org.epam.swiss.re.model;

import java.math.BigDecimal;

/**
 * The Ceo class represents the CEO of a company.
 * It extends the Employee class and sets the level to 0, as the CEO is at the top of the company hierarchy.
 */
public class Ceo extends Employee {

    /**
     * Constructor for the Ceo class.
     *
     * @param id        The unique ID of the CEO.
     * @param firstName The first name of the CEO.
     * @param lastName  The last name of the CEO.
     * @param salary    The salary of the CEO.
     */
    public Ceo(Long id,
            String firstName,
            String lastName,
            BigDecimal salary
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.level = 0;  // CEO is at the top of the hierarchy, so level is set to 0.
    }
}
