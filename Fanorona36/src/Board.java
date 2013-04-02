import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.*;
import java.util.Stack;
import java.util.ArrayList;

public class Board extends JPanel {
	private static final long serialVersionUID = -9165633611662257140L;
/**Data members*/
	
	/**Basic data-types*/
	private int numRows;
	private int numCols;
	private int xStartCoord;
	private int yStartCoord;
	private int sqSideLen;
	private int capturedThisTurn;
	private boolean removeAvailable;
	private boolean makingSacrifice;
	/**Objects*/
	private static Fanorona fan;
	private BoardState boardState;
//	private Stack<Move> moveList = new Stack<Move>();
	private Piece[][] boardPieces;
	private Pair selected;
	private Pair nextSelected;
	private ArrayList<Pair> visited;
	private Color maroon = new Color(80,0,30);
	
/**Constructor*/
	public Board(int rows, int columns, Fanorona f) {
		
		this.setLayout(null);
		fan = f;
		numRows = rows;
		numCols = columns;
		xStartCoord = 350-50*(numCols/2);
		yStartCoord = 50;
		sqSideLen = 50;
		capturedThisTurn = 0;
		removeAvailable = false;
		makingSacrifice = false;
		
		boardState = new BoardState(numRows, numCols,this);
		boardPieces = new Piece[numRows][numCols];
		selected = null;
		nextSelected = null;
		visited = new ArrayList<Pair>(20);
		visited.clear();
		
		//Row-by-row initialization of pieces at start positions on board
		for(int i = 0; i < numRows/2; i++){
			for(int j = 0; j < numCols; j++){
				int xCoord = xStartCoord + sqSideLen*(j+1) - 10;
				int yCoord = yStartCoord + sqSideLen*(i+1) - 10;
				boardPieces[i][j] = new Piece(xCoord, yCoord, 2, this, j, i);
				boardPieces[i + numRows/2 + 1][j] = new Piece(xCoord, yCoord+sqSideLen*(numRows/2 + 1), 1, this, j, i + numRows/2 + 1);
			}
		}
		for(int j = 0; j < numCols/2; j+=2){
			int yCoord = yStartCoord + sqSideLen*(numRows/2 + 1) - 10;
			int xCoord = xStartCoord + sqSideLen*(j+1) - 10;
			boardPieces[numRows/2][j] = new Piece(xCoord, yCoord, 2, this, j, numRows/2);
			boardPieces[numRows/2][j+1] = new Piece(xCoord + sqSideLen, yCoord, 1, this, j+1, numRows/2);
		}
		for(int j = numCols - 1; j > numCols/2; j -= 2){
			int yCoord = yStartCoord + sqSideLen*(numRows/2 + 1) - 10;
			int xCoord = xStartCoord + sqSideLen*(j+1) - 10;
			boardPieces[numRows/2][j] = new Piece(xCoord, yCoord, 1, this, j, numRows/2);
			boardPieces[numRows/2][j-1] = new Piece(xCoord - sqSideLen, yCoord, 2, this, j-1, numRows/2);
		}
		boardPieces[numRows/2][numCols/2] = new Piece(xStartCoord + sqSideLen*(numCols/2 + 1) - 10, yStartCoord + sqSideLen*(numRows/2 + 1) - 10, 0, this, numCols/2, numRows/2);
		
	    
	    for(int i = 0; i < numRows; i++){
	    	for(int j = 0; j < numCols; j++){
	    		this.add(boardPieces[i][j]);
	    	}
	    }
	    
	}

/**AI related items*/		
	class PiecePosition {
		int xPos;
		int yPos;
	}
	
	class Move {
		Move n;
		Move s;
		Move e;
		Move w;
		Move nw;
		Move ne;
		Move sw;
		Move se;
	}
	
