import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class Board extends JPanel {
	private static final long serialVersionUID = -9165633611662257140L;
//Data members
	
	//Basic data-types
	private int numRows;
	private int numCols;
	private int xStartCoord;
	private int yStartCoord;
	private int sqSideLen;
	private boolean removeAvailable;
	private int capturedThisTurn;
	//Objects
	private BoardState boardState;
	private Piece[][] boardPieces;
	private Pair selected;
	private Pair nextSelected;
	private ArrayList<Pair> visited;
	private Color maroon = new Color(80,0,30);
	
//Constructor
	public Board(int rows, int columns) {
		
		this.setLayout(null);
		
		numRows = rows;
		numCols = columns;
		xStartCoord = 350-50*(numCols/2);
		yStartCoord = 50;
		sqSideLen = 50;
		removeAvailable = false;
		capturedThisTurn = 0;

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
	
//Graphical Methods
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

//Get-methods
	public Piece[][] getBoardPieces() {
		return boardPieces;
	}
	public BoardState getBoardState(){
		return boardState;
	}
	public int getTurnCount(){
		return boardState.getTurnCount();
	}
	public int getP1Score(){
		return boardState.getP1Score();
	}
	public int getP2Score(){
		return boardState.getP2Score();
	}
	public Pair getSelected(){
		return selected;
	}
	public ArrayList<Pair> getVisited(){
		return visited;
	}
	public boolean isRemoveAvailable() {
		return removeAvailable;
	}
	public int getCapturedThisTurn(){
		return capturedThisTurn;
	}
	
//Update-methods
	public void select(Pair p){
		selected = p;
	}
	public void setBoardPieces(Piece[][] boardPieces) {
		this.boardPieces = boardPieces;
	}
	public void updateScores(){
		if(boardState.getCurrentPlayer() == 2){
			setBackground(maroon);
		}
		else{
			setBackground(Color.white);
		}
		boardState.updateScores();
		repaint();
	}
	public void nextTurn(){
		boardState.nextCurrentPlayer();
		boardState.updateTurnCount();
		boardState.deactivateRemovables();
		select(null);
		visited.clear();
		capturedThisTurn = 0;
		setHighlightAll(false, false);
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

//Game-logic methods
	//Make a moveType move from position p1 to p2
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
	
	//TODO implement sacrifice move
	public void sacrifice(Pair p){
		
	}
	
	
//Graphical Game-logic methods
	
	//TODO find use for this or delete it...might be useful for sacrifice
	public void unHighlight_and_Remove(Pair p){
		int xPos = p.getFirst();
		int yPos = p.getSecond();
		boardPieces[yPos][xPos].setHighlight(false);
		boardPieces[yPos][xPos].setPieceState(0);
		boardState.setBoardGrid(yPos, xPos, 0);
		repaint();
	}
	//Remove pieces found in removable.get(i)
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
	//Change the highlight status of all pieces, optionally call repaint()
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
	//Remove all pieces that have isHighlighted set to true
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
	//Highlight all pieces that can make a capture move this turn
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
	//Highlight all pieces that can make a paika move this turn
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
	//Highlight all empty spots adjacent to p
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
	//Highlight all empty spots adjacent to p that can be used for a capture move
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
	//Highlight all pieces that are options to be removed as a result of a capture move
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