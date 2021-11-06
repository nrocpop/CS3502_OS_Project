//Calls loader then calls  dispatcher , cpu and waitforintterupt in a loop

import java.io.IOException;

public class Driver {


    public static void main(String[] args) throws IOException {
        int count = 0;
        int memlocation = 0;
        Memory systemMemory = new Memory(1024);
        Disk systemDisk = new Disk(2048);
        PCB[] programList =  new PCB[50];
        Loader load = new Loader(systemMemory,systemDisk,programList);
        CPU myCpu = new CPU(1,systemMemory);
        while(count <= 75){
        myCpu.Fetch(memlocation);
        memlocation += 4;
        myCpu.Decode(myCpu.cache);
        myCpu.Execute();
        count++;
        }
        System.out.println("\nMEMORY");
        for (int i = 0; i < 1024; i++) {
            System.out.print(systemMemory.load(i) + "|");
        }
        System.out.println("\nJOB 1 DATA");
        for (int i = 0; i < 256; i++) {
            System.out.print(programList[0].data[i] + "|");
        }
        System.out.println("\nJOB 2 DATA");
        for (int i = 0; i < 256; i++) {
            System.out.print(programList[1].data[i] + "|");
        }
        System.out.println("\nJOB 3 DATA");
        for (int i = 0; i < 256; i++) {
            System.out.print(programList[2].data[i] + "|");
        }
        }

    }
