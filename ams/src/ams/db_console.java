/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams;

/**
 *
 * @author Maan Alaulaqi (201610814@aau.ac.ae)
 */


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;



public class db_console {

  /*
    public static void main(String[] args) {
       try {
        String host = "jdbc:derby://localhost:1527/ams/";
        String username = "test";
        String password = "test";
        Connection con = DriverManager.getConnection( host, username, password );//login info; test, test
        System.out.println(con.getSchema());
        /*
        Statement COMMANDTHEE = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);//ResultSet.CONCUR_UPDATABLE if we wish to update the record
        String SQL = "SELECT * FROM STUDENT";
        ResultSet rs = COMMANDTHEE.executeQuery(SQL);
        while (rs.next()){
        int id_col = rs.getInt("ID");
        String first_name = rs.getString("first_name");
        String last_name = rs.getString("last_name");
        Date birth_date = rs.getDate("birth_date"); // Figure out how to format this
        String phone = rs.getString("contact_phone");
        System.out.println(id_col + " " + first_name+ " " +last_name+ " " +birth_date+ " " +phone);
        //System.out.println(id_col);
        }


        
       } 
       catch (SQLException error){
           System.out.println("Oopsies, you gone get goofed, here's the error message: ");
           System.out.println(error.getMessage());
       }

    }
    
*/    
}

