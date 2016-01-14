package fr.isl.md.cc.game;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape {

	private Color color;

	public Shape(Color c) {
		this.color = c;
	}

	public Color getC() {
		return color;
	}

	// refers to changeColor() in the class diagram
	public void setC(Color c) {
		this.color = c;
	}

	public abstract void draw(Graphics g);

}
