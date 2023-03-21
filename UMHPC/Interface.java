package Assignment.UMHPC;

import static Assignment.UMHPC.UMHPC.SC;
import java.util.Map;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * @author kinderBono
 */
public class Interface extends JFrame{
    private JTextArea textArea;
    private JFrame frame = new JFrame();
    
    public Interface() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // Set the title of the frame
        frame.setTitle("Job Allocation Details");
        try {
            frame.setIconImage(ImageIO.read(new File("Details.png")));
        } catch (IOException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Set the size of the frame
        frame.setSize(550, 450);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        // Create a new JTextArea
        textArea = new JTextArea();
        textArea.setSize(450, 400);
        textArea.setBackground(new Color(172, 255, 252));
        textArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        textArea.setEditable(false);
    }
    
    public void printFormat(int jobAllocate, int jobComplete, int jobKill, int jobError,
                            int jobExhaust, int jobClean, int gpu, int opteron, int epyc,
                            int allocateComplete, int allocateKill, long durationTotal, 
                            int allocateExhaust, int allocateClean, int totalCount, Map<String, Integer> errorCounts){
        String output = String.format("\n");
        output += String.format("---------------------------------------------------------------\n");
        if (UMHPC.dateControl == 1) {
            output += String.format("Within " + UMHPC.SSC.month[Integer.parseInt(UMHPC.startDate.split("-")[1])] + " " + UMHPC.startDate.split("-")[0]+ " :-\n");
        } 
        else if (UMHPC.startDate.substring(0, 10).equals(UMHPC.endDate.substring(0, 10))) {
            output += String.format("Within " + UMHPC.startDate.split("T")[0].split("-")[2] + " " +
                    UMHPC.SSC.month[Integer.parseInt(UMHPC.startDate.split("-")[1])] + " " + UMHPC.startDate.split("-")[0]+ " :-\n");
        }
        else {
            output += String.format("From " + UMHPC.startDate.substring(0, 10) + " to " + UMHPC.endDate.substring(0, 10) + " :-\n");
        }
        output += String.format("---------------------------------------------------------------");
        output += String.format("\n%-20s", "Total jobs allocated"); output += String.format(" : %4d", jobAllocate);
        output += String.format("\n%-20s", "Total jobs completed"); output += String.format(" : %4d", jobComplete); output += String.format(", Within allocated time range : " + allocateComplete);
        output += String.format("\n%-20s", "Total jobs killed"); output += String.format(" : %4d", jobKill); output += String.format(", Within allocated time range : " + allocateKill);
        output += String.format("\n%-20s", "Total jobs running"); output += String.format(" : %4d", (jobAllocate - (allocateComplete + allocateKill)));
        output += String.format("\n---------------------------------------------------------------");
        output += String.format("\n%-20s", "Total jobs exhausted"); output += String.format(" : %4d", jobExhaust); output += String.format(", Within allocated time range : " + allocateExhaust);
        output += String.format("\n%-20s", "Total jobs cleaned"); output += String.format(" : %4d", jobClean); output += String.format(", Within allocated time range : " + allocateClean);
        output += String.format("\n%-20s", "Total error occured"); output += String.format(" : %4d", jobError);
        
        int columns = 0;
        output += String.format("\n");
        for (String user : errorCounts.keySet()) {
            output += String.format("%16s - %3d \t", user, errorCounts.get(user));
            columns++;
            if (columns % 3 == 0) {
                output += String.format("\n");
            }
        }
        
        output += String.format("\n");
        output += String.format("\n---------------------------------------------------------------\n");
        output += String.format("Jobs allocated by partition :\nGPU - " + gpu + ", EPYC - " + epyc
                + ", Opteron - " + opteron + "\n");
        
        output += String.format("---------------------------------------------------------------\n");
        executeTime time = new executeTime();
        output += String.format("Average execution time : " + time.getAverageDuration(durationTotal, totalCount) + "\n");
        textArea.setText(output);
        frame.add(textArea);
        frame.setVisible(true);
    }
    
    public void printJobsOverall(){
        String output = String.format("---------------------------------------------------------------");
        output += String.format("\nTotal jobs allocated : " + (SC.jobsAllocated.length + SC.jobsStarted.length));
        output += String.format("\nTotal jobs done      : " + SC.jobsCompleted.length);
        output += String.format("\nTotal jobs killed    : " + SC.jobsKilled.length + ", Total cleaned : " + SC.jobsCleaned.length);
        output += String.format("\nTotal jobs exhausted : " + SC.jobsTimeExhausted.length);
        output += String.format("\nTotal error occured  : " + SC.errorUser.length);
        
        
        output += String.format("\n");
        Map<String, Integer> errorCounts = new HashMap<>();
        for (int i = 0; i < SC.errorUser.length; i++) {
            String userError = SC.splitErrorUser[i][1];
            if (!errorCounts.containsKey(userError)) {
                errorCounts.put(userError, 0);
            }
            errorCounts.put(userError, errorCounts.get(userError) + 1);
        }
        int columns = 0;
        for (String user : errorCounts.keySet()) {
            output += String.format("%16s - %3d    ", user, errorCounts.get(user));
            columns++;
            if (columns % 3 == 0) {
                output += String.format("\n");
            }
        }
        
        output += String.format("\n---------------------------------------------------------------\n");
        output += String.format("Jobs allocated by partition :\nGPU - " + SC.totalGpu + ", EPYC - " + SC.totalEpyc
                + ", Opteron - " + SC.totalOpteron);
        output += String.format("\n---------------------------------------------------------------\n");
        textArea.setText(output);
        // Add the JTextArea to the frame
        frame.add(textArea);
        frame.setVisible(true);
        
        if (UMHPC_Interface.includeChart == 1) {
            Chart chart = new Chart();
            chart.errorPieChart(errorCounts);
            stackedBarChart stacked = new stackedBarChart("", "Jobs Allocated by Partitions", SC.totalGpu, SC.totalEpyc, SC.totalOpteron);
            UMHPC_Interface.includeChart = 0;
        }
    }
}
