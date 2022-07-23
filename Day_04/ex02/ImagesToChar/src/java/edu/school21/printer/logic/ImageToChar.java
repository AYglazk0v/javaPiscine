package edu.school21.printer.logic;
import java.awt.image.BufferedImage;
import java.awt.Color;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

public class ImageToChar {
	private BufferedImage img;
	private String	white;
	private	String	black;
	private int width;
	private int height;

	public ImageToChar(BufferedImage img, int height, int width, String color1, String color2) {
		this.img = img;
		this.height = height;
		this.width = width;
		this.black = color1;
		this.white = color2;
	}

	public void printImage() {

        Ansi.BColor color1 = Ansi.BColor.valueOf(white);
        Ansi.BColor color2 = Ansi.BColor.valueOf(black);
        ColoredPrinter cp1 = new ColoredPrinter.Builder(1, false).background(color1).build();
        ColoredPrinter cp2 = new ColoredPrinter.Builder(1, false).background(color2).build();
		for (int j = 0 ; j < this.height; j++) {
			for (int i = 0; i < this.width; i++) {
			Color color = new Color(img.getRGB(i, j));
				int rgb = color.getRGB();
				if (rgb == -1) {
					cp1.print(" ");;
				} else {
					cp2.print(" ");;
				}
			}
			System.out.println();
		}
	}
}
