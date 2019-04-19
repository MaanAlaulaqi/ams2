package ams;


import ams.UserInterface;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *This class is meant to change the UI of the "ACTIVE/NOT ACTIVE phase of the 
 * AMS. I made it to avoid having to deal with static/non-static arguments that 
 * tended to come up during the development phase of this project. 
 * @author Maan Alaulaqi (201610814@aau.ac.ae)
 */
public class updateChecker {
    public static void updateActiveCheck(boolean success) {
        /*
        System.out.println(ACTIVE_ICON+" SYSTEM");
        ImageIcon x,y;
        x = new ImageIcon(UserInterface.class.getResource("/images/Start_ACTIVE.png"));
        System.out.println(x + "updateChecker.updateActiveCheck reached");
        y = new ImageIcon(UserInterface.class.getResource("/images/Start_NOTACTIVE.png"));*/
        
        if(success) {
            
            
            /*System.out.println("YES" + x);
            ACTIVE_ICON.setIcon(ICON_ACTIVE);// = new ImageIcon("/images/Start_ACTIVE.png"););*/
            UserInterface.ACTIVE_ICON.setVisible(true);
            UserInterface.INACTIVE_ICON.setVisible(false);
        }
        else if(!success) {
            /*
            System.out.println("NO" + x);
            ACTIVE_ICON.setIcon(y);*/
            UserInterface.ACTIVE_ICON.setVisible(false);
            UserInterface.INACTIVE_ICON.setVisible(true);
            
        }
    }
}
