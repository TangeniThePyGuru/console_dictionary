package classes;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

public class serve {

	public static void main(String[] args) throws IOException, SQLException  {
		// TODO Auto-generated method stub
		
		System.out.println("-- Server up and running --");
/*		declaration */
		
//		store to search for
		String word;
//		store the definition
		String temp;
//		connect to DataBase
		DBConnect connect = new DBConnect();
		
		
//		connect to client via port 1000
		
		while(true){
			
			ServerSocket serveSocket = new ServerSocket(1000);
//			accept connection with client
			Socket clientSocket = serveSocket.accept();
//			get client input e.g word to find
			Scanner clientInput = new Scanner(clientSocket.getInputStream());
			word = clientInput.next();
			
			
//			get the definition of the word from the database
			temp = connect.getDefinition(word);
//			send the definition to the client
			PrintStream p = new PrintStream(clientSocket.getOutputStream());
			p.println(temp);

//			close connection
			serveSocket.close();
			clientInput.close();

			
		}
	}
	
}
