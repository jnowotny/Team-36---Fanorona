import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.*;


public class Board extends JPanel {
	
	public Board() {}

	private static final long serialVersionUID = -9165633611662257140L;

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
	
	public void paint(Graphics g)
    {
		super.paint(g);

		Graphics2D g2 = (Graphics2D) g;
	    
		initBoard(g2);
		initPieces(g2);
    }
	
	//Draw board
	public void initBoard(Graphics2D g2){
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
	
	//Row-by-row initialization of pieces at start positions on board
	public void initPieces(Graphics2D g2){
		Color maroon = new Color(80,0,30);
		
		//Row 0
	    boardPieces[0][0] = new Piece(g2, 155, 90, maroon);
	    boardPieces[0][1] = new Piece(g2, 211, 90, maroon);
	    boardPieces[0][2] = new Piece(g2, 268, 90, maroon);
	    boardPieces[0][3] = new Piece(g2, 324, 90, maroon);
	    boardPieces[0][4] = new Piece(g2, 380, 90, maroon);
	    boardPieces[0][5] = new Piece(g2, 436, 90, maroon);
	    boardPieces[0][6] = new Piece(g2, 493, 90, maroon);
	    boardPieces[0][7] = new Piece(g2, 549, 90, maroon);
	    boardPieces[0][8] = new Piece(g2, 605, 90, maroon);
	    //Row 1
	    boardPieces[1][0] = new Piece(g2, 155, 152, maroon);
	    boardPieces[1][1] = new Piece(g2, 211, 152, maroon);
	    boardPieces[1][2] = new Piece(g2, 268, 152, maroon);
	    boardPieces[1][3] = new Piece(g2, 324, 152, maroon);
	    boardPieces[1][4] = new Piece(g2, 380, 152, maroon);
	    boardPieces[1][5] = new Piece(g2, 436, 152, maroon);
	    boardPieces[1][6] = new Piece(g2, 493, 152, maroon);
	    boardPieces[1][7] = new Piece(g2, 549, 152, maroon);
	    boardPieces[1][8] = new Piece(g2, 605, 152, maroon);
	    //Row 2
	    boardPieces[2][0] = new Piece(g2, 155, 215, maroon);
	    boardPieces[2][1] = new Piece(g2, 211, 215, Color.white);
	    boardPieces[2][2] = new Piece(g2, 268, 215, maroon);
	    boardPieces[2][3] = new Piece(g2, 324, 215, Color.white);
	    boardPieces[2][5] = new Piece(g2, 436, 215, maroon);
	    boardPieces[2][6] = new Piece(g2, 493, 215, Color.white);
	    boardPieces[2][7] = new Piece(g2, 549, 215, maroon);
	    boardPieces[2][8] = new Piece(g2, 605, 215, Color.white);
	    //Row 3
	    boardPieces[3][0] = new Piece(g2, 155, 277, Color.white);
	    boardPieces[3][1] = new Piece(g2, 211, 277, Color.white);
	    boardPieces[3][2] = new Piece(g2, 268, 277, Color.white);
	    boardPieces[3][3] = new Piece(g2, 324, 277, Color.white);
	    boardPieces[3][4] = new Piece(g2, 380, 277, Color.white);
	    boardPieces[3][5] = new Piece(g2, 436, 277, Color.white);
	    boardPieces[3][6] = new Piece(g2, 493, 277, Color.white);
	    boardPieces[3][7] = new Piece(g2, 549, 277, Color.white);
	    boardPieces[3][8] = new Piece(g2, 605, 277, Color.white);
	    //Row 4
	    boardPieces[4][0] = new Piece(g2, 155, 340, Color.white);
	    boardPieces[4][1] = new Piece(g2, 211, 340, Color.white);
	    boardPieces[4][2] = new Piece(g2, 268, 340, Color.white);
	    boardPieces[4][3] = new Piece(g2, 324, 340, Color.white);
	    boardPieces[4][4] = new Piece(g2, 380, 340, Color.white);
	    boardPieces[4][5] = new Piece(g2, 436, 340, Color.white);
	    boardPieces[4][6] = new Piece(g2, 493, 340, Color.white);
	    boardPieces[4][7] = new Piece(g2, 549, 340, Color.white);
	    boardPieces[4][8] = new Piece(g2, 605, 340, Color.white);
	    
	    
	    /*
	     * Below loop is just for example purposes, demonstrating highlight effect on every piece.
	     * Some constraints on this approach: highlight() can only be called on a Piece object,
	     * so the empty spots cannot yet be highlighted with this unless empty spots are created as Piece objects
	     * that are simply differentiated by an "empty" attribute.
	     * 
	     */
	    for(int i = 0; i < 5; i++){
	    	for(int j = 0; j < 9; j++){
	    		if(!(i == 2 && j == 4))
	    			boardPieces[i][j].highlight();
	    	}
	    }
	}

	public Piece[][] getBoardPieces() {
		return boardPieces;
	}

	public void setBoardPieces(Piece[][] boardPieces) {
		this.boardPieces = boardPieces;
	}
	
	public void highlightMovablePieces(){
		/*
		 * This should highlight all Pieces that can be moved for the current player's turn
		 */
		
		/*
		 for(int i = 0; i < 5; i++){
	    	for(int j = 0; j < 9; j++){
	    		if(boardPieces[i][j].isMovable())
	    			boardPieces[i][j].highlight();
	    	}
	     }
		 */
	}
	
	public void highlightRemovablePieces(){
		/*
		 * This function should highlight all sets of Pieces that can be chosen to be removed
		 * as a result of a valid move.
		 */
		
		/*
		for(int i = 0; i < 5; i++){
	    	for(int j = 0; j < 9; j++){
	    		if(boardPieces[i][j].isRemovable())
	    			boardPieces[i][j].highlight();
	    	}
	    }
	    */
	}
	
	
}