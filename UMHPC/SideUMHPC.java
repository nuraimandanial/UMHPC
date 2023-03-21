package Assignment.UMHPC;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author kinderBono
 */
public class SideUMHPC extends UMHPC{
    
    public void printFormat(int jobAllocate, int jobComplete, int jobKill, int jobError,
                            int jobExhaust, int jobClean, int gpu, int opteron, int epyc,
                            int allocateComplete, int allocateKill, long durationTotal, 
                            int allocateExhaust, int allocateClean, int totalCount, Map<String, Integer> errorCounts){
        System.out.println();
        System.out.printf("---------------------------------------------------------------\n");
        if (dateControl == 1) {
            System.out.println("Within " + SSC.month[Integer.parseInt(startDate.split("-")[1])] + " " + startDate.split("-")[0]+ " :-");
        } 
        else if (startDate.substring(0, 10).equals(endDate.substring(0, 10))) {
            System.out.println("Within " + startDate.split("T")[0].split("-")[2] + " " +
                    SSC.month[Integer.parseInt(startDate.split("-")[1])] + " " + startDate.split("-")[0]+ " :-");
        }
        else {
            System.out.println("From " + startDate.substring(0, 10) + " to " + endDate.substring(0, 10) + " :-");
        }
        System.out.printf("\n---------------------------------------------------------------\n"
                + "\nTotal jobs allocated :  %5d"
                + "\nTotal jobs completed :  %5d, Within allocated time range : %5d"
                + "\nTotal jobs killed    :  %5d, Within allocated time range : %5d"
                + "\nTotal jobs running   :  %5d"
                + "\n---------------------------------------------------------------"
                + "\nTotal jobs exhausted :  %5d, Within allocated time range : %5d"
                + "\nTotal jobs cleaned   :  %5d, Within allocated time range : %5d"
                + "\nTotal error occured  :  %5d\n",
                            jobAllocate, jobComplete, allocateComplete, jobKill, allocateKill, 
                            (jobAllocate - (allocateComplete + allocateKill)),
                            jobExhaust, allocateExhaust, jobClean, allocateClean, jobError);
        
        int columns = 0;
        for (String user : errorCounts.keySet()) {
            System.out.printf("%16s - %3d \t", user, errorCounts.get(user));
            columns++;
            if (columns % 3 == 0) {
                System.out.println();
            }
        }
        System.out.println();
        
        executeTime time = new executeTime();
        System.out.printf("---------------------------------------------------------------"
                + "\nJobs allocated by partition :"
                + "\nGPU - %-5d, EPYC - %-5d, Opteron - %-5d"
                + "\n---------------------------------------------------------------"
                + "\nAverage execution time : %-20s"
                + "\n---------------------------------------------------------------",
                            gpu, epyc, opteron, time.getAverageDuration(durationTotal, totalCount));
    }
}

class executeTime{  
    private Date dateAllocate, dateComplete;
    private long duration;
    
    private String[] data = new String[6];
    
    private long durationSec, durationMin = 0, durationHr = 0, durationDay = 0, durationMonth = 0;
                        
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

    public executeTime() {
    }
    
    public executeTime(String allocate, String complete) throws ParseException{
        this.dateAllocate = sdf.parse(allocate);
        this.dateComplete = sdf.parse(complete);
        getDuration(dateAllocate, dateComplete);
    }
    
    public String getAverageDuration(long duration, int totalCount){
        long average = duration / (long) totalCount;
        
        long averageMilSec, averageSec, avgMin, avgHr;
        
        averageMilSec = average;
        long total = 0;
        while (averageMilSec >= 1000) {            
            averageMilSec -= 1000;
            total += 1;
        }
        avgHr = 0;
        avgMin = 0;
        while (total >= 60) {            
            total -= 60;
            avgMin += 1;
            if (avgMin >= 60) {
                avgHr += 1;
            }
            if (avgMin == 60) {
                avgMin = 0;
            }
        }
        averageSec = total;
        
        if (avgHr != 0) {
            return avgHr + " hr(s) " + avgMin + " min(s) " + averageSec + " sec(s) " + averageMilSec + " msec(s)";
        }
        else if (avgHr == 0) {
            if (avgMin != 0) {
                return avgMin + " min(s) " + averageSec + " sec(s) " + averageMilSec + " msec(s)";
            }
            else if (avgMin == 0) {
                if (avgMin != 0) {
                    return averageSec + " sec(s) " + averageMilSec + " msec(s)";
                }
            }
        }
        return "NaN";
    }

