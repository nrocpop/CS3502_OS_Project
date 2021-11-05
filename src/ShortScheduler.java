import java.util.Queue;

//11/4/21
public class ShortScheduler {
    int readyProcess; //the process with the ready state
    int ramSpace; //placeholder for RAM
    void selection(Queue<Integer> q1) // select the first process with the ready state
    {
        int m;
        for (m = 0; m < q1.size(); m++)
        {
            readyProcess = q1.remove(); //take the first ready process out of the queue
            ramSpace = readyProcess; // place it into the RAM
        }
    }
}
