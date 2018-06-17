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
import java.text.DecimalFormat;

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
        
        try
            {
                //read the file names and read SEED and VTR
                File folder = new File(directory_name);
                File[] listOfFiles = folder.listFiles();
            
            
                File Result = new File("../result_4.5/mean_Hist_4.5.csv");
            
                outputStream = new PrintWriter(new FileOutputStream(Result,false));
                outputStream.println("Seed,Evolution_Time,Hist_-1_-0.9,Hist_-0.9_-0.8,Hist_-0.8_-0.7,Hist_-0.7_-0.6,Hist_-0.6_-0.5,Hist_-0.5_-0.4,Hist_-0.4_-0.3,Hist_-0.3_-0.2,Hist_-0.2_-0.1,Hist_-0.1_0,Hist_0_0.1,Hist_0.1_0.2,Hist_0.2_0.3,Hist_0.3_0.4,Hist_0.4_0.5,Hist_0.5_0.6,Hist_0.6_0.7,Hist_0.7_0.8,Hist_0.8_0.9,Hist_0.9_1,Total_Count");

                for(int i = 0; i < listOfFiles.length; i++){
                    if (listOfFiles[i].isFile())
                        {
                            // Create a scanner for the file
                            File f = listOfFiles[i];
                            inputStream = new Scanner(new FileInputStream(f));

                            String name = f.getName();
                            String[] name_fragments = name.split("_");
                            double seed = Double.parseDouble(name_fragments[0].substring(7));
                            DecimalFormat numberFormat = new DecimalFormat("#.00");
                            numberFormat.format(seed);
                    
                            // Get rid of the header
                            read = inputStream.nextLine();
                    
                            // Read in a line of information
                            while(inputStream.hasNextLine()) {
                                read = inputStream.nextLine();
                                String[] fragments = read.split(",");
                                String output = "";
                                for (int j = 0; j < fragments.length; j++) {
                                    if (j != 1) {
                                        output = output + "," + fragments[j];
                                    }
                                }
                                outputStream.println(seed + output);
                            }
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

