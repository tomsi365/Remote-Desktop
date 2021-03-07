package Tools;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;

import Run.Configuration;

/**
 * 
 * this class is a service class that gives us useful functions to deal with UDP
 *
 */
public class UDPTools {



	/**
	 * 
	 * 
	 * converts number to bytes array
	 */
	public static byte[] intToBytes(int num)
	{

		ByteArrayOutputStream conv=new ByteArrayOutputStream();
		DataOutputStream out=new DataOutputStream(conv);
		byte [] bytes;
		try 
		{
			out.writeInt(num);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			out.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bytes=conv.toByteArray();
		try 
		{
			conv.close();
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bytes;
	}

	/**
	 * 
	 * 
	 * converts bytes array to number
	 */
	public static int bytesToInt (byte [] bytes)
	{

		ByteArrayInputStream conv=new ByteArrayInputStream(bytes);
		DataInputStream i=new DataInputStream(conv);
		int num = 0;
		try 
		{
			num=i.readInt();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try 
		{
			conv.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try 
		{
			i.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

	/**
	 * converts image to bytes array
	 */
	public static byte [] imageToBytes(BufferedImage image)
	{

		ByteArrayOutputStream o=new ByteArrayOutputStream();
		try 
		{
			ImageIO.write(image, "jpeg", o);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte [] bytes=o.toByteArray();

		try 
		{
			o.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bytes;

	}

	/**
	 * converts bytes array to image
	 */
	public static BufferedImage bytesToImage(byte [] bytes)
	{

		ByteArrayInputStream i=new ByteArrayInputStream(bytes);
		BufferedImage image=null;
		try 
		{
			image=ImageIO.read(i);
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try 
		{
			i.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}



	/**
	 * 
	 * the function sends data by the UDP protocol
	 */
	public static void writeBytesUDP (byte [] data,DatagramSocket socket,String host,int port)
	{
		try {
			java.lang.Thread.sleep(Configuration.DELAY);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DatagramPacket p=null;
		try 
		{
			p=new DatagramPacket(data, data.length, InetAddress.getByName(host), port);
		} 
		catch (UnknownHostException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try 
		{
			socket.send(p);
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * the function receives data by the UDP protocol 
	 */
	public static byte [] readBytesUDP (DatagramSocket socket,int bufferLength)
	{

		byte [] bytes=new byte [bufferLength];

		DatagramPacket p=new DatagramPacket(bytes, bufferLength);
		try 
		{
			socket.receive(p);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bytes;
	}

	/**
	 * 
	 * the function sends a lot of data by the UDP protocol
	 */
	public static void writeLargeBytesUDP (byte [] data,DatagramSocket socket,String host,int port)
	{
		int length=data.length;
		int rest=length%Configuration.LIMIT;
		int quanity1=length/Configuration.LIMIT;

		writeIntUDP(Configuration.CODE, socket, host, port);
		writeIntUDP(Configuration.CODE+1, socket, host, port);
		writeIntUDP(quanity1, socket, host, port);
		writeIntUDP(Configuration.CODE+2, socket, host, port);
		writeIntUDP(rest, socket, host, port);

		byte [] pack ;
		int k=0;
		int m=0;
		for(int i=0;i<quanity1;i++)
		{
			writeIntUDP(Configuration.CODE+3+i, socket, host, port);
			pack=new byte[Configuration.LIMIT];
			for(int j=0;j<Configuration.LIMIT;j++)
			{
				pack[j]=data[k];
				k++;

			}
			writeBytesUDP(pack, socket, host, port);	
			m++;
		}

		if(rest>0)
		{
			writeIntUDP(Configuration.CODE+3+m, socket, host, port);
			pack =new byte[rest];
			for(int l=0;l<rest;l++)
			{
				pack[l]=data[k];
				k++;
			}

			writeBytesUDP(pack, socket, host, port);	
		}



	}

	/**
	 * the function receives a lot of data by the UDP protocol
	 */
	public static byte [] readLargeBytesUDP (DatagramSocket socket)
	{


		if(readIntUDP(socket)==Configuration.CODE)
		{
			if(readIntUDP(socket)==Configuration.CODE+1)
			{
				int quanity1=readIntUDP(socket);
				if(readIntUDP(socket)==Configuration.CODE+2)
				{
					int rest=readIntUDP(socket);

					int length=(quanity1*Configuration.LIMIT)+(rest);

					byte [] bytes=new byte[length];
					byte [] pack=null;
					int k=0;
					boolean status=true;
					int m=0;

					for(int i=0;i<quanity1;i++)
					{
						if(readIntUDP(socket)==Configuration.CODE+3+i)
						{
							pack=readBytesUDP(socket, Configuration.LIMIT);

						}
						else
						{
							status=false;
							break;
						}
						for(int j=0;j<Configuration.LIMIT;j++)
						{

							bytes[k]=pack[j];
							k++;

						}
						m++;
					}


					if(readIntUDP(socket)==Configuration.CODE+3+m&&status)
					{

						if(rest>0)
						{
							pack=readBytesUDP(socket, rest);
							for(int l=0;l<rest;l++)
							{
								bytes[k]=pack[l];
								k++;
							}
						}


					}
					else
					{
						return null;
					}


					return bytes;

				}}}
		return null;

	}


	/**
	 * the function sends number by the UDP protocol
	 */
	public static void writeIntUDP (int num,DatagramSocket socket,String host,int port)
	{
		writeBytesUDP(intToBytes(num), socket, host, port);
	}

	/**
	 * the function receives number by the UDP protocol
	 */
	public static int readIntUDP (DatagramSocket socket)
	{
		return bytesToInt(readBytesUDP(socket, 4));
	}

	/**
	 * the function sends image by the UDP protocol
	 */
	public static void writeImageUDP (BufferedImage image,DatagramSocket socket,String host,int port)
	{
		writeLargeBytesUDP(imageToBytes(image), socket, host, port);
	}

	/**
	 * the function receives image by the UDP protocol
	 */
	public static BufferedImage readImageUDP (DatagramSocket socket)
	{
		byte[] bytes=readLargeBytesUDP(socket);
		if(bytes!=null)
		{
			return bytesToImage(bytes);
		}
		return null;
	}

	/**
	 * the function sends text by the UDP protocol
	 */
	public static void writeStringUDP (String text,DatagramSocket socket,String host,int port)
	{
		writeBytesUDP(text.getBytes(), socket, host, port);
	}

	/**
	 * the function receives text by the UDP protocol
	 */
	public static String readStringUDP (DatagramSocket s)
	{
		return new String(readBytesUDP(s, 1024));
	}
}
