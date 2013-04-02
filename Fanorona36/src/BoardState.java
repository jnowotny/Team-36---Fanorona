import java.util.ArrayList;

public class BoardState {
//Data members
	
	//Basic data-types
	private int numRows;
	private int numCols;
	private int[][] boardGrid;
	private int p1Score;
	private int p2Score;
	private int turnCount = 0;
	private int currentPlayer = 2;		//1 or 2 (player), 0: error

	//Objects
	private Board brd;
	ArrayList<ArrayList<Pair>> removables;
	
//Constructor
	public BoardState(int rows, int columns, Board b){
		brd = b;
		numRows = rows;
		numCols = columns;
		boardGrid = new int[numRows][numCols];
		removables = new ArrayList<ArrayList<Pair>>();
		removables.clear();
		
		for(int i = 0; i < numRows/2; i++){
			for(int j = 0; j < numCols; j++){
				boardGrid[i][j] = 2;
				boardGrid[i + numRows/2 + 1][j] = 1;
			}
		}
		for(int j = 0; j < numCols/2; j+=2){
			boardGrid[numRows/2][j] = 2;
			boardGrid[numRows/2][j+1] = 1;
		}
		for(int j = numCols - 1; j > numCols/2; j -= 2){
			boardGrid[numRows/2][j] = 1;
			boardGrid[numRows/2][j-1] = 2;
		}
		boardGrid[numRows/2][numCols/2] = 0;
	}
	
//Get-methods
	public int[][] getBoardGrid() {
		return boardGrid;
	}
	public int getP1Score(){
		return p1Score;
	}
	public int getP2Score(){
		return p2Score;
	}
	public int getTurnCount(){
		return turnCount;
	}
	public int getCurrentPlayer(){
		return currentPlayer;
	}
	public int getNextPlayer(){
		if(currentPlayer == 2){
			return 1;
		}
		else if(currentPlayer == 1){
			return 2;
		}
		else {
			return -1;
		}
	}
	public ArrayList<ArrayList<Pair>> getRemovables(){
		return removables;
	}

//Update-methods
	public void setBoardGrid(int[][] boardGrid) {
		this.boardGrid = boardGrid;
	}
	public void setBoardGrid(int i, int j, int pieceSt){
		if(pieceSt == 0 || pieceSt == 1 || pieceSt == 2){
			this.boardGrid[i][j] = pieceSt;
		}
	}
	public void setP1Score(int newP1Score){
		p1Score = newP1Score;
	}
	public void setP2Score(int newP2Score){
		p2Score = newP2Score;
	}
	public void updateScores(){
		int p1Count = 0;
		int p2Count = 0;
		for(int i = 0; i < numRows; ++i){
			for(int j = 0; j < numCols; ++j){
				if (boardGrid[i][j] == 2) {
					p2Count++;
				}
				if (boardGrid[i][j] == 1) {
					p1Count++;
				}
			}
		}
		p2Score = p2Count;
		p1Score = p1Count;
	}
	public void updateTurnCount(){
		turnCount++;
	}
	public void nextCurrentPlayer(){
		if(currentPlayer == 2){
			currentPlayer = 1;
		}
		else if(currentPlayer == 1){
			currentPlayer = 2;
		}
	}

//Game-logic methods
	
	
	//Returns true if p has an empty spot in a valid adjacency
	public boolean hasDestinations(Pair p){
		Pair[] destinations = checkDestinations(p);
		for(int i = 0; i < 8; i++){
			if(destinations[i] != null){
				return true;
			}
		}
		return false;
	}

