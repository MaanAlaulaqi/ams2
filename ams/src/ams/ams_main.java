/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Maan Alaulaqi (201610814@aau.ac.ae)
 */
public class ams_main {
    public static void main(String[] args) throws IOException {
//        System.out.println("Hello world!"); //More stuff to be added lol
//       //try{
//            student n = new student(); //This is temporary, just testing
//            n.print();
//            System.out.println("LOOK AT ME OMG THIS LINE WAS REACHED YIS");
//            n.cardIDLookUp("2989cacc"); //Returns Maan Alaulaqi'a System print
//        //}catch (NullPointerException d){System.out.println(d); System.out.println(d.getMessage());}
//        System.out.println("WOOF"); 

        //On-Screen keyboard. This will be needed when in UI
        //Just saving it here in case I lose the syntax later
        //Runtime.getRuntime().exec("cmd /c osk");
        CardConnection c1 = new CardConnection();
        c1.enablePlugnPlay();
        c1.initateTerminal();
        c1.disableSound(true); //Silent for now while I work, else it'd beep a lot
        new Thread(c1).start(); //This is to keep the reader running 
        String p = c1.cardUID();
        
        //n.cardIDLookUp(p);
       
//       dbControl x = new dbControl();
//       x.doClose();

       //BEGIN TESTING ARENA
       //System.out.println(1);
       Presence oo = new Presence();
       System.out.println(2);
       //oo.timeCheck();
       oo.MarkPresent("2989cacc");
       String DayWeek;
       DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");  
       LocalDateTime now = LocalDateTime.now();
       System.out.println(dtf.format(now));
       DateTimeFormatter dtfWEEK = DateTimeFormatter.ofPattern("EEEE");  
       LocalDateTime DayOfWeek = LocalDateTime.now();
       //DayWeek = DayOfWeek.toString();
       DayWeek = dtf.format(DayOfWeek);
       System.out.println(DayWeek + " " +Presence.toMins(dtf.format(now)));
       System.out.println(3);
       //END TESTING ARENA
       //System.out.println("ID CHECK HERE!!!!");
       //x.getID("4cf8a49");
       
      /* x.dbComdUpdate("SELECT * FROM CLASS_SCHEDULE FETCH FIRST 100 ROWS ONLY");
   
        try {
            //x.dbComdUpdate("SELECT * FROM TEST WHERE ID = 3");
            if(x.rs.next()){
                
               // int y = x.rs.getInt("ID");
                x.rs.absolute(2);
                System.out.println(x.rs.getString("START_TIME")+" " + x.rs.getString("END_TIME"));
                Presence oo = new Presence();
                oo.timeCheck();
                /*
                System.out.println(oo.toMins(x.rs.getString("START_TIME")));
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:MM");  
                LocalDateTime now = LocalDateTime.now();  
                System.out.println(dtf.format(now));
                System.out.println(oo.toMins(dtf.format(now)));
                //Go to row (starts at 1)
                int k; 
                String kk = (x.rs.getInt("PRESENT") +"");
                System.out.println(kk);
                
                //Column name in, k is the current value, k++ is to increment it (for attendace)
                k = Integer.parseInt(x.rs.getInt("PRESENT") +"");
                k++;
                //We turn it into a string to pass it as part of the sql comd
                String w = (""+k);
                System.out.println(k+ " " +w); //Just a testing line (k and w needed to be equal and incrementing)
                x.rs.updateString("present",w);
                x.rs.updateRow();
            }else x.doClose();
        } catch (SQLException ex) {
            Logger.getLogger(ams_main.class.getName()).log(Level.SEVERE, null, ex);
        }finally{ x.doClose();}
        
        
        try {
            c1.pause();
            c1.resume();
        } catch (InterruptedException ex) {
            Logger.getLogger(ams_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        
//       dbLookUp h = new dbLookUp();
//       System.out.println("UIDCHECK");
//       System.out.println(h.uidCheck("201110057"));
//       System.out.println("LOLOLL");
//       System.out.println(h.uidCheck("2989cacc"));
//       System.out.println(h.uidCheck("2989cac"));
       
       
       
    }
    
}
