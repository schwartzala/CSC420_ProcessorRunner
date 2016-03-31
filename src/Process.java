/**
 * Created by AJ on 3/23/2016.
 */

public class Process {

    long id;
    double arrivalTime;
    double serviceTime;
    double waitTime;
    double endTime;
    double accumulatedTime;
    double turnaroundTime;
    
    Process(long id, double arrivalTime, double serviceTime)
    {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(double serviceTime) {
        this.serviceTime = serviceTime;
    }

    public double getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(double waitTime) {
        this.waitTime = waitTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }

    public double getAccumulatedTime() {
        return accumulatedTime;
    }

    public void setAccumulatedTime(double accumulatedTime) {
        this.accumulatedTime = accumulatedTime;
    }

    public double getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setTurnaroundTime(double turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public String toString() { return "Process " + id + ": " + getWaitTime() + " " + getEndTime(); }

}
