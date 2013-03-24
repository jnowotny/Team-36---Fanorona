import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;


public class Piece {
	private int x_cord;
	private int y_cord;
	private Color maroon = new Color(80,0,30);
	private int pieceState;
	private boolean isHighlighted = false;
	
	public Piece(int x, int y, int pieceSt){
		x_cord = x;
		y_cord = y;
		pieceState = pieceSt;
	}
	
	public void draw(Graphics2D g2){
		if(pieceState == 1){
			Ellipse2D e = new Ellipse2D.Double(x_cord, y_cord, 20, 20);
			g2.setStroke(new BasicStroke(1));
			g2.setColor(Color.white);
		    g2.fill(e);
		}
		else if(pieceState == 2){
			Ellipse2D e = new Ellipse2D.Double(x_cord, y_cord, 20, 20);
			g2.setStroke(new BasicStroke(1));
			g2.setColor(maroon);
		    g2.fill(e);
		}
		
		if(isHighlighted){
			highlight(g2);
		}
	}
	
	public void highlight(Graphics2D g2){
		Ellipse2D e = new Ellipse2D.Double(x_cord - 1, y_cord - 1, 22, 22);
		g2.setStroke(new BasicStroke(3));
	    g2.setColor(Color.yellow);
	    g2.draw(e);
	}
	
	public void setHighlight(boolean b){
		isHighlighted = b;
	}
	
}
