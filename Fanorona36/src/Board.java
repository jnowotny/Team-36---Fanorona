import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.*;


public class Board extends JPanel {

	private static final long serialVersionUID = -9165633611662257140L;
	
	private BoardState boardState;
	
	private Piece[][] boardPieces = new Piece[5][9];
	/*	Below demonstrates the initial piece layout on the board
	 *  x = maroon piece
	 *  o = white piece
	 *  e = empty space 
	 * 		(empty spaces not currently represented as a piece object..so nothing is in matrix at [2][4])
	 * 
	 *		x x x x x x x x x
	 *		x x x x x x x x x
	 * 		x o x o e x o x o
	 * 		o o o o o o o o o 
	 * 		o o o o o o o o o		
	 */
	
	public Board() {
		//Row-by-row initialization of pieces at start positions on board
		this.setLayout(null);
		boardState = new BoardState();
		
		//Row 0
	    boardPieces[0][0] = new Piece(155, 90, 2, this, 0, 0);
	    boardPieces[0][1] = new Piece(211, 90, 2, this, 0, 1);
	    boardPieces[0][2] = new Piece(268, 90, 2, this, 0, 2);
	    boardPieces[0][3] = new Piece(324, 90, 2, this, 0, 3);
	    boardPieces[0][4] = new Piece(380, 90, 2, this, 0, 4);
	    boardPieces[0][5] = new Piece(436, 90, 2, this, 0, 5);
	    boardPieces[0][6] = new Piece(493, 90, 2, this, 0, 6);
	    boardPieces[0][7] = new Piece(549, 90, 2, this, 0, 7);
	    boardPieces[0][8] = new Piece(605, 90, 2, this, 0, 8);
	    //Row 1
	    boardPieces[1][0] = new Piece(155, 152, 2, this, 1, 0);
	    boardPieces[1][1] = new Piece(211, 152, 2, this, 1, 1);
	    boardPieces[1][2] = new Piece(268, 152, 2, this, 1, 2);
	    boardPieces[1][3] = new Piece(324, 152, 2, this, 1, 3);
	    boardPieces[1][4] = new Piece(380, 152, 2, this, 1, 4);
	    boardPieces[1][5] = new Piece(436, 152, 2, this, 1, 5);
	    boardPieces[1][6] = new Piece(493, 152, 2, this, 1, 6);
	    boardPieces[1][7] = new Piece(549, 152, 2, this, 1, 7);
	    boardPieces[1][8] = new Piece(605, 152, 2, this, 1, 8);
	    //Row 2
	    boardPieces[2][0] = new Piece(155, 215, 2, this, 2, 0);
	    boardPieces[2][1] = new Piece(211, 215, 1, this, 2, 1);
	    boardPieces[2][2] = new Piece(268, 215, 2, this, 2, 2);
	    boardPieces[2][3] = new Piece(324, 215, 1, this, 2, 3);
	    boardPieces[2][4] = new Piece(380, 215, 0, this, 2, 4);
	    boardPieces[2][5] = new Piece(436, 215, 2, this, 2, 5);
	    boardPieces[2][6] = new Piece(493, 215, 1, this, 2, 6);
	    boardPieces[2][7] = new Piece(549, 215, 2, this, 2, 7);
	    boardPieces[2][8] = new Piece(605, 215, 1, this, 2, 8);
	    //Row 3
	    boardPieces[3][0] = new Piece(155, 277, 1, this, 3, 0);
	    boardPieces[3][1] = new Piece(211, 277, 1, this, 3, 1);
	    boardPieces[3][2] = new Piece(268, 277, 1, this, 3, 2);
	    boardPieces[3][3] = new Piece(324, 277, 1, this, 3, 3);
	    boardPieces[3][4] = new Piece(380, 277, 1, this, 3, 4);
	    boardPieces[3][5] = new Piece(436, 277, 1, this, 3, 5);
	    boardPieces[3][6] = new Piece(493, 277, 1, this, 3, 6);
	    boardPieces[3][7] = new Piece(549, 277, 1, this, 3, 7);
	    boardPieces[3][8] = new Piece(605, 277, 1, this, 3, 8);
	    //Row 4
	    boardPieces[4][0] = new Piece(155, 340, 1, this, 4, 0);
	    boardPieces[4][1] = new Piece(211, 340, 1, this, 4, 1);
	    boardPieces[4][2] = new Piece(268, 340, 1, this, 4, 2);
	    boardPieces[4][3] = new Piece(324, 340, 1, this, 4, 3);
	    boardPieces[4][4] = new Piece(380, 340, 1, this, 4, 4);
	    boardPieces[4][5] = new Piece(436, 340, 1, this, 4, 5);
	    boardPieces[4][6] = new Piece(493, 340, 1, this, 4, 6);
	    boardPieces[4][7] = new Piece(549, 340, 1, this, 4, 7);
	    boardPieces[4][8] = new Piece(605, 340, 1, this, 4, 8);
	    
	    for(int i = 0; i < 5; i++){
	    	for(int j = 0; j < 9; j++){
	    		this.add(boardPieces[i][j]);
	    	}
	    }
	    
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
	    
		drawBoard(g2);
		drawPieces(g2);
    }
	
