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
        
        try
            {
                //read the file names and read SEED and VTR
                File folder = new File(directory_name);
                File[] listOfFolders = folder.listFiles();
            
            
                File Result = new File("../result_4.7/mean_Hist_4.7.csv");
            
                outputStream = new PrintWriter(new FileOutputStream(Result,false));
                outputStream.println("VTR,Evolution_Time,Interval,Hist_Count");
               
            
                // Prints an entry for each file in the directory
                for(int dir = 0; dir < listOfFolders.length; dir++) {
                    
                    //Get inside folder and retrieve files
                    File[] listOfFiles = listOfFolders[dir].listFiles();

                    double VTR = Double.parseDouble(listOfFolders[dir].getName());

                    //create 2d array for each folder
                    double[][] data = new double[20][10000];
                    
                    for(int i = 0; i < listOfFiles.length; i++){
                        if (listOfFiles[i].isFile())
                            {
                                // Create a scanner for the file
                                File f = listOfFiles[i];
                                inputStream = new Scanner(new FileInputStream(f));
                    
                                // Initialize a counter
                                int x = 0;
                    
                                // Get rid of the header
                                read = inputStream.nextLine();
                    
                                // Read in a line of information
                                while(inputStream.hasNextLine()) {
                                    read = inputStream.nextLine();
                                    String[] fragments = read.split(",");
                                    for (int k = 0; k < 20; k++) {
                                        data[k][x] += Integer.parseInt(fragments[k+2]);
                                    }
                                    x++;
                                }
                            }
                    }
                
                    // Average each element of the data array
                    for(int a = 0; a < 20; a++) {
                        for(int b = 0; b < 10000; b++) {
                            // Divide among the number of files (seeds)
                            data[a][b] = data[a][b] / listOfFiles.length;
                        }
                    }
            
                    // Create entries and print them to file
                    for(int a = 0; a < 10000; a++) {
                        for (int b = 0; b < 20; b++) {
                            String output = VTR + "," + a * 10 + "," + interval_names[b] + "," + data[b][a];
                            outputStream.println(output);
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