	private int utilityEval(PiecePosition pos, int curr_removed, int alt_removed, int player) {
		int other = (player == 1) ? 2 : 1;
		int util = (getPlayerScore(player)-curr_removed)-(getPlayerScore(other)-alt_removed);
		return util;
	}
	
//	protected int makeMoveList(PiecePosition pos, Stack<Move> moveList) {
//		int moveCount = 0;
//		Move moves = new Move();
//		if (checkMovable(pos.xPos,pos.yPos-1,false)) {
//			moveList.push(moves.n);
//			moveCount++;
//		}
//		if (checkMovable(pos.xPos,pos.yPos+1,false)) {
//			moveList.push(moves.s);
//			moveCount++;
//		}
//		if (checkMovable(pos.xPos+1,pos.yPos,false)) {
//			moveList.push(moves.e);
//			moveCount++;
//		}
//		if (checkMovable(pos.xPos-1,pos.yPos,false)) {
//			moveList.push(moves.w);
//			moveCount++;
//		}
//		if (checkMovable(pos.xPos-1,pos.yPos-1,false)) {
//			moveList.push(moves.nw);
//			moveCount++;
//		}
//		if (checkMovable(pos.xPos+1,pos.yPos-1,false)) {
//			moveList.push(moves.ne);
//			moveCount++;
//		}
//		if (checkMovable(pos.xPos+1,pos.yPos+1,false)) {
//			moveList.push(moves.se);
//			moveCount++;
//		}
//		if (checkMovable(pos.xPos-1,pos.yPos+1,false)) {
//			moveList.push(moves.sw);
//			moveCount++;
//		}
//		return moveCount;
//	}
	
	private int max (int num1, int num2){
		return ((num1 > num2) ? num1 : num2);
	}
	
	private int min (int num1, int num2){
		return ((num1 < num2) ? num1 : num2);
	}
	
	private void doMove(Move moves, PiecePosition pos){
		
	}
	
	private void undoMove(Move moves, PiecePosition pos){
		
	}
	
	int minimax(PiecePosition pos, int depth, int player)
	{
		//MOVE list[MAXMOVES];
		int i,n,bestvalue,value;

//		if(checkwin(pos)) 
//		{
//			if (pos->color == WHITE) 
//				return -INFINITY;
//			else 
//				return INFINITY;)
//		}

		if(depth == 0)	
			return utilityEval(pos,0,0,player);

//		if(pos->color==WHITE) 
//			bestvalue = -INFINITY;
//		else 
//			bestvalue = INFINITY;
//
//		n = makeMoveList(pos,moveList);
//		if(n == 0) 
//			return handlenomove(pos);

//		for(i=0; i<n; i++)
//		{
//			doMove(&list[i],pos);
//			value = minimax(pos,d-1,player);
//			undoMove(&list[i],pos);
//			if(color == WHITE) 
//				bestvalue = max(value,bestvalue);
//			else 
//				bestvalue = min(value,bestvalue);
//		}

		return bestvalue = 0;
	}
	
	class Node {
		int player;
		int turns;
		Node n;
		Node s;
		Node w;
		Node e;
		Node nw;
		Node ne;
		Node sw;
		Node se;
		int curPlayerScore;
		int altPlayerScore;
	}
	
	//TODO Have the makeGameTree function recursively build the tree
	protected Node makeGameTree(int curPlayer, int turns, int curPlayerScore, int altPlayerScore) {
		Node n = new Node();
		n.player = curPlayer;
		n.turns = turns;
		n.curPlayerScore = curPlayerScore;
		n.altPlayerScore = altPlayerScore;
		if (turns >= 1) {
			n.n = makeGameTree(turns-1,(curPlayer == 1) ? 2 : 1,0,0);
			
		}
		//checkMovable()
		return n;
	}
	
