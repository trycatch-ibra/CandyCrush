package md.cc.model;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape{
	protected int abs;
	protected int ord;
	protected Color color;
	private Color colors[] = { Color.WHITE, Color.RED, Color.GREEN, Color.BLUE, Color.GRAY, Color.PINK, Color.CYAN };
	
	public Shape(int color) {
		this.color = colors[color];
	}
	
	public int getAbs() {
		return abs;
	}
	
	public int getOrd() {
		return ord;
	}
	
	public Color getColor() {
		return color;
	}
	public void setAbs(int abs) {
		this.abs = abs;
	}
	
	public void setOrd(int ord) {
		this.ord = ord;
	}
	
	public abstract void draw(Graphics g);
}
