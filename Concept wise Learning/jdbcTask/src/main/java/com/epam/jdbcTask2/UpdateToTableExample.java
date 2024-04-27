package com.epam.jdbcTask2;

import java.sql.*;

public class UpdateToTableExample {
    public static void main(String[] args) {
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yashudb", "root", "(Yashu@12)");
            Statement statement = connection.createStatement();
            System.out.println("Creating Table Employee3");
            //statement.executeUpdate("Create Table Employee3(empID int, empName varchar(255)); ");
            statement.executeUpdate("INSERT INTO employee3(empID,empName) values(305,'Anusha')");
            ResultSet rs=statement.executeQuery("select * from EMPLOYEE3");
            while(rs.next()){
                System.out.println(rs.getInt(1)+"  "+rs.getString(2));
            }
            connection.close();
        }

        catch (SQLException e) {
            System.out.println(e);
        }
    }
}
