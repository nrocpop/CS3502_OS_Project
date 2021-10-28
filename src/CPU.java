public class CPU {
    //gets the individual bits that make up a byte.true = 1 and false = 0;
    private static boolean bitsFromByte(byte b, int bit){
        return(b &(1<<bit)) != 0 ;
    }
    private static void updateCache(){

    }
    int id;
    PCB myPcb;
    Memory myMemory;

    //Current instruction
    int[] instructionRegister = new int[32];
    // registers
    int[] reg_0 = new int[32];//Accumulator
    int[] reg_1 = new int[32];//zero register
    int[] reg_2 = new int[32];
    int[] reg_3 = new int[32];
    int[] reg_4 = new int[32];
    int[] reg_5 = new int[32];
    int[] reg_6 = new int[32];
    int[] reg_7 = new int[32];
    int[] reg_8 = new int[32];
    int[] reg_9 = new int[32];
    int[] reg_10 = new int[32];
    int[] reg_11 = new int[32];
    int[] reg_12 = new int[32];
    int[] reg_13 = new int[32];
    int[] reg_14 = new int[32];
    int[] reg_15 = new int[32];
    //buffers
    byte[] inputBuffer;
    byte[] outputBuffer;
    byte[] tempBuffer;
    //cache for next four encoded instructions
    byte[] cache = new byte[16];


    //Retrieves next instruction from memory and places it in the cache
    public void Fetch(int address, byte[] fetchTo, int toAddress){
        for (int i = 0; i < 4 ; i++)
        fetchTo[toAddress] = myMemory.load(address+i);
    }
    //decodes the bytes of an instruction into binary
    public void Decode(byte[] instruction){
        int count = 0;
        for(int i = 0; i <= instruction.length-1;i++){
            for (int j = 7; j >= 0; j--) {
                if(bitsFromByte(instruction[i], j)) {
                    instructionRegister[count] = 1;
                    System.out.print(1);
                } else {
                    instructionRegister[count] = 0;
                    System.out.print(0);
                }
                count++;
            }
        }
    }

    //execute the instruction on the IR
    public void Execute(int[] IR,PCB program){
        switch(IR[1] + IR[2]){
            case 0:
                //arithmetic

                break;
            case 1:
                //conditional branch and immediate format
                //unconditional jump
                break;
            case 2:
                //IO instruction
                break;


        }
    }
    //constructor
    public CPU(int cpuId){
        id = cpuId;
        myPcb = new PCB(cpuId);
    }


}
