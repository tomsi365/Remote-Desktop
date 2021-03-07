package Graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


import Run.Configuration;
import Tools.ImageTools;

/**
 * 
 * the class creates object of window that shows us the remote screen
 *
 */
public class Screen extends JPanel{

	private BufferedImage b; /**current screenshot*/
	private ImageIcon i; /**container of current screenshot*/
	private boolean start; /**screen switch*/

	/**
	 * 
	 * constructor
	 */
	public Screen() {
		b=null;
		i=new ImageIcon();
		start=false;
		GUI(300, 0);
	}

	/**
	 * this function builds graphic features 
	 */
	public void GUI(int x, int y)
	{
		JFrame jf=new JFrame("Remote Desktop Window");
		jf.setBounds(x, y,Configuration.WIDTH+50,Configuration.HIGHT+50);
		jf.add(this);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}

	/**
	 * the function draws an image
	 * */
	public void draw (BufferedImage b)
	{

		this.b=b;
		start=true;
		repaint();
	}

	/**
	 * the function draws the panel
	 * */
	public void paintComponent(Graphics g) {
		if(start){
			BufferedImage x=ImageTools.proportionalStretching(b, Configuration.WIDTH,Configuration.HIGHT);
			i.setImage(x);
			i.paintIcon(this, g, 0, 0);
		}
	}

}
