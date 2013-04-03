import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Client extends Thread{

	protected boolean moving = true;
	protected int portNum;
	protected String hostName;
	protected int gameType;
	protected int timerLen;
	protected int playerNumber;
	protected int numRows;
	protected int numCols;
	protected String color;
	static Socket clientSocket;
	Fanorona newGame;
	private ArrayList<MovesList.Triplet> moves;
	protected int turn;
	private boolean notStarted = true;
		
    /**
	@param location The game the client will command.
	@param host The Hostname of the server.
	@param port The Port the server is using.*/
	public Client(int playerNum, String host, int port) {

//		PLAYERNUMBER
//		- -1;
//		-  1: 
//		-  2: 
//		
//		GAMETYPE
//		- -1: you don't get to decide! (client)
//		-  0: P v P
//		-  1: P v C
//		-  2: C v C
		hostName = host;
		portNum = port;
		playerNumber = playerNum;
		try {
			clientSocket = new Socket(InetAddress.getByName(hostName), portNum);
		    /* assert:  Socket successfully created */
			Thread tClient = new Thread(new Worker(clientSocket, "client", this));
			tClient.start();
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
		newGame = new Fanorona(playerNumber, gameType, numRows, numCols, timerLen);
		try {
			Client.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		newGame.setVisible(true);
		turn = 0;
		notStarted = false;
	}
	
	public Pair transf2Cartesian(Pair p1) {
		Pair fixed;
		int x = p1.getFirst();
		int y = p1.getSecond();
		x++;
		y = newGame.board.getRows()-(p1.getSecond())+1;
		fixed = new Pair(x,y);
		return fixed;
	}
	
	public Pair transf2Matrix(Pair p1) {
		Pair fixed;
		int x = p1.getFirst();
		int y = p1.getSecond();
		x--;
		y = newGame.board.getRows()-(p1.getSecond())-1;
		fixed = new Pair(x,y);
		return fixed;
	}
	
	public void sendMove(Pair p, Pair q, String type){
		p = transf2Cartesian(p);
		q = transf2Cartesian(q);
		if (type.equals("A")) {
			try {
				OutputStream outStream = clientSocket.getOutputStream();
				Command move = new Command("capture_move", "A"+" "+p.getFirst()+" "+p.getSecond()+" "+q.getFirst()+" "+q.getSecond()+" ");
				move.send(outStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (type.equals("W")) {
			try {
				OutputStream outStream = clientSocket.getOutputStream();
				Command move = new Command("capture_move", "W"+" "+p.getFirst()+" "+p.getSecond()+" "+q.getFirst()+" "+q.getSecond()+" ");
				move.send(outStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (type.equals("P")) {
			try {
				OutputStream outStream = clientSocket.getOutputStream();
				Command move = new Command("paika_move", "P"+" "+p.getFirst()+" "+p.getSecond()+" "+q.getFirst()+" "+q.getSecond()+" ");
				move.send(outStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (type.equals("S")) {
			try {
				OutputStream outStream = clientSocket.getOutputStream();
				Command move = new Command("sacrifice_move", "S"+" "+p.getFirst()+" "+p.getSecond()+" ");
				move.send(outStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void configGame(String content) {
	    StringTokenizer sToken = new StringTokenizer(content, " ");
	    numCols  = Integer.parseInt(sToken.nextToken());
	    numRows = Integer.parseInt(sToken.nextToken());
	    color  = sToken.nextToken();
	    if (color.equals("W")) {
	    	playerNumber = 1;
	    }
	    else if (color.equals("B")) {
	    	playerNumber = 2;
	    }
	    timerLen  = Integer.parseInt(sToken.nextToken());
		
	}

	public void setEndCond(int end) {
		if (end == 0) {
			//WINNER;
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
		Pair p1, p2;
		String moveType;
		if (content.length() < 15) {
			StringTokenizer parts = new StringTokenizer(content, " ");
	    	moveType = parts.nextToken();
	    	p1 = new Pair(Integer.parseInt(parts.nextToken()),Integer.parseInt(parts.nextToken()));
	    	p1 = transf2Matrix(p1);
	    	if (moveType.equals("S")) {
	    		newGame.board.sacrifice(p1);
	    	}
	    	p2 = new Pair(Integer.parseInt(parts.nextToken()),Integer.parseInt(parts.nextToken()));
	    	p2 = transf2Matrix(p2);
			newGame.board.move(p1, p2, moveType);
			if (!(moveType.equals("P")) && !(moveType.equals("S"))) {
				newGame.board.activateRemovables(p1, p2);
			}
			if (moveType.equals("A")) {
				newGame.board.removeRemovables(0); //0 for A; 1 for W
			}
			else if (moveType.equals("W")) {
				newGame.board.removeRemovables(1); //0 for A; 1 for W
			}
		}
		else {
	    StringTokenizer moves = new StringTokenizer(content, "+");
	    LinkedList<String> moveList = new LinkedList<String>();
	    for (int i=0; i < moves.countTokens()-1; i++) {
	    	moveList.add(moves.nextToken());
	    }
	    for (String move : moveList) {
	    	StringTokenizer parts = new StringTokenizer(move, " ");
	    	moveType = parts.nextToken();
	    	p1 = new Pair(Integer.parseInt(parts.nextToken()),Integer.parseInt(parts.nextToken()));
	    	p1 = transf2Matrix(p1);
	    	if (moveType.equals("S")) {
	    		newGame.board.sacrifice(p1);
	    		break;
	    	}
	    	p2 = new Pair(Integer.parseInt(parts.nextToken()),Integer.parseInt(parts.nextToken()));
	    	p2 = transf2Matrix(p2);
			newGame.board.move(p1, p2, moveType);
			if (!(moveType.equals("P")) && !(moveType.equals("S"))) {
				newGame.board.activateRemovables(p1, p2);
			}
			if (moveType.equals("A")) {
				newGame.board.removeRemovables(0); //0 for A; 1 for W
			}
			else if (moveType.equals("W")) {
				newGame.board.removeRemovables(1); //0 for A; 1 for W
			}
	    }
	    }
	}
	
	public void run() {
		while (notStarted) {
			
		}
		//Turn == 0
		while (true) {
			if ((newGame.board.getTurnCount() > turn) && (newGame.board.getBoardState().getCurrentPlayer() == playerNumber)) {
				while (moving) {
					if (newGame.board.movesList.getSize() > 0 ) {
						moves = newGame.board.movesList.getMoveArray();
						for (MovesList.Triplet move : moves) {
							sendMove(move.getPair1(),move.getPair2(),move.getMoveType());
						}
						moving = false;
					}
				}
				turn++;
			}
		}
	}
}
