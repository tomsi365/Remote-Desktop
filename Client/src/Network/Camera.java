package Network;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.DatagramSocket;
import java.net.SocketException;



import Run.Configuration;
import Tools.ImageTools;
import Tools.Thread;
import Tools.UDPTools;

/**
 * 
 *the class creates object that makes screenshots and sends them to destination computerr
 *
 */
public class Camera extends Thread{


	private Rectangle rectangle; /*screen dimensions*/
	private Robot bot; /*robot object , makes screenshots*/
	private BufferedImage image; /*current screenshot*/

	private DatagramSocket socket; /*Socket object , makes connection*/


	private String ip; /*destination address*/

	private Dimension ScreenSize; /*screen dimensions*/

	private int x,y; /*cropped image coordinates*/


	/**
	 * Constructor
	 * */
	public Camera(String ip)
	{
		x=0;y=0;
		try
		{
			socket=new DatagramSocket();
		} 
		catch (SocketException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try
		{
			bot=new Robot();
		} 
		catch (AWTException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		ScreenSize = toolkit.getScreenSize();

		rectangle= new Rectangle(ScreenSize);

		this.ip=ip;

	}

	/**
	 * 
	 * the function moves the cropped image of screen
	 */
	public void move(int x,int y)
	{
		if((this.x+x)>=0&&(this.x+x+Configuration.WIDTH)<=ScreenSize.getWidth()){
			this.x+=x;}
		if((this.y+y)>=0&&(this.y+y+Configuration.HIGHT)<=ScreenSize.getHeight()){
			this.y+=y;}

	}

	/**
	 * 
	 * main function , sends the screenshots to the destination computer
	 */
	@Override
	public void action() {
		try {
			java.lang.Thread.sleep(Configuration.DELAY1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		image=bot.createScreenCapture(rectangle);
		ImageTools.drawRectOnImage(image, MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y, 15,15);
		image=ImageTools.crop(image, Configuration.WIDTH, Configuration.HIGHT, x, y);
		UDPTools.writeImageUDP(image, socket, ip, Configuration.PORT);



	}


}
