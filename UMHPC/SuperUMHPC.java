package Assignment.UMHPC;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * @author kinderBono
 */
public class SuperUMHPC {
    private final String filename = "extracted_log.txt";
    
    public final String[] allocateText = {"[ ]", "JobId=", "NodeList=", "#CPUs=", "Partition="},
                          startText = {"[ ]", "JobId=", "", ""},
                          completeText = {"[ ]", "JobId=", "-"},
                          killText = {"[ ]", "JobId=", "-"},
                          errorText = {"[ ]", "\\d{3}", "account=", "user=", "Partition=", "-"},
                          exhaustText = {"[ ]", "JobId="},
                          cleanText = {"[ ]", "JobId=", "-"};
    
    private Stream<String> jobsAllocate, jobsStart, jobsComplete, jobsKill, errorFromUser, jobsTimeExhaust, jobsClean;
    
    public String[] jobsAllocated; public String[][] splitJobsAllocated;
    public String[] jobsStarted; public String[][] splitJobsStarted;
    public String[] jobsCompleted; public String[][] splitJobsCompleted;
    public String[] jobsKilled; public String[][] splitJobsKilled;
    public String[] errorUser;  public String[][] splitErrorUser;
    public String[] jobsTimeExhausted;  public String[][] splitJobsTimeExhausted;
    public String[] jobsCleaned;  public String[][] splitJobsCleaned;
           
    public int totalGpu, totalOpteron, totalEpyc;
    
    public SuperUMHPC() {
        SuperClass();
    }
    
    private void SuperClass(){
        jobsAllocate();
        jobsStart();
        jobsComplete();
        jobsKill();
        errorUser();
        jobsExhausted();
        jobsClean();
    }
    
