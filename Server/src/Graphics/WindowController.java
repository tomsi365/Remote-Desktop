package Graphics;

import java.awt.BorderLayout;

import java.awt.Point;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.net.DatagramSocket;

import java.net.SocketException;

import javax.swing.JFrame;
import javax.swing.JPanel;


import Run.Configuration;
import Tools.UDPTools;

/**
 * 
 * the class creates object of control panel of the remote screen
 *
 */
public class WindowController  implements  MouseListener,MouseMotionListener{


	private Point p; /*mouse coordinates*/


	private String host; /*destination address*/
	private DatagramSocket s;  /*socket object*/

	/**
	 * 
	 * constructor
	 */
	public WindowController (String host) 
	{
		try 
		{
			this.s=new DatagramSocket();
		} 
		catch (SocketException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.host=host;
		GUI(0,300);
	}

	/**
	 * this function builds graphic features 
	 */
	public void GUI(int x, int y) {
		JFrame jf=new JFrame("Window");
		jf.setBounds(x, y, 200,200);
		jf.setLayout(new BorderLayout());
		JPanel jp=new JPanel();
		jf.add(jp,BorderLayout.CENTER);

		jp.addMouseListener(this);
		jp.addMouseMotionListener(this);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}

	/**
	 * the function response to dragging of mouse
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		int	x=e.getX();
		int	y=e.getY();
		calculate(x, y);
	}

	/**
	 * the function isn't in use
	 */
	@Override
	public void mouseMoved(MouseEvent e) {


	}

	/**
	 * the function isn't in use
	 */
	@Override
	public void mouseClicked(MouseEvent e) {




	}

	/**
	 * the function isn't in use
	 */
	@Override
	public void mouseEntered(MouseEvent e) {


	}

	/**
	 * the function isn't in use
	 */
	@Override
	public void mouseExited(MouseEvent e) {


	}

	/**
	 * the function isn't in use
	 */
	@Override
	public void mousePressed(MouseEvent e) {

	}

	/**
	 * the function response to realizing of mouse
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		p=null;
	}

	private void calculate (int x, int y)
	{
		if(p==null)
		{
			action3(0,0);
			p=new Point(x, y);
		}
		else 
		{
			action3((x-p.x),(y-p.y));
			p=new Point(x, y);
		}
	}



	private void action3(int x,int y)
	{
		String str="";
		str+="m";
		str+="#";
		str+=x;
		str+="#";
		str+=y;
		str+="#";
		str+="$$$$";
		UDPTools.writeStringUDP(str, s, host,Configuration.PORT2);

	}





}