	//TODO Have minMax recursively evaluate the children from its given node for alpha values
	@SuppressWarnings("unused")
	private int minMax(Node node, int depth) {
		
		return (0);
		
	}
/**Graphical Methods*/
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		    
		drawBoard(g2);
		drawPieces(g2);
	}
		
	public void drawBoard(Graphics2D g2){
			
		Rectangle2D backdrop = new Rectangle2D.Double(xStartCoord+sqSideLen-20, yStartCoord+sqSideLen-20, 40+50*(numCols-1), 40+50*(numRows-1));
		g2.setStroke(new BasicStroke(1));
	    g2.setColor(Color.gray);
	    g2.fill(backdrop);
	    
	    g2.setStroke(new BasicStroke(3));
	    g2.setColor(maroon);
		
	    if( (numRows == 1) && (numCols != 1)) {
	    	g2.drawLine(xStartCoord + sqSideLen, yStartCoord + sqSideLen, xStartCoord + sqSideLen*(numCols), yStartCoord + sqSideLen);
	    }
	    else if( (numCols == 1) && (numRows != 1)) {
	    	g2.drawLine(xStartCoord + sqSideLen, yStartCoord + sqSideLen, xStartCoord + sqSideLen, yStartCoord + sqSideLen*(numRows));
	    }
	    
	    //Draws the board
	    for(int i = 1; i < numRows; i++){
	    	for(int j = 1; j < numCols; j ++){
	    		g2.drawRect(xStartCoord + sqSideLen*(j), yStartCoord + sqSideLen*(i), sqSideLen, sqSideLen);
	    		if( ((i & 1) == 0 && (j & 1) == 0) || ((i & 1) != 0 && (j & 1) != 0) ){
	    			g2.drawLine(xStartCoord + sqSideLen*(j), yStartCoord + sqSideLen*(i), xStartCoord + sqSideLen*(j+1), yStartCoord + sqSideLen*(i+1));
	    		}
	    		else{
	    			g2.drawLine(xStartCoord + sqSideLen*(j), yStartCoord + sqSideLen*(i+1), xStartCoord + sqSideLen*(j+1), yStartCoord + sqSideLen*(i));
	    		}
	    	}
	    }
	}
	public void drawPieces(Graphics2D g2){
		for(int i = 0; i < numRows; i++){
			for(int j = 0; j < numCols; j++){
				boardPieces[i][j].paintComponent(g2);
			}
		}
	}

/**Get-methods*/
	public int getTurnCount(){
		return boardState.getTurnCount();
	}
	public int getPlayerScore(int player){
		if (player == 1)
			return getP1Score();
		else if (player == 2)
			return getP2Score();
		else return 0;
	}
	public int getRows(){
		return numRows;
	}
	public int getCols(){
		return numCols;
	}
	public int getP1Score(){
		return boardState.getP1Score();
	}
	public int getP2Score(){
		return boardState.getP2Score();
	}
	public int getCapturedThisTurn(){
		return capturedThisTurn;
	}
	public Pair getSelected(){
		return selected;
	}
	public Piece[][] getBoardPieces() {
		return boardPieces;
	}
	public BoardState getBoardState(){
		return boardState;
	}
	public ArrayList<Pair> getVisited(){
		return visited;
	}
	public boolean isRemoveAvailable() {
		return removeAvailable;
	}	
	public boolean isMakingSacrifice(){
		return makingSacrifice;
	}
	
	
	
