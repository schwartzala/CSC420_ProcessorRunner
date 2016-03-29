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
    static int currentIndex = 0;
    static boolean preempted = false;

    public static void main(String[] args) {
        processList = new ArrayList<>();
        processList.add(new Process(0, 0, 0));
        for (int i = 1; i <= TOTAL_JOBS; i++) {
            processList.add(new Process(i, arrivalTime(i), serviceTime()));
        }
        processList.remove(0);

        log = new ProcessLogger();

        while (log.size() < TOTAL_JOBS) {
            if (current().getArrivalTime() > clockTime) {
                if (currentIndex == 0) {
                    clockTime++;
                } else {
                    currentIndex = 0;
                }
            } else {
                preempted = false;
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
                        log.add(processList.remove(currentIndex));
                        preempted = true;
                        break;
                    }
                }
                clockTime += CS;
                if (!preempted) {
                    if (currentIndex >= processList.size() - 1) {
                        currentIndex = 0;
                    } else {
                        currentIndex++;
                    }
                }
            }
        }
        log.sort();
        System.out.println(log.toString());
    }

    public static Process current() {
        if (!processList.isEmpty()) {
            return processList.get(currentIndex);
        }
        return null;
    }

    public static double arrivalTime(int id) {
        double intertime = interArrivalTime();
        System.out.println(intertime);
        return Math.ceil(processList.get(id - 1).getArrivalTime() + intertime);
    }

    public static double interArrivalTime() {
        return -0.2 * Math.log(1 - Math.random());
    }

    public static double serviceTime() {
        return Math.ceil(2 + (5 - 2) * Math.random());
    }

}


