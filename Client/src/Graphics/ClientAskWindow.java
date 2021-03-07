package Graphics;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;


/**
 * 
 * the class creates object that receives input from the user
 *
 */
public class ClientAskWindow implements ActionListener{


	private JTextField jtf; /*text field component*/

	private JFrame jf; /*main window*/

	private String ip; /*destination address*/

	private boolean mode; /*window switch*/

	/**
	 * 
	 * constructor
	 */
	public ClientAskWindow (String name)
	{
		mode=false;
		ip="";
		GUI(0,0,name);
	}

	/**
	 * the function response to button press 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		ip=jtf.getText();
		mode=true;
		jf.setVisible(false);
	}

	/**
	 * this function builds graphic features 
	 */
	public void GUI(int x, int y,String name) {
		jf=new JFrame(name);
		jf.setBounds(x, y, 300,100);
		jf.setLayout(new BorderLayout() );

		JButton jb=new JButton("ok");
		jb.addActionListener(this);

		jtf=new JTextField(50);

		jf.add(jtf,BorderLayout.CENTER);
		jf.add(jb,BorderLayout.EAST);

		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 *the function waits to user response
	 */
	public String listen()
	{
		jf.setVisible(true);
		while(true)
		{
			try {
				Thread.sleep(100);
			} 
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(mode==true)
			{
				mode=false;
				return ip;	
			}	
		}

	}




}
