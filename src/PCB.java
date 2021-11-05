public class PCB {
    //int is just a placeholder as to not throw unhelpful errors
    public int cpuId;//Id of the assigned cpu
    public int BaseRegister;
    public int programCounter;//holds the address of the instruction to fetch
    int state;//record saved on interrupt
    public int codeSize;//length from //job control line
    int accounts;
    int memories;//Pagetable base, pages, pagesize
    int resources;//
    int status;//is program running, ready, blocked etc
    int statusInfo;
    int priority;//job priority from the control line

    public PCB(int id){
        cpuId = id;
        programCounter = 0;
    }

}