	/*	Given Pair(x,y), returns array[8] of Pair(x,y) for adjacent spaces that are empty.
	 *  If array[i] == null, then the Pair position represented by i is occupied or invalid.
	 * 
	 *  		0  1  2
	 *  		7  P  3
	 *  		6  5  4	
	 */
	public Pair[] checkDestinations(Pair p){
		Pair[] destinations = new Pair[8];
		for(int i = 0; i < 8; i++){
			destinations[i] = null;
		}
		
		int xPos = p.getFirst();
		int yPos = p.getSecond();
		
		if(yPos == 0){
			//Top-left corner
			if(xPos == 0){
				//E:3
				if(checkAdjacent(p,3) == 0){
					destinations[3] = new Pair(xPos+1, yPos);
				}
				//SE:4
				if(checkAdjacent(p,4) == 0){
					destinations[4] = new Pair(xPos+1, yPos+1);
				}
				//S:5
				if(checkAdjacent(p,5) == 0){
					destinations[5] = new Pair(xPos, yPos+1);
				}
			}
			//Top-right corner
			else if(xPos == numCols-1){
				//S:5
				if(checkAdjacent(p,5) == 0){
					destinations[5] = new Pair(xPos, yPos+1);
				}
				//SW:6
				if(checkAdjacent(p,6) == 0){
					destinations[6] = new Pair(xPos-1, yPos+1);
				}
				//W:7
				if(checkAdjacent(p,7) == 0){
					destinations[7] = new Pair(xPos-1, yPos);
				}
			}
			//Top row, odd columns
			else if((xPos & 1) != 0){
				//E:3
				if(checkAdjacent(p,3) == 0){
					destinations[3] = new Pair(xPos+1, yPos);
				}
				//S:5
				if(checkAdjacent(p,5) == 0){
					destinations[5] = new Pair(xPos, yPos+1);
				}
				//W:7
				if(checkAdjacent(p,7) == 0){
					destinations[7] = new Pair(xPos-1, yPos);
				}
			}
			//Top row, even columns
			else if((xPos & 1) == 0){			
				//E:3
				if(checkAdjacent(p,3) == 0){
					destinations[3] = new Pair(xPos+1, yPos);
				}
				//SE:4
				if(checkAdjacent(p,4) == 0){
					destinations[4] = new Pair(xPos+1, yPos+1);
				}
				//S:5
				if(checkAdjacent(p,5) == 0){
					destinations[5] = new Pair(xPos, yPos+1);
				}
				//SW:6
				if(checkAdjacent(p,6) == 0){
					destinations[6] = new Pair(xPos-1, yPos+1);
				}
				//W:7
				if(checkAdjacent(p,7) == 0){
					destinations[7] = new Pair(xPos-1, yPos);
				}
			}
		}
		else if(yPos == numRows-1){
			//Bottom-left corner
			if(xPos == 0){
				//N:1
				if(checkAdjacent(p,1) == 0){
					destinations[1] = new Pair(xPos, yPos-1);
				}
				//NE:2
				if(checkAdjacent(p,2) == 0){
					destinations[2] = new Pair(xPos+1, yPos-1);
				}
				//E:3
				if(checkAdjacent(p,3) == 0){
					destinations[3] = new Pair(xPos+1, yPos);
				}
			}
			//Bottom-right corner
			else if(xPos == numCols-1){
				//NW:0
				if(checkAdjacent(p,0) == 0){
					destinations[0] = new Pair(xPos-1, yPos-1);
				}
				//N:1
				if(checkAdjacent(p,1) == 0){
					destinations[1] = new Pair(xPos, yPos-1);
				}
				//W:7
				if(checkAdjacent(p,7) == 0){
					destinations[7] = new Pair(xPos-1, yPos);
				}
			}
			//Bottom row, odd columns
			else if((xPos & 1) != 0){
				//N:1
				if(checkAdjacent(p,1) == 0){
					destinations[1] = new Pair(xPos, yPos-1);
				}
				//E:3
				if(checkAdjacent(p,3) == 0){
					destinations[3] = new Pair(xPos+1, yPos);
				}
				//W:7
				if(checkAdjacent(p,7) == 0){
					destinations[7] = new Pair(xPos-1, yPos);
				}
			}
			//Bottom row, even columns
			else if((xPos & 1) == 0){
				//NW:0
				if(checkAdjacent(p,0) == 0){
					destinations[0] = new Pair(xPos-1, yPos-1);
				}
				//N:1
				if(checkAdjacent(p,1) == 0){
					destinations[1] = new Pair(xPos, yPos-1);
				}
				//NE:2
				if(checkAdjacent(p,2) == 0){
					destinations[2] = new Pair(xPos+1, yPos-1);
				}
				//E:3
				if(checkAdjacent(p,3) == 0){
					destinations[3] = new Pair(xPos+1, yPos);
				}
				//W:7
				if(checkAdjacent(p,7) == 0){
					destinations[7] = new Pair(xPos-1, yPos);
				}
			}
		}
		else if(xPos == 0){
			//Left-most column, odd rows
			if((yPos & 1) != 0){
				//N:1
				if(checkAdjacent(p,1) == 0){
					destinations[1] = new Pair(xPos, yPos-1);
				}
				//E:3
				if(checkAdjacent(p,3) == 0){
					destinations[3] = new Pair(xPos+1, yPos);
				}
				//S:5
				if(checkAdjacent(p,5) == 0){
					destinations[5] = new Pair(xPos, yPos+1);
				}
			}
			//Left-most column, even rows
			else if((yPos & 1) == 0){
				//N:1
				if(checkAdjacent(p,1) == 0){
					destinations[1] = new Pair(xPos, yPos-1);
				}
				//NE:2
				if(checkAdjacent(p,2) == 0){
					destinations[2] = new Pair(xPos+1, yPos-1);
				}
				//E:3
				if(checkAdjacent(p,3) == 0){
					destinations[3] = new Pair(xPos+1, yPos);
				}
				//SE:4
				if(checkAdjacent(p,4) == 0){
					destinations[4] = new Pair(xPos+1, yPos+1);
				}
				//S:5
				if(checkAdjacent(p,5) == 0){
					destinations[5] = new Pair(xPos, yPos+1);
				}
			}
		}
		else if(xPos == numCols-1){
			//Right-most column, odd rows
			if((yPos & 1) != 0){
				//N:1
				if(checkAdjacent(p,1) == 0){
					destinations[1] = new Pair(xPos, yPos-1);
				}
				//S:5
				if(checkAdjacent(p,5) == 0){
					destinations[5] = new Pair(xPos, yPos+1);
				}
				//W:7
				if(checkAdjacent(p,7) == 0){
					destinations[7] = new Pair(xPos-1, yPos);
				}
			}
			//Right-most column, even rows
			else if((yPos & 1) == 0){
				//NW:0
				if(checkAdjacent(p,0) == 0){
					destinations[0] = new Pair(xPos-1, yPos-1);
				}
				//N:1
				if(checkAdjacent(p,1) == 0){
					destinations[1] = new Pair(xPos, yPos-1);
				}
				//S:5
				if(checkAdjacent(p,5) == 0){
					destinations[5] = new Pair(xPos, yPos+1);
				}
				//SW:6
				if(checkAdjacent(p,6) == 0){
					destinations[6] = new Pair(xPos-1, yPos+1);
				}
				//W:7
				if(checkAdjacent(p,7) == 0){
					destinations[7] = new Pair(xPos-1, yPos);
				}
			}
		}
		else if( ((xPos & 1) == 0 && (yPos & 1) == 0) || ((xPos & 1) != 0&&(yPos & 1) != 0) ){
			//NW:0
			if(checkAdjacent(p,0) == 0){
				destinations[0] = new Pair(xPos-1, yPos-1);
			}
			//N:1
			if(checkAdjacent(p,1) == 0){
				destinations[1] = new Pair(xPos, yPos-1);
			}
			//NE:2
			if(checkAdjacent(p,2) == 0){
				destinations[2] = new Pair(xPos+1, yPos-1);
			}
			//E:3
			if(checkAdjacent(p,3) == 0){
				destinations[3] = new Pair(xPos+1, yPos);
			}
			//SE:4
			if(checkAdjacent(p,4) == 0){
				destinations[4] = new Pair(xPos+1, yPos+1);
			}
			//S:5
			if(checkAdjacent(p,5) == 0){
				destinations[5] = new Pair(xPos, yPos+1);
			}
			//SW:6
			if(checkAdjacent(p,6) == 0){
				destinations[6] = new Pair(xPos-1, yPos+1);
			}
			//W:7
			if(checkAdjacent(p,7) == 0){
				destinations[7] = new Pair(xPos-1, yPos);
			}
		}
		else{
			//N:1
			if(checkAdjacent(p,1) == 0){
				destinations[1] = new Pair(xPos, yPos-1);
			}
			//E:3
			if(checkAdjacent(p,3) == 0){
				destinations[3] = new Pair(xPos+1, yPos);
			}
			//S:5
			if(checkAdjacent(p,5) == 0){
				destinations[5] = new Pair(xPos, yPos+1);
			}
			//W:7
			if(checkAdjacent(p,7) == 0){
				destinations[7] = new Pair(xPos-1, yPos);
			}
		}
		
		
		return destinations;
	}
	
