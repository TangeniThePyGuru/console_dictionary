package classes;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class client {
	
	public static void main( String args[]) throws UnknownHostException, IOException{
//		Declaration
		int port = 0;
		String word = "",serverIp = "", temp = "";
		Scanner userInput = new Scanner(System.in);
		
//		get input from the command line
		for(int i=0;i<=args.length;i++){
//			capture the server ip address
			serverIp = args[i];
//			capture  the port address			
			port = Integer.parseInt(args[i]);
//			capture the word to find definition
			word = args[i];
		}


//		make a connection to the server
		Socket connectToServerSocket  = new Socket(serverIp,port);
		
		
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
