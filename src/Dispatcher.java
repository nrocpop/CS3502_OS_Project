//10/31/21
import java.util.Scanner;

public class Dispatcher {
    String state = "";
    int processNumber = 0;
    int pc = 0; //Program counter of process
    int pc2 = 0; //CPU process counter placeholder name
    String register = "";
    String register2 = ""; //CPU register name is just a placeholder
    Scanner sn = new Scanner("pcb.txt"); //use a scanner to get the parameters from the PCB

    void RegisterSet()
    {
        while(sn.hasNext()) { //scan each part of the process in the PCB
            state = sn.next();
            processNumber = sn.nextInt();
            pc = sn.nextInt();
            register = sn.next();

            if(register.equals(register2)) //check to see if the PCB data matches a CPU register
            {
                pc2 = pc; //set everything up in the CPU
                register2 = register;
                processNumber = 0; // that process is the one about to run
            }

        }

    }
}
