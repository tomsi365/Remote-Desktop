package Network;

import java.awt.image.BufferedImage;
import java.net.DatagramSocket;
import java.net.SocketException;

import Graphics.Screen;
import Run.Configuration;
import Tools.Thread;
import Tools.UDPTools;

/**
 * 
 * the class creates object that receives the screenshots and shows them
 *
 */
public class Reciever extends Thread{

	private Screen screen; /*window that shows us the the shared remote window*/

	private DatagramSocket socket; /*socket object , makes the connection*/

	/**
	 * constructor
	 */
	public Reciever ()
	{
		try
		{
			socket=new DatagramSocket(Configuration.PORT);
		} 
		catch (SocketException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		screen=new Screen();

	}


	/**
	 * main function , receives the screenshots and shows them
	 */
	@Override
	public void action() {

		try {
			java.lang.Thread.sleep(Configuration.DELAY2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedImage b=UDPTools.readImageUDP(socket);
		if(b!=null)
			screen.draw(b);

	}


}