	//Returns true if current player can make a capture move
	public boolean hasCaptureMoves(){
		for(int i = 0; i < numRows; i++){
			for(int j = 0; j < numCols; j++){
				if(boardGrid[i][j] == getCurrentPlayer() && hasCaptureDestinations(new Pair(j,i))){
					return true;
				}
			}
		}
		return false;
	}

	public Pair[] checkCaptureDestinations(Pair p){
		Pair[] destinations = checkDestinations(p);
		for(int i = 0; i < 8; i++){
			if(destinations[i] != null){
				if( !(hasCaptures_Move(p, destinations[i])) ){
					destinations[i] = null;
				}
				else if (!(brd.getVisited().isEmpty())) {
					for(int j = 0; j < brd.getVisited().size(); j++) {
						if (destinations[i].isEqualTo(brd.getVisited().get(j)) ){
							destinations[i] = null;
						}
					}
				}
			}
		}
		return destinations;
	}
	
	//Returns true if p has a destination for which the move would have capture options
	public boolean hasCaptureDestinations(Pair p){
		Pair[] destinations = checkCaptureDestinations(p);
		for(int i = 0; i < 8; i++){
			if(destinations[i] != null){
				return true;
			}
		}
		return false;
	}
	
	//Returns true if a Move from p1 to p2 has Capture options
	public boolean hasCaptures_Move(Pair p1, Pair p2){
		ArrayList<ArrayList<Pair>> capturables = new ArrayList<ArrayList<Pair>>();
		capturables = getCaptures_Move(p1,p2);
		if( !(capturables.get(0).isEmpty()) ){
			//TODO remove this line. HOWEVER, it demonstrated some odd behavior of the program not closing from certain windows
//			System.out.println("Array1  x: " + capturables.get(0).get(0).getFirst() + "  y: " + capturables.get(0).get(0).getSecond());
			return true;
		}
		else if( !(capturables.get(1).isEmpty()) ) {
			//TODO remove this line. HOWEVER, it demonstrated some odd behavior of the program not closing from certain windows
//			System.out.println("Array2  x: " + capturables.get(1).get(0).getFirst() + "  y: "+capturables.get(1).get(0).getSecond());
			return true;
		}
		else {
			return false;
		}
	}
	
