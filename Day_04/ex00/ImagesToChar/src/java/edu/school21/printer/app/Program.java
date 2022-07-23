package edu.school21.printer.app;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import edu.school21.printer.logic.ImageToChar;

public class Program {

	private static int height;
	private static int width;
	private static BufferedImage image;
	public static void main(String[] args) {
		if (args.length == 3) { 
			char white = args[0].charAt(0);
			char balck = args[1].charAt(0);
			File file = new File(args[2]);
			try {
				image = ImageIO.read(file);
				height = (int) image.getHeight();
				width = (int) image.getWidth();
				ImageToChar convert = new ImageToChar(image, height, width, white, balck);
				convert.printImage();
			} catch (IOException e) {
				System.err.println("Couldn't open this file!");
			}
	}
		else {
			System.err.println("Bro, give me 3 arguments:");
			System.err.println("whiteSymbol, blackSymbol, absolutPath");
			System.exit(-1);
		}
	}
}
