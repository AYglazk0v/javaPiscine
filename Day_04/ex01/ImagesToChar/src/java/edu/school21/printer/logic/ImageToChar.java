package edu.school21.printer.logic;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class ImageToChar {
	private BufferedImage img;
	private	char white;
	private char balck;
	private int width;
	private int height;

	public ImageToChar(BufferedImage img, int height, int width, char white, char balck) {
		this.img = img;
		this.height = height;
		this.width = width;
		this.balck = balck;
		this.white = white;
	}

	public void printImage() {
		for (int j = 0 ; j < this.height; j++) {
			for (int i = 0; i < this.width; i++) {
			Color color = new Color(img.getRGB(i, j));
				int rgb = color.getRGB();
				if (rgb == -1) {
					System.out.printf("%s", this.white);
				} else {
					System.out.printf("%s", this.balck);
				}
			}
			System.out.println();
		}
	}
}
