package ch.patchcode.stupid_oo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public boolean canLegallyBuyBeer() {
        return !LocalDate.now().isBefore(birthday().plusYears(16));
    }

    // behavior, by chosen technology

    public void saveToSqlDb(Connection sqlConnection) throws SQLException {
        Statement stmt = sqlConnection.createStatement();
        stmt.execute(String.format("""
                INSERT INTO Persons (firstName, lastName, birthday)
                VALUES ('%s', '%s', '%s')
                """,
                firstName(),
                lastName(),
                birthday().format(DateTimeFormatter.ISO_LOCAL_DATE)));
    }

    public static Person loadFromSqlDb(Connection sqlConnection, String firstName, String lastName) throws SQLException {
        Statement stmt = sqlConnection.createStatement();
        ResultSet rs = stmt.executeQuery(String.format("""
                SELECT firstName, lastName, birthday FROM Persons
                WHERE  firstName = '%s' AND lastName = '%s'
                """,
                firstName,
                lastName));
        return new Person(
                rs.getString("firstName"),
                rs.getString("lastName"),
                LocalDate.parse(rs.getString("birthday"))
        );
    }

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
