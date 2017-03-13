package classes;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//Thread Class to support threading

public class ServerThread extends Thread{
//	capture the port for the server
	public static String port = "", 
//			store the definition
			temp, 
//			store the word to search for
			word,
//			store the ip addresses of the server
			serverIP = "";
	
	public static TextFile dictionary = new TextFile();
	
	
	public void run() {    
    	/*		declaration */
		
		ServerSocket serveSocket = null;
		try {
			serveSocket = new ServerSocket(Integer.parseInt(port));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		System.out.println("-- Server up and running on port "+port+" --");
		while(true){
//			accept connection with client
			Socket clientSocket = null;
			try {
				clientSocket = serveSocket.accept();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
//			get client input e.g word to find
			Scanner clientInput = null;
			try {
				clientInput = new Scanner(clientSocket.getInputStream());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			word = clientInput.next();
			
//			read the definition from the text file/ dictionary
			temp = dictionary.definition(word);
			
//			send the definition to the client
			PrintStream p = null;
			try {
				p = new PrintStream(clientSocket.getOutputStream());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			p.println(temp);
			
//			close clientInput scanner
			clientInput.close();
			p.close();
			try {
				clientSocket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	
    public static void main(String args[]) {
        port = args[0];
       (new ServerThread()).start();
        
    }
    
}
