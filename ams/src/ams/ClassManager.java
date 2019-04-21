/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/** Something the professor requested that I'm dying over. 
 *
 * @author Maan Alaulaqi (201610814@aau.ac.ae)
 */
public class ClassManager extends javax.swing.JFrame {
    private static String y=""; //whenever I need a random string from somewhere. 
    private static int incrementMe = 0; 
    private static int sizeMeUpbb = 0;
    private static String [] stringy;
    private static String uid = "";
    /**
     * Creates new form ClassManager
     */
    public ClassManager() {
        initComponents();
    }
private String InstructorUID = UserInterface.UID;
    /**
     * This is to fill the first table of classes. All the classes will be listed with which section that particular professor is using
     * @return 
     */
    public static int classListFiller2(){
         sizeMeUpbb = 0; //resetting the values
         incrementMe = 0; 
         uid = UserInterface.uid_placeholder;
         System.out.println(uid+ "uid check, classListFiller2() method");
            
            
            try {
                dbControl.dbComd("select count (first_name) from class join instructor_class on instructor_class.CLASS_ID = class.id join class_schedule on class_schedule.id = instructor_class.CLASS_ID join instructor on instructor_class.INSTRUCTOR_ID = instructor.id where instructor.card_id = '"+uid+"'");
                if (dbControl.rs.next()) sizeMeUpbb = dbControl.rs.getInt("1");
                

            } catch (SQLException ex) {
                Logger.getLogger(ClassManager.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                dbControl.doClose();
            }
            stringy = new String[sizeMeUpbb];
            try {
                dbControl.dbComd("select class.name,class_section from class join instructor_class on instructor_class.CLASS_ID = class.id join class_schedule on class_schedule.id = instructor_class.CLASS_ID join instructor on instructor_class.INSTRUCTOR_ID = instructor.id where instructor.card_id = '"+uid+"'");
                //System.out.println(dbControl.rs.getFetchSize() + " rs.getFetchSize()");
                while (dbControl.rs.next()) {
                    //System.out.println(dbControl.rs.getString("NAME"));
                    y = dbControl.rs.getString("NAME") + " - " + dbControl.rs.getString("class_section");
                    stringy[incrementMe] = y;
                    //System.out.println(y);
                    //System.out.println(stringy[incrementMe] + " Stringy[incremementMe]");
                    incrementMe++;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClassManager.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                dbControl.doClose();
            }
            return sizeMeUpbb;
    }
    
    /**
     * This is to fill the time table with selectable times.
     * You'll see that class_select is taken and split into two sub strings. 
     * The substrings are used to call sql queries, therefore the string MUST be formated like so:
     * "string - #" where the section is the very last charact and preceeding it is 2 spaces and 1 dash(total 3 char spaces)
     * @param class_select
     * @return 
     */
    public static int timeListFiller2(String class_select){
        sizeMeUpbb = 0; //resetting the values
        incrementMe = 0; 
        String class_name = class_select.substring(0, class_select.length() - 4);
        
        String class_sec = class_select.substring(class_select.length() - 1);;
        
        String timeConcat = "";
        //String class;

        try {
            dbControl.dbComd("select count (*) from class_schedule inner join class on class.id = class_schedule.ID inner join instructor_class on instructor_class.CLASS_ID = class_schedule.ID inner join instructor on instructor.id = INSTRUCTOR_CLASS.INSTRUCTOR_ID where class.name = '"+ class_name+"' and class_section = '"+class_sec+"'");
             if (dbControl.rs.next()) sizeMeUpbb = dbControl.rs.getInt("1");
                
                
            //System.out.println(sizeMeUpbb + " sizeMeUpbb (size lol)");

        } catch (SQLException ex) {
            Logger.getLogger(ClassManager.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            dbControl.doClose();
        }
        stringy = new String[sizeMeUpbb];
        try {
            dbControl.dbComd("select start_time, end_time, firstt_class, second_class, name, class_section from class_schedule inner join class on class.id = class_schedule.ID inner join instructor_class on instructor_class.CLASS_ID = class_schedule.ID inner join instructor on instructor.id = INSTRUCTOR_CLASS.INSTRUCTOR_ID where class.name = '"+ class_name+"' and class_section = '"+class_sec+"'");
            //System.out.println(dbControl.rs.getFetchSize() + " rs.getFetchSize()");
            while (dbControl.rs.next()) {
                
                timeConcat = dbControl.rs.getString("firstt_class") + "/"+ dbControl.rs.getString("second_class")+" - "+ dbControl.rs.getString("start_time")+"~"+dbControl.rs.getString("end_time");
                stringy[incrementMe] = timeConcat;
                
                
                incrementMe++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassManager.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            dbControl.doClose();
        }
        return sizeMeUpbb;
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
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

        sTimeSelect1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        sTimeSelect.setViewportView(sTimeSelect1);

        sClassSelect.setModel(new javax.swing.AbstractListModel<String>() {

            int wut = classListFiller2();
            //I just need reason to run this method before making the String array, so that I can clone it
            String[] strings = stringy.clone();

            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        sClassSelect.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        sClassSelect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sClassSelectMouseReleased(evt);
            }
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
                            .addComponent(jScrollPane2)
                            .addComponent(sTimeSelect))
                        .addGap(34, 34, 34))
                    .addGroup(studentListLEFTLayout.createSequentialGroup()
                        .addComponent(sDisplayListBUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sExportListBUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        studentListLEFTLayout.setVerticalGroup(
            studentListLEFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentListLEFTLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(studentListLEFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sClassSelectButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(studentListLEFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sTimeSelectBUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(studentListLEFTLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(sTimeSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(studentListLEFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sDisplayListBUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sExportListBUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(149, 149, 149))
        );

        studentLIST.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Student Id", "First Name", "Last Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

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
                .addComponent(studentListRIGHT, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(studentListLEFT)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(studentListRIGHT, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
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

    
    private void sClassSelectMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sClassSelectMouseReleased
        // TODO add your handling code here:
        sTimeSelectBUTTON.setVisible(true); 
        sTimeSelect.setVisible(true); 
        String class_select = sClassSelect.getSelectedValue();
        timeListFiller2(class_select);
        
        sTimeSelect1.setModel(new javax.swing.AbstractListModel<String>() {

                
                //I just need reason to run this method before making the String array, so that I can clone it
                String[] strings = stringy.clone();

                public int getSize() { return strings.length; }
                public String getElementAt(int i) { return strings[i]; }
        });
        
        
    }//GEN-LAST:event_sClassSelectMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
       /* 
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
       /*
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClassManager().setVisible(true);
            }
        });*/
                
        

         
        
        
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
