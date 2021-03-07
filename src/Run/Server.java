package Run;

import Graphics.ClientAskWindow;
import Graphics.MouseController;
import Graphics.WindowController;
import Network.Reciever;

/**
 * 
 * Server main class
 *
 */
public class Server {



	public static void main(String[] args) {

		ClientAskWindow iw=new ClientAskWindow("IP");
		String ip=iw.listen();
		Reciever reciever=new Reciever();
		MouseController mc=new MouseController(ip);
		WindowController wc=new WindowController(ip);
		reciever.start();

	}

}
