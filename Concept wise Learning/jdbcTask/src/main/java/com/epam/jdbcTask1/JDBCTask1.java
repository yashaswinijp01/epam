package com.epam.jdbcTask1;

import java.sql.*;

public class JDBCTask1 {
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/yashudb","root","(Yashu@12)");
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from EMPLOYEE");
            while(rs.next()){
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getInt(4)+"  "+rs.getString(5));
            }
            con.close();
        }
        catch(SQLException | ClassNotFoundException e){
           e.printStackTrace();
        }
    }
}
