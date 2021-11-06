public class PCB {
    //int is just a placeholder as to not throw unhelpful errors
    public int cpuId;//Id of the assigned cpu
    public int BaseRegister;
    public int programCounter;//holds the address of the instruction to fetch
    public int programID;
    public int state;//record saved on interrupt
    public int codeSize;//length from //job control line
    public int accounts;
    public int memories;//Pagetable base, pages, pagesize
    public int resources;//
    public int status;//is program running, ready, blocked etc
    public int statusInfo;
    public int priority;//job priority from the control line
    public int inputBufferSize;
    public int outputBufferSize;
    public int tempBuffersSize;
    public byte[] data;
    public PCB(){
        data = new byte[256];
    }
    public void store(byte value, int address){
        data[address] = value;
    }


}
