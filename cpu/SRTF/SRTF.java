import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

public class SRTF{
    public static void main(String[] args) throws Exception {
        
        ArrayList<Task> tasks = new ArrayList<>();
        
        if (args.length == 1) // file input
        {
            try{
                Scanner fileScanner = new Scanner(new File(args[0]));
                int index = 1;
                
                while (fileScanner.hasNextInt()) {
                    int arrival = fileScanner.nextInt();
                    int burst = fileScanner.nextInt();
                    tasks.add(new Task(index++, arrival, burst));
                }
            }
            //catch exception if the file does not exist
            catch(FileNotFoundException e){ 
                System.out.println("Input file does not exist.");
                System.exit(-1);
            }
        } 
        else //randomly generated input if no file is provided
        {
            Random random = new Random();
             //set initial arrival time 
            int arrival = 0;
            for (int i = 1; i <= 10; i++) {
                //create burst time between 2 and 10
                int burst = 2 + random.nextInt(9);
                tasks.add(new Task(i, arrival, burst));
                //update arrival time with interval between 1 and 3
                arrival += 1 + random.nextInt(3); 
            }
            //for testing purpose, print out the info about all tasks created
            // for (Task task : tasks){
            //     System.out.println( task.index + "\t" + task.arrival + "\t" + task.burst);
            // }
        }

        //initialize needed variables
        int currentTime = 0, completedTasks = 0, waitingTime = 0;
        //sort Tasks from the least remainingburst to the most
        PriorityQueue<Task> readyQueue = new PriorityQueue<>(Comparator.comparingInt(t -> t.remainingBurst));
        int totalTasksCount = tasks.size();
        Task lastTask = null;

        
        while (completedTasks < totalTasksCount) {
            while (!tasks.isEmpty() && tasks.get(0).arrival <= currentTime) {
                readyQueue.add(tasks.remove(0));
            }

            if (!readyQueue.isEmpty()) {
                //take the first task in the ready queue
                Task currentTask = readyQueue.poll();
                //check if the new currenttask isn't matching with the previous one to avoid printing out the same task
                if (lastTask == null || lastTask.index != currentTask.index){
                    System.out.print(currentTime + " - P" + currentTask.index + " - ");
                }  
                //execute the task for 1 millisecond
                currentTime++; 
                //decrease remaining burst of currenttask for 1 millisecond
                currentTask.remainingBurst--;
                lastTask = currentTask;       

                if (currentTask.remainingBurst > 0) 
                {
                    //add the task back to ready que if burst is still remained
                    readyQueue.add(currentTask);
                } 
                else {
                    completedTasks++;
                    //update waiting time = turnaround time - burst  
                    waitingTime += currentTime - currentTask.arrival - currentTask.burst;
                }
            } 
            else {
                //if no task is ready, just increment the time
                currentTime++; 
            }
        }

        System.out.println(currentTime);
        double averageWaitingTime = (double) waitingTime / totalTasksCount;

        //print the final waiting time and Gantt chart
        System.out.println("waiting time: " +" = " + averageWaitingTime + "milliseconds");
    }
}
