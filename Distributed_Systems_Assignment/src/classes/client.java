package classes;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class client {
	
	public static void main( String args[]) throws UnknownHostException, IOException{
//		Declaration
		String port = "";
		String word = "",serverIp = "", temp = "";
		Scanner userInput = new Scanner(System.in);
		
//		get input from the command line
//		for(int i=0;i<=args.length;i++){
//			capture the server ip address
			serverIp = args[0];
//			capture  the port address			
			port = (args[1]);
//			capture the word to find definition
			word = args[2];
//		}


//		make a connection to the server
		Socket connectToServerSocket  = new Socket(serverIp,Integer.parseInt(port));
		
		
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
