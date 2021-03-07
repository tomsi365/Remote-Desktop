package Tools;

/**
 * 
 * this class allows objects that extend it to run parallel
 *
 */
public abstract class Thread implements Runnable{


	private java.lang.Thread thread; /*thread object*/

	private boolean mode;/*process switch*/

	/**
	 * constructor
	 */
	public Thread ()
	{
		thread=new java.lang.Thread(this);	
		mode=false;
	}

	/**
	 * the function starts the parallel process
	 */
	public void start ()
	{
		if(!mode){
			mode=true;
			thread.start();
		}
	}

	/**
	 * the function stops the parallel process
	 */
	public void stop()
	{
		mode=false;
	}



	@Override
	public void run() 
	{

		while(mode)
		{
			action();
		}

	}

	/**
	 * main action of the parallel process
	 */
	public abstract void action();
}
