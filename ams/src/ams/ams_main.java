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
import java.util.Arrays;
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
        //Class list/array init
        
        
//        for(int i = 0; i < ClassThread.class_table.length; i++){
//            for(int j = 0; j < ClassThread.class_table[i].length; j++){
//                System.out.print(ClassThread.class_table[i][j] + " ");
//            }
//        }
        
        
        CardConnection c1 = new CardConnection();
        ClassThread.ActiveClassesList();
        System.out.println(Arrays.deepToString(ClassThread.class_table));
        System.out.println("Class table array accessed \n");
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
       
       
       
    }
    
}
