/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams;

import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/** This will mostly geared towards obtaining different information from the database,
 * such as student info, class timings, etc. 
 * I mainly made it so I can use it with the UI so that I don't have a messy thing
 * going on over in that class and try to keep it strictly UI.
 *
 * @author Maan Alaulaqi (201610814@aau.ac.ae)
 */
public class InterfaceCmds {
    private static dbControl dbC;
    InterfaceCmds() {}
    
    public static String getCurrentClass(int x){//x = 1 : return class name, x = 2: return start, x = 3: return end time
        int classID = ClassThread.classCheck();
        String className = "", startTime = "", endTime = "";
        dbC.dbComd("select active_classes.id, name, start_time, end_time, firstt_class, second_class from active_classes join class on active_classes.CLASS_ID = class.id join class_schedule on active_classes.class_schedule_id = class_schedule.id where active_classes.ID = " +classID);
        try {
            if(dbC.rs.next()){
                if(classID == 0) {
                    className = "There are currently no classes ongoing.";
                }else{
                    className = dbC.rs.getString("NAME");
                    startTime = dbC.rs.getString("START_TIME");
                    endTime = dbC.rs.getString("END_TIME");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceCmds.class.getName()).log(Level.SEVERE, null, ex);
        } finally {dbC.doClose();}
        System.out.print(className + " " + classID);
        
        if(x == 1){
            return className;
            //return "fwefljewo igerbpogboewrngewrgnjewrpourewng;rewugnjewrpougbrewoighbregbhrewbgoihrewbgoihrewg";
            //I need to figure out a way to allow lines to wrap around the next line.
        }else if(x == 2)
            return startTime;
        else 
            return endTime;
    }
    /**
     * This is supposed to return the Card holder's name for the main screen of the UI
     */
    static String xx = "";
    public static String getCurrentUID(String UID){
        String y; 
         try {
                dbControl.dbComd("SELECT * FROM STUDENT WHERE CARD_ID ='"+UID+"'");
                if(dbControl.rs.next()){
                    y = dbControl.rs.getString("first_name")+" "+dbControl.rs.getString("last_name");
                }
            }catch (SQLException ex) {
                Logger.getLogger(ams_main.class.getName()).log(Level.SEVERE, null, ex);
            }finally{ dbControl.doClose();}
        System.out.println(Presence.UIStudentName);
        y = Presence.UIStudentName;
        xx = y;
        return CardConnection.cardUID();
        //return y;
    }
    
    /** This is mostly used in the UI of the application. It'll be 
     * mostly used to print out the name of the student on the 
     * front page of the application and their ID. 
     * 
     * @param UID Card UID
     * @return A string of words that relate to the student's ID card's being scanned
     */
    public static String getCurrentSwipe(String UID){
         //String x = "SELECT * FROM Student WHERE student.card_id = '"+UID+"'";
        /*int id = 0; String fName = "", lName = "", contact_phone = ""; Date dob;
        //x += cardid;
        try{
            //String SQL = "SELECT student.id, student.first_name, student.last_name,  FROM STUDENT WHERE student.card_id = cardid";
            dbControl.dbComd(x);
            while (dbControl.rs.next()){
              System.out.println("getCurrentSwipe Reached");
                id = dbControl.rs.getInt("STUDENT_ID");
                fName = dbControl.rs.getString("first_name");
                lName = dbControl.rs.getString("last_name");
                dob = dbControl.rs.getDate("birth_date");
                contact_phone = dbControl.rs.getString("contact_mobile");
                

            }
        }catch (SQLException error){
            System.out.println("Oopsies, you gone get goofed, here's the error message: ");
            System.out.println(error.getMessage());
        }finally{
            dbControl.doClose();
        }
        if (id == 0) 
            return "";
        else 
            return Integer.toString(id) + " - " + fName + " "+lName + " ";
        */
    
    int counter = 2*10;
    counter--;
    int id = 0; String fName = "", lName = "", contact_phone = ""; Date dob;
    
    String x = null, y = null;
        boolean placeholder = false;
        try{
            dbControl.dbComd("SELECT * FROM STUDENT WHERE CARD_ID = '"+UID+"'");
            if(dbControl.rs.next()){
                x = dbControl.rs.getString("STUDENT_ID") + " - " + dbControl.rs.getString("FIRST_NAME")+" "+dbControl.rs.getString("LAST_NAME");
                
               // System.out.println(db.rs.getRow());
                //System.out.println(x);
                dbControl.doClose();
            } else dbControl.doClose();
            dbControl.dbComd("SELECT * FROM INSTRUCTOR WHERE CARD_ID = '"+UID+"'");
            if(dbControl.rs.next()){
                y = dbControl.rs.getString("EMPLOYEE_ID") + " - " + dbControl.rs.getString("TITLE")+ " " +dbControl.rs.getString("FIRST_NAME")+" "+dbControl.rs.getString("LAST_NAME");
                //System.out.println(db.rs.getRow());
                //System.out.println(y);
                dbControl.doClose();
            } else dbControl.doClose();
        } catch (SQLException ex) {
            Logger.getLogger(dbLookUp.class.getName()).log(Level.SEVERE, null, ex);
        }finally{dbControl.doClose();}
        if (counter == 0){
            x = null;
            y = null;
        }
        if(x != null){
            //System.out.println("A student");
            return x;
        }else if(y != null){
            //System.out.println("An instructor");
            System.out.println(counter + " Counter");
            return y;
        }else {
            //System.out.println("Not in the database");
            return "";
        }
    
    
    
    
    
    }
    }