	/*Returns an ArrayList of 2 ArrayList<Pair> 
	 *	ArrayList<Pair> are filled with positions of capturable pieces in an unbroken line
	 *	As a result of a Move from p1 to p2*/
	public ArrayList<ArrayList<Pair>> getCaptures_Move(Pair p1, Pair p2){
		ArrayList<ArrayList<Pair>> removablePieces = new ArrayList<ArrayList<Pair>>(2);
		removablePieces.add(new ArrayList<Pair>(11));
		removablePieces.add(new ArrayList<Pair>(11));
		removablePieces.get(0).clear();
		removablePieces.get(1).clear();
		
		int p1x = p1.getFirst();
		int p1y = p1.getSecond();
		int p2x = p2.getFirst();
		int p2y = p2.getSecond();
		int xDelta = p2x - p1x;
		int yDelta = p2y - p1y;
		
		//NW:0
		if(xDelta == -1 && yDelta == -1){
			while(p2y > 0 && p2x > 0){
				int adjacent = checkAdjacent(new Pair(p2x,p2y),0);
				if( adjacent == getNextPlayer()){
					p2y += yDelta;
					p2x += xDelta;
					removablePieces.get(0).add(new Pair(p2x,p2y));
				}
				else{
					break;
				}
			}
			while(p1y < numRows - 1 && p1x < numCols - 1){
				int adjacent = checkAdjacent(new Pair(p1x,p1y),4);
				if( adjacent == getNextPlayer() ){
					p1y -= yDelta;
					p1x -= xDelta;
					removablePieces.get(1).add(new Pair(p1x,p1y));
				}
				else{
					break;
				}
			}
		}
		//N:1
		else if(xDelta == 0 && yDelta == -1){
			while(p2y > 0){
				int adjacent = checkAdjacent(new Pair(p2x,p2y),1);
				if( adjacent == getNextPlayer() ){
					p2y += yDelta;
					removablePieces.get(0).add(new Pair(p2x,p2y));
				}
				else{
					break;
				}
			}
			while(p1y < numRows - 1){
				int adjacent = checkAdjacent(new Pair(p1x,p1y),5);
				if( adjacent == getNextPlayer() ){
					p1y -= yDelta;
					removablePieces.get(1).add(new Pair(p1x,p1y));
				}
				else{
					break;
				}
			}	
		}
		//NE:2
		else if(xDelta == 1 && yDelta == -1){
			while(p2y > 0 && p2x < numCols - 1){
				int adjacent = checkAdjacent(new Pair(p2x,p2y),2);
				if( adjacent == getNextPlayer() ){
					p2y += yDelta;
					p2x += xDelta;
					removablePieces.get(0).add(new Pair(p2x,p2y));
				}
				else{
					break;
				}
			}
			while(p1y < numRows - 1 && p1x > 0){
				int adjacent = checkAdjacent(new Pair(p1x,p1y),6);
				if( adjacent == getNextPlayer() ){
					p1y -= yDelta;
					p1x -= xDelta;
					removablePieces.get(1).add(new Pair(p1x,p1y));
				}
				else{
					break;
				}
			}
		}
		//E:3
		else if(xDelta == 1 && yDelta == 0){
			while(p2x < numCols - 1){
				int adjacent = checkAdjacent(new Pair(p2x,p2y),3);
				if( adjacent == getNextPlayer() ){
					p2x += xDelta;
					removablePieces.get(0).add(new Pair(p2x,p2y));
				}
				else{
					break;
				}
			}
			while(p1x > 0){
				int adjacent = checkAdjacent(new Pair(p1x,p1y),7);
				if( adjacent == getNextPlayer() ){
					p1x -= xDelta;
					removablePieces.get(1).add(new Pair(p1x,p1y));
				}
				else{
					break;
				}
			}
		}
		//SE:4
		else if(xDelta == 1 && yDelta == 1){
			while(p2y < numRows - 1 && p2x < numCols - 1){
				int adjacent = checkAdjacent(new Pair(p2x,p2y),4);
				if( adjacent == getNextPlayer() ){
					p2y += yDelta;
					p2x += xDelta;
					removablePieces.get(0).add(new Pair(p2x,p2y));
				}
				else{
					break;
				}
			}
			while(p1y > 0 && p1x > 0){
				int adjacent = checkAdjacent(new Pair(p1x,p1y),0);
				if( adjacent == getNextPlayer() ){
					p1y -= yDelta;
					p1x -= xDelta;
					removablePieces.get(1).add(new Pair(p1x,p1y));
				}
				else{
					break;
				}
			}
		}
		//S:5
		else if(xDelta == 0 && yDelta == 1){
			while(p2y < numRows - 1 ){
				int adjacent = checkAdjacent(new Pair(p2x,p2y),5);
				if( adjacent == getNextPlayer() ){
					p2y += yDelta;
					removablePieces.get(0).add(new Pair(p2x,p2y));
				}
				else{
					break;
				}
			}
			while(p1y > 0){
				int adjacent = checkAdjacent(new Pair(p1x,p1y),1);
				if( adjacent == getNextPlayer() ){
					p1y -= yDelta;
					removablePieces.get(1).add(new Pair(p1x,p1y));
				}
				else{
					break;
				}
			}
		}
		//SW:6
		else if(xDelta == -1 && yDelta == 1){
			while(p2y < numRows - 1 && p2x > 0){
				int adjacent = checkAdjacent(new Pair(p2x,p2y),6);
				if( adjacent == getNextPlayer() ){
					p2y += yDelta;
					p2x += xDelta;
					removablePieces.get(0).add(new Pair(p2x,p2y));
				}
				else{
					break;
				}
			}
			while(p1y > 0 && p1x < numCols - 1){
				int adjacent = checkAdjacent(new Pair(p1x,p1y),2);
				if( adjacent == getNextPlayer() ){
					p1y -= yDelta;
					p1x -= xDelta;
					removablePieces.get(1).add(new Pair(p1x,p1y));
				}
				else{
					break;
				}
			}
		}
		//W:7
		else if(xDelta == -1 && yDelta == 0){
			while(p2x > 0){
				int adjacent = checkAdjacent(new Pair(p2x,p2y),7);
				if( adjacent == getNextPlayer() ){
					p2x += xDelta;
					removablePieces.get(0).add(new Pair(p2x,p2y));
				}
				else{
					break;
				}
			}
			while(p1x < numCols - 1){
				int adjacent = checkAdjacent(new Pair(p1x,p1y),3);
				if( adjacent == getNextPlayer() ){
					p1x -= xDelta;
					removablePieces.get(1).add(new Pair(p1x,p1y));
				}
				else{
					break;
				}
			}
		}
		removablePieces.get(0).trimToSize();
		removablePieces.get(1).trimToSize();
		return removablePieces;
	}

