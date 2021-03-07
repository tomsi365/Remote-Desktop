package Network;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.net.DatagramSocket;
import java.net.SocketException;


import Run.Configuration;
import Tools.Thread;
import Tools.UDPTools;

/**
 * the class creates object that controls the mouse
 * 
 *
 */
public class Mouse extends Thread{



	private Robot bot; /*robot object , controls the mouse*/
	private DatagramSocket socket; /*socket object , makes the connection*/

	/**
	 * 
	 * constructor
	 */
	public Mouse ()
	{
		try
		{
			bot=new Robot();
		} 
		catch (AWTException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try 
		{
			socket=new DatagramSocket(Configuration.PORT1);
		} 
		catch (SocketException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * main function , controls the mouse
	 */
	@Override
	public void action() {


		String str=UDPTools.readStringUDP(socket);

		if(str!=null)
		{
			if(str.charAt(0)=='p')
			{
				bot.mousePress(MouseEvent.BUTTON1_MASK);
			}
			if(str.charAt(0)=='r')
			{
				bot.mouseRelease(MouseEvent.BUTTON1_MASK);
			}
			if(str.charAt(0)=='m')
			{
				String[] move=str.split("#");
				bot.mouseMove(MouseInfo.getPointerInfo().getLocation().x+Integer.parseInt(move[1]), MouseInfo.getPointerInfo().getLocation().y+Integer.parseInt(move[2]));

			}

		}

	}


}
