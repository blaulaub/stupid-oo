package ch.patchcode.stupid_oo;

import java.time.LocalDate;

public class Person {

    // =========================================================================
    // exposed

    // properties
    public String firstName() { return firstName; }
    public String lastName() { return lastName; }
    public LocalDate birthday() { return birthday; }

    // initialization
    public static Person create(String firstName, String lastName, LocalDate birthday) {
        return new Person(firstName, lastName, birthday);
    }

    // =========================================================================
    // encapsulated

    private String firstName;
    private String lastName;
    private LocalDate birthday;

    private Person(String firstName, String lastName, LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }
}
