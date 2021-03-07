package Network;


import java.net.DatagramSocket;
import java.net.SocketException;
import Tools.Thread;
import Tools.UDPTools;

/**
 * 
 * the class creates object that controls the cropped remote window
 *
 */
public class Window extends Thread{



	private DatagramSocket socket; /*socket object , makes the connection*/
	private Camera camera; /*Camera object , makes the screenshots and sends them*/ 

	/**
	 * constructor
	 */
	public Window (int port,Camera camera)
	{
		this.camera=camera;


		try 
		{
			socket=new DatagramSocket(port);
		} 
		catch (SocketException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * main function , controls the cropped window 
	 */
	@Override
	public void action() {


		String str=UDPTools.readStringUDP(socket);

		if(str!=null)
		{
			if(str.charAt(0)=='m')
			{
				String[] move=str.split("#");
				camera.move(Integer.parseInt(move[1]),Integer.parseInt(move[2]));
			}

		}

	}


}
