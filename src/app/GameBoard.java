

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

import java.util.Timer;
import java.util.ArrayList;


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
public class GameBoard extends JFrame{
	private GamePanel myGamePanel;
	
//	public GameBoard(){
//		super("PingPong4Player - Dvelopers Inc.");
//		myGamePanel = new GamePanel();
//		start();
//	}

	public GameBoard(ArrayList<String> als){
		super("PingPong4Player - Dvelopers Inc.");
		myGamePanel = new GamePanel(als);
		start();
	}
	
	public void start(){
		setVisible(true);
		buildGUI();
	}
	
	public void buildGUI(){
		//displays the board data
		
		
		//add(masterPanel);
		this.add(myGamePanel);
		setPreferredSize(new Dimension(618,640));
		
        setVisible(true);
		
        //setLocationRelativeTo(null);
		pack();
	}
	
//	public static void main(String[] args) {
//        
//        EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {                
//                GameBoard game = new GameBoard();
//                game.setVisible(true);  
//                game.setDefaultCloseOperation(EXIT_ON_CLOSE);
//            }
//        });
//    }
}
