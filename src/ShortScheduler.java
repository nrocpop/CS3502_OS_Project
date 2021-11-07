import java.util.Queue;

//11/4/21
public class ShortScheduler {
    int readyProcess; //the process with the ready state
    int ramSpace; //placeholder for RAM          
    String processState;
    void selection(Queue<Integer> q1) // select the first process with the ready state
    {
            readyProcess = q1.remove(); //take the first ready process out of the queue
            ramSpace = readyProcess; // place it into the RAM
            processState = "running";
    }
   }