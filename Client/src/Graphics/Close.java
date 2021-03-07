package Graphics;



import javax.swing.JFrame;

import javax.swing.JTextArea;


/**
 * 
 * the class creates object that close the program
 *
 */
public class Close{



	/**
	 * constructor
	 */
	public Close (){
		GUI();
	}

	/**
	 * this function builds graphic features 
	 */
	public void GUI() {
		JFrame jf=new JFrame("Console");

		jf.setBounds(0, 0, 200,200);


		JTextArea j=new JTextArea();
		j.append("press X to close");
		jf.add(j);

		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}



}
