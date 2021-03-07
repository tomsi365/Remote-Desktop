package Tools;




import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * 
 * this class is a service class that gives us useful functions that edit with images
 *
 */
public class ImageTools {


	/**
	 * manual resizing of image , by giving dimensions in numbers
	 */
	public static BufferedImage  resizeByNumbers(BufferedImage image,int width,int hight){
		BufferedImage EditedImage=new BufferedImage(width, hight,image.getType() );
		Graphics g=EditedImage.getGraphics();
		g.drawImage(image, 0, 0, width, hight, null); 
		g.dispose();
		return EditedImage;
	}

	/**
	 * manual resizing of image , by giving dimensions in percents
	 */
	public static BufferedImage  resizeByPrecents(BufferedImage image,int widthPrecents,int hightPrecents){
		return resizeByNumbers(image,image.getWidth()*widthPrecents/100,image.getHeight()*hightPrecents/100);
	}

	/**
	 * manual proportional resizing of image , by giving percents
	 */
	public static BufferedImage  proportionalResize(BufferedImage image,int precents){
		return resizeByPrecents(image,precents,precents);
	}

	/**
	 * auto proportional stretching  of image in rectangle(width and height are the rectangle's dimensions)
	 */
	public static BufferedImage proportionalStretching (BufferedImage image,int width,int hight){
		return proportionalResize(image,(int) Math.min(width*100/image.getWidth(), hight*100/image.getHeight()));
	}

	/**
	 * the function draws another image on the main image
	 */
	public static void drawImageOnImage (BufferedImage mainImage,BufferedImage anotherImage,int x,int y,int width,int hight){
		Graphics g=mainImage.getGraphics();
		g.drawImage(anotherImage,x, y, width, hight, null);
	}

	/**
	 * the function draws red rectangle on the image
	 */
	public static void drawRectOnImage (BufferedImage image,int x,int y,int width,int hight){
		Graphics g=image.getGraphics();
		g.setColor(Color.red);
		g.fillRect(x, y, width, hight);
	}

	/**
	 * the function crops image
	 */
	public static BufferedImage crop(BufferedImage sourceImage, int width,int hight,int x,int y) {

		BufferedImage dest=null;

		dest= sourceImage.getSubimage(x, y, width, hight);
		return dest; 
	}

}
