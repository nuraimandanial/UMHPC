package Assignment.UMHPC;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author kinderBono
 */

public class UMHPC{
    static SuperUMHPC SC = new SuperUMHPC();
    static SubSuperUMHPC SSC = new SubSuperUMHPC();
    
    static Scanner keyboard = new Scanner(System.in);
    
    static String startDate, endDate;
    static int dateControl = 0;
    
    public static Map<String, Integer> errorCounts = new HashMap<>();
    
    public static int oneHdrd, twoHdrd, threeHdrd, upperFifty, lowerFifty, twentyFour, twelve, oneHr;
    
    public static void main(String[] args) throws ParseException {
        UMHPC_Interface.setting = 2;
        printJobsOverall();
        
        getSingleDate();
        getTotalJobsOnCertain();
        
        getRangeDate();
        getTotalJobsOnCertain();
    }
    
    public static void printJobsOverall(){       
        System.out.println("Total jobs allocated : " + (SC.jobsAllocated.length + SC.jobsStarted.length));
        System.out.println("Total jobs done : " + SC.jobsCompleted.length);
        System.out.println("Total jobs killed : " + SC.jobsKilled.length + ", Total cleaned : " + SC.jobsCleaned.length);
        System.out.println("Total jobs exhausted : " + SC.jobsTimeExhausted.length);
        System.out.println("Total error occured : " + SC.errorUser.length);
        
        for (int i = 0; i < SC.errorUser.length; i++) {
            String userError = SC.splitErrorUser[i][1];
            if (!errorCounts.containsKey(userError)) {
                errorCounts.put(userError, 0);
            }
            errorCounts.put(userError, errorCounts.get(userError) + 1);
        }
        int columns = 0;
        for (String user : errorCounts.keySet()) {
            System.out.printf("%20s - %3d \t", user, errorCounts.get(user));
            columns++;
            if (columns % 3 == 0) {
                System.out.println();
            }
        }
        
        System.out.println();
        System.out.println("Jobs allocated by partition :\nGPU - " + SC.totalGpu + ", EPYC - " + SC.totalEpyc
                + ", Opteron - " + SC.totalOpteron);
    }

    public static void getSingleDate(){
        System.out.println();
        System.out.print("Enter date (yyyy-MM-dd) or (yyyy-MM): ");
        startDate = keyboard.nextLine();
        endDate = startDate;
        if (startDate.length() < 8) {
            dateControl = 1;
        }
        else {
            startDate += SSC.fixedStartTime;
            endDate += SSC.fixedEndTime;
        }
    }

    public static void getRangeDate(){
        System.out.println();
        do {
            System.out.print("Enter start date (yyyy-MM-dd) or (yyyy-MM-ddTHH:mm:ss.SSS) : ");
            startDate = keyboard.nextLine();
            System.out.print("Enter end date (yyyy-MM-dd) or (yyyy-MM-ddTHH:mm:ss.SSS) : ");
            endDate = keyboard.nextLine();
        } while (startDate.length() < 8 || endDate.length() < 8);
        if (startDate.length() == 10 || endDate.length() == 10){
            startDate += SSC.fixedStartTime;
            endDate += SSC.fixedEndTime;
        }
        dateControl = 0;
    }

