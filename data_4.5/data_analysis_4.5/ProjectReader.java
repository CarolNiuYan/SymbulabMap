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

	String[] interval_names = {"-1_-.9","-.9_-.8","-.8_-.7","-.7_-.6","-.6_-.5","-.5_-.4","-.4_-.3","-.3_-.2","-.2_-.1","-.1_0","0_.1",".1_.2",".2_.3",".3_.4",".4_.5",".5_.6",".6_.7",".7_.8",".8_.9",".9_1"};

	File Result = new File("../result_4.5/mean_Hist_4.5.csv");
        
        try
            {
                //read the file names and read SEED and VTR
                File folder = new File(directory_name);
                File[] listOfFiles = folder.listFiles();
            
                outputStream = new PrintWriter(new FileOutputStream(Result,false));
                outputStream.println("Seed,Evolution_Time,Interval,Hist_Count");

                for(int i = 0; i < listOfFiles.length; i++){
                    if (listOfFiles[i].isFile())
                        {
                            // Create a scanner for the file
                            File f = listOfFiles[i];
                            inputStream = new Scanner(new FileInputStream(f));

                            String name = f.getName();
                            String[] name_fragments = name.split("_");
                            double seed = Double.parseDouble(name_fragments[2]);
                            DecimalFormat numberFormat = new DecimalFormat("#.00");
                            numberFormat.format(seed);
                    
                            // Get rid of the header
                            read = inputStream.nextLine();
                    
                            // Read in a line of information
                            while(inputStream.hasNextLine()) {
                                read = inputStream.nextLine();
                                String[] fragments = read.split(",");
                                for (int j = 0; j < 20; j++) {
				    String output = seed + "," + fragments[0] + ",";
				    output = output + interval_names[j] + "," + fragments[j+2];
				    outputStream.print(output);
				    outputStream.print("\n");
                                }
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

