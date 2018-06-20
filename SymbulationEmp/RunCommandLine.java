import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.*;

import org.omg.CORBA.portable.InputStream;
    
public class RunCommandLine {
    public static void main (String[] args) {
        // Run each set of parameters for 30 times
        int  random_num = 0;
        for (float VTR = 0.0; VTR <= 1.0; VTR += 0.1) {
            for (int i = 0; i < 20; i++) {
                try {
                    System.out.println(i+1);
				
                    // Generate new config files
                    ReplaceFileContents rfc = new ReplaceFileContents();
                    rfc.replace(i+1, VTR);
				
                    Runtime rt = Runtime.getRuntime();

                    Process proc = rt.exec("./symbulation");
                    proc.waitFor();
                    proc.destroy();
		
                } catch(Exception e) {
                    System.out.println(e.toString());
                    e.printStackTrace();
                }
            }
        }
    }
}
