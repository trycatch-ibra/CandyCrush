package fr.isl.md.cc.game;

import java.awt.Color;
import java.awt.Graphics;

public class Candy extends Shape {
	private int abs;
	private int ord;

	public Candy(Color c) {
		super(c);
	}

	public int getAbs() {
		return abs;
	}

	public void setAbs(int abs) {
		this.abs = abs;
	}

	public int getOrd() {
		return ord;
	}

	public void setOrd(int ord) {
		this.ord = ord;
	}

	@Override
	public void draw(Graphics g) {
		System.out.println("jjchjgh$$$$");
		g.fillOval(48 * abs + 3, 53 * ord + 3, 44, 44);

	}

	public void deplacer() {

	}

}
