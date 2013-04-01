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
				Command info = new Command("info",server.getConfigGame());
				info.send(outStream);
			}
			while(true){
				Command comm = new Command(inStream);

				if (comm.getType().equals("info")){
					if(!comm.getContent().equals("BEGIN")) {
						client.startGame();
					}
					else {
						//game_config is being passed
						client.configGame(comm.getContent());
					    Command ready = new Command("ack", "READY"); 
					    ready.send(outStream);	
					}
				}
				if (comm.getType().equals("ack")){
					if(!comm.getContent().equals("READY")) {
						server.startGame();
						Command begin = new Command("info", "BEGIN"); 
						begin.send(outStream);
					}
				}
				if ((comm.getType().equals("capture_move"))&&(workType.equals("server"))){
					server.movePiece(comm.getContent());
				}
				else if ((comm.getType().equals("capture_move"))&&(workType.equals("client"))){
					client.movePiece(comm.getContent());
				}
				if ((comm.getType().equals("paika_move"))&&(workType.equals("server"))){
					server.movePiece(comm.getContent());
				}
				else if ((comm.getType().equals("paika_move"))&&(workType.equals("client"))){
					client.movePiece(comm.getContent());
				}
				if ((comm.getType().equals("sacrifice_move"))&&(workType.equals("server"))){
					server.movePiece(comm.getContent());
				}
				else if ((comm.getType().equals("sacrifice_move"))&&(workType.equals("client"))){
					client.movePiece(comm.getContent());
				}
//				if (comm.getType().equals("ack")){
//				    Command ok = new Command("ack", "OK"); 
//				    ok.send(outStream);	
//				}
				if (comm.getType().equals("ack")){
					if(comm.getContent().equals("ILLEGAL")) {
						client.setEndCond(3);
						break;
					}
					if(comm.getContent().equals("TIME")) {
						client.setEndCond(4);
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
