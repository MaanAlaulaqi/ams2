/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams;

/**
 * This is the class that will handle reading cards a USB NFC Reader(ACR122U).
 * Its purpose is to obtain the UID of the cards it connects with.
 * There are methods with boolean returns, they were mostly used 
 * for testing purposes.
 *
 * @author Maan Alaulaqi (201610814@aau.ac.ae)
 */



import java.awt.HeadlessException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.smartcardio.TerminalFactory;
 /**
  * implements Runnable in order to allow Threads to be used 
  * Based on this tutorial ( https://www.callicoder.com/java-multithreading-thread-and-runnable-tutorial/ )
  */
 public class CardConnection implements Runnable{
    private CardTerminal terminal = null;
    private boolean running = true;
    private boolean sendConfirmation = false;
    // Windows escape command
    private static final int ESCAPE_COMMAND = 0x003136b0;
    // API-ACR122U-2.02.pdf Page 11 - 4.1 - Get data (Example 1).
    //(A copy of this should be included in the project)
    private final CommandAPDU GET_UID_COMMAND_APDU = new CommandAPDU(0xFF, 0xCA, 0x00, 0x00, 0x04);
    public static String cardUID = ""; //Used to test passing the UID through classes to interact with db.
    public static int counter = 0;
	public CardConnection(){
		
	}
    
    /**
     * Enables the USB NFC reader to be removed and then reinserted without errors.
     * Based entirely on: http://stackoverflow.com/a/16987873
     * @return boolean of whether it's a success or not.
     */
    public boolean enablePlugnPlay() {
        try {
            Class pcscterminal = Class.forName("sun.security.smartcardio.PCSCTerminals");
            Field contextId = pcscterminal.getDeclaredField("contextId");
            contextId.setAccessible(true);

            if (contextId.getLong(pcscterminal) != 0L) {
                Class<?> pcsc = Class.forName("sun.security.smartcardio.PCSC");

                Method SCardEstablishContext = pcsc.getDeclaredMethod(
                        "SCardEstablishContext", new Class[]{Integer.TYPE});
                SCardEstablishContext.setAccessible(true);

                Field SCARD_SCOPE_USER =
                        pcsc.getDeclaredField("SCARD_SCOPE_USER");
                SCARD_SCOPE_USER.setAccessible(true);

                long newId = ((Long) SCardEstablishContext.invoke(pcsc, new Object[]
                {SCARD_SCOPE_USER.getInt(pcsc)}));
                contextId.setLong(pcscterminal, newId);
            }
            return true;
        } catch (ClassNotFoundException | NoSuchFieldException | SecurityException | 
                IllegalArgumentException | IllegalAccessException | NoSuchMethodException | 
                InvocationTargetException ex) {
            return false;
        }
    }
    /**
     * Initiates the card terminal connection, i.e. connects to the USB NFC Reader.
     * @return boolean of whether it's a success or not. 
     */
    public boolean initateTerminal() {
        try {
            TerminalFactory terminalFactory = TerminalFactory.getDefault();
            List<CardTerminal> cardTerminals = terminalFactory.terminals().list();
            try{
                terminal = cardTerminals.get(0); 
                System.out.println(terminal);
                return true;
            }
            catch(Exception ex){
                System.out.println(ex);
                return false;
            }
        } catch (Exception ex) { //
            System.out.println(ex);
            return false;
        }
    }
    /**
     * This method disables the buzzer sound of the USB NCF Reader based on a boolean
     * A neat option I found while researching the card reader ( API-ACR122U-2.02.pdf Page 28 - 6.7- Set Buzzer output)
     * Some issues with this method is that I couldn't figure out how to enable it to work with the
     * method enablePlugnPlay() as it wasn't a priority. 
     * @param set boolean on whether it's set as disabled or not
     */
    public void disableSound(boolean set) { 
        if(set) {
            if(terminal!=null){
                try {
                    Card card = terminal.connect("DIRECT"); 
                    // 
                    byte[] command = { (byte) 0xFF, (byte) 0x00, (byte) 0x52, (byte) 0x00, (byte) 0x00 }; 
                    card.transmitControlCommand(ESCAPE_COMMAND, command);
                    card.disconnect(true);
                    System.out.println("Beeper silenced!");
                } catch (Exception ex) {
                    System.out.println("Failed to silence beeper!");
                    ex.printStackTrace();
                }
            }
        }
        else if(!set) {
            if(terminal!=null){
                try {
                    Card card = terminal.connect("DIRECT");
                    byte[] command = { (byte) 0xFF, (byte) 0x00, (byte) 0x52, (byte) 0xFF, (byte) 0x00 }; 
                    card.transmitControlCommand(ESCAPE_COMMAND, command);
                    card.disconnect(true);
                    System.out.println("Beeper silenced!");
                } catch (Exception ex) {
                    System.out.println("Failed to silence beeper!");
                    ex.printStackTrace();
                }
            }
        }
    }
    /**
     * This method is the main loop for the CardConnection, 
     * it'll continously check for new cards.
     * This will be used as something like:
     * {@code text}: new Thread(CardConnection).start();
     */
    public void run() {
        Boolean emp_stud_check = false;
        Card card;
        CardChannel channel;
        ResponseAPDU answer;
        byte[] uid;
        // Main-loop to keep the thread running.
        while(true){
            //Start the UI stuff
            java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserInterface().setVisible(true);
            }
        });
            // Checks if the CardConnection has been paused, if so it'll sleep and then check again.
            
                while(!running) //<editor-fold defaultstate="collapsed" desc="running = false">
                {

                    try {
                        Thread.sleep(500);
                        if (counter > 0) counter--;
                        else {
                            counter = 0;
                            if(Presence.instructorIN){}
                            else {
                                Presence.FifteenMinActivate();
                                //System.out.print (" else counter = 0 reached ");
                            }

                        }
                        //System.out.println();
                        //Uncomment the next line to see a count down of the timer
                        //And the 2nd System print in the 2nd while loop
                        //System.out.print(counter + " ");
                    } catch (InterruptedException ex) {
                    }
                }
                //</editor-fold>

                while(running)//<editor-fold defaultstate="collapsed" desc="running = true">
                {
                    
                try{
                    //System.out.println();
                    //See "while(!running) comments 
                    //This is the timer countdown System print
                    System.out.print (counter + " ");
                    if (counter > 0) counter--;
                    else  {
                        counter = 0;
                        if(Presence.instructorIN){}
                        else {
                            Presence.FifteenMinActivate();
                            //System.out.print (" else counter = 0 reached ");
                        }
                    }
                    //Checks if an instructor ever swiped. This also resets after the class ends
                    //if (ClassThread.class_table[ClassThread.classCheck()][1] )
                    System.out.print(Presence.instructorIN+" ");
                    if (Presence.screwmeover) Presence.screwmeover = false;
                    else Presence.screwmeover = true;
                    System.out.print(Presence.screwmeover +" ");
                    // Checks if a card is present, if not it'll sleep and then check again.
                    if(!terminal.isCardPresent()){
                        //Pause for half a second
                        try{
                        Thread.sleep(500);
                        ClassThread.classCheck();
                        //UI NON-ACTIVE 
                        if (counter <= 0)UserInterface.updateActiveCheck(false);
                        //Continuosly check class slot. 
                        //This part can be to display current class
                        }catch (NullPointerException e){}
                    }
                    else{
                        ClassThread.classCheck();
                        // Connects to the card
                        
                        card = terminal.connect("*");
                        // Opens a channel
                        channel = card.getBasicChannel();
                        // Transmits the get UID command
                        answer = channel.transmit(GET_UID_COMMAND_APDU);
                        // Takes the response and puts it in a byte array.
                        uid = answer.getData();
                              //These are all just tests I used to better understand the card reader
//                            System.out.println(uid);
//                            System.out.println("APDU command transmit "+answer.getNr());
//                            System.out.println("P1: "+GET_UID_COMMAND_APDU.getP1() );
//                            System.out.println("P2: "+GET_UID_COMMAND_APDU.getP2() );
//                            System.out.println("INS: "+GET_UID_COMMAND_APDU.getINS() );
//                            System.out.println();
//                            System.out.println(answer.toString());
//                            System.out.println(" ");
                        if(Integer.toHexString(answer.getSW()).equals("9000")) { //90 00 = operation success
                            String out = "";
                            // Gets the UID from the array. MSB at [3] and LSB at [0]
                            for(int i = 3; i > -1; i--) {
                                //The next line is based partially on https://stackoverflow.com/questions/11380062/what-does-value-0xff-do-in-java 
                                //tl;dr = the "& 0xFF" prevents the result being something like fffffff8, and keeps it at f8.
                                out += Integer.toHexString((int) uid[i] & 0xFF);
								System.out.print(" " + Integer.toHexString((int) uid[i] & 0xFF));
                            }System.out.println();
                            cardUID = out;
                            //InterfaceCmds.getCurrentUID(out);
                            UserInterface.updateStudent(out);
                            System.out.println(out);
                            student.cardIDLookUp(out);
                            // Checks if the UID is in our database
                            System.out.println("bdLookUp HERE:");
                            emp_stud_check = dbLookUp.uidCheck(out);
                            
                            //HEY POOPOO FACE LOOK HERE! Place activation method here
                            boolean imageIconBoolean;
                            if (emp_stud_check) {
                                Presence.instructorIN = true;
                                Presence.InstructorPresence(out);
                                UserInterface.viewStudentsButton.setVisible(true);
                                //UI ACTIVE CHECK (This should return true)
                                UserInterface.updateActiveCheck(amsActivate.ActivateOrNah(emp_stud_check, out));
                                //THIS IS FOR TESTING TEST YALLA
                                //updateActiveCheck(true);
                                //UserInterfaceActiveCheck.setIcon(ICON_ACTIVE);
                            } else{
                                UserInterface.viewStudentsButton.setVisible(false);
                            }
                            amsActivate.activateAms(emp_stud_check, out);
                                
                            
                            }
                            card.disconnect(true);
                            while(true) {
                                if(!terminal.isCardPresent()){
                                    break;
                                }
                                else{
                                    Thread.sleep(100);
                                }
                            }
                        }
                    }
                catch(CardException | InterruptedException | HeadlessException ex){
                }
            }
            //</editor-fold>
            
        }
    }
    /**
     * For instances where we call the card UID from another class
     * @return Returns the card's UID in String format.
     */
    public static String cardUID(){ 
        String x = cardUID;
        return x;
        }
    /**
     * Will pause the CardConnection. I don't think I ever needed to use it
     * But to have an unused method is better than having to make one later on I think.
     * @throws InterruptedException InterruptException
     */
    public void pause() throws InterruptedException
    {
        running = false;
    }
    /**
     * Will resume the CardConnection.
     * @throws InterruptedException InterruptedException 
     */
    public void resume() throws InterruptedException
    {
        running = true;
    }
    
}

