import java.util.Queue;
 class LongScheduler {
    String processState = "";
    boolean IO; //placeholder boolean for IO checks
    int process;
    Queue<Integer>ready;  //placeholder ready queue
    void StateChange() {  //change the state of the process
        switch (processState) {
            case ("running"):
            {
                if(IO == true) {
                    processState = "waiting"; // for a process waiting on IO operations
                } else {
                    processState = "ready";   //why would it go to ready?
                }
                break;
            }
            case ("waiting"):
            {
                if(IO == false) {
                    processState = "ready"; //completed the interruption or IO operation
                }
            }
            default:
            {
                processState = "terminate";
            }
        }
    }
}
