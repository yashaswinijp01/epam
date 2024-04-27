package com.epam.jdbcTask2;

import java.sql.*;

public abstract class JDBCTask2 {
    public static void main(String[] args) {
        String updateTableSql = "UPDATE employee2 SET empId =?" + "WHERE empName =?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/yashudb", "root", "(Yashu@12)");
            PreparedStatement pst = con.prepareStatement(updateTableSql);

            pst.setString(1,"Aman");
            pst.setString(2,"Arun");

            pst.executeUpdate();

            System.out.println("Table is updated");

            pst.close();
            con.close();

        }

        catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
