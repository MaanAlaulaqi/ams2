/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams;

import static ams.dbControl.rs;
import net.proteanit.sql.DbUtils;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListDataListener;


/** Something the professor requested that I'm dying over. 
 *
 * @author Maan Alaulaqi (201610814@aau.ac.ae)
 */
public class ClassManager extends javax.swing.JFrame {
    private static String y=""; //whenever I need a random string from somewhere. 
    private static int incrementMe = 0; 
    private static int sizeMeUpbb = 0;
    private static String [] stringy;
    private static String [][] stringy2;
    private static String uid = "";
    private static String class_name;
    private static String class_sec;
    private static String[] stringy3;
    /**
     * Creates new form ClassManager
     */
    public ClassManager() {
        initComponents();
    }
private String InstructorUID = UserInterface.UID;
    /**
     * This is to fill the first table of classes. All the classes will be listed with which section that particular professor is using
     * @return  The return here is useless mostly, the method just needs a return to be used in the constructing of wherever this is called
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
     * @param class_select This will be concatnated into two separate Strings .
     * @return The return here is useless mostly, the method just needs a return to be used in the constructing of wherever this is called
     */
    public static int timeListFiller2(String class_select){
        sizeMeUpbb = 0; //resetting the values
        incrementMe = 0; 
         class_name = class_select.substring(0, class_select.length() - 4);
        
         class_sec = class_select.substring(class_select.length() - 1);;
        
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
        stringy2 = new String[2][sizeMeUpbb];
        try {
            dbControl.dbComd("select class.id, start_time, end_time, firstt_class, second_class, name, class_section from class_schedule inner join class on class.id = class_schedule.ID inner join instructor_class on instructor_class.CLASS_ID = class_schedule.ID inner join instructor on instructor.id = INSTRUCTOR_CLASS.INSTRUCTOR_ID where class.name = '"+ class_name+"' and class_section = '"+class_sec+"'");
            //System.out.println(dbControl.rs.getFetchSize() + " rs.getFetchSize()");
            while (dbControl.rs.next()) {
                
                timeConcat = dbControl.rs.getString("firstt_class") + "/"+ dbControl.rs.getString("second_class")+" - "+ dbControl.rs.getString("start_time")+"~"+dbControl.rs.getString("end_time");
                stringy2[0][incrementMe] = rs.getString("ID");
                stringy2[1][incrementMe] = timeConcat;
                System.out.print(stringy2[0][incrementMe] + ", ");
                incrementMe++;
            }System.out.println();
        } catch (SQLException ex) {
            Logger.getLogger(ClassManager.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            dbControl.doClose();
        }
        System.out.println(stringy2[0].length + " stringy2[0].length");
        return sizeMeUpbb;
    }
    
    /**
     * This is basically to fill in the "add class" roster
     * shouldn't be too complicated 
     * @return The return here is useless mostly, the method just needs a return to be used in the constructing of wherever this is called
     */
    public static int class2ListFiller2(){
         sizeMeUpbb = 0; //resetting the values
         incrementMe = 0; 
         uid = UserInterface.uid_placeholder;
         System.out.println(uid+ "uid check, classListFiller2() method");
            
            
            try {
                dbControl.dbComd("select count (*) from class");
                if (dbControl.rs.next()) sizeMeUpbb = dbControl.rs.getInt("1");
                

            } catch (SQLException ex) {
                Logger.getLogger(ClassManager.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                dbControl.doClose();
            }
            stringy = new String[sizeMeUpbb];
            try {
                dbControl.dbComd("select name from class");
                //System.out.println(dbControl.rs.getFetchSize() + " rs.getFetchSize()");
                while (dbControl.rs.next()) {
                    //System.out.println(dbControl.rs.getString("NAME"));
                    y = dbControl.rs.getString("NAME");
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
    
     public static int timingsListFiller2(){
        sizeMeUpbb = 0; //resetting the values
        incrementMe = 0; 
         
        String timeConcat = "";
        //String class;

        try {
            dbControl.dbComd("select count (*) from class_schedule");
             if (dbControl.rs.next()) sizeMeUpbb = dbControl.rs.getInt("1");
                
                
            //System.out.println(sizeMeUpbb + " sizeMeUpbb (size lol)");

        } catch (SQLException ex) {
            Logger.getLogger(ClassManager.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            dbControl.doClose();
        }
            stringy3 = new String[sizeMeUpbb];
            dbControl.dbComd("select * from class_schedule");
            try {
            //System.out.println(dbControl.rs.getFetchSize() + " rs.getFetchSize()");
            while (dbControl.rs.next()) {
                
                timeConcat = dbControl.rs.getString("firstt_class") + "/"+ dbControl.rs.getString("second_class")+" - "+ dbControl.rs.getString("start_time")+"~"+dbControl.rs.getString("end_time");
                stringy3[incrementMe] = timeConcat;
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(798, 514));
        setMinimumSize(new java.awt.Dimension(798, 514));
        setPreferredSize(new java.awt.Dimension(798, 514));

        studentListLEFT.setVisible(true);

        sClassSelectButton.setText("Select class");

        sTimeSelectBUTTON.setText("Select time");

        sDisplayListBUTTON.setLabel("Display student list");
        sDisplayListBUTTON.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sDisplayListBUTTONMouseReleased(evt);
            }
        });
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

        jLabel1.setText("Select classes: ");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {

            int wut = class2ListFiller2();
            //I just need reason to run this method before making the String array, so that I can clone it
            String[] strings = stringy.clone();

            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jList1);

        jLabel2.setText("Select timings:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<String>() {

            int wut = timingsListFiller2();
            //I just need reason to run this method before making the String array, so that I can clone it
            String[] strings = stringy3.clone();

            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Add classes", jPanel2);

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
                String[] strings = stringy2[1].clone();

                public int getSize() { return strings.length; }
                public String getElementAt(int i) { return strings[i]; }
        });
        
        
    }//GEN-LAST:event_sClassSelectMouseReleased

    private void sDisplayListBUTTONMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sDisplayListBUTTONMouseReleased
        // TODO add your handling code here:
        System.out.println(stringy2[0][incrementMe-1] + " " + class_sec);
        dbControl.dbComd("select student.STUDENT_ID,student.first_name, student.last_name from student join student_class on student.id = STUDENT_CLASS.STUDENT_ID join class on class.id = student_class.class_id join instructor_class on instructor_class.CLASS_ID = student_class.CLASS_ID join instructor on instructor.ID = instructor_class.INSTRUCTOR_ID where class.id = "+stringy2[0][incrementMe-1]+" and student_class.class_section = '"+class_sec+"'");
        try {
            if (dbControl.rs.next()) studentLIST.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            Logger.getLogger(ClassManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally{ dbControl.doClose(); }
    }//GEN-LAST:event_sDisplayListBUTTONMouseReleased

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
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClassManager().setVisible(true);
            }
        });
                
        

         
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.persistence.EntityManager amsPUEntityManager;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
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
    // End of variables declaration//GEN-END:variables
}
