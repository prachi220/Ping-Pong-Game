
import java.io.*;
import java.net.*;
public class ReceiveData extends Thread
{
    private MulticastSocket msocket;
    private DatagramPacket dp;
    private InetAddress group;
    private int port;
    public int player_tag;
    public String data;
    public long stamp;
    public String tmp;

    public ReceiveData(MulticastSocket msock ,InetAddress group_no , int Port_no,int tag)
      {
                msocket= msock;
                group=group_no;
                port=Port_no;
                stamp=System.currentTimeMillis();

                
                player_tag=tag;
                
                start();                // start calls run
        }

    public void run()
        {
                byte[] buf = new byte[1000];
                
                try{
                        for(;;)
                        {

                                //Read from the STDN input
                            dp = new DatagramPacket(buf, buf.length);
                            msocket.receive(dp);
                            tmp = new String(dp.getData(),0,dp.getLength());
//                            System.out.println("22222");
//                            if (Integer.parseInt(tmp.substring(0,1))==player_tag)
//                            {
//
//                                data=tmp;
//                                stamp=System.currentTimeMillis();
//                            }
//                            if(System.currentTimeMillis()-stamp>5000)
//                            {
//                                data="0";
//                            }

                            data=tmp;
                        }

                } catch (IOException e) {
                        System.out.println("Exit...");
                        e.printStackTrace();
                } finally {
                        msocket.close();
                }
        }

}