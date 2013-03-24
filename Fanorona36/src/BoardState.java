


public class BoardState {
	private int[][] boardGrid = new int [5][9];
	private int p1Score;			//0-22
	private int p2Score;			//0-22
	private int turnCount;			//1-50
	private int currentPlayer;		//1 or 2 (player), 0: error

	public BoardState(){
		/*	Below demonstrates the initial piece layout on the board
		 *  x = maroon piece
		 *  o = white piece
		 *  e = empty space 
		 * 		(empty spaces not currently represented as a piece object..so nothing is in matrix at [2][4])
		 * 
		 *		2 2 2 2 2 2 2 2 2
		 *		2 2 2 2 2 2 2 2 2
		 * 		2 1 2 1 0 2 1 2 1
		 * 		1 1 1 1 1 1 1 1 1 
		 * 		1 1 1 1 1 1 1 1 1		
		 */
		
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 9; j++){
				boardGrid[i][j] = 2;
				boardGrid[i+3][j] = 1;
			}
		}
		
		boardGrid[2][0] = 2;
		boardGrid[2][1] = 1;
		boardGrid[2][2] = 2;
		boardGrid[2][3] = 1;
		boardGrid[2][4] = 0;
		boardGrid[2][5] = 2;
		boardGrid[2][6] = 1;
		boardGrid[2][7] = 2;
		boardGrid[2][8] = 1;
	}
	
	
	public int[][] getBoardGrid() {
		return boardGrid;
	}

	public void setBoardGrid(int[][] boardGrid) {
		this.boardGrid = boardGrid;
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

	public void setP1Score(int newP1Score){
		p1Score = newP1Score;
	}
	public void setP2Score(int newP2Score){
		p2Score = newP2Score;
	}
	public void updateTurnCount(int newP1Score){
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

}