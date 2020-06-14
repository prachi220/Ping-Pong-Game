
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package view;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.* ;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;

import javax.swing.JFrame;





public class User extends Frame {

static ArrayList<String> arrList = new ArrayList<String>();   //1st: Tag , 2nd: IPaddress , 3rd: Port , else: Others

private MulticastSocket socket;
private String ip;
private int portadd, tag;
InetAddress group;
byte[] buf = new byte[256];

Label l1 = new Label("Your Player choice") ;
TextField myPort = new TextField(2);
Label l2 = new Label("Your IPaddress") ;
TextField IPaddress = new TextField(20);
Label l5 = new Label("Port") ;
TextField port = new TextField(20);
Label level = new Label("Level") ;
Checkbox easy = new Checkbox("EASY") ;
Checkbox medium = new Checkbox("MEDIUM") ;
Checkbox hard = new Checkbox("HARD") ;
Label l3 = new Label("Others") ;
Label l4 = new Label(" ") ;
Checkbox p1 = new Checkbox("P0") ;
Checkbox p2 = new Checkbox("P1") ;
Checkbox p3 = new Checkbox("P2") ;
Checkbox p4 = new Checkbox("P3") ;
Button Submit=new Button("SUBMIT");
Button Start=new Button("START");

public User()
{
    setLayout(new GridLayout(20, 1)) ;
    myPort.setPreferredSize( new Dimension( 100, 24 ) );
    add(l1) ;
    add(myPort) ;
    add(l2) ;
    add(IPaddress) ;
    add(l5) ;
    add(port) ;
    add(level) ;
    add(l4) ;
    add(easy) ;
    add(medium) ;
    add(hard) ;
    add(l3) ;
    add(l4) ;
    add(p1) ;
    add(p2) ;
    add(p3) ;
    add(p4) ;
    add(Submit) ;
    add(l4) ;
    add(Start) ;
 /*   addWindowListener(new WindowAdapter()
    {
        public void WindowClosing(WindowEvent we)
        {
            setVisible(false) ;
            System.exit(0) ;
        }
    }) ; */
}

/*public void addMultimap(String key, Object o) {
    List<Object> list;
    if (multiMap.containsKey(key)) {
     list = multiMap.get(key);
      list.add(o);
    } else {
      list = new ArrayList<Object>();
      list.add(o);
      multiMap.put(key, list);
    }
  } */
    public void function(){
        Submit.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
    	  
    	  arrList.clear();
          String myPortValue = myPort.getText();
          arrList.add(myPortValue) ;
          tag = Integer.parseInt(myPortValue);
          
          String IPValue = IPaddress.getText() ;
           arrList.add(IPValue) ;
           ip = IPValue;
           
           String Port = port.getText() ;
           arrList.add(Port) ;
           portadd = Integer.parseInt(Port);

           if (easy.getState()) {
               arrList.add("easy") ;
   
          }
          if (medium.getState()) {
               arrList.add("medium") ;
   
          }
          if (hard.getState()) {
               arrList.add("hard") ;
   
          }
           
          if (p1.getState()) {
               arrList.add("0") ;
   
          }
          if (p2.getState()) {
               arrList.add("1") ;
   
          }
          if (p3.getState()) {
               arrList.add("2") ;
   
          }
           if (p4.getState()) {
               arrList.add("3") ;
   
          }
             System.out.println(arrList) ;
      }
      
    }) ;
        
        Start.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
//    	  try {
//				socket = new MulticastSocket(portadd);
//				group = InetAddress.getByName(ip);
//			} catch (IOException ex) {
//				// TODO Auto-generated catch block
//				ex.printStackTrace();
//			}
//      	String str = "add " + tag;
//      	buf = str.getBytes();
//      	// Create a DatagramPacket 
//      	DatagramPacket packet = new DatagramPacket(buf, buf.length,group, portadd);
//      	// Do a send.
//      	try {
//				socket.send(packet);
//			} catch (IOException ex) {
//				// TODO Auto-generated catch block
//				System.out.println("send failed");
//			}
//      	// And when we have finished sending data close the socket
//      	socket.close();
    	  
    	  
     //     GameBoard startGame = new GameBoard() ;
        EventQueue.invokeLater(new Runnable() {
        @Override
        public void run() {                
            GameBoard game = new GameBoard(arrList);
            game.setVisible(true);  
            game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    });
      
      }
    }) ;
                }
                
    
    
    
    public static void main(String args[])
    {
        User ui = new User() ;
        ui.setSize(700, 1000);
        ui.show() ;
        
        ui.function() ;
        ui.addWindowListener(
                new WindowAdapter() {
                   public void windowClosing( WindowEvent e )
                   {
                      System.exit( 0 );
                   }
                }
             );
    }
}
