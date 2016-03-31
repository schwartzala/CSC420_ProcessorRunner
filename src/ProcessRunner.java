/**
 * Created by AJ on 3/23/2016.
 */

import java.util.ArrayList;

public class ProcessRunner {

    final static int TOTAL_JOBS = 100;
    final static int QUANTUM = 1;
    final static int CS = 0;

    static ArrayList<Process> processList;
    static ProcessLogger log;

    static int clockTime = 0;
    static boolean finished = false;

    static int jobCount = 0;
    static Process nextJob = new Process(0,0,0);

    public static void main(String[] args) {
        processList = new ArrayList<>();
        log = new ProcessLogger();
        nextJob = new Process(nextJob.getId() + 1,
                nextJob.getArrivalTime() + interArrivalTime(),
                serviceTime()
        );
        jobCount++;

        // TOTAL_JOBS loop begins.

        while (log.size() < TOTAL_JOBS) {
            while (nextJob.getArrivalTime() <= clockTime + 1) {
                processList.add(nextJob);
                jobCount++;
                nextJob = new Process(nextJob.getId() + 1,
                        arrivalTime(nextJob.getArrivalTime()),
                        serviceTime()
                );
            }
            if (processList.isEmpty() || clockTime < current().getArrivalTime()) { clockTime++; }
            else {
                finished = false;
                for (int i = 0; i < QUANTUM; i++) {
                    current().setAccumulatedTime(current().getAccumulatedTime() + 1);
                    clockTime++;
                    if (current().getAccumulatedTime() >= current().getServiceTime()) {
                        current().setEndTime(clockTime);
                        current().setTurnaroundTime(clockTime - current().getArrivalTime());
                        current().setWaitTime(
                                current().getEndTime() -
                                        current().getServiceTime() -
                                        current().arrivalTime
                        );
                        finished = true;
                        log.add(processList.remove(0));
                        break;
                    }
                }
                clockTime += CS;
                if (!finished) {
                    processList.add(processList.remove(0));
                }
            }
        }

        // TOTAL_JOBS loop ends.

        log.sort();
        System.out.println(log.toString());
        System.out.println(jobCount + " Total Jobs Generated");
    }

    public static Process current() {
        return processList.get(0);
    }

    public static double arrivalTime(double previousArrivalTime) {
        return previousArrivalTime + interArrivalTime();
    }

    public static double interArrivalTime() {
        return -0.2 * Math.log(1 - Math.random());
    }

    public static double serviceTime() {
        return Math.ceil(2 + (5 - 2) * Math.random());
    }

}