package GUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LinkImages {
	private static String path = "CardSuits"+File.separator;
	private static String tyleImages = ".png";
	//BufferedImage  co = ImageIO.read(new File(path))
	// BufferedImage
	private static BufferedImage co;
	private static BufferedImage ro;
	private static BufferedImage chuon;
	private static BufferedImage bich;
	
	private static boolean ready = false;
	
	
	// constructor
	public static void LinkImagesSuit() throws IOException {
		co = ImageIO.read(new File(path +"heart"+ tyleImages));
		ro = ImageIO.read(new File(path +"diamond"+ tyleImages));
		chuon = ImageIO.read(new File(path +"tref"+ tyleImages));
		bich = ImageIO.read(new File(path +"spade"+ tyleImages));
		ready = true;
	}
	// tao seter - getter
	public static BufferedImage getCo() {
		return co;
	}
	public static void setCo(BufferedImage co) {
		LinkImages.co = co;
	}
	public static BufferedImage getRo() {
		return ro;
	}
	public static void setRo(BufferedImage ro) {
		LinkImages.ro = ro;
	}
	public static BufferedImage getChuon() {
		return chuon;
	}
	public static void setChuon(BufferedImage chuon) {
		LinkImages.chuon = chuon;
	}
	public static BufferedImage getBich() {
		return bich;
	}
	public static void setBich(BufferedImage bich) {
		LinkImages.bich = bich;
	}
	public static boolean isReady() {
		return ready;
	}
	public static void setReady(boolean ready) {
		LinkImages.ready = ready;
	}
	

	

}
