package ch.patchcode.stupid_oo;

import java.time.LocalDate;

public class Person {

    // =========================================================================
    // exposed, inherent

    // properties
    public String firstName() { return firstName; }
    public String lastName() { return lastName; }
    public LocalDate birthday() { return birthday; }

    // initialization
    public static Person create(String firstName, String lastName, LocalDate birthday) {
        return new Person(firstName, lastName, birthday);
    }

    // =========================================================================
    // exposed, extrinsic

    // attributes, by local law
    public boolean canLegallyBuyBeer() { return !LocalDate.now().isBefore(birthday().plusYears(16)); }

    // behavior, by chosen technology
    public void saveToSqlDb(String connectionString) { throw new RuntimeException("not implemented"); }
    public static Person loadFromSqlDb(String connectionString) { throw new RuntimeException("not implemented"); }

    // =========================================================================
    // encapsulated, inherent

    private String firstName;
    private String lastName;
    private LocalDate birthday;

    private Person(String firstName, String lastName, LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }
}
