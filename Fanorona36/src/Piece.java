import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Piece extends JComponent implements MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7913822887443560796L;
	
	private int x_cord;
	private int y_cord;
	private Color maroon = new Color(80,0,30);
	private int pieceState;
	private boolean isHighlighted = false;
	private Board brd;
	private int x_pos;
	private int y_pos;
	private int x_movedFrom;
	private int y_movedFrom;
	
	public Piece(int x, int y, int pieceSt, Board b, int xpos, int ypos){
		brd = b;
		x_cord = x;
		y_cord = y;
		x_pos = xpos;
		y_pos = ypos;
		pieceState = pieceSt;
		setBounds(x_cord-3,y_cord-3,26,26);
		setVisible(true);
		this.addMouseListener(this);
	}
	
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
	
	public void setHighlight(boolean b){
		isHighlighted = b;
	}

	public boolean getHighlight(){
		return isHighlighted;
	}
	
	public void setPieceState(int pieceSt){
		if(pieceSt == 0 || pieceSt == 1 || pieceSt == 2){
			pieceState = pieceSt;
		}
	}
	
	public int getPieceState(){
		return pieceState;
	}
	
	public void findMovedFrom(){
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 9; j++){
				if( (brd.getBoardPieces()[i][j].getHighlight()) && 
					(brd.getBoardState().getBoardGrid()[i][j] == brd.getBoardState().getCurrentPlayer()) &&
					(brd.getBoardPieces()[i][j].getPieceState() == brd.getBoardState().getCurrentPlayer()) ){
						x_movedFrom = i;
						y_movedFrom = j;
				}
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (isHighlighted){
			
			if(pieceState == brd.getBoardState().getCurrentPlayer()){
				brd.setHighlightAll(false);
				setHighlight(true);
				brd.checkMovable(x_pos, y_pos, true);
			}
			else if(pieceState == 0){
				findMovedFrom();
				brd.removeHighlightedPieces();
				brd.setHighlightAll(false);
				brd.getBoardState().setBoardGrid(x_pos, y_pos, brd.getBoardState().getCurrentPlayer());
				pieceState = brd.getBoardState().getCurrentPlayer();
				brd.checkRemovable(x_movedFrom, y_movedFrom, x_pos, y_pos, true, true);
			}
			else{
				brd.unHighlight_and_Remove(x_pos, y_pos);
				brd.removeAdjacentHighlighted(x_pos, y_pos);
				brd.setHighlightAll(false);
			}
			
		}
		
		
		super.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
