
import java.awt.*;
import java.util.*;
import java.applet.*;

public class Computer
{
	private Pedal pedal ;			

	public int k=15;
	private static final double size_x = 10;	
	private static final double size_y = 100;	

	// Ball
	private static Pball ball;

	// Constructor
	public Computer (Pedal pedal, Pball ball, int diff)
	{
		k=diff;
		Computer.ball = ball;
                this.pedal = pedal ;
	}

	public void move ()
	{
                double y_pos = this.pedal.getMyY()+50 ;
                //System.out.println(y_pos) ;
   		//System.out.println(ball.getYdir()) ;
                if(this.pedal.getMyX()>100)
                {if(ball.getXdir()>0)
                    {if (ball.getYdir() < 0)
		{
              
                       if  (y_pos <= -30)
			{
				y_pos = -30 ;
			} 
                        else 
                       {
                           if((this.pedal.getMyY()+50-ball.getMyY())>=0){y_pos = y_pos - ((this.pedal.getMyY()+50-ball.getMyY())+(0.5*Math.abs(ball.getXdir())))/(0.1*Math.abs(this.pedal.getMyX()-ball.getMyX())+k) ;
                           this.pedal.setYSpeed(-((this.pedal.getMyY()+50-ball.getMyY())+(0.5*Math.abs(ball.getXdir())))/(0.1*Math.abs(this.pedal.getMyX()-ball.getMyX())+k));
                           }
                           else{
                               y_pos = y_pos - ((this.pedal.getMyY()+50-ball.getMyY())-(0.5*Math.abs(ball.getXdir())))/(0.1*Math.abs(this.pedal.getMyX()-ball.getMyX())+k) ;
                               this.pedal.setYSpeed(-((this.pedal.getMyY()+50-ball.getMyY())-(0.5*Math.abs(ball.getXdir())))/(0.1*Math.abs(this.pedal.getMyX()-ball.getMyX())+k));
                           }
                       }
                        
		}
		else 
                {
                    if (ball.getYdir() > 0)
                
		{
			if (y_pos >= 470)
			{
				y_pos = 470;
			}
                         else 
                       
			{
				if((this.pedal.getMyY()+50-ball.getMyY())>=0){y_pos = y_pos - ((this.pedal.getMyY()+50-ball.getMyY())+(0.5*Math.abs(ball.getXdir())))/(0.1*Math.abs(this.pedal.getMyX()-ball.getMyX())+k) ;
                                this.pedal.setYSpeed(-((this.pedal.getMyY()+50-ball.getMyY())+(0.5*Math.abs(ball.getXdir())))/(0.1*Math.abs(this.pedal.getMyX()-ball.getMyX())+k));
                                }
                           else{
                               y_pos = y_pos - ((this.pedal.getMyY()+50-ball.getMyY())-(0.5*Math.abs(ball.getXdir())))/(0.1*Math.abs(this.pedal.getMyX()-ball.getMyX())+k) ;
                               this.pedal.setYSpeed(-((this.pedal.getMyY()+50-ball.getMyY())-(0.5*Math.abs(ball.getXdir())))/(0.1*Math.abs(this.pedal.getMyX()-ball.getMyX())+k));
                           }
			} 
                }
                 
		}
                
                  //  y_pos -= 4 ;
		//this.pedal.setYSpeed(-((this.pedal.getMyY()+50-ball.getMyY())-(0.5*Math.abs(ball.getXdir())))/(0.1*Math.abs(this.pedal.getMyX()-ball.getMyX())+k));}
                    }else{
                    y_pos=y_pos+0;
                    this.pedal.setYSpeed(0);
                }}
                else{
                    if(ball.getXdir()<0)
                    {if (ball.getYdir() < 0)
		{
              
                       if  (y_pos <= -30)
			{
				y_pos = -30 ;
			} 
                        else 
                       {
                           if((this.pedal.getMyY()+50-ball.getMyY())>=0){y_pos = y_pos - ((this.pedal.getMyY()+50-ball.getMyY())+(0.5*Math.abs(ball.getXdir())))/(0.1*Math.abs(this.pedal.getMyX()-ball.getMyX())+k) ;
                           this.pedal.setYSpeed(-((this.pedal.getMyY()+50-ball.getMyY())+(0.5*Math.abs(ball.getXdir())))/(0.1*Math.abs(this.pedal.getMyX()-ball.getMyX())+k));
                           }
                           else{
                               y_pos = y_pos - ((this.pedal.getMyY()+50-ball.getMyY())-(0.5*Math.abs(ball.getXdir())))/(0.1*Math.abs(this.pedal.getMyX()-ball.getMyX())+k) ;
                               this.pedal.setYSpeed(-((this.pedal.getMyY()+50-ball.getMyY())-(0.5*Math.abs(ball.getXdir())))/(0.1*Math.abs(this.pedal.getMyX()-ball.getMyX())+k));
                           }
                       }
                        
		}
		else 
                {
                    if (ball.getYdir() > 0)
                
		{
			if (y_pos >= 470)
			{
				y_pos = 470;
			}
                         else 
                       
			{
				if((this.pedal.getMyY()+50-ball.getMyY())>=0){y_pos = y_pos - ((this.pedal.getMyY()+50-ball.getMyY())+(0.5*Math.abs(ball.getXdir())))/(0.1*Math.abs(this.pedal.getMyX()-ball.getMyX())+k) ;
                                this.pedal.setYSpeed(-((this.pedal.getMyY()+50-ball.getMyY())+(0.5*Math.abs(ball.getXdir())))/(0.1*Math.abs(this.pedal.getMyX()-ball.getMyX())+k));}
                           else{
                               y_pos = y_pos - ((this.pedal.getMyY()+50-ball.getMyY())-(0.5*Math.abs(ball.getXdir())))/(0.1*Math.abs(this.pedal.getMyX()-ball.getMyX())+k) ;
                               this.pedal.setYSpeed(-((this.pedal.getMyY()+50-ball.getMyY())-(0.5*Math.abs(ball.getXdir())))/(0.1*Math.abs(this.pedal.getMyX()-ball.getMyX())+k));
                           }
			} 
                }
                 
		}
                
                  //  y_pos -= 4 ;
		//this.pedal.setYSpeed(-(this.pedal.getMyY()+50-ball.getMyY())/(0.5*Math.abs(this.pedal.getMyX()-ball.getMyX())));}
                    }else{
                    y_pos=y_pos+0;
                    this.pedal.setYSpeed(0);
                }
                }
                this.pedal.setMyY(y_pos-50);
	}
        
