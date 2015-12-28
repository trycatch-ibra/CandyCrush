package fr.isl.md.cc.game;

import java.awt.Color;
import java.awt.Graphics;

public class Candy extends Shape {

	private static final long serialVersionUID = 1L;
	Color color;
	int abs;
	int ord;

	public Candy(Color color, int x, int y) {
		this.color = color;
		this.abs = x;
		this.ord = y;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(this.color);
		g.fillOval(abs, ord, 40, 4);

	}

	public int getAbs() {
		return abs;
	}

	public int getOrd() {
		return ord;
	}

	public void setAbs(int abs) {
		this.abs = abs;
	}

	public void setOrd(int ord) {
		this.ord = ord;
	}

}
