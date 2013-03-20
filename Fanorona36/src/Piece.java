import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;


public class Piece {
	private int x_cord;
	private int y_cord;
	private Color color;
	private Graphics2D gPiece;
	
	public Piece(Graphics2D g, int x, int y, Color c){
		x_cord = x;
		y_cord = y;
		color = c;
		gPiece = g;
		
		draw(x_cord, y_cord, color);
	}
	
	public void draw(int x, int y, Color c){
		Ellipse2D e = new Ellipse2D.Double(x, y, 20, 20);
		gPiece.setStroke(new BasicStroke(1));
	    gPiece.setColor(color);
	    gPiece.fill(e);
	}
	
	public void highlight(){
		Ellipse2D e = new Ellipse2D.Double(x_cord - 1, y_cord - 1, 22, 22);
		gPiece.setStroke(new BasicStroke(3));
	    gPiece.setColor(Color.yellow);
	    gPiece.draw(e);
	}
	
	public boolean isMovable(){
		return true;
	}
	
	public boolean isRemovable(){
		return true;
	}
	
}