	public void drawBoard(Graphics2D g2){
		Rectangle2D backdrop = new Rectangle2D.Double(0, 0, 800, 600);
		g2.setStroke(new BasicStroke(1));
	    g2.setColor(Color.gray);
	    g2.fill(backdrop);

	    Rectangle2D boardBase = new Rectangle2D.Double(165, 100, 450, 250);
	    g2.setStroke(new BasicStroke(3));
	    Color maroon = new Color(80,0,30);
	    g2.setColor(maroon);
	    g2.draw(boardBase);

	    //diagonal (slant down left->right)
	    g2.drawLine(165, 225, 278, 350);
	    g2.drawLine(165, 100, 390, 350);
	    g2.drawLine(278, 100, 503, 350);
	    g2.drawLine(390, 100, 615, 350);
	    g2.drawLine(503, 100, 615, 225);
	    //diagonal (slant up left->right)
	    g2.drawLine(165, 225, 278, 100);
	    g2.drawLine(390, 350, 615, 100);
	    g2.drawLine(278, 350, 503, 100);
	    g2.drawLine(165, 350, 390, 100);
	    g2.drawLine(503, 350, 615, 225);
	    //vertical
	    g2.drawLine(221, 100, 221, 350);
	    g2.drawLine(278, 100, 278, 350);
	    g2.drawLine(334, 100, 334, 350);
	    g2.drawLine(390, 100, 390, 350);
	    g2.drawLine(446, 100, 446, 350);
	    g2.drawLine(503, 100, 503, 350);
	    g2.drawLine(559, 100, 559, 350);
	    //horizontal
	    g2.drawLine(165, 162, 615, 162);
	    g2.drawLine(165, 225, 615, 225);
	    g2.drawLine(165, 287, 615, 287);
	}

	public void drawPieces(Graphics2D g2){
		for(int i = 0; i < 5; i++){
	    	for(int j = 0; j < 9; j++){
    			boardPieces[i][j].paintComponent(g2);
	    	}
	    }
	}

	public Piece[][] getBoardPieces() {
		return boardPieces;
	}

