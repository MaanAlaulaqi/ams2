/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/** The attendance taking class. 
 *
 * @author Maan Alaulaqi (201610814@aau.ac.ae)
 */
public class Presence {
    private static dbControl db = new dbControl();
    private static dbControl dbUpdate = new dbControl();
    private static dbLookUp LookUp;
    private static ClassThread classC;
    static boolean instructorIN = false;
    
    
    
    
    public Presence(){}
    /**
     * The timeCheck() method is meant to be an inclusive method that can be used
     * anyhwhere in the application to check whether the current time has any 
     * ongoing classes. 
     * 
     * @return Time Check Positive or Negative
     */
    public static boolean timeCheck(){
        String start="", end="";
        int startX = 0, endX = 0, currentTime,classNum; 
        //Time x;
        classNum = classC.classCheck();
        //System.out.println(classNum);
        db.dbComd("select active_classes.id, name, start_time, end_time, firstt_class, second_class from active_classes join class on active_classes.CLASS_ID = class.id join class_schedule on active_classes.class_schedule_id = class_schedule.id where active_classes.ID = " +classNum);
        if (classNum == 0){
            System.out.println("There are no classes at this time to register your attendance to.");
            return false;
        }else{
            try {
                if(db.rs.next()){
                    start = db.rs.getString("START_TIME");
                    startX = toMins(start);
                    end = db.rs.getString("END_TIME");
                    endX = toMins(end);
                }//else db.doClose();
            } catch (SQLException ex) {
                Logger.getLogger(Presence.class.getName()).log(Level.SEVERE, null, ex);
            }finally {db.doClose();}
            //The following 3 lines is to obtain the current time in HH:MM format to use in "tiMins" method.
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");  
            LocalDateTime now = LocalDateTime.now();
            currentTime = toMins(dtf.format(now));
            //currentTime = currentTime - 500; //Testing methods that rely on this variable. 
            System.out.println(currentTime + " Altered currenttime " +startX);
            if(currentTime >= startX && currentTime <= endX){
                System.out.println("Time Check positive");
                return true;
            }else {
                System.out.println("Time Check negative");
                    return false;
                //TO-DO Present profile
            }
        }
    }
    /**
     * There are times when the instructor is late or not present during a lecture. 
     * This method is geared towards activating the the AMS 15 minutes into 
     * class automatically so students who've attended may mark their presence
     * in the system.
     * 
     */
    public static void FifteenMinActivate(){
        String start, end;
        int startX = 0, endX = 0, currentTime; 
        Time x;
        db.dbComd("SELECT * FROM CLASS_SCHEDULE");
        try {
            if(db.rs.next()){
                start = db.rs.getString("START_TIME");
                startX = toMins(start);
                end = db.rs.getString("END_TIME");
                endX = toMins(end);
            }else db.doClose();
        } catch (SQLException ex) {
            Logger.getLogger(Presence.class.getName()).log(Level.SEVERE, null, ex);
        }finally {db.doClose();}
        //The following 3 lines is to obtain the current time in HH:MM format to use in "tiMins" method.
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");  
        LocalDateTime now = LocalDateTime.now();  
        System.out.println(dtf.format(now));
        currentTime = toMins(dtf.format(now));
        if(currentTime >= (startX+15) && currentTime <= endX){
            //TO-DO Activate for students 100% regardless of whether professor is there
            //15*(60*2) refers to 15 minutes, it's written this way because the counter variable
            //in CardConnection decrements every half a second, and we want 15 minutes
            CardConnection.counter = +(15*(60*2));
        }else{
            //TO-DO Present profile
        }
    }
    /**
     * This method is meant for the timeCheck method. It returns the minute format for DD:HH:MM
     * 
     * @param s This is a method used to turn HH:MM:SS format into one int number in MINUTES only. It helps with time comparisons. 
     * @return HoursInMins 
     */
    public static int toMins(String s) {
    String[] hourMin = s.split(":");
    int hour = Integer.parseInt(hourMin[0]);
    int mins = Integer.parseInt(hourMin[1]);
    int hoursInMins = hour * 60;
    return hoursInMins + mins;
}
    public static boolean readStudentUID = false;
    public static String UIStudentName;
    public static void MarkPresent(String UID){
        //TO-DO Main Attendance taking method
        int class_id = ClassThread.classCheck();
        if(timeCheck()){
            System.out.print(" MarkPresent() reached successfully. ");
            try {
            dbUpdate.DO_THE_THING = dbUpdate.doConnect().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            dbUpdate.DO_THE_THING.executeUpdate("update attend \n" +
                    "    set present = true \n" +
                    "    where student_id = (select attend.student_id from attend, class, student, active_classes\n" +
                    "    where student.ID = attend.STUDENT_ID\n" +
                    "    and active_classes.CLASS_ID = class.id\n" +
                    "    and CLASS.ID = ATTEND.CLASS_ID\n" +
                    "    and class.id = active_classes.class_id\n" +
                    "    and student.CARD_ID = '"+UID+"'\n" +
                    "    AND ACTIVE_CLASSES.ID = "+class_id+")");
            } catch (SQLException ex) {
                Logger.getLogger(Presence.class.getName()).log(Level.SEVERE, null, ex);
            }finally {dbUpdate.doClose();}

        
        
    }
    }
    //This will most likely be a button, so we want to send in as much info as 
    //we can, without any work from the instructor.
    //We can send in a boolean, or int, to signify UP/DOWN in terms of presence
    /**
     * The following three methods are geared towards adjusting a student's presence
     * in a certain class. 
     * @param UID Card UID
     * @param req  req
     */
    public static void AdjustPresence(String UID, boolean req){ 
        //TO-DO Method for moments where instructors needs to adjust a presence
        //Utilize the UpPresence and DownPresence for this
       if(req == true) UpPresence(UID);
       else DownPresence(UID);
    /**
    * In case the instructor needs to up someone's presence count manually for a class
    * This can only be used effectively within the time range of a particular class session 
    * @param UID 
    */
    }
    public static void UpPresence(String UID){
        //TO-DO Present +1 
        int class_id = ClassThread.classCheck();
        if(timeCheck()){
            System.out.print(" MarkPresent() reached successfully. ");
            try {
            dbUpdate.DO_THE_THING = dbUpdate.doConnect().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            dbUpdate.DO_THE_THING.executeUpdate("update attend \n" +
                    "    set present = true \n" +
                    "    where student_id = (select attend.student_id from attend, class, student, active_classes\n" +
                    "    where student.ID = attend.STUDENT_ID\n" +
                    "    and active_classes.CLASS_ID = class.id\n" +
                    "    and CLASS.ID = ATTEND.CLASS_ID\n" +
                    "    and class.id = active_classes.class_id\n" +
                    "    and student.CARD_ID = '"+UID+"'\n" +
                    "    AND ACTIVE_CLASSES.ID = "+class_id+")");
            } catch (SQLException ex) {
                Logger.getLogger(Presence.class.getName()).log(Level.SEVERE, null, ex);
            }finally {dbUpdate.doClose();}
            
        }
    }
    /**
     * In case the instructor needs to lower someone's presence count for a class
     * This can only be used effectively within the time range of a particular class session 
     * @param UID 
     */
    public static void DownPresence(String UID){
        //TO-DO Present -1
        int class_id = ClassThread.classCheck();
        if(timeCheck()){
            System.out.print(" MarkPresent() reached successfully. ");
            try {
            dbUpdate.DO_THE_THING = dbUpdate.doConnect().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            dbUpdate.DO_THE_THING.executeUpdate("update attend \n" +
                    "    set present = false \n" +
                    "    where student_id = (select attend.student_id from attend, class, student, active_classes\n" +
                    "    where student.ID = attend.STUDENT_ID\n" +
                    "    and active_classes.CLASS_ID = class.id\n" +
                    "    and CLASS.ID = ATTEND.CLASS_ID\n" +
                    "    and class.id = active_classes.class_id\n" +
                    "    and student.CARD_ID = '"+UID+"'\n" +
                    "    AND ACTIVE_CLASSES.ID = "+class_id+")");
            } catch (SQLException ex) {
                Logger.getLogger(Presence.class.getName()).log(Level.SEVERE, null, ex);
            }finally {dbUpdate.doClose();}
            
        }
    }
    
