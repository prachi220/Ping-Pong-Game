
import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import javafx.scene.shape.Circle;

public class Pball {

	public static final double DEFAULT_WIDTH_PANEL = 600;
	public static final double DEFAULT_HEIGHT_PANEL = 600;
	
	/**ball's x coordinate
	 */
	private double myX, Xdir;
	/**ball's y coordinate
	 */
	private double myY, Ydir;
	//have to add direction later
	private Color myColor;
	private Image image;
	
	public Pball(Color theColor) {
		// TODO Auto-generated constructor stub
		// set up starting coordinates of the ball.
		resetState();
		Xdir = 1;
		Ydir = -3;
		myColor = theColor;
		
		ImageIcon ii = new ImageIcon("ball.png");
        image = ii.getImage();
		
	}

	public void resetState(){
		myX = 300;
		myY = 300;
	}
	
	public double getMyX() {
		return myX;
	}

	public void setMyX(double myX) {
		this.myX = myX;
	}

	public double getMyY() {
		return myY;
	}

	public void setMyY(double myY) {
		this.myY = myY;
	}

	public Color getMyColor() {
		return myColor;
	}

	public void setMyColor(Color myColor) {
		this.myColor = myColor;
	}
	
	public void move() {
        
        myX += Xdir;
        myY += Ydir;

//        if (myX == 0) {
//            Xdir =2;
//        }
//
//        if (myX == DEFAULT_WIDTH_PANEL - image.getWidth(null)) {
//        	Xdir = -2;
//        }
//
//        if (myY == 0) {
//        	Ydir = 2;
//        }
//        if (myY == DEFAULT_HEIGHT_PANEL - image.getHeight(null)) {
//        	Ydir = -2;
//        }
    }

	public Image getImage() {
		// TODO Auto-generated method stub
		return image;
	}

	public double getHeight() {
		// TODO Auto-generated method stub
		return image.getHeight(null);
	}

	public double getWidth() {
		// TODO Auto-generated method stub
		return image.getWidth(null);
	}
	public Circle getcircle(){
		return new Circle((double) (myX+10),(double) (myY+10), (double)10);
	}
	
	public Rectangle getRect() {
        return new Rectangle((int)myX, (int)myY,
                20, 20);
    }

	public double getXdir() {
		return Xdir;
	}

	public void setXdir(double xdir) {
		Xdir = xdir;
	}

	public double getYdir() {
		return Ydir;
	}

	public void setYdir(double ydir) {
		Ydir = ydir;
	}
	
}