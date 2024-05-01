package org.epam.swiss.re.model;

/**
 * The CompanyStructure class represents the structure of a company.
 * It holds a reference to the CEO of the company.

 * The CEO is the top of the company hierarchy and all other employees are
 * subordinates of the CEO or subordinates of the CEO's subordinates.
 */
public record CompanyStructure(Ceo ceo) {

}