	//Returns the pieceState of adjacent spot to p in given direction
	public int checkAdjacent(Pair p, int direction){
		int xPos = p.getFirst();
		int yPos = p.getSecond();
		int adjacentPieceState = -1; //If -1 is returned, this function was called erroneously
		
		switch(direction){
			//NW
			case 0:	
				adjacentPieceState = boardGrid[yPos-1][xPos-1];
				break;
			//N
			case 1:	
				adjacentPieceState = boardGrid[yPos-1][xPos];
				break;
			//NE
			case 2:	
				adjacentPieceState = boardGrid[yPos-1][xPos+1];
				break;
			//E
			case 3:	
				adjacentPieceState = boardGrid[yPos][xPos+1];
				break;
			//SE
			case 4:	
				adjacentPieceState = boardGrid[yPos+1][xPos+1];
				break;
			//S
			case 5:	
				adjacentPieceState = boardGrid[yPos+1][xPos];
				break;
			//SW
			case 6:	
				adjacentPieceState = boardGrid[yPos+1][xPos-1];
				break;
			//W
			case 7:	
				adjacentPieceState = boardGrid[yPos][xPos-1];
				break;
			
			default: 
				break;
		}
		
		return adjacentPieceState;
	}
	
	//Load the ArrayList of pieces that can be captured from getCaptures_Move into removables ArrayList
	public void activateRemovables(Pair p1, Pair p2){
		removables = getCaptures_Move(p1, p2);
	}
	
	//Empties the removables ArrayList
	public void deactivateRemovables(){
		removables.clear();
	}
	
}
