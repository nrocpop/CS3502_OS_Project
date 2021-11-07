//10/31/21
import java.util.Scanner;

public class Dispatcher {
    String state = "";
    int CPUId2 = 0;
    int pc = 0; //Program counter of process
    int pc2 = 0; //CPU process counter placeholder name
    int register = 0;
    int register2 = 0; //CPU register name is just a placeholder
    public int cpuId;

    public Dispatcher()
    {
        cpuId = 0;
        pc = 0;
        register = 0;
        state = "ready";
    }
    public Dispatcher(int cpuId, int pc, int register, String state)
    {
        this.cpuId = cpuId;
        this.pc = pc;
        this.register = register;
        this.state = state;
    }
    void RegisterSet()
    {
        //set everything up in the CPU
        pc2 = pc;
        register2 = register;
        CPUId2 = cpuId;

    }
}
