package fr.isl.md.cc.game;

import java.awt.Color;
import java.util.Random;

public class CandyFactory {
	// couleur des cases : 0 = vide
	Color colors[] = { Color.WHITE, Color.RED, Color.GREEN, Color.BLUE, Color.GRAY, Color.PINK, Color.CYAN };

	Random rand = new Random();

	public Shape getShape() {
		return new Candy(colors[1 + rand.nextInt(colors.length - 1)]);

	}

}
