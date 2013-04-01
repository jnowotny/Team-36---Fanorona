import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

public class Client {

	public boolean recv = true;
	protected int portNum;
	protected String hostName;
	Socket inSock;
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
			setSocket(clientSocket);
		    /* assert:  Socket successfully created */
			Thread tClient = new Thread(new Worker(inSock, "client", this));
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
		//TODO Make function which starts a game with the selected config
//		fan.startGame();
		
	}
	
	public void setSocket(Socket sock) {
		inSock = sock;
	}
	
	public void sendMove(Pair p, Pair q, String type){
		if (type.equals("A")) {
			try {
				OutputStream outStream = inSock.getOutputStream();
				Command move = new Command("capture_move", "A"+p.getFirst()+p.getSecond()+q.getFirst()+q.getSecond());
				move.send(outStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (type.equals("W")) {
			try {
				OutputStream outStream = inSock.getOutputStream();
				Command move = new Command("capture_move", "W"+p.getFirst()+p.getSecond()+q.getFirst()+q.getSecond());
				move.send(outStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (type.equals("P")) {
			try {
				OutputStream outStream = inSock.getOutputStream();
				Command move = new Command("paika_move", "P"+p.getFirst()+p.getSecond()+q.getFirst()+q.getSecond());
				move.send(outStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (type.equals("S")) {
			try {
				OutputStream outStream = inSock.getOutputStream();
				Command move = new Command("sacrifice_move", "S"+p.getFirst()+p.getSecond()+q.getFirst()+q.getSecond());
				move.send(outStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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

	public void movePiece(String content) {
		//TODO move piece on board
		
	}

}
