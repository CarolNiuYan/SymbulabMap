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
            
            File temp = listOfFiles[0];
    	    		String name = temp.getName();
    	    		String[] info = name.split("_");
    	    		double VTR = Double.parseDouble(info[1].substring(0, 8));
            
    	    		File Result = new File("../result/mean_Hist_" + VTR + ".csv");
            
            outputStream = new PrintWriter(new FileOutputStream(Result,false));
            outputStream.println("VTR,Evolution_Time,Hist_-1_-0.9,Hist_-0.9_-0.8,Hist_-0.8_-0.7,Hist_-0.7_-0.6,Hist_-0.6_-0.5,Hist_-0.5_-0.4,Hist_-0.4_-0.3,Hist_-0.3_-0.2,Hist_-0.2_-0.1,Hist_-0.1_0,Hist_0_0.1,Hist_0.1_0.2,Hist_0.2_0.3,Hist_0.3_0.4,Hist_0.4_0.5,Hist_0.5_0.6,Hist_0.6_0.7,Hist_0.7_0.8,Hist_0.8_0.9,Hist_0.9_1");
            
            double[][] data = new double[20][10000];
            
            // Prints an entry for each file in the directory
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
            		String hist_num = "";
            		for (int b = 0; b < 20; b++) {
            			DecimalFormat numberFormat = new DecimalFormat("#.00");
            			if(data[b][a] != 0) {
            				hist_num += numberFormat.format(data[b][a]);
            			}else {
            				hist_num += data[b][a];
            			}
            			if (b != 19) {
            				hist_num += ",";
            			}
            		}
            		String output = VTR + "," + a * 10 + "," + hist_num;
            		outputStream.println(output);
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

