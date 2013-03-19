import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.*;


public class Board extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9165633611662257140L;

	public void paint(Graphics g)
    {
		super.paint(g);

		Graphics2D g2 = (Graphics2D) g;

		Rectangle2D backdrop = new Rectangle2D.Double(0, 0, 784, 561);
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



	    piece(g2, 155, 90, maroon);
	    piece(g2, 211, 90, maroon);
	    piece(g2, 268, 90, maroon);
	    piece(g2, 324, 90, maroon);
	    piece(g2, 380, 90, maroon);
	    piece(g2, 436, 90, maroon);
	    piece(g2, 493, 90, maroon);
	    piece(g2, 549, 90, maroon);
	    piece(g2, 605, 90, maroon);

	    piece(g2, 155, 152, maroon);
	    piece(g2, 211, 152, maroon);
	    piece(g2, 268, 152, maroon);
	    piece(g2, 324, 152, maroon);
	    piece(g2, 380, 152, maroon);
	    piece(g2, 436, 152, maroon);
	    piece(g2, 493, 152, maroon);
	    piece(g2, 549, 152, maroon);
	    piece(g2, 605, 152, maroon);


	    piece(g2, 155, 215, maroon);
	    piece(g2, 211, 215, Color.white);
	    piece(g2, 268, 215, maroon);
	    piece(g2, 324, 215, Color.white);
	    piece(g2, 436, 215, maroon);
	    piece(g2, 493, 215, Color.white);
	    piece(g2, 549, 215, maroon);
	    piece(g2, 605, 215, Color.white);

	    piece(g2, 155, 277, Color.white);
	    piece(g2, 211, 277, Color.white);
	    piece(g2, 268, 277, Color.white);
	    piece(g2, 324, 277, Color.white);
	    piece(g2, 380, 277, Color.white);
	    piece(g2, 436, 277, Color.white);
	    piece(g2, 493, 277, Color.white);
	    piece(g2, 549, 277, Color.white);
	    piece(g2, 605, 277, Color.white);

	    piece(g2, 155, 340, Color.white);
	    piece(g2, 211, 340, Color.white);
	    piece(g2, 268, 340, Color.white);
	    piece(g2, 324, 340, Color.white);
	    piece(g2, 380, 340, Color.white);
	    piece(g2, 436, 340, Color.white);
	    piece(g2, 493, 340, Color.white);
	    piece(g2, 549, 340, Color.white);
	    piece(g2, 605, 340, Color.white);


    }

	public void piece(Graphics g, int x, int y, Color c){

		Ellipse2D e = new Ellipse2D.Double(x, y, 20, 20);
		((Graphics2D) g).setStroke(new BasicStroke(1));
	    g.setColor(c);
	    ((Graphics2D) g).fill(e);
	}
}