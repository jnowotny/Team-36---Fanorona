import java.awt.EventQueue;
import java.io.*;
import java.net.*;

public class Server {
	
	public boolean recv = true;
	protected int portNum;
	Fanorona fan;
	Socket inSock;
	ServerSocket servSock;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
	
    /**
	@param f The game the client will command.
	@param port The port for the server to listen on.*/
	public Server (Fanorona f, int port) {
		portNum = port;
		try {
			final ServerSocket serv = new ServerSocket(portNum);
			servSock = serv;
			fan = f;
		    /* assert:  ServerSocket successfully created */
    		EventQueue.invokeLater(new Runnable() {
    			public void run() {
    				try {
    					final Socket in = servSock.accept();
    					setSocket(in);
    	
    				} catch (Exception e) {
    					System.exit(1);
    					System.err.println("Accept failed.");
    					e.printStackTrace();
    				}
    			}
    		});
			Thread t = new Thread(new Worker(inSock, "server", this));
			t.start();		    
		}
		catch (IOException e) {
		    System.err.println("Receiver failed.");
		    System.err.println(e.getMessage());
		    System.exit(1);  // an error exit status
		    return;
		}	
	}
	public void startGame() {
		//TODO Make function which starts a game with the selected config
//		fan.startGame();
	}
	
	public void setSocket(Socket sock) {
		inSock = sock;
	}

	public String getConfigGame() {
		String config = null;
		//TODO make function in fanorona which returns the selected config
//		config = fan.getConfig();
		return config;
	}
	
	public void sendMove(Pair p, Pair q, String type){
		if (type.equals("capture")) {
			try {
				OutputStream outStream = inSock.getOutputStream();
				Command move = new Command("capture_move", "A"+p.getFirst()+p.getSecond()+q.getFirst()+q.getSecond());
				move.send(outStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (type.equals("paika")) {
			try {
				OutputStream outStream = inSock.getOutputStream();
				Command move = new Command("paika_move", "P"+p.getFirst()+p.getSecond()+q.getFirst()+q.getSecond());
				move.send(outStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (type.equals("sacrifice")) {
			try {
				OutputStream outStream = inSock.getOutputStream();
				Command move = new Command("sacrifice_move", "S"+p.getFirst()+p.getSecond()+q.getFirst()+q.getSecond());
				move.send(outStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void movePiece(String content) {
		//TODO move piece on board
		
	}
}
