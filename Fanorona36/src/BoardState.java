


public class BoardState {
	private int boardHeight = 5;
	private int boardWidth = 9;
	private int[][] boardGrid = new int [boardHeight][boardWidth];
	private int p1Score = 22;			//0-22
	private int p2Score = 22;			//0-22
	private int turnCount = 0;			//1-50
	private int currentPlayer = 2;		//1 or 2 (player), 0: error


	public BoardState(){
		/*	Below demonstrates the initial piece layout on the board
		 *  2 = maroon piece
		 *  1 = white piece
		 *  0 = empty space 
		 * 		(empty spaces not currently represented as a piece object..so nothing is in matrix at [2][4])
		 * 
		 *		2 2 2 2 2 2 2 2 2
		 *		2 2 2 2 2 2 2 2 2
		 * 		2 1 2 1 0 2 1 2 1
		 * 		1 1 1 1 1 1 1 1 1 
		 * 		1 1 1 1 1 1 1 1 1		
		 */
		if (boardHeight != 1) {
			for(int i = 0; i < 2; ++i){
				for(int j = 0; j < 9; ++j){
					boardGrid[i][j] = 2;
					boardGrid[i+3][j] = 1;
				}
			}
			boardGrid[boardHeight/2][0] = 2;
			boardGrid[boardHeight/2][1] = 1;
			boardGrid[boardHeight/2][2] = 2;
			boardGrid[boardHeight/2][3] = 1;
			boardGrid[boardHeight/2][4] = 0;
			boardGrid[boardHeight/2][5] = 2;
			boardGrid[boardHeight/2][6] = 1;
			boardGrid[boardHeight/2][7] = 2;
			boardGrid[boardHeight/2][8] = 1;
		}
		else {
			boardGrid[0][0] = 2;
			boardGrid[0][1] = 1;
			boardGrid[0][2] = 2;
			boardGrid[0][3] = 1;
			boardGrid[0][4] = 0;
			boardGrid[0][5] = 2;
			boardGrid[0][6] = 1;
			boardGrid[0][7] = 2;
			boardGrid[0][8] = 1;
		}
	}
	
	
	public int[][] getBoardGrid() {
		return boardGrid;
	}

	public void setBoardGrid(int[][] boardGrid) {
		this.boardGrid = boardGrid;
	}
	
	public void setBoardGrid(int i, int j, int pieceSt){
		if(pieceSt == 0 || pieceSt == 1 || pieceSt == 2){
			this.boardGrid[i][j] = pieceSt;
		}
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
	public void updateScores(){
		int p1Count = 0;
		int p2Count = 0;
		for(int i = 0; i < boardHeight; ++i){
			for(int j = 0; j < boardWidth; ++j){
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

}
