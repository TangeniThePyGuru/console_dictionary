package classes;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

//Thread Class to support threading

public class ServerThread extends Thread {
	
	
	public void run() {
		System.out.println("-- Server up and running --");
		/*		declaration */
				
//				store to search for
				String word;
//				store the definition
				String temp = null;
//				connect to DataBase
				DBConnect connect = new DBConnect();
				
		
//				connect to client via the port 1000
		
				ServerSocket serveSocket = null;
				try {
					serveSocket = new ServerSocket(1000);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				while(true){
//					accept connection with client
					Socket clientSocket = null;
					try {
						clientSocket = serveSocket.accept();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					get client input e.g word to find
					Scanner clientInput = null;
					try {
						clientInput = new Scanner(clientSocket.getInputStream());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					word = clientInput.next();
					
//					get the definition of the word from the database
					try {
						temp = connect.getDefinition(word);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					send the definition to the client
					PrintStream p = null;
					try {
						p = new PrintStream(clientSocket.getOutputStream());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					p.println(temp);

//					close connection
//					serveSocket.close();
					
//					close clientInput scanner
					clientInput.close();
					p.close();
					clientSocket.isClosed();
				}
			}

    public static void main(String args[]) {
        (new ServerThread()).start();
    }

}
