/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams;

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
        dbC.dbComd("select  NAME, START_TIME, END_TIME FROM CLASS_SCHEDULE JOIN CLASS ON CLASS_SCHEDULE.ID = " +classID);
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
}
