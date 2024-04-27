package com.epam.jdbc;

import java.sql.*;

public class CreateTableExample {
    public static void main(String[] args) {
        final String URL = "jdbc:mariadb://localhost:3306/root";
        final String username = "root";
        final String password = "(Yashu@12)";

        try (
                Connection connection = DriverManager.getConnection(URL, username, password);
                Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate("Create table employees (employee_id int, name varchar(255));");
            statement.executeUpdate("Insert into employees values(1, 'Vishal');");
            ResultSet rs = statement.executeQuery("Select * from employees");

            while (rs.next()) {
                System.out.println(rs.getInt(1));
                System.out.println(rs.getString(2));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

}