/**Update-methods*/
	public void select(Pair p){
		selected = p;
	}
	public void setBoardPieces(Piece[][] boardPieces) {
		this.boardPieces = boardPieces;
	}
	public void setMakingSacrifice(boolean b){
		makingSacrifice = b;
	}
	public void updateScores(){
		boardState.updateScores();
		repaint();
	}
	public void updateRemoveAvailable() {
		if( !(boardState.getRemovables().isEmpty()) ){
			for(int i = 0; i < boardState.getRemovables().size(); i++){
				if( !(boardState.getRemovables().get(i).isEmpty()) ){
					removeAvailable = true;
					break;					
				}
			}
		}
		else{
			removeAvailable = false;
		}
	}
	public void updateSelected(){
		selected = nextSelected;
		nextSelected = null;
	}
	/**Resets/updates the state of appropriate variables/objects, effectively starting the next turn*/
	public void nextTurn(){
		fan.sacrificeButton.setSelected(false);
		makingSacrifice = false;
		boardState.nextCurrentPlayer();
		boardState.updateTurnCount();
		boardState.deactivateRemovables();
		select(null);
		visited.clear();
		capturedThisTurn = 0;
		
		if(boardState.getCurrentPlayer() == 2){
			setBackground(maroon);
		}
		else{
			setBackground(Color.white);
		}
		
		removeSacrificed(boardState.getCurrentPlayer());
		setHighlightAll(false, true);
		if (this.getTurnCount() <= 1) {
			JOptionPane.showMessageDialog(new JFrame(), "The Game Will Begin Now!");
		}
		else {
			JOptionPane.showMessageDialog(new JFrame(), "Next User's Turn!");
		}
	}
/**Game-logic methods*/
	/**Make a moveType move from position p1 to p2*/
	public void move(Pair p1, Pair p2, String moveType){
		int xPos1 = p1.getFirst();
		int yPos1 = p1.getSecond();
		int xPos2 = p2.getFirst();
		int yPos2 = p2.getSecond();
		
		if(moveType == "paika"){
			//P1 becomes empty
			boardState.setBoardGrid(yPos1, xPos1, 0);
			boardPieces[yPos1][xPos1].setPieceState(0);
			//P2 becomes current player's piece
			boardState.setBoardGrid(yPos2, xPos2, boardState.getCurrentPlayer());
			boardPieces[yPos2][xPos2].setPieceState(boardState.getCurrentPlayer());
			nextTurn();
			
		}
		
		else if (moveType == "capture"){
			//P1 becomes empty
			boardState.setBoardGrid(yPos1, xPos1, 0);
			boardPieces[yPos1][xPos1].setPieceState(0);
			boardPieces[yPos1][xPos1].setHighlight(false);
			visited.add(p1);
			//P2 becomes current player's piece
			boardState.setBoardGrid(yPos2, xPos2, boardState.getCurrentPlayer());
			boardPieces[yPos2][xPos2].setPieceState(boardState.getCurrentPlayer());
			//select(p2);
			nextSelected = p2;
			boardState.activateRemovables(p1, p2);
			setHighlightAll(false, true);
			highlightRemovables();
			repaint();
		}
		
	}
	/**Make sacrifice move that leaves the chosen piece in-game until the start of that player's next turn, possibly blocking captures from the opponent*/
	public void sacrifice(Pair p){
		int xPos = p.getFirst();
		int yPos = p.getSecond();
		boardState.setBoardGrid(yPos, xPos, (boardState.getCurrentPlayer()*(-1)) );
		boardPieces[yPos][xPos].setPieceState((boardState.getCurrentPlayer()*(-1)));
		boardPieces[yPos][xPos].setSacrifice(true);
		nextTurn();
	}
	/**Calls boardState's activateRemovables function*/
	public void activateRemovables(Pair p1, Pair p2) {
		boardState.activateRemovables(p1, p2);	
	}	

