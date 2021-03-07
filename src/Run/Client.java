package Run;

import Graphics.Close;

import Graphics.ClientAskWindow;
import Network.Camera;
import Network.Mouse;
import Network.Window;

/**
 * 
 * Client main class
 *
 */
public class Client {


	public static void main(String[] args) {


		ClientAskWindow iw=new ClientAskWindow("IP");
		String ip=iw.listen();
		Camera cam=new Camera(ip);
		Mouse m=new Mouse();
		Window w=new Window(Configuration.PORT2, cam);
		cam.start();
		m.start();
		w.start();
		new Close();

	}



}
