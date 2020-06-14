import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Pedal {
	
	public static final double DEFAULT_WIDTH_PANEL = 600;
	public static final double DEFAULT_HEIGHT_PANEL = 600;
	public static final double DEFAULT_PADDLE_SIZE = 100;
	private double paddleSize;
	private double dx;
	private double dy;
	private boolean horizontal;
	public int springPower = 0;
	public long springTime;
	public boolean canSpring = false;
	/**pedal's x coordinate
	 */
	private double myX;
	/**pedal's y coordinate
	 */
	private double myY;
	//have to add direction later
	private Color myColor;
	
	public double getPaddleSize() {
		return paddleSize;
	}

	public void setPaddleSize(double paddleSize) {
		this.paddleSize = paddleSize;
	}

	private Image image;
	
	public Pedal(double x, double y, Color theColor, boolean orientH) {
		// TODO Auto-generated constructor stub
		// set up starting coordinates of the ball.
		myX = x;
		myY = y;
		myColor = theColor;
		paddleSize = DEFAULT_PADDLE_SIZE;
		ImageIcon ii = new ImageIcon("paddle.png");
        image = ii.getImage();
        horizontal = orientH;
		
	}

	public boolean isHorizontal(){
		return horizontal;
	}
	public double getXSpeed(){
		return dx;
	}
	public double getYSpeed(){
		return dy;
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
	public void moveX() {

        myX += dx;

        if (myX <= -30) {
            myX = -30;
        }
        

        if (myX >= DEFAULT_WIDTH_PANEL - 70) {
            myX = DEFAULT_WIDTH_PANEL - 70;
        }
    }
	
	public void moveY() {

        myY += dy;

        if (myY <= -30) {
            myY = -30;
        }
        

        if (myY >= DEFAULT_WIDTH_PANEL - 70) {
            myY = DEFAULT_WIDTH_PANEL - 70;
        }
    }
	
	public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            if(dx >= -1.5){dx= -3.5;}
            else {dx = dx - 0.5;}
        }

        if (key == KeyEvent.VK_RIGHT) {
        	if(dx <= 1.5){dx= 3.5;}
            else {dx = dx + 0.5;}
        }
        
        if (key == KeyEvent.VK_UP) {
            if(dy >= -1.5){dy= -3.5;}
            else {dy = dy - 0.5;}
        }

        if (key == KeyEvent.VK_DOWN) {
        	if(dy <= 1.5){dy= 3.5;}
            else {dy = dy + 0.5;}
        }
        if (key == KeyEvent.VK_SPACE) {
        	springPower++;
        	if(springPower < 4){
        	canSpring=true;
			springTime = System.currentTimeMillis();}
        	else {canSpring = false;}
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
        
        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
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
	
	public Rectangle getRect() {
        return new Rectangle((int)myX, (int)myY,
                (int) getPaddleSize(), 20);
    }
	
	public Rectangle getRectV() {
        return new Rectangle((int)myX, (int)myY,
                20, (int)getPaddleSize());
    }
	
	public void setXSpeed(double sp){
		dx=sp;
	}
	
	public void setYSpeed(double sp){
		dy=sp;
	}
	
}
