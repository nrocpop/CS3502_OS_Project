public class CPU {
    //gets the individual bits that make up a byte.true = 1 and false = 0;
    private static boolean bitsFromByte(byte b, int bit){
        return(b &(1<<bit)) != 0 ;
    }
    //Get the bits that make up an integer
    private static boolean bitsFromInt(int i, int bit){
        return(i & (1<<bit)) != 0 ;
    }
    static void intToBinary( int value, int[] bits){
        for (int i = 31; i >= 0; i--) {
            if (bitsFromInt(value, i)) {
                System.out.print(1);
                bits[31-i] = 1;
            } else {
                System.out.print(0);
                bits[31-i] = 0;
            }
        }
    }
    //Conversion from Binary to other bases
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
    //Conversion from bytes to numbers
    static void byteToBinary(byte byteVal, int[] bits){
        for (int i = 7; i >= 0; i--) {
            if (bitsFromByte(byteVal, i)) {
                System.out.print(1);
                bits[7-i] = 1;
            } else {
                System.out.print(0);
                bits[7-i] = 0;
            }
        }
    }
    public static void byteArrayToBinary(byte[] bytes, int[] binary){
        int BL = 0;
        int BR = 7;
        int [] temp = new int[8];
        for (int i = 0; i < bytes.length; i++) {
            byteToBinary(bytes[i],temp);
            for (int j = BL; j <= BR  ; j++) {//0-7,8-15,16-23,24-31
                binary[j] = temp[j-BL];
            }
            BL += 8;
            BR += 8;
        }

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
    int[] inputBuffer;
    int[] outputBuffer;
    int[] tempBuffer;
    //cache for next four encoded instructions
    byte[] cache = new byte[4];
    //Retrieves next instruction from memory and places it in the cache
    public void Fetch(int address){
        byte[] instruction = new byte[4];
        for (int i = 0; i < 4; i++) {
           instruction[i] = myMemory.load(i);
        }
        cache = instruction.clone();
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
        int[] Sreg1 = Registers[0];
        int[] Sreg2 = Registers[0];
        int[] Dreg = Registers[0];
        int[] Breg = Registers[0];
        int address = -1;

        switch(IR[1] + IR[2]){

            case 0:
                //arithmetic
                opCode = (short)binToDecimal(IR,2,7);
                Sreg1 = Registers[binToDecimal(IR,8,11)];
                Sreg2 = Registers[binToDecimal(IR,12,15)];
                Dreg =  Registers[binToDecimal(IR,16,19)];
                break;
            case 1:
                opCode = (short)binToDecimal(IR,2,7);
                //conditional branch and immediate format
                if(IR[0] == 0){
                Breg = Registers[binToDecimal(IR,8,11)];
                Dreg = Registers[binToDecimal(IR,12,15)];
                address = binToDecimal(IR,15,31);

                }
                //unconditional jump
                else{
                    address = binToDecimal(IR,7,31);
                }
                break;
            case 2:
                //IO instruction
                opCode = (short)binToDecimal(IR,2,7);
                Sreg1 = Registers[binToDecimal(IR,8,11)];
                Sreg2 = Registers[binToDecimal(IR,12,15)];
                address = binToDecimal(IR,15,31);
                break;
        }
        switch(opCode){
            case 0:
                //RD I/O Reads content of I/P buffer into an accumulator or a register
                Sreg2 = Sreg1.clone();
                break;
            case 1:
                //WR I/O Writes the content of accumulator into O/P buffer
                outputBuffer = Registers[0].clone();
                break;
            case 2:
                //ST I Stores content of a reg. into an address
                for (int i = 0; i < 32 ; i += 8) {
                    myMemory.store((byte)binToDecimal(Breg,i,i+7),address);
                    address += 1;
                }

                break;
            case 3://LW I Loads the content of an address into a reg.
                int registerPosition = 0;
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 7; j++) {
                        if(bitsFromByte(myMemory.load(address),j)){
                            Breg[registerPosition] = 1;
                        }
                        else{
                            Breg[registerPosition] = 0;

                        }
                        registerPosition++;
                    }
                    address++;
                }
                break;
            case 4://MOV R Transfers the content of one register into another
                Dreg = Sreg1.clone();
                break;
            case 5://ADD R Adds content of two S-regs into D-reg
                int addResult = binToDecimal(Sreg1) + binToDecimal(Sreg2);
                intToBinary(addResult,Dreg);
                break;
            case 6://SUB R Subtracts content of two S-regs into D-reg
                int subResult = binToDecimal(Sreg1) - binToDecimal(Sreg2);
                intToBinary(subResult,Dreg);
                break;
            case 7://MUL R Multiplies content of two S-regs into D-reg
                int mulResult = binToDecimal(Sreg1) * binToDecimal(Sreg2);
                intToBinary(mulResult,Dreg);
                break;
            case 8://DIV R divides content of two S-regs into D-reg
                System.out.println(opCode);
                int divResult = binToDecimal(Sreg1)/binToDecimal(Sreg2);
                intToBinary(divResult,Dreg);
                break;
            case 9://AND R Logical AND of two S-regs into D-reg
                for(int i = 0;i < 32; i++){
                    if(Sreg1[i] == 1 && Sreg2[i] == 1){
                        Dreg[i] = 1;
                    }
                    else{
                        Dreg[i] = 0;
                    }
                }
                break;
            case 10://OR R Logical OR of two S-regs into D-reg
                for(int i = 0;i < 32; i++){
                    if(Sreg1[i] == 1 || Sreg2[i] == 1){
                        Dreg[i] = 1;
                    }
                    else{
                        Dreg[i] = 0;
                    }
                }
                break;
            case 11://MOVI I Transfers address/data directly into a register
                for (int i = 0; i < 4 ; i++) {
                    for (int j = 0; j < 7; j++) {

                    }
                }

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
                if (binToDecimal(Breg) == binToDecimal(Dreg)){

                }
                break;
            case 22://BNE I Branches to an address when content of B-reg <> D-reg
                if (binToDecimal(Breg) != binToDecimal(Dreg)){

                }
                break;
            case 23://BEZ I Branches to an address when content of B-reg = 0
                if(binToDecimal(Breg) == binToDecimal(Registers[1])){

                }
                break;
            case 24://BNZ I Branches to an address when content of B-reg <> 0
                if(binToDecimal(Breg) != binToDecimal(Registers[1])){

                }
                break;
            case 25://BGZ I Branches to an address when content of B-reg > 0
                if(binToDecimal(Breg) > binToDecimal(Registers[1])){

                }
                break;
            case 26://BLZ I Branches to an address when content of B-reg < 0
                if(binToDecimal(Breg) < binToDecimal(Registers[1])){

                }
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
