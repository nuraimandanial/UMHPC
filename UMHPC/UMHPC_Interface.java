package Assignment.UMHPC;

import Assignment.UMHPC.UMHPC;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * @author kinderBono
 */
public class UMHPC_Interface extends JPanel {
    public static int setting = 0;
    public static int includeChart = 0;
    JFrame frame = new JFrame();
    /**
     * Creates new form UMHPC_Interface
     */
    public UMHPC_Interface() {
        initComponents();
        frame.add(this);
        frame.setTitle("UMHPC");
        try {
            frame.setIconImage(ImageIO.read(new File("Slurm Logo.svg")));
        } catch (IOException ex) {
            Logger.getLogger(UMHPC_Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 350);
        frame.setLocationRelativeTo(null);
        frame.setBackground(new Color(172, 255, 252));
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Swing / AWT Positioning">                          
    private void initComponents() {

        jTabbedPane1 = new JTabbedPane();
        allData = new javax.swing.JPanel();
        getAll = new javax.swing.JButton();
        singleDatePanel = new JPanel();
        singleDateTextLabel = new JLabel();
        jScrollPane1 = new JScrollPane();
        singleDate = new JTextPane();
        enterButton = new JButton();
        clearButton = new JButton();
        exitButton = new JButton();
        jPanel1 = new JPanel();
        rangedDatePanel = new JPanel();
        enterButton1 = new JButton();
        clearButton1 = new JButton();
        exitButton1 = new JButton();
        exitButton2 = new JButton();
        endDateTextLabel = new JLabel();
        jScrollPane2 = new JScrollPane();
        endDate = new JTextPane();
        startDateTextLabel = new JLabel();
        jScrollPane3 = new JScrollPane();
        startDate = new JTextPane();
        includeCharts = new JCheckBox();
        includeChartsSingle = new JCheckBox();
        includeChartsAll = new JCheckBox();

        getAll.setText("Get All Job Details");
        getAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getAllActionPerformed(evt);
            }
        });

