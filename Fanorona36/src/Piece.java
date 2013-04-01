import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Piece extends JComponent implements MouseListener{
	private static final long serialVersionUID = -7913822887443560796L;
//Data members
	private boolean isHighlighted = false;
	private Color maroon = new Color(80,0,30);
	
	private int x_cord;
	private int y_cord;
	private int pieceState;
	private Board brd;
	private int xPos;
	private int yPos;

//Constructor	
	public Piece(int x, int y, int pieceSt, Board b, int x_pos, int y_pos){
		brd = b;
		x_cord = x;
		y_cord = y;
		xPos = x_pos;
		yPos = y_pos;
		pieceState = pieceSt;
		setBounds(x_cord-3,y_cord-3,26,26);
		setVisible(true);
		this.addMouseListener(this);
	}

//Graphical methods
	public void paintComponent(Graphics2D g2){
		super.paintComponent(g2);
		
		
		if(pieceState == 1){
			Ellipse2D e = new Ellipse2D.Double(this.getLocation().x + 3, this.getLocation().y + 3, 20, 20);
			g2.setStroke(new BasicStroke(1));
			g2.setColor(Color.white);
		    g2.fill(e);
		}
		else if(pieceState == 2){
			Ellipse2D e = new Ellipse2D.Double(this.getLocation().x + 3, this.getLocation().y + 3, 20, 20);
			g2.setStroke(new BasicStroke(1));
			g2.setColor(maroon);
		    g2.fill(e);
		}
		
		if(isHighlighted){
			highlight(g2);
		}
	}
	public void highlight(Graphics2D g2){
		Ellipse2D e = new Ellipse2D.Double(this.getLocation().x+2, this.getLocation().y+2, 22, 22);
		g2.setStroke(new BasicStroke(3));
	    g2.setColor(Color.yellow);
	    g2.draw(e);
	}

//Get-methods
	public boolean getHighlight(){
		return isHighlighted;
	}
	public int getPieceState(){
		return pieceState;
	}
			
	
//Update-methods
	public void setHighlight(boolean b){
		isHighlighted = b;
	}
	public void setPieceState(int pieceSt){
		if(pieceSt == 0 || pieceSt == 1 || pieceSt == 2){
			pieceState = pieceSt;
		}
	}


//MouseListener overridden methods
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (isHighlighted){
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
				//If the Move has capture options, make a "capture" move
				if(brd.getBoardState().hasCaptures_Move(brd.getSelected(), new Pair(xPos, yPos))){
					brd.move(brd.getSelected(), new Pair(xPos, yPos), "capture");
				}
				//If the Move does not have capture options, make a "paika" move
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
//							brd.setHighlightAll(false,true);
							break;
						}
					}
				}
			}
		}
		/*	If clicked piece is ( (NOT highlighted) && (no moves have been made this turn) )
		 *		deselect the selected piece
		 */
		
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
	public void mousePressed(MouseEvent arg0) {
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	
}
