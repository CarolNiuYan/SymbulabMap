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
			File[] listOfFolders = folder.listFiles();


			File Result = new File("../result_4.8_b/mean_4.8_b.csv");

			outputStream = new PrintWriter(new FileOutputStream(Result,false));
			outputStream.println("Role,VTR,Evolution_Time,Mean_IntVal");


			// Prints an entry for each file in the directory
			for(int dir = 0; dir < listOfFolders.length; dir++) {			
				// Get VTR from the name of the folder
				double VTR = Double.parseDouble(listOfFolders[dir].getName());
				
				//Get inside role folder and retrieve files
				File[] listOfRoleFolders = listOfFolders[dir].listFiles();
				for (int roledir = 0; roledir < listOfRoleFolders.length; roledir++) {
					if(listOfRoleFolders[roledir].isDirectory()) {
						// Check if is a symbiont folder or a host folder
						String role = listOfRoleFolders[roledir].getName().charAt(0) + "";
						
						// Get list of files inside each role folder
						File[] listOfFiles = listOfRoleFolders[roledir].listFiles();

						//create array for each role folder
						double[] data = new double[10000];

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
									data[x] += Double.parseDouble(fragments[1]);
									x++;
								}
							}
						}

						// Average each element of the data array
						
						for(int b = 0; b < 10000; b++) {
							// Divide among the number of files (seeds)
							data[b] = data[b] / listOfFiles.length;
						}

						// Create entries and print them to file
						for(int a = 0; a < 10000; a++) {
							String output = role + "," + VTR + "," + a * 10 + "," + data[a];
						    outputStream.println(output);
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

