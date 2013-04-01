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
	@param host The Hostname of the server.*/
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
//		fan.startGame();
	}
	
	public void setSocket(Socket sock) {
		inSock = sock;
	}
}
