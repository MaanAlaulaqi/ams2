/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams;

import static ams.Presence.toMins; //easier than setting up a different variable for every time I use this..
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *The idea here is to try and have a constant java thread that's consistently checking 
 * the time to see verify which class is currently ongoing. 
 * The main use I have for this is for the Presence class, the timeCheck() method
 * 
 * @author Maan Alaulaqi (201610814@aau.ac.ae)
 */
public class ClassThread {
    private static int[][] class_table;
    
    public ClassThread(){}
    
    /**
     * Creates an array of the active classes of a current semester every time
     * it's called. 
     * @return a 2D array, index [x][0] is for the class_id, [x][1] is for the 
     * time of that class_start in minutes.
     */
    public static int[][] ActiveClassesList(){
        int arraySize = 0;
        
        System.out.println("ActiveClassesList accessed)");
        //I'm keeping the next few lines of code for later reference 
        //in case I need to reference a way to access dates and stuff
//        String DayWeek;
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");  
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter dtfWEEK = DateTimeFormatter.ofPattern("EEEE");  
//        LocalDateTime DayOfWeek = LocalDateTime.now();
//        DayWeek = dtfWEEK.format(DayOfWeek);
        //int currentTime = toMins(dtf.format(now));
        dbControl.dbComd("select count(*) from active_classes");
        try {
            if(dbControl.rs.next()) arraySize = dbControl.rs.getInt(1)+2;
            class_table = new int[arraySize][2];
            dbControl.doClose();
            dbControl.dbComd("select active_classes.id,start_time from active_classes join class on active_classes.CLASS_ID = class.id join class_schedule on active_classes.class_schedule_id = class_schedule.id order by active_classes.id");
            while (dbControl.rs.next() && arraySize >= 0) {
                class_table[dbControl.rs.getInt("id")][0] = dbControl.rs.getInt("id");
                class_table[dbControl.rs.getInt("id")][1] = Presence.toMins(dbControl.rs.getString("start_time"));
                arraySize--;
            }class_table[class_table.length-1][1] = class_table[class_table.length-2][1] + 90;
        } catch (SQLException ex) {
            Logger.getLogger(ClassThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {dbControl.doClose();}
        return class_table;
    }
    
    /**
     * This method is to check which "time slot" of the day as class is ongoing.
     * The classCheck() method is written with knowledge of the tables in the 
     * database. 
     * @return The number returned will be used in SQL queries where we need to obtain the class list of active classes
     */
     public static int classCheck(){
        int class_id = 0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");  
        LocalDateTime now = LocalDateTime.now();
        int currentTime = toMins(dtf.format(now));
        for(int i = 0; i < class_table.length;i++){
            if (currentTime > class_table[0][1] && currentTime <class_table[class_table.length-1][1])
                class_id = 0;
            else if (currentTime >= class_table[i][1] && currentTime >= class_table[i][1]){
                class_id = class_table[i][0];
            }
        }
        return class_id;
        //return 2;
     }
    
    
}