    public long getDuration() {
        return this.duration;
    }
    
    public void setDuration(long durationMillis){
        if ((durationMillis / 3600000) >= 300) {
            UMHPC.threeHdrd++;
        }
        else if ((durationMillis / 3600000) >= 200) {
            UMHPC.twoHdrd++;
        }
        else if ((durationMillis / 3600000) >= 100) {
            UMHPC.oneHdrd++;
        }
        else if ((durationMillis / 3600000) >= 50) {
            UMHPC.upperFifty++;
        }
        else if ((durationMillis / 3600000) >= 24) {
            UMHPC.lowerFifty++;
        }
        else if ((durationMillis / 3600000) >= 12) {
            UMHPC.twentyFour++;
        }
        else if ((durationMillis / 3600000) > 1) {
            UMHPC.twelve++;
        }
        else
            UMHPC.oneHr++;
    }
    
    private void getDuration(Date dateAllocate, Date dateComplete) {
        duration = dateComplete.getTime() - dateAllocate.getTime();
        this.durationSec = (duration / 1000) % 60;
        this.durationMin = (duration / (1000 * 60)) % 60;
        this.durationHr = (duration / (1000 * 60 * 60)) % 24;
        this.durationDay = (duration / (1000 * 60 * 60 * 24)) % 365;
        this.durationMonth = (duration / (1000 * 60 * 60 * 24)) / 365;
    }
    
    private void getDuration(long duration) {
        this.durationSec = (duration / 1000) % 60;
        this.durationMin = (duration / (1000 * 60)) % 60;
        this.durationHr = (duration / (1000 * 60 * 60)) % 24;
        this.durationDay = (duration / (1000 * 60 * 60 * 24)) % 365;
        this.durationMonth = (duration / (1000 * 60 * 60 * 24)) / 365;
    }
    
    private void durationToString(){
        this.data[1] = this.durationMonth + " month(s)";
        this.data[2] = this.durationDay + " day(s)";
        this.data[3] = this.durationHr + " hour(s)";
        this.data[4] = this.durationMin + " min(s)";
        this.data[5] = this.durationSec + " sec(s)";
    }
    
    @Override
    public String toString(){
        durationToString();
        if (!data[1].equals("0 month(s)")) {
            return data[1] + " " + data[2] + " " + data[3] + " " + data[4] + " " + data[5];
        }
        else if (data[1].equals("0 month(s)")) {
            if (!data[2].equals("0 day(s)")) {
                return data[2] + " " + data[3] + " " + data[4] + " " + data[5];
            }
            else if (data[2].equals("0 day(s)")) {
                if (!data[3].equals("0 hour(s)")) {
                    return data[3] + " " + data[4] + " " + data[5];
                }
                else if (data[3].equals("0 hour(s)")) {
                    if (!data[4].equals("0 min(s)")) {
                        return data[4] + " " + data[5];
                    }
                    else if (data[4].equals("0 min(s)")) {
                        return data[5];
                    }
                }
            }
        }
        return data[5];
    }
    
    public String toString(long durationTotal, int totalCount){
        if (!data[1].equals("0 month(s)")) {
            return data[1] + " " + data[2] + " " + data[3] + " " + data[4] + " " + data[5];
        }
        else if (data[1].equals("0 month(s)")) {
            if (!data[2].equals("0 day(s)")) {
                return data[2] + " " + data[3] + " " + data[4] + " " + data[5];
            }
            else if (data[2].equals("0 day(s)")) {
                if (!data[3].equals("0 hour(s)")) {
                    return data[3] + " " + data[4] + " " + data[5];
                }
                else if (data[3].equals("0 hour(s)")) {
                    if (!data[4].equals("0 min(s)")) {
                        return data[4] + " " + data[5];
                    }
                    else if (data[4].equals("0 min(s)")) {
                        return data[5];
                    }
                }
            }
        }
        return data[5];
    }
}


