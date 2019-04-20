/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/** Something the professor requested that I'm dying over. 
 *
 * @author Maan Alaulaqi (201610814@aau.ac.ae)
 */
public class ClassManager extends javax.swing.JFrame {
    private static Vector<String> elements;// = new Vector<String>(); //Was playing with ArrayList and Vectors here. Read somewhere it made a difference.
    private static DefaultListModel dlm;
    private static String y=""; //whenever I need a random string from somewhere. 
    private static int incrementMe = 0; 
    private static int sizeMeUpbb = 0;
    /**
     * Creates new form ClassManager
     */
    public ClassManager() {
        initComponents();
    }
private String InstructorUID = UserInterface.UID;
    public static void classListFiller(){
//        Vector<String> elements = new Vector<String>();
    
    int x = 0;
        try {
//        dbControl.dbComd("SELECT CLASS.NAME FROM INSTRUCTOR INNER JOIN INSTRUCTOR_CLASS ON INSTRUCTOR.ID =  INSTRUCTOR_CLASS.INSTRUCTOR_ID INNER JOIN CLASS ON INSTRUCTOR_CLASS.CLASS_ID = CLASS.ID WHERE INSTRUCTOR.CARD_ID = '"+InstructorUID+"';");
        dbControl.dbComd("SELECT CLASS.NAME, INSTRUCTOR_CLASS.ID FROM INSTRUCTOR INNER JOIN INSTRUCTOR_CLASS ON INSTRUCTOR.ID =  INSTRUCTOR_CLASS.INSTRUCTOR_ID INNER JOIN CLASS ON INSTRUCTOR_CLASS.CLASS_ID = CLASS.ID WHERE INSTRUCTOR.CARD_ID = 'e0b48a'");
        System.out.println(dbControl.rs.getFetchSize() + " rs.getFetchSize()");
        while (dbControl.rs.next()) {            
                // or whatever is appropriate
                System.out.println(dbControl.rs.getString("NAME"));
                //System.out.println(sClassSelect.getSize() + " sClassSelect.getSize()");
                y = dbControl.rs.getString("NAME");
                
                
                
              //  elements.add(dbControl.rs.getString("NAME"));
                
                //dlm.addElement((dbControl.rs.getString("NAME")));
                //Needed to test if this worked. The list isn't updating so I need to test things. 
                System.out.println(y);
                x++;
                //System.out.println(elements.get((dbControl.rs.getInt(dbControl.rs.getRow()))-1));
                //sClassSelect.add(elements.get((dbControl.rs.getInt(dbControl.rs.getRow()))-1));
                
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClassManager.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                dbControl.doClose();
            }  
    //System.out.println (elements.size());
    //System.out.print(dlm.getSize() + "dlm size");
    }
    
    public static String[] classListFiller2(){
         
            //dbControl.dbComd("SELECT CLASS.NAME FROM INSTRUCTOR INNER JOIN INSTRUCTOR_CLASS ON INSTRUCTOR.ID =  INSTRUCTOR_CLASS.INSTRUCTOR_ID INNER JOIN CLASS ON INSTRUCTOR_CLASS.CLASS_ID = CLASS.ID WHERE INSTRUCTOR.CARD_ID = '"+InstructorUID+"';");
            dbControl.dbComd("SELECT COUNT (CLASS.NAME) from INSTRUCTOR INNER JOIN INSTRUCTOR_CLASS ON INSTRUCTOR.ID =  INSTRUCTOR_CLASS.INSTRUCTOR_ID INNER JOIN CLASS ON INSTRUCTOR_CLASS.CLASS_ID = CLASS.ID WHERE INSTRUCTOR.CARD_ID = 'e0b48a'");
            try {
                int xx = dbControl.rs.getInt("1");
                System.out.println(xx + " xx (size lol)");

            } catch (SQLException ex) {
                Logger.getLogger(ClassManager.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                dbControl.doClose();
            }
            String[] string = new String[3];
            try {
                dbControl.dbComd("SELECT CLASS.NAME, INSTRUCTOR_CLASS.ID FROM INSTRUCTOR INNER JOIN INSTRUCTOR_CLASS ON INSTRUCTOR.ID =  INSTRUCTOR_CLASS.INSTRUCTOR_ID INNER JOIN CLASS ON INSTRUCTOR_CLASS.CLASS_ID = CLASS.ID WHERE INSTRUCTOR.CARD_ID = 'e0b48a'");
                System.out.println(dbControl.rs.getFetchSize() + " rs.getFetchSize()");
                while (dbControl.rs.next()) {
                    System.out.println(dbControl.rs.getString("NAME"));
                    y = dbControl.rs.getString("NAME");
                    stringy[incrementMe] = y;
                    System.out.println(y);
                    System.out.println(string[x] + " String[x]");
                    x++;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClassManager.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                dbControl.doClose();
            }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        amsPUEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("amsPU").createEntityManager();
        student_1Query = java.beans.Beans.isDesignTime() ? null : amsPUEntityManager.createQuery("SELECT s FROM Student_1 s");
        student_1List = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : student_1Query.getResultList();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        studentListLEFT = new javax.swing.JInternalFrame();
        sClassSelectButton = new java.awt.Label();
        sTimeSelectBUTTON = new java.awt.Label();
        sDisplayListBUTTON = new java.awt.Button();
        sExportListBUTTON = new java.awt.Button();
        sTimeSelect = new javax.swing.JScrollPane();
        sTimeSelect1 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        sClassSelect = new javax.swing.JList<>();
        studentListRIGHT = new javax.swing.JScrollPane();
        studentLIST = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(798, 514));
        setMinimumSize(new java.awt.Dimension(798, 514));
        setPreferredSize(new java.awt.Dimension(798, 514));

        studentListLEFT.setVisible(true);

        sClassSelectButton.setText("Select class");

        sTimeSelectBUTTON.setText("Select time");

        sDisplayListBUTTON.setLabel("Display student list");
        sDisplayListBUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sDisplayListBUTTONActionPerformed(evt);
            }
        });

