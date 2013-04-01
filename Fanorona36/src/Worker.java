import java.io.*;
import java.net.*;

public class Worker implements Runnable {

    /** Socket that will carry commands */
    Socket socket;
    
    String workType;
    
    boolean waiting;
    
    Server server;
    Client client;

    /**
	@param sock The socket currently connected.
	@param type The type of connection, "client" or "server".
	@param serv The server object that called the worker*/
    public Worker(Socket sock, String type, Server serv){
		socket = sock;
		workType = type;
		server = serv;
    }
	
    /**
	@param sock The socket currently connected.
	@param type The type of connection, "client" or "server".
	@param serv The server object that called the worker*/
    public Worker(Socket sock, String type, Client cli){
    	socket = sock;
    	workType = type;
    	client  = cli;
    }
    
	@Override
	public void run() {
		try{
			InputStream inStream = socket.getInputStream();
			OutputStream outStream = socket.getOutputStream();
			//Send WELCOME command if server
			if (workType.equals("server")) {
				Command welcome = new Command("ack","WELCOME");
				welcome.send(outStream);
			}
			//Send server info
			if (workType.equals("server")) {
				Command info = new Command("info","WELCOME");
				info.send(outStream);
			}
			while(true){
//				System.out.println("\nWorker waiting...");
				Command comm = new Command(inStream);

				if (comm.getType().equals("info")){
					if(!comm.getContent().equals("BEGIN")) {
						client.startGame();
					}
					else { 
						client.configGame(comm.getContent());
					}
				}
//				if (comm.getType().equals("ack")){
//				    Command ok = new Command("OK", "Connection terminated..."); 
//				    ok.send(outStream);	
//				}
//				else {
//				    //System.out.println("Connection terminated..."); 
//				    Command ack = new Command("ack", "Connection terminated..."); 
//				    ack.send(outStream);
//				    break;
//				}
				if (comm.getType().equals("ack")){
					if(comm.getContent().equals("ILLEGAL")) {
						break;
					}
					if(comm.getContent().equals("TIME")) {
						break;
					}
					if(comm.getContent().equals("WINNER")) {
						client.setEndCond(0);
						break;
					}
					if(comm.getContent().equals("TIE")) {
						client.setEndCond(1);
						break;
					}
					if(!comm.getContent().equals("LOSER")) {
						client.setEndCond(2);
						break;
					}
				}
			}
			socket.close();
			server.servSock.close();
			
		} catch (IOException e) {System.err.println("Message error in Worker: " + e.getMessage());}
	
	}

}
