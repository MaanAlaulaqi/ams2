/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams;

import java.io.File;
import java.io.FileWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Maan Alaulaqi (201610814@aau.ac.ae)
 */
public class FileSaver {
    
    public FileSaver(){}
    
    public static void saveMap() {
		String sb = "TEST CONTENT";
		JFileChooser chooser = new JFileChooser();
		chooser.setSelectedFile(new File("Lololol.txt"));
		chooser.setCurrentDirectory(new File("/home/me/Dcouments"));
		File file = new File (chooser.getSelectedFile().toString() + ".txt");
		boolean s = file.exists();
		int retrival = chooser.showSaveDialog(null);
		if (retrival == JFileChooser.APPROVE_OPTION) {
			try {
				FileWriter fw = new FileWriter(chooser.getSelectedFile()+".txt");
				 if (s) {
					int response = JOptionPane.showConfirmDialog(null, //
							"Do you want to replace the existing file?", //
							"Confirm", JOptionPane.YES_NO_OPTION, //
							JOptionPane.QUESTION_MESSAGE);
					if (response != JOptionPane.YES_OPTION) {
						return;
					} 
				} 
				fw.write(sb.toString());
				fw.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
    
}
