import java.util.Scanner;
import java.io.*;

public class Operating_Systems_Loader1
{
   public static void main (String[]args) 
   {
      
      // Phase 1 is NOT non-contiguous
      
      BufferedReader br = null;
      String line;
      String[] PCB_array = new String[120]; // ID, # of Words, Priority, Base Reg
      String[] RAM_memory = new String[1024];
      int pcb_count = 0;
      int ram_count = 0;
      int base_reg_count = 0;
      boolean base_reg_check = false;
      
      try
      {
         br = new BufferedReader(new FileReader("C:/Users/Jiggy/Documents/JOBS_Example.txt"));
         
         
         while ((line = br.readLine()) != null) 
         {
            if(line.contains("//"))
            {
               String temp = line.replace("// ", "");
               temp = temp.trim();  //Removes leading and ending white space
               
               if(temp.charAt(0) == 'J')
               {
                  int i = 4; //starting from char-position [3] in string, to get ID, # of words, and priority
                  
                  String holder = "";
                  
                  while(i < temp.length())
                  {
                     if(temp.charAt(i) != ' ') // ID check
                     {
                        holder = "" + temp.charAt(i);
                        
                        if(i == (temp.length() - 1))
                        {
                           PCB_array[pcb_count] = holder;
                           pcb_count++;
                           i++;
                           base_reg_check = true;
                        }
                        else if(temp.charAt(i+1) != ' ') //ID is 2 chars long
                        {
                           holder = holder + temp.charAt(i+1);
                           PCB_array[pcb_count] = holder;
                           pcb_count++;
                           i += 3;
                        }
                        else // ID is 1 char long
                        {
                           PCB_array[pcb_count] = holder;
                           pcb_count++;
                           i += 2;
                        }
                     }
                  }
               }
            }
            else
            {
               if(base_reg_check == true) // assigning base reg to PCB, and keep count of location
               {
                  String temp = line.trim();
                  PCB_array[pcb_count] = "" + ram_count; //was 'base_reg_count'
                  pcb_count++;
                  base_reg_check = false;
                  RAM_memory[ram_count] = temp;
                  ram_count++;
               }
               else
               {
                  RAM_memory[ram_count] = temp;
                  ram_count++;
               }
               
            }
            
         }
            
      }
      catch (IOException e)
      {
         System.out.println();
      }
      
     
     
     
        
   }
}