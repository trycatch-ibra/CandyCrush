package md.cc.model;

import java.awt.Graphics;

public class CircleCandy extends Shape {
	
	public CircleCandy(int color) {
		super(color);
	}
	@Override
	public void draw(Graphics g) {
		g.fillOval(48 * abs + 1, 48 * ord + 1, 42, 44);
	}

}