        public void moveSideways ()
	{
                double x_pos = this.pedal.getMyX()+50 ;
                //System.out.println(y_pos) ;
   		//System.out.println(ball.getYdir()) ;
                if(this.pedal.getMyY()>100)
                {if(ball.getYdir()>0)
                    {if (ball.getXdir() < 0)
		{
              
                       if  (x_pos <= -30)
			{
				x_pos = -30 ;
			} 
                        else 
                       {
                           if((this.pedal.getMyX()+50-ball.getMyY())>=0){x_pos = x_pos - ((this.pedal.getMyX()+50-ball.getMyX())+(0.5*Math.abs(ball.getYdir())))/(0.1*Math.abs(this.pedal.getMyY()-ball.getMyY())+k) ;
                           this.pedal.setXSpeed(-((this.pedal.getMyX()+50-ball.getMyX())+(0.5*Math.abs(ball.getYdir())))/(0.1*Math.abs(this.pedal.getMyY()-ball.getMyY())+k));
                           }
                           else{
                               x_pos = x_pos - ((this.pedal.getMyX()+50-ball.getMyX())-(0.5*Math.abs(ball.getYdir())))/(0.1*Math.abs(this.pedal.getMyY()-ball.getMyY())+k) ;
                               this.pedal.setXSpeed(-((this.pedal.getMyX()+50-ball.getMyX())-(0.5*Math.abs(ball.getYdir())))/(0.1*Math.abs(this.pedal.getMyY()-ball.getMyY())+k));
                           }
                       }
                        
		}
		else 
                {
                    if (ball.getXdir() > 0)
                
		{
			if (x_pos >= 470)
			{
				x_pos = 470;
			}
                         else 
                       
			{
				if((this.pedal.getMyX()+50-ball.getMyX())>=0){x_pos = x_pos - ((this.pedal.getMyX()+50-ball.getMyX())+(0.5*Math.abs(ball.getYdir())))/(0.1*Math.abs(this.pedal.getMyY()-ball.getMyY())+k) ;
                                this.pedal.setXSpeed(-((this.pedal.getMyX()+50-ball.getMyX())+(0.5*Math.abs(ball.getYdir())))/(0.1*Math.abs(this.pedal.getMyY()-ball.getMyY())+k));
                                }
                           else{
                               x_pos = x_pos - ((this.pedal.getMyX()+50-ball.getMyX())-(0.5*Math.abs(ball.getYdir())))/(0.1*Math.abs(this.pedal.getMyY()-ball.getMyY())+k) ;
                               this.pedal.setXSpeed(-((this.pedal.getMyX()+50-ball.getMyX())-(0.5*Math.abs(ball.getYdir())))/(0.1*Math.abs(this.pedal.getMyY()-ball.getMyY())+k));
                           }
			} 
                }
                 
		}
                
                  //  y_pos -= 4 ;
		//this.pedal.setXSpeed(-((this.pedal.getMyX()+50-ball.getMyX())-(0.5*Math.abs(ball.getYdir())))/(0.1*Math.abs(this.pedal.getMyY()-ball.getMyY())+k));}
                    }else{
                    x_pos=x_pos+0;
                    this.pedal.setXSpeed(0);
                }}
                else{
                    if(ball.getYdir()<0)
                    {if (ball.getXdir() < 0)
		{
              
                       if  (x_pos <= -30)
			{
				x_pos = -30 ;
			} 
                        else 
                       {
                           if((this.pedal.getMyX()+50-ball.getMyY())>=0){x_pos = x_pos - ((this.pedal.getMyX()+50-ball.getMyX())+(0.5*Math.abs(ball.getYdir())))/(0.1*Math.abs(this.pedal.getMyY()-ball.getMyY())+k) ;
                           this.pedal.setXSpeed(-((this.pedal.getMyX()+50-ball.getMyX())+(0.5*Math.abs(ball.getYdir())))/(0.1*Math.abs(this.pedal.getMyY()-ball.getMyY())+k));}
                           else{
                               x_pos = x_pos - ((this.pedal.getMyX()+50-ball.getMyX())-(0.5*Math.abs(ball.getYdir())))/(0.1*Math.abs(this.pedal.getMyY()-ball.getMyY())+k) ;
                               this.pedal.setXSpeed(-((this.pedal.getMyX()+50-ball.getMyX())-(0.5*Math.abs(ball.getYdir())))/(0.1*Math.abs(this.pedal.getMyY()-ball.getMyY())+k));
                           }
                       }
                        
		}
		else 
                {
                    if (ball.getXdir() > 0)
                
		{
			if (x_pos >= 470)
			{
				x_pos = 470;
			}
                         else 
                       
			{
				if((this.pedal.getMyX()+50-ball.getMyX())>=0){x_pos = x_pos - ((this.pedal.getMyX()+50-ball.getMyX())+(0.5*Math.abs(ball.getYdir())))/(0.1*Math.abs(this.pedal.getMyY()-ball.getMyY())+k) ;
                                this.pedal.setXSpeed(-((this.pedal.getMyX()+50-ball.getMyX())+(0.5*Math.abs(ball.getYdir())))/(0.1*Math.abs(this.pedal.getMyY()-ball.getMyY())+k));}
                           else{
                               x_pos = x_pos - ((this.pedal.getMyX()+50-ball.getMyX())-(0.5*Math.abs(ball.getYdir())))/(0.1*Math.abs(this.pedal.getMyY()-ball.getMyY())+k) ;
                               this.pedal.setXSpeed(-((this.pedal.getMyX()+50-ball.getMyX())-(0.5*Math.abs(ball.getYdir())))/(0.1*Math.abs(this.pedal.getMyY()-ball.getMyY())+k));
                           }
			} 
                }
                 
		}
                
                  //  y_pos -= 4 ;
		//this.pedal.setXSpeed(-(this.pedal.getMyX()+50-ball.getMyX())/(0.5*Math.abs(this.pedal.getMyY()-ball.getMyY())));}
                    }else{
                    x_pos=x_pos+0;
                    this.pedal.setXSpeed(0);}
               
	}
                this.pedal.setMyX(x_pos-50);       }

	public double getXPos()
	{
		return this.getXPos();
	}

	public double getYPos()
	{
		return this.getYPos();
	}

	public double getXSize()
	{
		return size_x;
	}

	public double getYSize()
	{
		return size_y;
	}
	
	public Pedal getPedal(){
		return pedal;
	}


	/*public void paint (Graphics g)
	{
		g.setColor (Color.blue);
		g.fillRect (this.getXPos(), this.getYPos(), size_x, size_y);
	}*/
        
        
}
