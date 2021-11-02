public class CPU {
    //gets the individual bits that make up a byte.true = 1 and false = 0;
    private static boolean bitsFromByte(byte b, int bit){
        return(b &(1<<bit)) != 0 ;
    }
    static int binToDecimal(int[] array){
        int result = 0;
        int power = (int)Math.pow(2, array.length-1);
        for (int i = 0;i < array.length; i++){
            result += array[i]*power;
            power = power/2;
        }
        return result;
    }
    static int binToDecimal(int[] array,int start,int end){
        int result = 0;
        int power = (int)Math.pow(2,end-start);
        for (int i = start;i <= end; i++){
            result += array[i]*power;
            power = power/2;
        }
        return result;
    }
    private static void updateCache(){

    }
    int id;
    PCB myPcb;
    Memory myMemory;

    //Current instruction
    int[] instructionRegister = new int[32];
    // registers
    int[][] Registers = new int[16][32];//0 is accumulator, 1 is the zero register
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
        short opCode = -1;
        int Sreg1;
        int Sreg2;
        int Dreg;
        int Breg;
        int address;

        switch(IR[1] + IR[2]){

            case 0:
                //arithmetic
                opCode = (short)binToDecimal(IR,2,7);
                Sreg1 = binToDecimal(IR,8,11);
                Sreg2 = binToDecimal(IR,12,15);
                Dreg =  binToDecimal(IR,16,19);
                break;
            case 1:
                opCode = (short)binToDecimal(IR,2,7);
                //conditional branch and immediate format
                if(IR[0] == 0){
                Breg = binToDecimal(IR,8,11);
                Dreg = binToDecimal(IR,12,15);
                address = binToDecimal(IR,15,31);

                }
                //unconditional jump
                else{
                    address =  binToDecimal(IR,7,31);
                }
                break;
            case 2:
                //IO instruction
                opCode = (short)binToDecimal(IR,2,7);
                Sreg1 = binToDecimal(IR,8,11);
                Sreg2 = binToDecimal(IR,12,15);
                address = binToDecimal(IR,15,31);
                break;
        }
        switch(opCode){
            case 0:
                //RD I/O Reads content of I/P buffer into an accumulator or a register
                break;
            case 1:
                //WR I/O Writes the content of accumulator into O/P buffer
                break;
            case 2:
                //ST I Stores content of a reg.  into an address
                break;
            case 3:
                //LW I Loads the content of an address into a reg.
                break;
            case 4://MOV R Transfers the content of one register into another

                break;
            case 5://ADD R Adds content of two S-regs into D-reg

                break;
            case 6://SUB R Subtracts content of two S-regs into D-reg
                break;
            case 7://MUL R Multiplies content of two S-regs into D-reg
                break;
            case 8://DIV R Multiplies content of two S-regs into D-reg
                break;
            case 9://AND R Logical AND of two S-regs into D-reg
                break;
            case 10://OR R Logical OR of two S-regs into D-reg
                break;
            case 11://MOVI I Transfers address/data directly into a register
                break;
            case 12://ADDI I Adds a data value directly to the content of a register
                break;
            case 13://MULI I Multiplies a data value directly with the content of a register
                break;
            case 14://DIVI I Divides a data directly into the content of a register
                break;
            case 15://LDI I Loads a data/address directly to the content of a register
                break;
            case 16://SLT R Sets the D-reg to 1 if  first S-reg is less than the B-reg; 0 otherwise
                break;
            case 17://SLTI I Sets the D-reg to 1 if  first S-reg is less than a data; 0 otherwise
                break;
            case 18://HLT J Logical end of program
                break;
            case 19://NOP - Does nothing and moves to next instruction
                break;
            case 20://JMP J Jumps to a specified location
                break;
            case 21://BEQ I Branches to an address when content of B-reg = D-reg
                break;
            case 22://BNE I Branches to an address when content of B-reg <> D-reg
                break;
            case 23://BEZ I Branches to an address when content of B-reg = 0
                break;
            case 24://BNZ I Branches to an address when content of B-reg <> 0
                break;
            case 25://BGZ I Branches to an address when content of B-reg > 0
                break;
            case 26://BLZ I Branches to an address when content of B-reg < 0
                break;
            default:// Invalid Operation
                System.out.println("Opcode Not Recognized");
                break;
        }
    }
    //constructor
    public CPU(int cpuId){
        id = cpuId;
    }


}
