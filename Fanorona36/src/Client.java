import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

public class Client {

	public boolean recv = true;
	protected int portNum;
	protected String hostName;
	Fanorona fan;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
	
    /**
	@param f The game the client will command.
	@param host The Hostname of the server.
	@param port The Port the server is using.*/
	public Client(Fanorona f, String host, int port) {
		hostName = host;
		portNum = port;
		fan = f;
		try {
			final Socket clientSocket = new Socket(hostName, portNum);
		    /* assert:  Socket successfully created */
			Thread tClient = new Thread(new Worker(clientSocket, "client", this));
			tClient.start();
//		    while(recv){
//	    		EventQueue.invokeLater(new Runnable() {
//	    			public void run() {
//	    				try {
//	    					Thread tClient = new Thread(new Worker(clientSocket, "client", recv));
//	    					tClient.start();
//	    				} catch (Exception e) {
//	    					System.exit(1);
//	    					System.err.println("Accept failed.");
//	    					e.printStackTrace();
//	    				}
//	    			}
//	    		});
//
//		    }
//		    if (clientSocket.isConnected())
//		    clientSocket.close();
		}
		
		catch (UnknownHostException e) {
			System.err.println("Unable to locate host: "+hostName);
		}
		catch (IOException e) {
		    System.err.println("Coundn't get I/O for the connection.");
		    System.err.println(e.getMessage());
		    System.exit(1);  // an error exit status
		}
	}

	public void startGame() {
//		fan.startGame();
		
	}

	public void configGame(String content) {
	    StringTokenizer sToken = new StringTokenizer(content, " ");
	    int row = Integer.parseInt(sToken.nextToken());
	    int col  = Integer.parseInt(sToken.nextToken());
	    String color  = sToken.nextToken();
	    int time  = Integer.parseInt(sToken.nextToken());
//		fan.setConfig(row, col, color, time);
		
	}

	public void setEndCond(int end) {
		if (end == 0) {
			//WINNER
		}
		if (end == 1) {
			//TIE
		}
		if (end == 2) {
			//LOSER
		}
		if (end == 3) {
			//ILLLEGAL
		}
		if (end == 4) {
			//TIME
		}
		
	}

}
