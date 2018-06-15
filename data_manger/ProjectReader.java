// ProjectReader.java
// Author: Tina Yao, Yuanqi Zhao
// (Deliverable3)
// A class read projects from a text file.
// 
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.stream.*;
import java.util.ArrayList;
import java.lang.*;

class ProjectReader
{
    String directory_name;
    
    // default constructor for reading file
    ProjectReader(String directory_name){ this.directory_name = directory_name;}
    
    //a function read from the file
    public void fromFile()
    {
        Scanner inputStream = null;
        PrintWriter outputStream = null;
        String read = null;
        //create a file for the information that we want
        
        
        //change path!!!!!!!!
        File Result = new File("/Users/Aurora/Desktop/Symbulation/MAP/result/Result.csv");
        
        try
        {
            //read the file names and read SEED and VTR
            File folder = new File(directory_name);
            File[] listOfFiles = folder.listFiles();
            
            outputStream = new PrintWriter(new FileOutputStream(Result,false));
            outputStream.println("Role,Seed,VTR,mean_IntVal,Hist_-1_-0.9,Hist_-0.9_-0.8,Hist_-0.8_-0.7,Hist_-0.7_-0.6,Hist_-0.6_-0.5,Hist_-0.5_-0.4,Hist_-0.4_-0.3,Hist_-0.3_-0.2,Hist_-0.2_-0.1,Hist_-0.1_0,Hist_0_0.1,Hist_0.1_0.2,Hist_0.2_0.3,Hist_0.3_0.4,Hist_0.4_0.5,Hist_0.5_0.6,Hist_0.6_0.7,Hist_0.7_0.8,Hist_0.8_0.9,Hist_0.9_1");

            // Prints an entry for each file in the directory
            for(int i = 0; i < listOfFiles.length; i++){
                if (listOfFiles[i].isFile())
                {
                	    File f = listOfFiles[i];
                    String name = f.getName();
                    String buffer = "";
                    
                    if(name.charAt(0) == 'H')
                    {
                    	   String[] name_fragments = name.split("_");
                        buffer = name_fragments[0].substring(8);
                     
                        //write role
                        outputStream.print("H,");
                        
                        //read seed
                        double seed = Double.parseDouble(buffer);
                        
                        //write seed
                        outputStream.print(seed+ ",");
                        
                        //read VTR
                        buffer = name_fragments[1].substring(0, 8);
                        double VTR = Double.parseDouble(buffer);
                        
                        //write VTR
                        outputStream.print(VTR + ",");
        
                    }
                    else if (name.charAt(0) == 'S')
                    {
                        
                    		String[] name_fragments = name.split("_");
                        buffer = name_fragments[0].substring(7);
                        
                        //write role
                        outputStream.print("S,");
                        
                        //read seed
                        double seed = Double.parseDouble(buffer);
                        
                        //write seed
                        outputStream.print(seed+ ",");
                        
                        //read VTR
                        buffer = name_fragments[1].substring(0, 8);
                        float VTR = Float.parseFloat(buffer);
                        
                        //write VTR
                         outputStream.print(VTR + ",");
                    }
                    
                    inputStream = new Scanner(new FileInputStream(f));
                    
                    // Read the last line of the file
                    while(inputStream.hasNextLine()){
                        read = inputStream.nextLine();
                    }

                    // Print the needed information to the .csv file
                    String[] content = read.split(",");
                    for(int j = 1; j < content.length; j++) {
                        outputStream.print(content[j]);
                        outputStream.flush();
                        if (j != content.length - 1) {
                        		outputStream.print(",");
                        }
                    }
                    outputStream.print("\n");
                }
            }
            outputStream.close();
        }
    //catch the exception that no file is found
    catch(FileNotFoundException e)
    {
        //create a new file
        try
        {
            outputStream = new PrintWriter(new FileOutputStream("projectinfo.txt",true));
        }
        catch(FileNotFoundException ee)
        {
            System.out.print("Failed to create the file.\n");
            System.exit(0);
        }
        outputStream.close();
    }
}
}