    public void jobsAllocate(){
        try {
            jobsAllocate = Files.lines(Path.of(filename)).filter(line -> line.contains(" Allocate"));
            
            jobsAllocated = this.jobsAllocate.toArray(String[]::new);
            
            splitJobsAllocated = new String[this.jobsAllocated.length][];
            for (int i = 0; i < jobsAllocated.length; i++) {
                String[] split = this.jobsAllocated[i].replace("sched: Allocate ", "").replace("[", "").replace("]", "").split(" ");
                
                if (Arrays.toString(split).contains("gpu")) {
                    totalGpu++;
                }
                else if (Arrays.toString(split).contains("epyc")) {
                    totalEpyc++;
                }
                else if (Arrays.toString(split).contains("opteron")) {
                    totalOpteron++;
                }
                
                splitJobsAllocated[i] = new String[split.length];
                for (int j = 0; j < split.length; j++) {
                    splitJobsAllocated[i][j] = split[j].replace(this.allocateText[j], "");
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(SuperUMHPC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void jobsStart(){
        try {
            jobsStart = Files.lines(Path.of(filename)).filter(line -> line.contains(": _start_job: Started"));
            
            jobsStarted = this.jobsStart.toArray(String[]::new);
            
            splitJobsStarted = new String[this.jobsStarted.length][];
            for (int i = 0; i < jobsStarted.length; i++) {
                String[] split = this.jobsStarted[i].replace("sched/backfill: _start_job: Started ", "").replace(" in", "")
                                .replace(" on", "").split(" ");
                
                if (Arrays.toString(split).contains("gpu")) {
                    totalGpu++;
                }
                else if (Arrays.toString(split).contains("epyc")) {
                    totalEpyc++;
                }
                else if (Arrays.toString(split).contains("opteron")) {
                    totalOpteron++;
                }
                
                splitJobsStarted[i] = new String[5];
                splitJobsStarted[i][0] = split[0].replace("[", "").replace("]", "");
                splitJobsStarted[i][1] = split[1].replace(this.startText[1], "");
                splitJobsStarted[i][2] = split[3].replace(this.startText[3], "");
                splitJobsStarted[i][3] = "none";
                splitJobsStarted[i][4] = split[2].replace(this.startText[2], "");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(SuperUMHPC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void jobsComplete(){
        try {
            jobsComplete = Files.lines(Path.of(filename)).filter(line -> line.contains("done"));
            
            jobsCompleted = this.jobsComplete.toArray(String[]::new);
            
            splitJobsCompleted = new String[this.jobsCompleted.length][];
            for (int i = 0; i < jobsCompleted.length; i++) {
                String[] split = this.jobsCompleted[i].replace("_job_complete: ", "").replace("[", "").replace("]", "").split(" ");
                splitJobsCompleted[i] = new String[split.length];
                for (int j = 0; j < split.length; j++) {
                    splitJobsCompleted[i][j] = split[j].replace(this.completeText[j], "");
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(SuperUMHPC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void jobsKill(){
        try {
            jobsKill = Files.lines(Path.of(filename)).filter(line -> line.contains(": REQUEST_KILL_JOB"));
            
            jobsKilled = this.jobsKill.toArray(String[]::new);
            
            splitJobsKilled = new String[this.jobsKilled.length][];
            for (int i = 0; i < jobsKilled.length; i++) {
                String[] split = this.jobsKilled[i].replace("_slurm_rpc_kill_job: REQUEST_KILL_JOB ", "")
                                .replace("uid ", "").replace("[", "").replace("]", "").split(" ");
                splitJobsKilled[i] = new String[split.length];
                for (int j = 0; j < split.length; j++) {
                    splitJobsKilled[i][j] = split[j].replace(this.killText[j], "");
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(SuperUMHPC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void errorUser(){
        try {
            errorFromUser = Files.lines(Path.of(filename)).filter(line -> line.contains("user="));
            
            errorUser = this.errorFromUser.toArray(String[]::new);
            
            splitErrorUser = new String[this.errorUser.length][];
            for (int i = 0; i < errorUser.length; i++) {
                String[] split = this.errorUser[i]
                                .replace("error: This association ", "").replace(" does not have access to qos ", "")
                                .replace("normal", "").replace("long", "").replace("'", "").replace("(", " ").replace(")", "")
                                .replace("NORMAL", "").replace(",", "").replace("[", "").replace("]", "").split(" ");
                splitErrorUser[i] = new String[2];
                splitErrorUser[i][0] = split[0].replaceAll(errorText[0], "");
                splitErrorUser[i][1] = split[3].replaceAll(errorText[3], "");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(SuperUMHPC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void jobsExhausted(){
        try {
            jobsTimeExhaust = Files.lines(Path.of(filename)).filter(line -> line.contains("] Time limit"));
            
            jobsTimeExhausted = this.jobsTimeExhaust.toArray(String[]::new);
            
            splitJobsTimeExhausted = new String[this.jobsTimeExhausted.length][];
            for (int i = 0; i < jobsTimeExhausted.length; i++) {
                String[] split = this.jobsTimeExhausted[i].replace("Time limit exhausted for ", "")
                                .replace("[", "").replace("]", "").split(" ");
                splitJobsTimeExhausted[i] = new String[split.length];
                for (int j = 0; j < split.length; j++) {
                    splitJobsTimeExhausted[i][j] = split[j].replace(exhaustText[j], "");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(SuperUMHPC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void jobsClean(){
        try {
            jobsClean = Files.lines(Path.of(filename)).filter(line -> line.contains("] cleanup"));
            
            jobsCleaned = this.jobsClean.toArray(String[]::new);
            
            splitJobsCleaned = new String[this.jobsCleaned.length][];
            for (int i = 0; i < jobsCleaned.length; i++) {
                String[] split = this.jobsCleaned[i].replace("cleanup_completing: ", "")
                                .replace("[", "").replace("]", "").replace("completion process took ", "")
                                .replace("seconds", "").split(" ");
                splitJobsCleaned[i] = new String[split.length];
                for (int j = 0; j < split.length; j++) {
                    splitJobsCleaned[i][j] = split[j].replace(cleanText[j], "");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(SuperUMHPC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class SubSuperUMHPC extends SuperUMHPC{
    SuperUMHPC SC = new SuperUMHPC();
    
    String[] month = {"0", "January", "February", "March", "April", "May", "June", "July", 
                        "August", "September", "October", "November", "December"};
    
    public SimpleDateFormat dateTimeFullFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
    public SimpleDateFormat dateTimeYearMonth = new SimpleDateFormat("yyyy-MM");
    
    public final String fixedStartTime = "T00:00:00.000",
                        fixedEndTime   = "T23:59:59.999";
}