/**Graphical Game-logic methods*/
	
	/**Change the highlight status of all pieces, optionally call repaint()*/
	public void setHighlightAll(boolean isHighlight, boolean doRepaint){
		for(int i = 0; i < numRows; i++){
	    	for(int j = 0; j < numCols; j++){
	    		boardPieces[i][j].setHighlight(isHighlight);
	    	}
		}
		if(doRepaint){
			repaint();
		}
	}
	/**Remove pieces found in removable.get(i)*/
	public void removeRemovables(int i){
		if( (i == 0) || (i == 1)){
			for(int j = 0; j < boardState.getRemovables().get(i).size(); j++){
				int xPos = boardState.getRemovables().get(i).get(j).getFirst();
				int yPos = boardState.getRemovables().get(i).get(j).getSecond();
				boardState.setBoardGrid(yPos, xPos, 0);
				boardPieces[yPos][xPos].setPieceState(0);
				capturedThisTurn++;
			}
			setHighlightAll(false, true);
			boardState.deactivateRemovables();
		}
	}
	/**Remove all pieces that have isHighlighted set to true*/
	public void removeHighlightedPieces(){
		for(int i = 0; i < numRows; i++){
	    	for(int j = 0; j < numCols; j++){
	    		if(boardPieces[i][j].getHighlight()){
	    			boardPieces[i][j].setPieceState(0);
	    			boardState.setBoardGrid(i, j, 0);
	    			repaint();
	    		}
	    	}
		}
	}
	/**Remove pieces sacrificed by current player on their last turn */
	public void removeSacrificed(int currentPlayer){
		for(int i = 0; i < numRows; i++){
			for(int j = 0; j < numCols; j++){
				if(boardState.getBoardGrid()[i][j] == (currentPlayer * (-1))){
					boardState.setBoardGrid(i, j, 0);
					boardPieces[i][j].setSacrifice(false);
					boardPieces[i][j].setPieceState(0);
					break;
				}
			}
		}
	}
	/**Highlight all pieces that can make a capture move this turn*/
	public void highlightCaptureMovable(){
		for(int i = 0; i < numRows; i++){
			for(int j = 0; j < numCols; j++){
				if( (boardState.getBoardGrid()[i][j] == boardState.getCurrentPlayer()) && (boardState.hasCaptureDestinations(new Pair(j,i))) ){
					boardPieces[i][j].setHighlight(true);
				}
			}
		}
		repaint();
	}
	/**Highlight all pieces that can make a paika move this turn*/
	public void highlightPaikaMovable(){		
		 for(int i = 0; i < numRows; i++){
	    	for(int j = 0; j < numCols; j++){
	    		if( (boardState.getBoardGrid()[i][j] == boardState.getCurrentPlayer()) && (boardState.hasDestinations(new Pair(j,i))) ){
	    			boardPieces[i][j].setHighlight(true);
	    		}
	    	}
	     }
		 repaint();
	}
	/**Highlight all empty spots adjacent to p*/
	public void highlightDestinations(Pair p){
		Pair[] destinations = boardState.checkDestinations(p);
		for(int i = 0; i < 8; i++){
			if(destinations[i] != null){
				boolean notVisited = true;
				for(int j = 0; j < visited.size(); j++){
					if( destinations[i].isEqualTo(visited.get(j)) ){
						notVisited = false;
						break;
					}
				}
				if(notVisited){
					int xPos = destinations[i].getFirst();
					int yPos = destinations[i].getSecond();
					boardPieces[yPos][xPos].setHighlight(true);
				}
			}
		}	
		repaint();
	}
	/**Highlight all empty spots adjacent to p that can be used for a capture move*/
	public void highlightCaptureDestinations(Pair p){
		Pair[] destinations = boardState.checkCaptureDestinations(p);
		for(int i = 0; i < 8; i++){
			if(destinations[i] != null){
				boolean notVisited = true;
				for(int j = 0; j < visited.size(); j++){
					if( destinations[i].isEqualTo(visited.get(j)) ){
						notVisited = false;
						break;
					}
				}
				if(notVisited){
					int xPos = destinations[i].getFirst();
					int yPos = destinations[i].getSecond();
					boardPieces[yPos][xPos].setHighlight(true);
				}
			}
		}	
		repaint();
	}
	/**Highlight all pieces that are options to be removed as a result of a capture move*/
	public void highlightRemovables(){
		for(int i = 0; i < boardState.getRemovables().size(); i++){
			for(int j = 0; j < boardState.getRemovables().get(i).size(); j++){
				int xPos = boardState.getRemovables().get(i).get(j).getFirst();
				int yPos = boardState.getRemovables().get(i).get(j).getSecond();
				boardPieces[yPos][xPos].setHighlight(true);
			}
		}
		repaint();
	}
	
}