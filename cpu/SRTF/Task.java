public class Task{
    public int index;
    public int arrival;
    public int burst;
    public int remainingBurst;

    public Task(int index, int arrival, int burst) {
        this.index = index;
        this.arrival = arrival;
        this.burst = burst;
        this.remainingBurst = burst;

    }
}