        includeChartsAll.setText("Include Charts");
        includeChartsAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                includeChartsAllActionPerformed(evt);
            }
        });
        
        exitButton2.setText("Exit");
        exitButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                exitButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout allDataLayout = new GroupLayout(allData);
        allData.setLayout(allDataLayout);
        allDataLayout.setHorizontalGroup(
            allDataLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(allDataLayout.createSequentialGroup()
                .addGroup(allDataLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(allDataLayout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(getAll))
                    .addGroup(allDataLayout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(includeChartsAll))
                    .addGroup(allDataLayout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(exitButton2)))
                .addContainerGap(130, Short.MAX_VALUE))
        );
        allDataLayout.setVerticalGroup(
            allDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(allDataLayout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(getAll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(includeChartsAll)
                .addGap(18, 18, 18)
                .addComponent(exitButton2)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("All", allData);
        //
        singleDateTextLabel.setText("Enter Date : ");

        singleDate.setToolTipText("yyyy-MM-dd");
        jScrollPane1.setViewportView(singleDate);

        enterButton.setText("Enter");
        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                enterButtonActionPerformed(evt);
            }
        });

        clearButton.setText("Clear");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        exitButton.setText("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        includeChartsSingle.setText("Include Charts");
        includeChartsSingle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                includeChartsSingleActionPerformed(evt);
            }
        });
        
        javax.swing.GroupLayout singleDatePanelLayout = new javax.swing.GroupLayout(singleDatePanel);
        singleDatePanel.setLayout(singleDatePanelLayout);
        singleDatePanelLayout.setHorizontalGroup(
            singleDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(singleDatePanelLayout.createSequentialGroup()
                .addGroup(singleDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(singleDatePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(enterButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(clearButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(exitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(singleDatePanelLayout.createSequentialGroup()
                        .addGroup(singleDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(singleDatePanelLayout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(singleDateTextLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(singleDatePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(includeChartsSingle)))
                        .addGap(0, 64, Short.MAX_VALUE)))
                .addContainerGap())
        );
        singleDatePanelLayout.setVerticalGroup(
            singleDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(singleDatePanelLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(singleDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(singleDateTextLabel))
                .addGap(94, 94, 94)
                .addGroup(singleDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enterButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clearButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(includeChartsSingle)
                .addGap(37, 37, 37))
        );


        jTabbedPane1.addTab("Single Date", singleDatePanel);

        enterButton1.setText("Enter");
        enterButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                enterButton1ActionPerformed(evt);
            }
        });

        clearButton1.setText("Clear");
        clearButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                clearButton1ActionPerformed(evt);
            }
        });

        exitButton1.setText("Exit");
        exitButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                exitButton1ActionPerformed(evt);
            }
        });

        endDateTextLabel.setText("Enter End Date : ");

        endDate.setToolTipText("yyyy-MM-dd");
        jScrollPane2.setViewportView(endDate);

        startDateTextLabel.setText("Enter Start Date : ");

        startDate.setToolTipText("yyyy-MM-dd");
        jScrollPane3.setViewportView(startDate);
        
        includeCharts.setText("Include Charts");
        includeCharts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                includeChartsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rangedDatePanelLayout = new javax.swing.GroupLayout(rangedDatePanel);
        rangedDatePanel.setLayout(rangedDatePanelLayout);
        rangedDatePanelLayout.setHorizontalGroup(
            rangedDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rangedDatePanelLayout.createSequentialGroup()
                .addGroup(rangedDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rangedDatePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(enterButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(clearButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(exitButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                    .addGroup(rangedDatePanelLayout.createSequentialGroup()
                        .addGroup(rangedDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(rangedDatePanelLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(rangedDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(rangedDatePanelLayout.createSequentialGroup()
                                        .addComponent(endDateTextLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(rangedDatePanelLayout.createSequentialGroup()
                                        .addComponent(startDateTextLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(rangedDatePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(includeCharts)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        rangedDatePanelLayout.setVerticalGroup(
            rangedDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rangedDatePanelLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(rangedDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startDateTextLabel))
                .addGap(36, 36, 36)
                .addGroup(rangedDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endDateTextLabel))
                .addGap(59, 59, 59)
                .addGroup(rangedDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enterButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clearButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exitButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(includeCharts)
                .addGap(37, 37, 37))
        );

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(rangedDatePanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(rangedDatePanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Ranged Date", jPanel1);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>                        

    // <editor-fold defaultstate="collapsed" desc="ActionEvent Declaration">
    private void enterButtonActionPerformed(ActionEvent evt) {                                            
        try {
            UMHPC.startDate = singleDate.getText();
            UMHPC.endDate = singleDate.getText();
            if (UMHPC.startDate.length() < 8) {
                UMHPC.dateControl = 1;
            }
            else {
                UMHPC.startDate += UMHPC.SSC.fixedStartTime;
                UMHPC.endDate += UMHPC.SSC.fixedEndTime;
            }
            setting = 1;
            UMHPC.getTotalJobsOnCertain();
        } catch (ParseException ex) {
            Logger.getLogger(UMHPC_Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                                           

    private void enterButton1ActionPerformed(ActionEvent evt) {                                             
        try {
            UMHPC.startDate = startDate.getText();
            UMHPC.endDate = endDate.getText();
            if (UMHPC.startDate.length() == 10 || UMHPC.endDate.length() == 10){
                UMHPC.startDate += UMHPC.SSC.fixedStartTime;
                UMHPC.endDate += UMHPC.SSC.fixedEndTime;
            }
            UMHPC.dateControl = 0;
            setting = 1;
            
            UMHPC.getTotalJobsOnCertain();
        } catch (ParseException ex) {
            Logger.getLogger(UMHPC_Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                                            

    private void clearButtonActionPerformed(ActionEvent evt) {                                            
        singleDate.setText("");
    }                                           

    private void exitButtonActionPerformed(ActionEvent evt) {                                           
        System.exit(0);
    }                                          

    private void clearButton1ActionPerformed(ActionEvent evt) {                                             
        startDate.setText("");
        endDate.setText("");
    }                                            

    private void exitButton1ActionPerformed(ActionEvent evt) {                                            
        System.exit(0);
    }                                           

    private void exitButton2ActionPerformed(ActionEvent evt) {                                            
        System.exit(0);
    }                                           

    private void getAllActionPerformed(ActionEvent evt) {                                       
        Interface I = new Interface();
        I.printJobsOverall();
    } 
    
    private void includeChartsActionPerformed(ActionEvent evt) {                                              
        if (includeCharts.isSelected()) {
            includeChart = 1;
        }
    } 
    
    private void includeChartsSingleActionPerformed(ActionEvent evt) {                                              
        if (includeChartsSingle.isSelected()) {
            includeChart = 1;
        }
    } 
    
    private void includeChartsAllActionPerformed(ActionEvent evt) {                                              
        if (includeChartsAll.isSelected()) {
            includeChart = 1;
        }
    } // </editor-fold> 
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UMHPC_Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UMHPC_Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UMHPC_Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UMHPC_Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UMHPC_Interface().setVisible(true);
            }
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Variable Declaration">                  
    private JPanel allData;
    private JButton getAll;
    private JButton clearButton;
    private JButton clearButton1;
    private JTextPane endDate;
    private JLabel endDateTextLabel;
    private JButton enterButton;
    private JButton enterButton1;
    private JButton exitButton;
    private JButton exitButton1;
    private JButton exitButton2;
    private JCheckBox includeCharts;
    private JCheckBox includeChartsSingle;
    private JCheckBox includeChartsAll;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JTabbedPane jTabbedPane1;
    private JPanel rangedDatePanel;
    private JTextPane singleDate;
    private JPanel singleDatePanel;
    private JLabel singleDateTextLabel;
    private JTextPane startDate;
    private JLabel startDateTextLabel;
    // </editor-fold>              
}