	public void setBoardPieces(Piece[][] boardPieces) {
		this.boardPieces = boardPieces;
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
	public void updateScores(){
		boardState.updateScores();
	}
	
	public void nextTurn(){
		setHighlightAll(false);
		boardState.nextCurrentPlayer();
		boardState.updateTurnCount();
		highlightMovablePieces();
	}
	
	//For a position [i][j] checks for empty spot (0) in surrounding positions
	public boolean checkMovable(int i, int j, boolean doHighlight){
		boolean isMovable = false;
		if(i >= 0 && i <= 4 && j >= 0 && j <=8){
			if(boardState.getCurrentPlayer() == boardState.getBoardGrid()[i][j]){
				if( (i == 1 && j == 1) || (i == 1 && j == 3) || (i == 1 && j == 5) ||
					(i == 1 && j == 7) || (i == 2 && j == 2) || (i == 2 && j == 4) ||
					(i == 2 && j == 6) || (i == 3 && j == 1) || (i == 3 && j == 3) ||
					(i == 3 && j == 5) || (i == 3 && j == 7) ){
					//NW
					if(boardState.getBoardGrid()[i-1][j-1] == 0){
						if(doHighlight){
						boardPieces[i-1][j-1].setHighlight(true);
						}
						isMovable = true;
					}
					//N
					if(boardState.getBoardGrid()[i-1][j] == 0){
						if(doHighlight){
							boardPieces[i-1][j].setHighlight(true);
						}
						isMovable = true;
					}
					//NE
					if(boardState.getBoardGrid()[i-1][j+1] == 0){
						if(doHighlight){
							boardPieces[i-1][j+1].setHighlight(true);
						}
						isMovable = true;
					}
					//E
					if(boardState.getBoardGrid()[i][j+1] == 0){
						if(doHighlight){
							boardPieces[i][j+1].setHighlight(true);
						}
						isMovable = true;
					}
					//SE
					if(boardState.getBoardGrid()[i+1][j+1] == 0){
						if(doHighlight){
							boardPieces[i+1][j+1].setHighlight(true);
						}
						isMovable = true;
					}
					//S
					if(boardState.getBoardGrid()[i+1][j] == 0){
						if(doHighlight){
							boardPieces[i+1][j].setHighlight(true);
						}
						isMovable = true;
					}
					//SW
					if(boardState.getBoardGrid()[i+1][j-1] == 0){
						if(doHighlight){
							boardPieces[i+1][j-1].setHighlight(true);
						}
						isMovable = true;
					}
					//W
					if(boardState.getBoardGrid()[i][j-1] == 0){
						if(doHighlight){
							boardPieces[i][j-1].setHighlight(true);
						}
						isMovable = true;
					}
				}
				
				if( (i == 1 && j== 2) ||  (i == 1 && j == 4) || (i == 1 && j == 6) ||
					(i == 2 && j== 1) ||  (i == 2 && j == 3) || (i == 2 && j == 5) ||
					(i == 2 && j== 7) ||  (i == 3 && j == 2) || (i == 3 && j == 4) ||
					(i == 3 && j== 6) ){
					//N
					if(boardState.getBoardGrid()[i-1][j] == 0){
						if(doHighlight){
							boardPieces[i-1][j].setHighlight(true);
						}
						isMovable = true;
					}
					//E
					if(boardState.getBoardGrid()[i][j+1] == 0){
						if(doHighlight){
							boardPieces[i][j+1].setHighlight(true);
						}
						isMovable = true;
					}
					//S
					if(boardState.getBoardGrid()[i+1][j] == 0){
						if(doHighlight){
							boardPieces[i+1][j].setHighlight(true);
						}
						isMovable = true;
					}
					//W
					if(boardState.getBoardGrid()[i][j-1] == 0){
						if(doHighlight){
							boardPieces[i][j-1].setHighlight(true);
						}
						isMovable = true;
					}
				}
				
				if( (i == 0 && j == 2) ||  (i == 0 && j == 4) || (i == 0 && j == 6) ){
					//E
					if(boardState.getBoardGrid()[i][j+1] == 0){
						if(doHighlight){
							boardPieces[i][j+1].setHighlight(true);
						}
						isMovable = true;
					}
					//SE
					if(boardState.getBoardGrid()[i+1][j+1] == 0){
						if(doHighlight){
							boardPieces[i+1][j+1].setHighlight(true);
						}
						isMovable = true;
					}
					//S
					if(boardState.getBoardGrid()[i+1][j] == 0){
						if(doHighlight){
							boardPieces[i+1][j].setHighlight(true);
						}
						isMovable = true;
					}
					//SW
					if(boardState.getBoardGrid()[i+1][j-1] == 0){
						if(doHighlight){
							boardPieces[i+1][j-1].setHighlight(true);
						}
						isMovable = true;
					}
					//W
					if(boardState.getBoardGrid()[i][j-1] == 0){
						if(doHighlight){
							boardPieces[i][j-1].setHighlight(true);
						}
						isMovable = true;
					}
				}
				
				if( (i == 4 && j == 2) ||  (i == 4 && j == 4) || (i == 4 && j == 6) ){
					//NW
					if(boardState.getBoardGrid()[i-1][j-1] == 0){
						if(doHighlight){
							boardPieces[i-1][j-1].setHighlight(true);
						}
						isMovable = true;
					}
					//N
					if(boardState.getBoardGrid()[i-1][j] == 0){
						if(doHighlight){
							boardPieces[i-1][j].setHighlight(true);
						}
						isMovable = true;
					}
					//NE
					if(boardState.getBoardGrid()[i-1][j+1] == 0){
						if(doHighlight){
							boardPieces[i-1][j+1].setHighlight(true);
						}
						isMovable = true;
					}
					//E
					if(boardState.getBoardGrid()[i][j+1] == 0){
						if(doHighlight){
							boardPieces[i][j+1].setHighlight(true);
						}
						isMovable = true;
					}
					//W
					if(boardState.getBoardGrid()[i][j-1] == 0){
						if(doHighlight){
							boardPieces[i][j-1].setHighlight(true);
						}
						isMovable = true;
					}
				}
				
				if( (i == 2 && j == 0) ){
					//N
					if(boardState.getBoardGrid()[i-1][j] == 0){
						if(doHighlight){
							boardPieces[i-1][j].setHighlight(true);
						}
						isMovable = true;
					}
					//NE
					if(boardState.getBoardGrid()[i-1][j+1] == 0){
						if(doHighlight){
							boardPieces[i-1][j+1].setHighlight(true);
						}
						isMovable = true;
					}
					//E
					if(boardState.getBoardGrid()[i][j+1] == 0){
						if(doHighlight){
							boardPieces[i][j+1].setHighlight(true);
						}
						isMovable = true;
					}
					//SE
					if(boardState.getBoardGrid()[i+1][j+1] == 0){
						if(doHighlight){
							boardPieces[i+1][j+1].setHighlight(true);
						}
						isMovable = true;
					}
					//S
					if(boardState.getBoardGrid()[i+1][j] == 0){
						if(doHighlight){
							boardPieces[i+1][j].setHighlight(true);
						}
						isMovable = true;
					}
				}
				
				if( (i == 2 && j == 8) ){
					//NW
					if(boardState.getBoardGrid()[i-1][j-1] == 0){
						if(doHighlight){
							boardPieces[i-1][j-1].setHighlight(true);
						}
						isMovable = true;
					}
					//N
					if(boardState.getBoardGrid()[i-1][j] == 0){
						if(doHighlight){
							boardPieces[i-1][j].setHighlight(true);
						}
						isMovable = true;
					}
					//S
					if(boardState.getBoardGrid()[i+1][j] == 0){
						if(doHighlight){
							boardPieces[i+1][j].setHighlight(true);
						}
						isMovable = true;
					}
					//SW
					if(boardState.getBoardGrid()[i+1][j-1] == 0){
						if(doHighlight){
							boardPieces[i+1][j-1].setHighlight(true);
						}
						isMovable = true;
					}
					//W
					if(boardState.getBoardGrid()[i][j-1] == 0){
						if(doHighlight){
							boardPieces[i][j-1].setHighlight(true);
						}
						isMovable = true;
					}
				}
				
				if( (i == 0 && j == 1) || (i == 0 && j == 3) || (i == 0 && j == 5) || (i == 0 && j == 7) ){					
					//E
					if(boardState.getBoardGrid()[i][j+1] == 0){
						if(doHighlight){
							boardPieces[i][j+1].setHighlight(true);
						}
						isMovable = true;
					}
					//S
					if(boardState.getBoardGrid()[i+1][j] == 0){
						if(doHighlight){
							boardPieces[i+1][j].setHighlight(true);
						}
						isMovable = true;
					}
					//W
					if(boardState.getBoardGrid()[i][j-1] == 0){
						if(doHighlight){
							boardPieces[i][j-1].setHighlight(true);
						}
						isMovable = true;
					}
				}
				
				if( (i == 4 && j == 1) || (i == 4 && j == 3) || (i == 4 && j == 5) || (i == 4 && j == 7) ){
					//N
					if(boardState.getBoardGrid()[i-1][j] == 0){
						if(doHighlight){
							boardPieces[i-1][j].setHighlight(true);
						}
						isMovable = true;
					}
					//E
					if(boardState.getBoardGrid()[i][j+1] == 0){
						if(doHighlight){
							boardPieces[i][j+1].setHighlight(true);
						}
						isMovable = true;
					}
					
					//W
					if(boardState.getBoardGrid()[i][j-1] == 0){
						if(doHighlight){
							boardPieces[i][j-1].setHighlight(true);
						}
						isMovable = true;
					}
				}
				
				if( (i == 1 && j == 0) || (i == 3 && j == 0) ){
					//N
					if(boardState.getBoardGrid()[i-1][j] == 0){
						if(doHighlight){
							boardPieces[i-1][j].setHighlight(true);
						}
						isMovable = true;
					}
					//E
					if(boardState.getBoardGrid()[i][j+1] == 0){
						if(doHighlight){
							boardPieces[i][j+1].setHighlight(true);
						}
						isMovable = true;
					}
					//S
					if(boardState.getBoardGrid()[i+1][j] == 0){
						if(doHighlight){
							boardPieces[i+1][j].setHighlight(true);
						}
						isMovable = true;
					}					
				}
				
				if( (i == 1 && j == 8) || (i == 3 && j == 8) ){
					//N
					if(boardState.getBoardGrid()[i-1][j] == 0){
						if(doHighlight){
							boardPieces[i-1][j].setHighlight(true);
						}
						isMovable = true;
					}
					//S
					if(boardState.getBoardGrid()[i+1][j] == 0){
						if(doHighlight){
							boardPieces[i+1][j].setHighlight(true);
						}
						isMovable = true;
					}
					//W
					if(boardState.getBoardGrid()[i][j-1] == 0){
						if(doHighlight){
							boardPieces[i][j-1].setHighlight(true);
						}
						isMovable = true;
					}							
				}
				
				if(i == 0 && j == 0){
					//E
					if(boardState.getBoardGrid()[i][j+1] == 0){
						if(doHighlight){
							boardPieces[i][j+1].setHighlight(true);
						}
						isMovable = true;
					}
					//SE
					if(boardState.getBoardGrid()[i+1][j+1] == 0){
						if(doHighlight){
							boardPieces[i+1][j+1].setHighlight(true);
						}
						isMovable = true;
					}
					//S
					if(boardState.getBoardGrid()[i+1][j] == 0){
						if(doHighlight){
							boardPieces[i+1][j].setHighlight(true);
						}
						isMovable = true;
					}
				}
				
				if(i == 0 && j == 8){
					//S
					if(boardState.getBoardGrid()[i+1][j] == 0){
						if(doHighlight){
							boardPieces[i+1][j].setHighlight(true);
						}
						isMovable = true;
					}
					//SW
					if(boardState.getBoardGrid()[i+1][j-1] == 0){
						if(doHighlight){
							boardPieces[i+1][j-1].setHighlight(true);
						}
						isMovable = true;
					}
					//W
					if(boardState.getBoardGrid()[i][j-1] == 0){
						if(doHighlight){
							boardPieces[i][j-1].setHighlight(true);
						}
						isMovable = true;
					}
				}
				
				if(i == 4 && j == 0){
					//N
					if(boardState.getBoardGrid()[i-1][j] == 0){
						if(doHighlight){
							boardPieces[i-1][j].setHighlight(true);
						}
						isMovable = true;
					}
					//NE
					if(boardState.getBoardGrid()[i-1][j+1] == 0){
						if(doHighlight){
							boardPieces[i-1][j+1].setHighlight(true);
						}
						isMovable = true;
					}
					//E
					if(boardState.getBoardGrid()[i][j+1] == 0){
						if(doHighlight){
							boardPieces[i][j+1].setHighlight(true);
						}
						isMovable = true;
					}
				}
				
				if(i == 4 && j == 8){
					//NW
					if(boardState.getBoardGrid()[i-1][j-1] == 0){
						if(doHighlight){
							boardPieces[i-1][j-1].setHighlight(true);
						}
						isMovable = true;
					}
					//N
					if(boardState.getBoardGrid()[i-1][j] == 0){
						if(doHighlight){
							boardPieces[i-1][j].setHighlight(true);
						}
						isMovable = true;
					}
					//W
					if(boardState.getBoardGrid()[i][j-1] == 0){
						if(doHighlight){
							boardPieces[i][j-1].setHighlight(true);
						}
						isMovable = true;
					}
				}
			}
		}
		this.repaint();
		return isMovable;
	}
	
	public boolean checkRemovable(int x1, int y1, int x2, int y2, boolean isInitial, boolean doHighlight){
		boolean hasRemovable = false;
		int xDelta = x2 - x1;
		int yDelta = y2 - y1;
		
		if( (x1 - xDelta >= 0) && (x1 - xDelta <= 4) && (y1 - yDelta >= 0) && (y1 - yDelta <= 8) && (isInitial)){
			if( (boardState.getBoardGrid()[x1-xDelta][y1-yDelta] != 0) && (boardState.getBoardGrid()[x1-xDelta][y1-yDelta] != boardState.getCurrentPlayer())){
				hasRemovable = true;
				boardPieces[x1-xDelta][y1-yDelta].setHighlight(true);
				checkRemovable(x1, y1, x1-xDelta, y1-yDelta, false, doHighlight);
			}
		}
		
		if( (x2 + xDelta >= 0) && (x2 + xDelta <= 4) && (y2 + yDelta >= 0) && (y2 + yDelta <= 8) ){
			if( (boardState.getBoardGrid()[x2+xDelta][y2+yDelta] != 0) && (boardState.getBoardGrid()[x2+xDelta][y2+yDelta] != boardState.getCurrentPlayer())){
				hasRemovable = true;
				boardPieces[x2+xDelta][y2+yDelta].setHighlight(true);
				checkRemovable(x2, y2, x2+xDelta, y2+yDelta, false, doHighlight);
			}
		}
		
		return hasRemovable;
	}
	
	public void removeAdjacentHighlighted(int x, int y){
		if( (x >= 0) && (x <= 4) && (y >= 0) && (y <= 8)){
			//NW
			if( (x > 0) && (y > 0) ){
				if (boardPieces[x-1][y-1].getHighlight() ){
					unHighlight_and_Remove(x-1, y-1);
					removeAdjacentHighlighted(x-1, y-1);
				}
			}
			//N
			if(x > 0){
				if (boardPieces[x-1][y].getHighlight() ){
					unHighlight_and_Remove(x-1, y);
					removeAdjacentHighlighted(x-1, y);
				}
			}
			//NE
			if( (x > 0) && (y < 8)){
				if (boardPieces[x-1][y+1].getHighlight() ){
					unHighlight_and_Remove(x-1, y+1);
					removeAdjacentHighlighted(x-1, y+1);
				}
			}
			//E
			if(y < 8){
				if (boardPieces[x][y+1].getHighlight() ){
					unHighlight_and_Remove(x, y+1);
					removeAdjacentHighlighted(x, y+1);
				}
			}
			//SE
			if( (x < 4) && (y < 8)){
				if (boardPieces[x+1][y+1].getHighlight() ){
					unHighlight_and_Remove(x+1, y+1);
					removeAdjacentHighlighted(x+1, y+1);
				}
			}
			//S
			if(x < 4){
				if (boardPieces[x+1][y].getHighlight() ){
					unHighlight_and_Remove(x+1, y);
					removeAdjacentHighlighted(x+1, y);
				}
			}
			//SW
			if( (x < 4) && (y > 0) ){
				if (boardPieces[x+1][y-1].getHighlight() ){
					unHighlight_and_Remove(x+1, y-1);
					removeAdjacentHighlighted(x+1, y-1);
				}
			}
			//W
			if(y > 0){
				if (boardPieces[x][y-1].getHighlight() ){
					unHighlight_and_Remove(x, y-1);
					removeAdjacentHighlighted(x, y-1);
				}
			}
		}
			
		
	}
	
	public void unHighlight_and_Remove(int x, int y){
		boardPieces[x][y].setHighlight(false);
		boardPieces[x][y].setPieceState(0);
		boardState.setBoardGrid(x, y, 0);
		repaint();
	}
	
	public void setHighlightAll(boolean isHighlight){
		for(int i = 0; i < 5; i++){
	    	for(int j = 0; j < 9; j++){
	    		boardPieces[i][j].setHighlight(isHighlight);
	    	}
		}
	}
	
	public void removeHighlightedPieces(){
		for(int i = 0; i < 5; i++){
	    	for(int j = 0; j < 9; j++){
	    		if(boardPieces[i][j].getHighlight()){
	    			boardPieces[i][j].setPieceState(0);
	    			boardState.setBoardGrid(i, j, 0);
	    			repaint();
	    		}
	    	}
		}
	}
	
	public void highlightMovablePieces(){		
		 for(int i = 0; i < 5; i++){
	    	for(int j = 0; j < 9; j++){
	    		if(checkMovable(i, j, false)){
	    			boardPieces[i][j].setHighlight(true);
	    		}
	    	}
	     }
	}
	
	
	
}