    public static void getTotalJobsOnCertain() throws ParseException{
        int jobAllocate = 0, jobComplete = 0, jobKill = 0, jobError = 0, jobExhaust = 0, jobClean = 0;
        int gpu = 0, opteron = 0, epyc = 0;
        int allocateComplete = 0, allocateKill = 0, allocateExhaust = 0, allocateClean = 0;
        
        long durationTotal = 0; int totalCount = 0;
        
        Date inputStartDate, inputEndDate;
        Map<String, Integer> errorCounts = new HashMap<>();
        if (dateControl == 1) {
            for (int i = 0; i < SC.jobsAllocated.length; i++) {
                if (startDate.equals(SC.splitJobsAllocated[i][0].substring(0, 7))) {
                    jobAllocate++;
                    
                    if (SC.jobsAllocated[i].contains("gpu"))
                        gpu++;
                    else if(SC.jobsAllocated[i].contains("epyc"))
                        epyc++;
                    else if(SC.jobsAllocated[i].contains("opteron"))
                        opteron++;
                    
                    for (int j = 0; j < SC.jobsCompleted.length; j++) {
                        if (SC.splitJobsAllocated[i][1].equals(SC.splitJobsCompleted[j][1])) {
                            executeTime time = new executeTime(SC.splitJobsAllocated[i][0], SC.splitJobsCompleted[j][0]);
                            durationTotal += time.getDuration();
                            time.setDuration(time.getDuration());
                            totalCount++; allocateComplete++;
                        }
                    }
                    
                    for (int j = 0; j < SC.jobsKilled.length; j++) {
                        if (SC.splitJobsAllocated[i][1].equals(SC.splitJobsKilled[j][1])) {
                            allocateKill++;
                        }
                    }
                    
                    for (int j = 0; j < SC.jobsTimeExhausted.length; j++) {
                        if (SC.splitJobsAllocated[i][1].equals(SC.splitJobsTimeExhausted[j][1])) {
                            allocateExhaust++;
                        }
                    }
                    
                    for (int j = 0; j < SC.jobsCleaned.length; j++) {
                        if (SC.splitJobsAllocated[i][1].equals(SC.splitJobsCleaned[j][1])) {
                            allocateClean++;
                        }
                    }
                }
            }
            for (int i = 0; i < SC.jobsStarted.length; i++) {
                if (startDate.equals(SC.splitJobsStarted[i][0].substring(0, 7))) {
                    jobAllocate++;
                    
                    if (SC.jobsStarted[i].contains("gpu"))
                        gpu++;
                    else if(SC.jobsStarted[i].contains("epyc"))
                        epyc++;
                    else if(SC.jobsStarted[i].contains("opteron"))
                        opteron++;
                    
                    for (int j = 0; j < SC.jobsCompleted.length; j++) {
                        if (SC.splitJobsStarted[i][1].equals(SC.splitJobsCompleted[j][1])) {
                            executeTime time = new executeTime(SC.splitJobsStarted[i][0], SC.splitJobsCompleted[j][0]);
                            durationTotal += time.getDuration();
                            time.setDuration(time.getDuration());
                            totalCount++; allocateComplete++;
                        }
                    }
                    
                    for (int j = 0; j < SC.jobsKilled.length; j++) {
                        if (SC.splitJobsStarted[i][1].equals(SC.splitJobsKilled[j][1])) {
                            allocateKill++;
                        }
                    }
                }
            }
            for (int i = 0; i < SC.jobsCompleted.length; i++) {
                if (startDate.equals(SC.splitJobsCompleted[i][0].substring(0, 7))) {
                    jobComplete++;
                }
            }
            for (int i = 0; i < SC.jobsKilled.length; i++) {
                if (startDate.equals(SC.splitJobsKilled[i][0].substring(0, 7))) {
                    jobKill++;
                }
            }
            for (int i = 0; i < SC.errorUser.length; i++) {
                if (startDate.equals(SC.splitErrorUser[i][0].substring(0, 7))) {
                    jobError++;
                    
                    String userError = SC.splitErrorUser[i][1];
                    if (!errorCounts.containsKey(userError)) {
                        errorCounts.put(userError, 0);
                    }
                    errorCounts.put(userError, errorCounts.get(userError) + 1);
                }
            }
            for (int i = 0; i < SC.jobsTimeExhausted.length; i++) {
                if (startDate.equals(SC.splitJobsTimeExhausted[i][0].substring(0, 7))) {
                    jobExhaust++;
                }
            }
            for (int i = 0; i < SC.jobsCleaned.length; i++) {
                if (startDate.equals(SC.splitJobsCleaned[i][0].substring(0, 7))) {
                    jobClean++;
                }
            }
        }
        else {
            inputStartDate = SSC.dateTimeFullFormat.parse(startDate);
            inputEndDate = SSC.dateTimeFullFormat.parse(endDate);
        
            for (int i = 0; i < SC.jobsAllocated.length; i++) {
                Date compareDateTime = SSC.dateTimeFullFormat.parse(SC.splitJobsAllocated[i][0]);

                if ((compareDateTime.compareTo(inputStartDate) >= 0) && (compareDateTime.compareTo(inputEndDate) <= 0)) {
                    jobAllocate++;
                    
                    if (SC.jobsAllocated[i].contains("gpu"))
                        gpu++;
                    else if(SC.jobsAllocated[i].contains("epyc"))
                        epyc++;
                    else if(SC.jobsAllocated[i].contains("opteron"))
                        opteron++;
                    
                    for (int j = 0; j < SC.jobsCompleted.length; j++) {
                        if (SC.splitJobsAllocated[i][1].equals(SC.splitJobsCompleted[j][1])) {
                            executeTime time = new executeTime(SC.splitJobsAllocated[i][0], SC.splitJobsCompleted[j][0]);
                            durationTotal += time.getDuration();
                            time.setDuration(time.getDuration());
                            totalCount++; allocateComplete++;
                        }
                    }
                    
                    for (int j = 0; j < SC.jobsKilled.length; j++) {
                        if (SC.splitJobsAllocated[i][1].equals(SC.splitJobsKilled[j][1])) {
                            allocateKill++;
                        }
                    }
                    
                    for (int j = 0; j < SC.jobsTimeExhausted.length; j++) {
                        if (SC.splitJobsAllocated[i][1].equals(SC.splitJobsTimeExhausted[j][1])) {
                            allocateExhaust++;
                        }
                    }
                    
                    for (int j = 0; j < SC.jobsCleaned.length; j++) {
                        if (SC.splitJobsAllocated[i][1].equals(SC.splitJobsCleaned[j][1])) {
                            allocateClean++;
                        }
                    }
                }
            }
            for (int i = 0; i < SC.jobsStarted.length; i++) {
                Date compareDateTime = SSC.dateTimeFullFormat.parse(SC.splitJobsStarted[i][0]);

                if ((compareDateTime.compareTo(inputStartDate) >= 0) && (compareDateTime.compareTo(inputEndDate) <= 0)) {
                    jobAllocate++;
                    
                    if (SC.jobsStarted[i].contains("gpu"))
                        gpu++;
                    else if(SC.jobsStarted[i].contains("epyc"))
                        epyc++;
                    else if(SC.jobsStarted[i].contains("opteron"))
                        opteron++;
                    
                    for (int j = 0; j < SC.jobsCompleted.length; j++) {
                        if (SC.splitJobsStarted[i][1].equals(SC.splitJobsCompleted[j][1])) {
                            executeTime time = new executeTime(SC.splitJobsStarted[i][0], SC.splitJobsCompleted[j][0]);
                            durationTotal += time.getDuration();
                            time.setDuration(time.getDuration());
                            totalCount++; allocateComplete++;
                        }
                    }
                    
                    for (int j = 0; j < SC.jobsKilled.length; j++) {
                        if (SC.splitJobsStarted[i][1].equals(SC.splitJobsKilled[j][1])) {
                            allocateKill++;
                        }
                    }
                }
            }
            for (int i = 0; i < SC.jobsCompleted.length; i++) {
                Date compareDateTime = SSC.dateTimeFullFormat.parse(SC.splitJobsCompleted[i][0]);

                if ((compareDateTime.compareTo(inputStartDate) >= 0) && (compareDateTime.compareTo(inputEndDate) <= 0)) {
                    jobComplete++;
                }
            }
            for (int i = 0; i < SC.jobsKilled.length; i++) {
                Date compareDateTime = SSC.dateTimeFullFormat.parse(SC.splitJobsKilled[i][0]);

                if ((compareDateTime.compareTo(inputStartDate) >= 0) && (compareDateTime.compareTo(inputEndDate) <= 0)) {
                    jobKill++;
                }
            }
            for (int i = 0; i < SC.errorUser.length; i++) {
                Date compareDateTime = SSC.dateTimeFullFormat.parse(SC.splitErrorUser[i][0]);

                if ((compareDateTime.compareTo(inputStartDate) >= 0) && (compareDateTime.compareTo(inputEndDate) <= 0)) {
                    jobError++;
                    
                    String userError = SC.splitErrorUser[i][1];
                    if (!errorCounts.containsKey(userError)) {
                        errorCounts.put(userError, 0);
                    }
                    errorCounts.put(userError, errorCounts.get(userError) + 1);
                }
            }
            for (int i = 0; i < SC.jobsTimeExhausted.length; i++) {
                Date compareDateTime = SSC.dateTimeFullFormat.parse(SC.splitJobsTimeExhausted[i][0]);
                
                if ((compareDateTime.compareTo(inputStartDate) >= 0) && (compareDateTime.compareTo(inputEndDate) <= 0)) {
                    jobExhaust++;
                }
            }
            for (int i = 0; i < SC.jobsCleaned.length; i++) {
                Date compareDateTime = SSC.dateTimeFullFormat.parse(SC.splitJobsCleaned[i][0]);
                
                if ((compareDateTime.compareTo(inputStartDate) >= 0) && (compareDateTime.compareTo(inputEndDate) <= 0)) {
                    jobClean++;
                }
            }
        }
        Chart chart = new Chart();
        SideUMHPC SU = new SideUMHPC();
        Interface I = new Interface();
        
        if (UMHPC_Interface.setting == 2) {
            SU.printFormat(jobAllocate, jobComplete, jobKill, jobError, jobExhaust, jobClean, gpu, opteron, epyc, allocateComplete, allocateKill, durationTotal, allocateExhaust, allocateClean, totalCount, errorCounts);
            UMHPC_Interface.setting = 0;
        }
        else if (UMHPC_Interface.setting == 1) {
            I.printFormat(jobAllocate, jobComplete, jobKill, jobError, jobExhaust, jobClean, gpu, opteron, epyc, allocateComplete, allocateKill, durationTotal, allocateExhaust, allocateClean, totalCount, errorCounts);
            
            if (UMHPC_Interface.includeChart == 1) {
                chart.allJobBarChart(jobAllocate, jobComplete, jobKill, jobExhaust, jobClean, allocateComplete, allocateKill, allocateExhaust, allocateClean);
                chart.errorPieChart(errorCounts);
                stackedBarChart stacked = new stackedBarChart("", "Jobs Allocated by Partitions", gpu, epyc, opteron);
                executeTime time = new executeTime();
                chart.executionLineChart(oneHdrd, twoHdrd, threeHdrd, upperFifty, lowerFifty, twentyFour, twelve, oneHr);
                
                UMHPC_Interface.includeChart = 0;
            }
            
            UMHPC_Interface.setting = 0;
        }
        oneHdrd = 0; twoHdrd = 0; threeHdrd = 0; upperFifty = 0; lowerFifty = 0; twentyFour = 0; twelve = 0; oneHr = 0;
        jobAllocate = 0; jobComplete = 0; jobKill = 0; jobError = 0; jobExhaust = 0; jobClean = 0;
        gpu = 0; opteron = 0; epyc = 0;
        allocateComplete = 0; allocateKill = 0; allocateExhaust = 0; allocateClean = 0;
        
        durationTotal = 0; totalCount = 0;
    }
}