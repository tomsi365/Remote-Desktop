package Run;

/**
 * 
 * this class configures the program
 *
 */
public class Configuration {


	public final static int LIMIT=65000;  /**UDP package size limit in bytes*/
	public final static int DELAY=3;  /**delay time during sending UDP packages in milliseconds*/
	public final static int DELAY1=1;  /**camera delay time in milliseconds*/
	public final static int DELAY2=1;  /**Receiver delay time in milliseconds*/
	public final static int WIDTH=500;  /**Cropped screenshot image width */
	public final static int HIGHT=500;  /**Cropped screenshot image height */
	public final static int PORT=7777;  /**Camera port*/
	public final static int PORT1=7778;  /**Mouse Controller port*/
	public final static int PORT2=7779;  /**Window Controller port*/
	public final static int CODE=44332211;  /**UDP Packages Encoding*/

}
