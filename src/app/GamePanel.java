

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.TimerTask;
import java.util.*;
import java.util.Timer;
import java.util.HashMap;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;


import javax.swing.JPanel;

/**
 * the inner panel for displaying the game objects.
 * @author sarvesh_c
 *
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	
	public static final double DEFAULT_WIDTH = 600;
	public static final double DEFAULT_HEIGHT = 600;
	private final Timer myTimer;
    private String message = "Game Over";
    private Pball ball;
	
	private Pedal[] paddleArr = new Pedal[4];
	private ArrayList<Integer> others = new ArrayList<>();
	private HashMap<Integer,Long> Tstamps = new HashMap<>();
	private int lastBallTouch, bigOne;
	private boolean[] PisBig = new boolean[4];
	private long[] bigTime = new long[4];
	private int[] life = new int[4];
	
//    private Pedal paddleBottom;
//    private Pedal paddleTop;
//    private Pedal paddleLeft;
//    private Pedal paddleRight;
    private boolean ingame = true;
    private boolean newgame = true;
    String ip;
    ReceiveData rec;
    int bak=0;
    MulticastSocket socket;
    InetAddress group;
    int port;
    int myTag = 0;		//0 to 3
    int ServerTag = 0;
    byte[] buf = new byte[256];
    byte[] buf2 = new byte[256];
	public boolean gstop=false;
	public int winner;
	public int difficulty = 8;
	public GamePanel(ArrayList<String> als) {
		super();
		// TODO Auto-generated constructor stub
		myTag = Integer.parseInt(als.get(0));
		ip = als.get(1);
		port = Integer.parseInt(als.get(2));
		int sz = als.size();
		String tempdiff = als.get(3);
		if(tempdiff.equals("easy")){difficulty=14;}
		else 
			if(tempdiff.equals("medium")){difficulty=8;}
		else 
				if(tempdiff.equals("hard")){difficulty=3;}
		for (int i=4; i<sz; i++){
			others.add(Integer.parseInt(als.get(i)));
		}
		for(int i =0; i<others.size(); i++){
			Tstamps.put(others.get(i), System.currentTimeMillis());
		}
		addKeyListener(new TAdapter());
        setFocusable(true);
        Arrays.fill(PisBig, Boolean.FALSE);
        Arrays.fill(life, 0);
        setDoubleBuffered(true);
		myTimer = new Timer();
        myTimer.scheduleAtFixedRate(new ScheduleTask(), 200, 10);
        ingame = true;
        setVisible(true);
        //setPreferredSize(new Dimension((int)DEFAULT_WIDTH +30,(int)DEFAULT_HEIGHT+30));
    
	}
	
	@Override
        public void addNotify() {

        super.addNotify();
        gameInit();
    }
	
	private void gameInit() {

        ball = new Pball(Color.white);
		
        paddleArr[0] = new Pedal(250, 570, Color.BLUE, true);			//bottom
        paddleArr[1] = new Pedal(250, 10, Color.BLUE, true);			//top
        paddleArr[2] = new Pedal(570, 250, Color.BLUE, false);			//right
        paddleArr[3] = new Pedal(10, 250, Color.BLUE, false);			//left
        //others.add(1);
        
    }
	@Override
	public void paint(Graphics g) {
	    // Let UI Delegate paint first, which 
	    // includes background filling since 
	    // this component is opaque.

	    super.paint(g); 
	    g.fillRect(0, 0, 600, 600);
	    g.setColor(Color.blue);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.blue);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
	    
        if(gstop){
        	winnerDis(g2d, winner);
        }
        
        if (ingame) {
            //System.out.println("chal!");
            //System.out.println(ball.getMyY());
            drawObjects(g2d);
            for(int i =0; i<4; i++){
            scoreDisp(g2d, i);}
        } else {
        	drawObjects(g2d);
            gameFinished(g2d);
        }
        
        Toolkit.getDefaultToolkit().sync();

	} 
	
	private void winnerDis(Graphics2D g2d, int winner2) {
		Font font = new Font("Verdana", Font.BOLD, 18);
        FontMetrics metr = this.getFontMetrics(font);

        g2d.setColor(Color.ORANGE);
        g2d.setFont(font);
        g2d.drawString("Winner is player "+winner2,(int)
                (DEFAULT_WIDTH - metr.stringWidth("Winner is player "+winner2)) / 2,
                400);
		
		
	}

	private void drawObjects(Graphics2D g2d) {
        g2d.setColor(ball.getMyColor());
        g2d.fillOval((int)ball.getMyX(),(int) ball.getMyY(),
                (int)ball.getcircle().getRadius()*2, 20);
        g2d.setColor(paddleArr[0].getMyColor());
        g2d.fillRect((int)paddleArr[0].getMyX(),(int) paddleArr[0].getMyY(),
        		(int)paddleArr[0].getPaddleSize(), 20);
        g2d.setColor(paddleArr[1].getMyColor());
         g2d.fillRect((int)paddleArr[1].getMyX(),(int) paddleArr[1].getMyY(),
        		 (int)paddleArr[1].getPaddleSize(), 20);
        g2d.setColor(paddleArr[2].getMyColor());
          g2d.fillRect((int)paddleArr[2].getMyX(),(int) paddleArr[2].getMyY(),
                20, (int)paddleArr[2].getPaddleSize());
  		//System.out.println("Drawing...");
        g2d.setColor(paddleArr[3].getMyColor());
         g2d.fillRect((int)paddleArr[3].getMyX(),(int) paddleArr[3].getMyY(),
                20, (int)paddleArr[3].getPaddleSize());
             }
	private void gameFinished(Graphics2D g2d) {

        Font font = new Font("Verdana", Font.BOLD, 18);
        FontMetrics metr = this.getFontMetrics(font);

        g2d.setColor(Color.ORANGE);
        g2d.setFont(font);
        g2d.drawString(message,(int)
                (DEFAULT_WIDTH - metr.stringWidth(message)) / 2,
                (int)DEFAULT_WIDTH/ 2);
    }
	
	private void scoreDisp(Graphics2D g2d, int p) {

        Font font = new Font("Verdana", Font.BOLD, 10);
        FontMetrics metr = this.getFontMetrics(font);
        int placex = 0, placey= 0;
        if(p==0){
        	placex = (int)(DEFAULT_WIDTH - metr.stringWidth(message)) / 2;
        	placey = 560;
        }
        if(p==1){
        	placex = (int)(DEFAULT_WIDTH - metr.stringWidth(message)) / 2;
        	placey = 40;
        }
        if(p==2){
        	placex = (int)(DEFAULT_WIDTH - metr.stringWidth(message)-110);
        	placey = 300;
        }
        if(p==3){
        	placex = 40;
        	placey = 300;
        }
        
        g2d.setColor(Color.red);
        g2d.setFont(font);
        if(life[p]<4){
        	if(paddleArr[p].springPower<4){
                g2d.drawString("Lives used: "+life[p]+" Spring used: "+paddleArr[p].springPower,placex,placey);

        	}
        	else{
                g2d.drawString("Lives used: "+life[p]+" Spring used: "+3,placex,placey);

        	}
        }
        else{
        	if(paddleArr[p].springPower<4){
                g2d.drawString("Lives used: "+3+" Spring used: "+paddleArr[p].springPower,placex,placey);

        	}
        	else{
                g2d.drawString("Lives used: "+3+" Spring used: "+3,placex,placey);

        	}
        }
    }
	
	private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
			paddleArr[myTag].keyReleased(e);
			
			
//            for(int i =0; i<4; i++){
//				paddleArr[i].keyReleased(e);
//				}
			
        }

        @Override
        public void keyPressed(KeyEvent e) {
			paddleArr[myTag].keyPressed(e);
//            for(int i =0; i<4; i++){
//				paddleArr[i].keyPressed(e);
//				}
        }
    }
	
	private class ScheduleTask extends TimerTask {

        @Override
        public void run() {

        	if(paddleArr[myTag].isHorizontal()){paddleArr[myTag].moveX();}
        	else {paddleArr[myTag].moveY();}
        	
        	
        	for(int i=0; i<4; i++){
        		if(i != myTag){
        			if(!others.contains(i)){
        				Computer comPaddle = new Computer(paddleArr[i], ball, difficulty );
        				if(paddleArr[i].isHorizontal()){comPaddle.moveSideways();}
        				else {comPaddle.move();}
        				paddleArr[i].setMyColor(Color.white);
        			}
        		}
        		
        		if(i==myTag || others.contains(i)){
        			if(paddleArr[i].canSpring){paddleArr[i].setMyColor(Color.cyan);}
            		else{paddleArr[i].setMyColor(Color.BLUE);}
        		}
        		
        		if(life[i]==1){
        			paddleArr[i].setMyColor(Color.ORANGE);
        			}
        		else if(life[i] == 2){paddleArr[i].setMyColor(Color.red);
//    			paddleArr[i].setPaddleSize(200);
//    			PisBig[i]= true;
//    			bigTime[i]=System.currentTimeMillis();
    			}
        		else if(life[i] == 3){
        			paddleArr[i].setPaddleSize(0);
        			if(i == myTag){
        				ingame= false;
        			}
        		
        		}
        	}
//        	if(paddleArr[myTag].getPaddleSize()!=0){
//        		for(int i=0; i<others.size();i++){
//        			gstop = true;
//        		}
//        	}
        	
//        	if(paddleArr[myTag].getPaddleSize()!=0 && ){
//        		for(int i=0; i<4; i++){
//        			
//        		}
//        	}

        	if(paddleArr[0].getPaddleSize()+paddleArr[1].getPaddleSize()+paddleArr[2].getPaddleSize()+paddleArr[3].getPaddleSize()<110){
        		if(paddleArr[0].getPaddleSize()!=0){gstop = true; winner=0;}
        		if(paddleArr[1].getPaddleSize()!=0){gstop = true; winner=1;}
        		if(paddleArr[2].getPaddleSize()!=0){gstop = true; winner=2;}
        		if(paddleArr[3].getPaddleSize()!=0){gstop = true; winner=3;}
        	}

        	if(gstop){
        		
        	}
        	
        	for(int i=0; i<4; i++){
        		if(PisBig[i] && life[i] < 3){
        			if(System.currentTimeMillis()-bigTime[i]>2000){
        				paddleArr[i].setPaddleSize(100);
        	            PisBig[i]= false;
        	            System.out.println("shortened "+ i);
        			}
        		}
        		if(paddleArr[i].canSpring){
					if(System.currentTimeMillis()-paddleArr[i].springTime>2000){
						paddleArr[i].canSpring=false;
					}
				}
        		
        	}
        	
			//System.out.println((Tstamps.get(1) - System.currentTimeMillis()));
        	for(int i =0; i<others.size(); i++){
         	   if((System.currentTimeMillis() -Tstamps.get(others.get(i))) > 5000){
         		   if(others.get(i) == ServerTag){
         			  if(ServerTag == 0 || ServerTag == 1 || ServerTag == 2 ){ServerTag++;}
         			  else if(ServerTag == 3){ServerTag = 0;}
         		   }
         		  System.out.println("other Removed");
        		  others.remove(i);
         		   
         	   }
            }
        	//if(myTag==0){ball.move();}
        	//ball.move();
        	if(myTag==ServerTag){
        		if(bak >416){ball.move();}
        	}
        	else {ball.move();}
            if(ball.getXdir()*ball.getXdir()+ball.getYdir()*ball.getYdir() >= 100){
            	ball.setXdir(ball.getXdir()*0.67);
            	ball.setYdir(ball.getYdir()*0.67);
            }
            if(ball.getXdir()*ball.getXdir()+ball.getYdir()*ball.getYdir() <= 10){
            	ball.setXdir(ball.getXdir()*1.3);
            	ball.setYdir(ball.getYdir()*1.3);
            }
            
           if(newgame){
        	   try {
				socket = new MulticastSocket(port);
				group =InetAddress.getByName(ip);
				socket.joinGroup(group);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
				System.out.println("error");
			}
        	   rec = new ReceiveData(socket, group, port, myTag);
        	newgame = false;   
           }
           
           //System.out.println(rec.data);
           
           if(bak > 15){
        	   
        	   setAllPos(rec.data.trim());
           }
           bak++;
           
           checkCollision();
           repaint();
           
           
           
        	try {
				socket = new MulticastSocket(port);
				group = InetAddress.getByName(ip);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	String str = myTag+" "+(ball.getMyX())+" "+(ball.getMyY())+" "+(ball.getXdir())+" "+(ball.getYdir())+" "+(paddleArr[myTag].getMyX())+" "+(paddleArr[myTag].getMyY())+" "+paddleArr[myTag].canSpring+" "+paddleArr[myTag].springPower+" "+life[0]+" "+life[1]+" "+life[2]+" "+life[3];
        	buf = str.getBytes();
        	// Create a DatagramPacket 
        	DatagramPacket packet = new DatagramPacket(buf, buf.length,group, port);
        	// Do a send.
        	try {
				socket.send(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("send failed");
			}
        	// And when we have finished sending data close the socket
        	socket.close();
           
        }
    }
	
	public void setAllPos(String s){
		
		//System.out.println(s);
		String[] data = s.split(" ");
		for(int  i=0; i<others.size(); i++){
			if(data[0].equals(Integer.toString(others.get(i)))){
				System.out.println("stampUpdated"+others.get(i));
				Tstamps.put(others.get(i), System.currentTimeMillis());}
		}
		
		if(myTag != ServerTag){
			if(data[0].equals(Integer.toString(ServerTag)))
			{
				ball.setMyX(Double.parseDouble(data[1]));
				ball.setMyY(Double.parseDouble(data[2]));
				ball.setXdir(Double.parseDouble(data[3]));
				ball.setYdir(Double.parseDouble(data[4]));
				for(int i=0; i<4;i++){life[i]=Integer.parseInt(data[i+9]);}
				//ball.move();
				//paddleBottom.setMyX(Integer.parseInt(data[5]));
				//content.udpPosition = data[5];
				System.out.println("Ball pos set");
			}
		}
		
		
		if(others.contains(Integer.parseInt(data[0])))
		{
			paddleArr[Integer.parseInt(data[0])].setMyX(Double.parseDouble(data[5]));
			paddleArr[Integer.parseInt(data[0])].setMyY(Double.parseDouble(data[6]));
			paddleArr[Integer.parseInt(data[0])].canSpring = (Boolean.parseBoolean(data[7]));
			paddleArr[Integer.parseInt(data[0])].springPower = (Integer.parseInt(data[8]));
			
			//content.udpPosition = data[5];
			System.out.println(data[7]);
		
		}
	}
	
	private void stopGame() {

        ingame = false;
        myTimer.cancel();
    }
	
	private void checkCollision(){
		/*if (ball.getcircle().getCenterY() > 590) {
            stopGame();
        }
                
                if (ball.getcircle().getCenterY() < 30) {
            stopGame();
        }
                
               if (ball.getcircle().getCenterX() > 590) {
            stopGame();
        }
                
                if (ball.getcircle().getCenterX() < 30) {
            stopGame();
        }*/
		
		if (ball.getcircle().getCenterX() >= 590) {
            ball.setXdir(ball.getXdir()*(-1));
            if(myTag==ServerTag){life[2]++;}
            //life[2]++;
//            if(!PisBig[lastBallTouch]){
//            paddleArr[lastBallTouch].setPaddleSize(paddleArr[lastBallTouch].getPaddleSize()*2);
//            PisBig[lastBallTouch]= true;
//    		bigOne = lastBallTouch;
//            System.out.println("size huge "+lastBallTouch);
//            bigTime[lastBallTouch]=System.currentTimeMillis();}
        }
		if (ball.getcircle().getCenterY() >= 590) {
            ball.setYdir(ball.getYdir()*(-1));
            if(myTag==ServerTag){life[0]++;}
            //life[0]++;
//            if(!PisBig[lastBallTouch]){
//            paddleArr[lastBallTouch].setPaddleSize(paddleArr[lastBallTouch].getPaddleSize()*2);
//            PisBig[lastBallTouch]= true;
//    		bigOne = lastBallTouch;
//            System.out.println("size huge "+lastBallTouch);
//            bigTime[lastBallTouch]=System.currentTimeMillis();}
        }
		if (ball.getcircle().getCenterX() <= 0) {
            ball.setXdir(ball.getXdir()*(-1));
            if(myTag==ServerTag){life[3]++;}
            //life[3]++;
//            if(!PisBig[lastBallTouch]){
//            paddleArr[lastBallTouch].setPaddleSize(paddleArr[lastBallTouch].getPaddleSize()*2);
//            PisBig[lastBallTouch]= true;
//            System.out.println("size huge "+lastBallTouch);
//    		bigOne = lastBallTouch;
//            bigTime[lastBallTouch]=System.currentTimeMillis();}
        }
		if (ball.getcircle().getCenterY() <= 0) {
			ball.setYdir(ball.getYdir()*(-1));
            if(myTag==ServerTag){life[1]++;}
			//life[1]++;
//            
//            if(!PisBig[lastBallTouch]){
//            paddleArr[lastBallTouch].setPaddleSize(paddleArr[lastBallTouch].getPaddleSize()*2);
//            PisBig[lastBallTouch]= true;
//            System.out.println("size huge "+lastBallTouch);
//    		bigOne = lastBallTouch;
//            bigTime[lastBallTouch]=System.currentTimeMillis();}
        }
		
		if ((ball.getRect()).intersects(paddleArr[0].getRect()) && (ball.getYdir()>0)){
			if(paddleArr[0].canSpring){
				if(paddleArr[0].springPower >0 && paddleArr[0].springPower<4){
					ball.setYdir(ball.getYdir()*(-2.5));
		            ball.setXdir((ball.getXdir()*2.5)+0.15*paddleArr[0].getXSpeed());
		           // paddleArr[0].canSpring=false;
				}
			}
			else{
            ball.setYdir(ball.getYdir()*(-0.92));
            ball.setXdir(ball.getXdir()+0.15*paddleArr[0].getXSpeed());
			}
			{lastBallTouch = 0;
			System.out.println(lastBallTouch);}
			
			}
		if ((ball.getRect()).intersects(paddleArr[1].getRect()) && (ball.getYdir()<0)){

			if(paddleArr[1].canSpring){
				if(paddleArr[1].springPower >0 && paddleArr[1].springPower<4){
					ball.setYdir(ball.getYdir()*(-2.5));
		            ball.setXdir((ball.getXdir()*2.5)+0.15*paddleArr[1].getXSpeed());
		            //paddleArr[1].canSpring=false;
				}
			}
			else{
				ball.setYdir(ball.getYdir()*(-0.92));
	            ball.setXdir(ball.getXdir()+0.15*paddleArr[1].getXSpeed());
			}
			{lastBallTouch = 1;
			}System.out.println(lastBallTouch);
			}
            
           
		if ((ball.getRect()).intersects(paddleArr[2].getRectV()) && (ball.getXdir()>0)){
			if(paddleArr[2].canSpring){
				if(paddleArr[2].springPower >0 && paddleArr[2].springPower<4){
					ball.setXdir(ball.getXdir()*(-2.5));
		            ball.setYdir(ball.getYdir()*2.5 +0.15*paddleArr[2].getYSpeed());
		            //paddleArr[2].canSpring=false;
				}
			}
			else{
				ball.setXdir(ball.getXdir()*(-0.92));
	            ball.setYdir(ball.getYdir()+0.15*paddleArr[2].getYSpeed());
			}
			{lastBallTouch = 2;
			System.out.println(lastBallTouch);}
			}
            
            if ((ball.getRect()).intersects(paddleArr[3].getRectV())  && (ball.getXdir()<0)){
            	if(paddleArr[3].canSpring){
    				if(paddleArr[3].springPower >0 && paddleArr[3].springPower<4){
    					ball.setXdir(ball.getXdir()*(-2.5));
    		            ball.setYdir(ball.getYdir()*2.5 +0.15*paddleArr[3].getYSpeed());
    		            //paddleArr[3].canSpring=false;
    				}
    			}
    			else{
    				ball.setXdir(ball.getXdir()*(-0.92));
    	            ball.setYdir(ball.getYdir()+0.15*paddleArr[3].getYSpeed());
    			}
    			{lastBallTouch = 3;
    			System.out.println(lastBallTouch);}
            	
            	
            
            }
            
            
	}
	
	
	
	
	
	
}
