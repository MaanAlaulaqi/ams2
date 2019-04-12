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
public class amsActivate {
    private static boolean status = false; 
    
    
    public static boolean activeCheck(){
        
        return status;
    }
    /**
     * The idea here is the instructor will be presented with a choice of whether to activate
     * or not. If not, it should call another method to present the instructor with options.
     * @param x
     * @return 
     */
    public static boolean ActivateOrNah(boolean x, String UID){
        System.out.println("ActivateOrNah reached: " + UID);
        if (x = true) {
            //activateAms(x, UID);
            CardConnection.counter = 2*10;
            return x;
        }
        else {
            //TO-DO Method to present options
            return x;
        }
    }
    /**
     * A straight to it activate or deactivate method of the AMS.
     * @param x
     * @return true will turn it on 
     * @return false will turn it off
     */
    public static boolean activateAms(boolean x, String UID){
        System.out.println("activateAms reached: " + UID);
        status = x;
        if(CardConnection.counter > 0){    
            if(!x){
                /**
                 * Ok what we need to do is allow students to walk up to this 
                 * and scan their cards and be marked present, ya?
                 * So let's assume. 
                 * Swipe > getUID > pass UID to markPresnce > End of transaction?
                 * That's basically it tbh.. There's nothing more to it..
                 */
                Presence.MarkPresent(UID);
                // WE NEED AN ACTIVATION TIMER THIS WON'T WORK
                //Because with every loop of the thread, this is reset, which makes the whole thing
                //useless. We need to keep this active for 15 minutes at least.
                //Maybe we can use a counter? The thread loops every half a second, therfore:
                //counter = 2*60*15
                // and every loop will be -> counter--
                //And once counter reaches zero, the presence deactivates. idk
                return status;
            }else {
                //TO-DO Deactivate AMS
                return status;
            }
        }return status;
    }
    public void activationTimerRunOut(){
        //TO-DO Add timer that calls activateAms(false) once timer pass
    }
    
    
}
