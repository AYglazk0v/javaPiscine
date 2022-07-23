package edu.school21.printer.app;
import edu.school21.printer.logic.ImageToChar;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Program {

	private static int height;
	private static int width;
	private static String white;
	private static String black;
	private static BufferedImage image;

	public static void main(String[] args) {
		if (args.length == 2) {
			if (args[0].startsWith("--white=")) {
				white = args[0].substring(8);
			} else {
				System.err.println("NOT CORRECT ARG");
				System.exit(-1);
			}
			if (args[1].startsWith("--black=")) {
				black = args[1].substring(8);
			} else {
				System.err.println("NOT CORRECT ARG");
				System.exit(-1);
			}
			try {
				image = ImageIO.read(ImageIO.class.getResource("/resources/image.bmp"));
			} catch (java.io.IOException e) {
				e.printStackTrace();
			}
			height = (int) image.getHeight();
			width = (int) image.getWidth();
			ImageToChar convert = new ImageToChar(image, height, width, white, black);
			convert.printImage();
		} else {
			System.err.println("Bro, give me 2 arguments:");
			System.err.println("--white=\"whiteReplaceColor in UPPER_CASE\", --white=\"blackReplaceColor in UPPER_CASE\"");
			System.exit(-1);
		}
	}
}
