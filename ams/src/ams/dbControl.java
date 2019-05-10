/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams;

/**The Database Controller class
 *
 * @author Maan Alaulaqi (201610814@aau.ac.ae)
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.lang.NullPointerException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dbControl {
    
    private static Connection con ;
    static Statement COMMANDTHEE;
    static Statement DO_THE_THING; //generally known as "stmt"
    static ResultSet rs, rsUpdate;
    static ResultSet rsUpdateMe;
    private String SQL;
    
    public dbControl(){
        //doConnect();
    }
    /**
     * This method is aimed at making a general use method for SQL statements. 
     * When using this method, be sure to add the "doClose();" method in your finally block
     * in the end of the try-catch. 
     * 
     * @param sqlcmd The all-in-one SQL query (READ ONLY) method. The WRITABLE method is doComdUpdate(String) 
     */
    public static void dbComd(String sqlcmd){
        try {
            doConnect();
            COMMANDTHEE = doConnect().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);//ResultSet.CONCUR_UPDATABLE if we wish to update the record
            String SQL = sqlcmd;
            rs = COMMANDTHEE.executeQuery(SQL);
           /* while (rs.next()){
                int id_col = rs.getInt("ID");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                Date birth_date = rs.getDate("birth_date"); // Figure out how to format this
                String phone = rs.getString("contact_phone");
                System.out.println(id_col + " " + first_name+ " " +last_name+ " " +birth_date+ " " +phone);
                //System.out.println(id_col);
            }*/



           } 
           catch (SQLException error){
               System.out.println("Oopsies, you gone get goofed, here's the error message:  ");
               System.out.println(error.getMessage());
           }
    }
    /**
     * dComUpdate is to be used when updating a record in the database(student presence for example)
     * When this is called, call doClose() right after it to avoid database errors and close unneeded connections.
     * Generally, at the "finally" stage of a try-catch-finally.
     * @param sqlcmd The all-in-one SQL query (UPDATABLE) method. 
     */
    public static void dbComdUpdate(String sqlcmd){
        try {
            doConnect();
            DO_THE_THING = doConnect().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);//ResultSet.CONCUR_UPDATABLE if we wish to update the record
            String SQL = sqlcmd;
            rsUpdate = DO_THE_THING.executeQuery(SQL);
            
           /* while (rs.next()){
                int id_col = rs.getInt("ID");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                Date birth_date = rs.getDate("birth_date"); // Figure out how to format this
                String phone = rs.getString("contact_phone");
                System.out.println(id_col + " " + first_name+ " " +last_name+ " " +birth_date+ " " +phone);
                //System.out.println(id_col);
            }*/



           } 
           catch (SQLException error){
               System.out.println("Oopsies, you gone get goofed, here's the error message: ");
               System.out.println(error.getMessage());
           }
    }
    
    public static String getID(String uid){
        String x = null;
        try {
            dbComd("SELECT ID FROM STUDENT WHERE CARD_ID = '"+uid+"'");
            if(rs.next()){
                x = rs.getInt("ID") + "";
                System.out.println(x);
                doClose();
            }   } catch (SQLException ex) {
            Logger.getLogger(dbControl.class.getName()).log(Level.SEVERE, null, ex);
            }finally {doClose();}
        return x;
    }
    
    
        
    
    
    /**
     * Connects to the database. 
     * I've had issues with this bit. It continuously tried to connect to a 
     * deleted scheme, therefore I manually set a direct connection to 
     * the schema we're using with "con.setSchema("AMS");"
     * @param username = "test" username for the DB
     * @param password = "test" username for the DB
     * @return  con to carry out the connection to other methods
     */
    public static Connection doConnect(){ 
            try{
                //Connect to the db 
                String host = "jdbc:derby://localhost:1527/ams/";
                String username = "test";
                String password = "test";
                Connection con = DriverManager.getConnection( host, username, password );//login info; test, test
                con.setSchema("AMS");
                con.setAutoCommit(false);

                //System.out.println(con.getSchema());
                
                return con;
            }catch (SQLException error){
                System.out.println("Oopsies, you gone get goofed, here's the error message: ");
                System.out.println(error.getMessage());
                return con;
            }
            
        }
    /**
     * Closes the connection in the agreed upon safe pattern:
     * ResultSet ¬ Statement ¬ Connection.
     * Should generally be added to the finally block after the try-catch. 
     * Based on ( https://stackoverflow.com/questions/2225221/closing-database-connections-in-java ) 
     */
    public static void doClose(){
        try { rs.close(); } catch (Exception e) { /* ignored */ }
        try { COMMANDTHEE.close(); } catch (Exception e) { /* ignored */ }
        try { con.close(); } catch (Exception e) { /* ignored */ }
    }
}


