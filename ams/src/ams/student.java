/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams;

import java.sql.SQLException;
import java.sql.Date;

/** @deprecated An early class assigned for students
 *
 * @author Maan Alaulaqi (201610814@aau.ac.ae)
 */
public class student {
    private static String fName;
    private static String lName;
    private static int id;
    private static Date dob; //consider date format?
    private static String contact_phone;
    private static String card_id;
    private static dbControl boop;
    
    
    public student() {
        
        try{
            boop.dbComd(SQLStudent("*", "STUDENT"));
           //boop.SQL = SQLStudent();
           //boop.rs = boop.COMMANDTHEE.executeQuery(boop.SQL);
           System.out.println(boop.rs.getRow());
           while (boop.rs.next()){
               id = boop.rs.getInt("ID");
               fName = boop.rs.getString("first_name");
               lName = boop.rs.getString("last_name");
               dob = boop.rs.getDate("birth_date");
               contact_phone = boop.rs.getString("contact_mobile");
               System.out.println(boop.rs.getRow());
               print();

           }
        }catch (SQLException error){
               System.out.println("Oopsies, you gone get goofed, here's the error message: ");
               System.out.println(error.getMessage());
        }finally{
            boop.doClose();
            System.out.println("Closed");
        }
    }/**@deprecated 
     * The idea here is to have a ready SQL statement that can be multi-purposed
     * @param column This is a string. To include more than one column, make sure to pass 
     * all the needed columns as a string. ie: "col1, col2"
     * @param table This is a string. At a later time, this will be able to include multiple tables
     * @return Returns an SQL command
     */
    public String SQLStudent(String column, String table){
        return "SELECT "+column+" FROM "+table;
    }
    /**
     * This method will compare the card_id (from the database) with the UID of the card that 
     * is scanned 
     * @param cardid This will be called forth from the database. 
     * //TO-DO Figure out how to pass SQL parameters through java.
     */
    public static void cardIDLookUp(String cardid){
        String x = "SELECT id, student.first_name, student.last_name, birth_date, contact_mobile FROM Student WHERE student.card_id = '"+cardid+"'";
        //x += cardid;
        try{
            //String SQL = "SELECT student.id, student.first_name, student.last_name,  FROM STUDENT WHERE student.card_id = cardid";
            boop.dbComd(x);
            //boop.SQL = SQLStudent();
            //boop.rs = boop.COMMANDTHEE.executeQuery(boop.SQL);
            System.out.println(boop.rs.getRow());
            System.out.println("boop");
            //System.out.println(boop.rs.next());
            while (boop.rs.next()){
              System.out.println("Reached");
                id = boop.rs.getInt("ID");
                fName = boop.rs.getString("first_name");
                lName = boop.rs.getString("last_name");
                dob = boop.rs.getDate("birth_date");
                contact_phone = boop.rs.getString("contact_mobile");
                System.out.println(boop.rs.getRow());
                print();


            }
        }catch (SQLException error){
            System.out.println("Oopsies, you gone get goofed, here's the error message: ");
            System.out.println(error.getMessage());
        }finally{
            boop.doClose();
        }
    }
    public static void print(){
        System.out.println(id + " " + fName+ " " +lName+ " " +dob+ " " +contact_phone);

    }
    /**
     * Used when a student wishes to see their profile and attendance records
     * This will be automatically called if AMS is inactive (amsActivate class will determine this)
     */
    public static void getProfile(){
        //TO-DO get student's profile
    }
    
}