        sExportListBUTTON.setLabel("Export student list");
        sExportListBUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sExportListBUTTONActionPerformed(evt);
            }
        });

        sTimeSelect1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        sTimeSelect.setViewportView(sTimeSelect1);

        sClassSelect.setModel(new javax.swing.AbstractListModel<String>() {

            int x = 0; int xx = 0;
            /*
            //dbControl.dbComd("SELECT CLASS.NAME FROM INSTRUCTOR INNER JOIN INSTRUCTOR_CLASS ON INSTRUCTOR.ID =  INSTRUCTOR_CLASS.INSTRUCTOR_ID INNER JOIN CLASS ON INSTRUCTOR_CLASS.CLASS_ID = CLASS.ID WHERE INSTRUCTOR.CARD_ID = '"+InstructorUID+"';");
            dbControl.dbComd("SELECT COUNT (CLASS.NAME) from INSTRUCTOR INNER JOIN INSTRUCTOR_CLASS ON INSTRUCTOR.ID =  INSTRUCTOR_CLASS.INSTRUCTOR_ID INNER JOIN CLASS ON INSTRUCTOR_CLASS.CLASS_ID = CLASS.ID WHERE INSTRUCTOR.CARD_ID = 'e0b48a'");
            try {
                int xx = dbControl.rs.getInt("1");
                System.out.println(xx + " xx (size lol)");

            } catch (SQLException ex) {
                Logger.getLogger(ClassManager.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                dbControl.doClose();
            }*/
            String[] strings = new String[3];
            try {
                dbControl.dbComd("SELECT CLASS.NAME, INSTRUCTOR_CLASS.ID FROM INSTRUCTOR INNER JOIN INSTRUCTOR_CLASS ON INSTRUCTOR.ID =  INSTRUCTOR_CLASS.INSTRUCTOR_ID INNER JOIN CLASS ON INSTRUCTOR_CLASS.CLASS_ID = CLASS.ID WHERE INSTRUCTOR.CARD_ID = 'e0b48a'");
                System.out.println(dbControl.rs.getFetchSize() + " rs.getFetchSize()");
                while (dbControl.rs.next()) {
                    System.out.println(dbControl.rs.getString("NAME"));
                    y = dbControl.rs.getString("NAME");
                    string[x] = y;
                    System.out.println(y);
                    System.out.println(string[x] + " String[x]");
                    x++;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClassManager.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                dbControl.doClose();
            }

            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(sClassSelect);

        javax.swing.GroupLayout studentListLEFTLayout = new javax.swing.GroupLayout(studentListLEFT.getContentPane());
        studentListLEFT.getContentPane().setLayout(studentListLEFTLayout);
        studentListLEFTLayout.setHorizontalGroup(
            studentListLEFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentListLEFTLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(studentListLEFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(studentListLEFTLayout.createSequentialGroup()
                        .addGroup(studentListLEFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sClassSelectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sTimeSelectBUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(studentListLEFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                            .addComponent(sTimeSelect))
                        .addGap(34, 34, 34))
                    .addGroup(studentListLEFTLayout.createSequentialGroup()
                        .addGroup(studentListLEFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sDisplayListBUTTON, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sExportListBUTTON, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        studentListLEFTLayout.setVerticalGroup(
            studentListLEFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentListLEFTLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(studentListLEFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(studentListLEFTLayout.createSequentialGroup()
                        .addComponent(sClassSelectButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(studentListLEFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sTimeSelectBUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(studentListLEFTLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(sTimeSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sDisplayListBUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(sExportListBUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, student_1List, studentLIST);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${id}"));
        columnBinding.setColumnName("Id");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${studentId}"));
        columnBinding.setColumnName("Student Id");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${firstName}"));
        columnBinding.setColumnName("First Name");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${lastName}"));
        columnBinding.setColumnName("Last Name");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        studentListRIGHT.setViewportView(studentLIST);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(studentListLEFT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(studentListRIGHT, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(studentListLEFT)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(studentListRIGHT, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 75, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Student list", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sDisplayListBUTTONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sDisplayListBUTTONActionPerformed
        // TODO add your handling code here: 
    }//GEN-LAST:event_sDisplayListBUTTONActionPerformed

    private void sExportListBUTTONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sExportListBUTTONActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sExportListBUTTONActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        dbControl.dbComd("SELECT COUNT (CLASS.NAME) from INSTRUCTOR INNER JOIN INSTRUCTOR_CLASS ON INSTRUCTOR.ID =  INSTRUCTOR_CLASS.INSTRUCTOR_ID INNER JOIN CLASS ON INSTRUCTOR_CLASS.CLASS_ID = CLASS.ID WHERE INSTRUCTOR.CARD_ID = 'e0b48a'");
        try {
            int xx = 0;
            if (dbControl.rs.next()) xx = dbControl.rs.getInt("1");
            System.out.println(xx+ " xx on Main");
            
            
            
            //java.awt.List test = new java.awt.List
            //sClassSelect.setSize(322,322);
            
//        System.out.print(sClassSelect.getSize() + " sClassSelect.getSize()");
        } catch (SQLException ex) {
            Logger.getLogger(ClassManager.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            dbControl.doClose();
        }
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClassManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClassManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClassManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClassManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClassManager().setVisible(true);
            }
        });
        
        classListFiller();
        
        
        java.awt.Frame f= new java.awt.Frame();  
        java.awt.List l1=new java.awt.List(5);  
        l1.setBounds(100,100, 75,75);  
        l1.add("Item 1");  
        l1.add("Item 2");  
        l1.add("Item 3");  
        l1.add("Item 4");  
        l1.add("Item 5");  
        f.add(l1);
//        studentListLEFT.add(l1);
//        sClassSelect.setBounds(200,200,200,200);
//        sClassSelect.add("Lol");
//        f.add(sClassSelect);
        f.setSize(1000,400);  
        f.setLayout(null);  
        f.setVisible(false);  

         
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.persistence.EntityManager amsPUEntityManager;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private static javax.swing.JList<String> sClassSelect;
    private java.awt.Label sClassSelectButton;
    private java.awt.Button sDisplayListBUTTON;
    private java.awt.Button sExportListBUTTON;
    private javax.swing.JScrollPane sTimeSelect;
    private javax.swing.JList<String> sTimeSelect1;
    private java.awt.Label sTimeSelectBUTTON;
    private javax.swing.JTable studentLIST;
    private javax.swing.JInternalFrame studentListLEFT;
    private javax.swing.JScrollPane studentListRIGHT;
    private java.util.List<ams.Student_1> student_1List;
    private javax.persistence.Query student_1Query;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
