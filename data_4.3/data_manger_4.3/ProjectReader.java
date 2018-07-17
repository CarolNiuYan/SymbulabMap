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
        File Result = new File("../result_4.3/Result_4.3.csv");
        
        try
	    {
		//read the file names and read SEED and VTR
		File folder = new File(directory_name);
		File[] listOfFiles = folder.listFiles();
            
		outputStream = new PrintWriter(new FileOutputStream(Result,false));
		outputStream.println("Role,Seed,VTR,mean_IntVal");

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
				    buffer = name_fragments[2];
                     
				    //write role
				    outputStream.print("H,");
                        
				    //read seed
				    double seed = Double.parseDouble(buffer);
                        
				    //write seed
				    outputStream.print(seed+ ",");
                        
				    //read VTR
				    buffer = name_fragments[3].substring(0, 8);
				    double VTR = Double.parseDouble(buffer);
                        
				    //write VTR
				    outputStream.print(VTR + ",");
        
				}
			    else if (name.charAt(0) == 'S')
				{
                        
				    String[] name_fragments = name.split("_");
				    buffer = name_fragments[2];
                        
				    //write role
				    outputStream.print("S,");
                        
				    //read seed
				    double seed = Double.parseDouble(buffer);
                        
				    //write seed
				    outputStream.print(seed+ ",");
                        
				    //read VTR
				    buffer = name_fragments[3].substring(0, 8);
				    double VTR = Double.parseDouble(buffer);
                        
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
			    outputStream.print(content[1]);
			    outputStream.flush();
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

