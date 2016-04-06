import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by AJ on 3/24/2016.
 */
public class ProcessLogger extends ArrayList<Process> {

    double totalServiceTime;
    double totalWaitTime;
    double totalTurnaroundTime;

    ProcessLogger() {
        super();
        totalServiceTime = 0;
        totalWaitTime = 0;
        totalTurnaroundTime = 0;
    }

    @Override
    public boolean add(Process process) {
        addTotalServiceTime(process.getServiceTime());
        addTotalWaitTime(process.getWaitTime());
        addTotalTurnaroundTime(process.getTurnaroundTime());
        return super.add(process);
    }

    public void sort() {
        this.sort(new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                if (o1.getId() < o2.getId()) {
                    return -1;
                } else if (o1.getId() > o2.getId()) {
                    return 1;
                }
                return 0;
            }
        });
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "EMPTY PROCESS LOGGER";
        }
        String list = new String();
        list += "PROCESS LOGGER\n" +
                "\tPROCESS_LIST\n";
        for (int i = 0; i < this.size() && i < 20; i++) {
            list += "\t\tPROCESS " + this.get(i).getId() + "\n" +
                    "\t\t\tARRIVAL TIME: " + this.get(i).getArrivalTime() + "\n" +
                    "\t\t\tSERVICE TIME: " + this.get(i).getServiceTime() + "\n" +
                    "\t\t\tEND TIME: " + this.get(i).getEndTime() + "\n" +
                    "\t\t\tTURNAROUND TIME: " + this.get(i).getTurnaroundTime() + "\n" +
                    "\t\t\tWAIT TIME: " + this.get(i).getWaitTime() + "\n";
        }
        list += "\tTOTAL_PROCESSES_COMPLETED: " + this.size() + "\n" +
                "\tAVERAGE_SERVICE_TIME: " + this.getAverageServiceTime() + "\n" +
                "\tAVERAGE_TURNAROUND_TIME: " + this.getAverageTurnaroundTime() + "\n" +
                "\tAVERAGE_WAIT_TIME: " + this.getAverageWaitTime() + "\n" +
                "END LOGGER\n";
        return list;
    }

    public void addTotalServiceTime(double time) { this.totalServiceTime += time; }

    public void addTotalWaitTime(double time) {
        this.totalWaitTime += time;
    }

    public void addTotalTurnaroundTime(double time) {
        this.totalTurnaroundTime += time;
    }

    public double getAverageServiceTime() {
        if (!this.isEmpty()) { return this.totalServiceTime / this.size(); }
        else { return 0; }
    }

    public double getAverageWaitTime() {
        if (!this.isEmpty()) { return this.totalWaitTime / this.size(); }
        else { return 0; }
    }

    public double getAverageTurnaroundTime() {
        if (!this.isEmpty()) { return this.totalTurnaroundTime / this.size(); }
        else { return 0; }
    }
}
