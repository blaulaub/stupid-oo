package ch.patchcode.stupid_oo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {

    public static void main(String args[]) throws ClassNotFoundException, SQLException {

        // tell the Java VM we use H2 and get a connection
        Class.forName ("org.h2.Driver");
        Connection conn = DriverManager.getConnection ("jdbc:h2:mem:anyTest");

        // create the data tables
        createSqlTables(conn);

        // store one person in the tables
        Person
                .create("Kim", "Qwertzuiop", LocalDate.parse("2000-01-01"))
                .saveToSqlDb(conn);
    }

    private static void createSqlTables(Connection conn) throws SQLException {
        conn.createStatement().executeUpdate("""
                CREATE TABLE Persons (
                    firstName VARCHAR(255),
                    lastName VARCHAR(255),
                    birthday DATE,
                    PRIMARY KEY (firstName, lastName)
                )
                """);
    }
}
