package classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TextFile {
	
	 public final String fileName = "dictionary.txt";
	 public String final_def = "";
	
	public String definition(String word){
		 
//	     This will reference one line at a time
	     String line = "";
//	     read the line file
	     BufferedReader bufferedReader = null;
	     
//    	 reserve the variable for storage of the definition from the file
    	 String def = "";
	     try{

	         // Always wrap FileReader in BufferedReader.
	    	 bufferedReader = new BufferedReader(new FileReader(fileName));
	
//	    	 iterate through the file line by line
	         while((line = bufferedReader.readLine()) != null) {
//	        	 split line into two parts the word itself and the definition and store into an array
	        	 String[] row = line.split(",");
//	        	 iterate through the array and compare the words in the file
	        	 for(int i = 0; i<=0; i++){
	        		 
	        		 if (row[i].equals(word)){
	        			 def = row[1];
	        			 final_def = def;
	        		 }
	        	 }
//	        	 if definition is found assign to variable final_def
	        	 if (final_def.equals("")){
	        		 final_def = "-- Word not found --";
	        		 break;
	        	 }
	        	 
	         }   
	         // Always close files.
	         bufferedReader.close();         
	     }
	     catch(FileNotFoundException ex) {
	         System.out.println(
	             "Unable to open file '" + 
	             fileName + "'");                
	     }
	     catch(IOException IOex) {
	         System.out.println(
	             "Error reading file '" 
	             + fileName + "'");                 
	     }
	     
	     return final_def;
	 }
}
