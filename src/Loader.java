import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Loader {

    //Jobs are described by JOB ID LENGTH PRIORITY
    //data is defined  by InputBuffer OutputBuffer TempBuffer
    //using hex values (for example 17 = 23 and a = 10)
    //Job instructions are store in memory and Data is stored on the PCB
    static final File code = new File("C:\\Users\\Alex\\Desktop\\Program1.txt");

    Loader(Memory sysMemory,Disk sysDisk, PCB[] programArray) throws IOException {


        int memoryLocation = 0;
        int dataLocation = 0;
        int Base = 0;
        boolean JobSection = false;
        int pcbCount = 0;
        PCB p = null;
        Scanner reader = new Scanner(code);
        while(reader.hasNextLine()){

            String instruction = reader.nextLine();
            if(instruction.contains("//")){
                String[] temp = instruction.split(" ");
                if(temp[1].equals("JOB")) {
                    p = new PCB();
                    p.programID = Integer.parseInt(temp[2], 16);
                    p.codeSize = Integer.parseInt(temp[3], 16);
                    p.priority = Integer.parseInt(temp[4], 16);
                    p.BaseRegister = Base;
                    Base = 4 * p.codeSize;
                    JobSection = true;
                }
                else if(temp[1].equals("Data")){
                    p.inputBufferSize = Integer.parseInt(temp[2], 16);
                    p.outputBufferSize = Integer.parseInt(temp[3], 16);
                    p.tempBuffersSize = Integer.parseInt(temp[4], 16);
                    JobSection = false;
                }
                else if(temp[1].equals("END")){
                    programArray[pcbCount] = p;
                    dataLocation = 0;
                    pcbCount++;
                    //end
                }



            }
            else{
                System.out.print(instruction);
                String[] arr = instruction.split("x");
                if(JobSection){//Load Instructions into memory
                    System.out.print(" -> " + arr[1]);
                    String [] hexSplit = arr[1].split("(?<=\\G..)");
                    for (int i = 0; i < hexSplit.length; i++) {
                        byte b = (byte)((Character.digit((hexSplit[i].charAt(0)),16) << 4)+
                                (Character.digit((hexSplit[i].charAt(1)),16)));
                        sysMemory.store(b,memoryLocation);
                        memoryLocation++;
                    }
                }
                else{//Load attributes into PCB
                    String [] hexSplit = arr[1].split("(?<=\\G..)");
                    for (int i = 0; i < hexSplit.length; i++) {
                        byte b = (byte)((Character.digit((hexSplit[i].charAt(0)),16) << 4)+
                                (Character.digit((hexSplit[i].charAt(1)),16)));
                        p.store(b,dataLocation);
                        dataLocation++;

                    }
                }

            }



        }
    }


}