    /**
     * InstructorPresence will have multiple functions: 
     *      • It'll mark their presence for that class
     *      • If this method is not met, the 15 minute activation of the AMS will automatically commence
     * 
     * @param UID 
     */
    //static boolean screwmeover = timeCheck(); 
    public static void InstructorPresence(String UID){
        
        int class_id = ClassThread.classCheck();
        if(timeCheck()){
            System.out.print(" MarkPresent() reached successfully. ");
            try {
            dbUpdate.DO_THE_THING = dbUpdate.doConnect().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            dbUpdate.DO_THE_THING.executeUpdate("update instructor_attend\n" +
                    "        set present = true \n" +
                    "        where instructor_id = (select instructor_id from instructor_attend \n" +
                    "        join instructor on instructor_attend.instructor_id = instructor.ID\n" +
                    "        join active_classes on instructor_attend.CLASS_ID = active_classes.CLASS_ID\n" +
                    "        join class on instructor_attend.CLASS_ID = class.id\n" +
                    "        where instructor.CARD_ID = '"+UID+"'\n" +
                    "        and active_classes.class_id =" +class_id+")\n" +
                    "        and class_id = (select instructor_attend.class_id from instructor_attend \n" +
                    "        join instructor on instructor_attend.instructor_id = instructor.ID\n" +
                    "        join active_classes on instructor_attend.CLASS_ID = active_classes.CLASS_ID\n" +
                    "        join class on instructor_attend.CLASS_ID = class.id\n" +
                    "        where instructor.CARD_ID = '"+UID+"'\n" +
                    "        and active_classes.class_id = "+class_id+" )");
            instructorIN = true;
            } catch (SQLException ex) {
                Logger.getLogger(Presence.class.getName()).log(Level.SEVERE, null, ex);
            }finally {dbUpdate.doClose();}
            
        } else instructorIN = false;
        
    }
    
    public static void getPresncePercentage(String UID){
        //TO-DO Return percentage of absence of student
        //Maybe also instructor since we have their presence kaman
    }
    
}
