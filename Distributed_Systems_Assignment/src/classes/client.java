package classes;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class client {
	
	public static void main( String args[]) throws UnknownHostException, IOException{
//		Declaration
		String word, serverIp, temp = "";
		Scanner userInput = new Scanner(System.in);

//		take down the ip address of the server
		System.out.println("Enter server ip: ");
		serverIp = userInput.next();
//		make a connection to the server
		Socket connectToServerSocket = new Socket(serverIp,1000);
		
//		enter the word to be searched
		System.out.println("Enter word to find definition: ");
		
//		accept the word and store to variable word
		word = userInput.next();
		
//		send the word to the server and search
		PrintStream  p = new PrintStream(connectToServerSocket.getOutputStream());
		p.println(word);
		
//		get back the result from the server
		Scanner op = new Scanner(connectToServerSocket.getInputStream());
		temp = op.nextLine();

		System.out.println("Definition(s) of word: "+word);
		System.out.println(temp);
					
//		Close scanner objects
		op.close();
		userInput.close();

//		close the socket
		connectToServerSocket.close();
			
			
		
	}

}
