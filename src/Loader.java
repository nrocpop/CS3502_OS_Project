import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Loader {
    //Jobs are described by JOB ID LENGTH PRIORITY
    //data is defined  by InputBuffer OutputBuffer TempBuffer
    //using hex values (for example 17 = 23 and a = 10)
    //Job instructions are store in memory and Data is stored on the PCB
    static final File code = new File("C:\\Users\\xbato\\Desktop\\Code.txt");

    Loader(Memory sysMemory,Disk sysDisk, PCB[] programArray) throws IOException {
        int memoryLocation = 0;
        Scanner reader = new Scanner(code);
        while(reader.hasNextLine()){
            String instruction = reader.nextLine();
            System.out.print(instruction);
            String[] arr = instruction.split("x");
            System.out.print(" -> " + arr[1]);
            String [] hexSplit = arr[1].split("(?<=\\G..)");
            for (int i = 0; i < hexSplit.length; i++) {
                byte b = (byte)((Character.digit((hexSplit[i].charAt(0)),16) << 4)+
                        (Character.digit((hexSplit[i].charAt(1)),16)));
                sysMemory.store(b,memoryLocation);
                memoryLocation++;
            }



        }
    }


}
