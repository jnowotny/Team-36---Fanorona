/**Imports*/
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;


/**Class Definition*/
public class Piece extends JComponent implements MouseListener{
	private static final long serialVersionUID = -7913822887443560796L;
/**Data members*/
	
	/**Basic data-types*/
	private boolean isHighlighted;
	private boolean isSacrificed;
	private int x_cord;
	private int y_cord;
	private int pieceState;
	private int xPos;
	private int yPos;
	
	/**Objects*/
	private Color maroon = new Color(80,0,30);
	private Board brd;

/**Constructor*/
	public Piece(int x, int y, int pieceSt, Board b, int x_pos, int y_pos){
		brd = b;
		x_cord = x;
		y_cord = y;
		xPos = x_pos;
		yPos = y_pos;
		pieceState = pieceSt;
		isHighlighted = false;
		isSacrificed = false;
		setBounds(x_cord-3,y_cord-3,26,26);
		setVisible(true);
		this.addMouseListener(this);
	}

/**Graphical methods*/
	//Draw the piece and any additional graphical modifications
	public void paintComponent(Graphics2D g2){
		super.paintComponent(g2);
		//Draw white circle pieces for active white pieces(1) and sacrificed white pieces(-1)
		if(pieceState == 1 || pieceState == -1){
			Ellipse2D e = new Ellipse2D.Double(this.getLocation().x + 3, this.getLocation().y + 3, 20, 20);
			g2.setStroke(new BasicStroke(1));
			g2.setColor(Color.white);
		    g2.fill(e);
		}
		//Draw maroon circle pieces for active white pieces(2) and sacrificed maroon pieces(-2)
		else if(pieceState == 2 || pieceState == -2){
			Ellipse2D e = new Ellipse2D.Double(this.getLocation().x + 3, this.getLocation().y + 3, 20, 20);
			g2.setStroke(new BasicStroke(1));
			g2.setColor(maroon);
		    g2.fill(e);
		}
		//Highlight pieces that have isHighlight==true
		if(isHighlighted){
			highlight(g2);
		}
		//Sacrifice-specific type of highlight applied to pieces with isSacrificed==true
		else if(isSacrificed){
			sacrificialHighlight(g2);
		}
	}
	//Draw a yellow circle around the piece
	public void highlight(Graphics2D g2){
		Ellipse2D e = new Ellipse2D.Double(this.getLocation().x+2, this.getLocation().y+2, 22, 22);
		g2.setStroke(new BasicStroke(3));
	    g2.setColor(Color.yellow);
	    g2.draw(e);
	}
	//Draw a black circle with an X through it on the piece
	public void sacrificialHighlight(Graphics2D g2){
		Ellipse2D e = new Ellipse2D.Double(this.getLocation().x+2, this.getLocation().y+2, 22, 22);
		g2.setStroke(new BasicStroke(3));
	    g2.setColor(Color.black);
	    g2.draw(e);
	    //Lines of the X
	    g2.drawLine(this.getLocation().x, this.getLocation().y, this.getLocation().x+26, this.getLocation().y+26);
	    g2.drawLine(this.getLocation().x, this.getLocation().y+26, this.getLocation().x+26, this.getLocation().y);
	}

/**Get-methods*/
	public boolean getHighlight(){
		return isHighlighted;
	}
	public int getPieceState(){
		return pieceState;
	}
			
	
/**Update-methods*/
	public void setHighlight(boolean b){
		isHighlighted = b;
	}
	public void setPieceState(int pieceSt){
		//	Empty	  0	||	White 	 1	||	Maroon	  2	||White(sac)  -1 ||Maroon(sac) -2
		if(pieceSt == 0 || pieceSt == 1 || pieceSt == 2 || pieceSt == -1 || pieceSt == -2){
			pieceState = pieceSt;
		}
	}
	public void setSacrifice(boolean b){
		isSacrificed = b;
	}

/**MouseListener overridden methods*/
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}
//Unused mouseListener functions that are required to be overridden	
	@Override
	public void mousePressed(MouseEvent arg0) {
		//If makingSacrifice is set true by sacrificeButton && the piece belongs to the current player, sacrifice it
		if( (brd.isMakingSacrifice()) && (pieceState == brd.getBoardState().getCurrentPlayer()) ){
			brd.sacrifice(new Pair(xPos,yPos));
		}
		//Otherwise, move-relevant actions can only apply to highlighted pieces 
		else if (isHighlighted){
			//If no piece selected at time of click, select the clicked piece
			if(brd.getSelected() == null){
				brd.setHighlightAll(false, true);
				setHighlight(true);
				brd.select(new Pair(xPos,yPos));
			}
			//If clicked piece matches selected piece , deselect it
			else if((xPos == brd.getSelected().getFirst()) && (yPos == brd.getSelected().getSecond())){
				brd.setHighlightAll(false, true);
				brd.select(null);
			}
			//If clicked piece is empty, move selected piece there
			else if(pieceState == 0){
				//If the move has capture options, make a "capture" move
				if(brd.getBoardState().hasCaptures_Move(brd.getSelected(), new Pair(xPos, yPos))){
					brd.move(brd.getSelected(), new Pair(xPos, yPos), "capture");
				}
				//If the move does not have capture options, make a "paika" move
				else {
					brd.move(brd.getSelected(), new Pair(xPos, yPos), "paika");					
				}
			}
			//If clicked piece belongs to currentPlayer's opponent
			else if(pieceState == brd.getBoardState().getNextPlayer()){
				//Find a matching pair in ArrayList removables, then remove all pieces in that ArrayList
				for(int i = 0; i < brd.getBoardState().getRemovables().size(); i++){
					for(int j = 0; j < brd.getBoardState().getRemovables().get(i).size(); j++){
						if(	brd.getBoardState().getRemovables().get(i).get(j).isEqualTo(new Pair(xPos, yPos))){
							brd.removeRemovables(i);
							brd.updateSelected();
							break;
						}
					}
				}
			}
		}
		//Deselect currently selected piece by clicking any un-highlighted piece
		else if(brd.getVisited().isEmpty()){
			brd.setHighlightAll(false, true);
			brd.select(null);
		}
		super.repaint();
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	
}
