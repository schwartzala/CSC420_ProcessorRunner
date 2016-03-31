/**
 * Created by AJ on 3/23/2016.
 */

import java.util.ArrayList;
import java.util.Comparator;

public class ProcessRunner {

    final static int TOTAL_JOBS = 100;
    final static int QUANTUM = 1;
    final static int CS = 0;

    static ArrayList<Process> processList;
    static ProcessLogger log;

    static int clockTime = 0;
    static boolean finished = false;

    static Process nextJob = new Process(0,0,0);

    public static void main(String[] args) {
        processList = new ArrayList<>();
        log = new ProcessLogger();
        nextJob = new Process(nextJob.getId() + 1,
                nextJob.getArrivalTime() + interArrivalTime(),
                serviceTime()
        );

        while (log.size() < TOTAL_JOBS) {
            while (nextJob.getArrivalTime() <= clockTime + 1) {
                processList.add(nextJob);
                nextJob = new Process(nextJob.getId() + 1,
                        nextJob.getArrivalTime() + interArrivalTime(),
                        serviceTime()
                );
            }
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
                if (!finished) { processList.add(processList.remove(0)); }
            }


        // TOTAL_JOBS reached.

        log.sort();
        System.out.println(log.toString());
    }

    public static Process current() {
        if (!processList.isEmpty()) {
            return processList.get(0);
        }
        return null;
    }

    public static double arrivalTime(int id) {
        double intertime = interArrivalTime();
        return processList.get(id - 1).getArrivalTime() + intertime;
    }

    public static double interArrivalTime() {
        return -0.2 * Math.log(1 - Math.random());
    }

    public static double serviceTime() {
        return Math.ceil(2 + (5 - 2) * Math.random());
    }

    public static Process makeNewProcess(int i) {
        return new Process(i, arrivalTime(i), serviceTime());
    }

}


