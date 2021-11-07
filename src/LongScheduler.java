import java.util.Queue;
 class LongScheduler {
    public String processState = "";
    boolean IO; //placeholder boolean for IO checks
    int process;
    Queue<Integer>ready;    //placeholder ready process queue
     Queue<Integer>wait;    //placeholder waiting process queue
     Queue<Integer>start;    //placeholder new process queue

    void StateChange() {  //change the state of the process
        switch (processState) {
            //add() adds the process to the specific queue based on the process current state
            case ("running"):
            {
                if(IO) {
                    processState = "waiting"; // for a process waiting on IO operations
                    wait.add(process);
                } else {
                    processState = "ready";   //why would it go to ready? Interrupts
                    ready.add(process);
                }
                break;
            }
            case ("waiting"):
            {
                if(!IO) {
                    processState = "ready"; //completed the interruption or IO operation
                    ready.add(process);
                }
                break;
            }
            case ("new"):
            {
                start.add(process);   // for the processes that just entered the RAM
                break;
            }
            default:
            {
                processState = "terminate";
                break;
            }
        }
    }
}
