/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams;

import java.io.IOException;
import java.util.Arrays;


/**The main class that runs the application
 *
 * @author Maan Alaulaqi (201610814@aau.ac.ae)
 */
public class ams_main {
    public static void main(String[] args) throws IOException {
        
        CardConnection c1 = new CardConnection();
        ClassThread.ActiveClassesList();
        System.out.println(Arrays.deepToString(ClassThread.class_table));
        System.out.println("Class table array accessed \n");
        c1.enablePlugnPlay();
        c1.initateTerminal();
        c1.disableSound(true); //Silent for now while I work, else it'd beep a lot
        new Thread(c1).start(); //This is to keep the reader running 

       
    }
    
}
