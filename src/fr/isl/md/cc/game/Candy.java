package fr.isl.md.cc.game;

import com.sun.javafx.geom.Shape;

import javafx.scene.paint.Color;

public class Candy extends MyShape {
	private int abs;
	private int ord;
	
	public Candy()   {
	
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

	public void draw() {
		super.draw();
	}
	
	public void changeColor(Color color) {
		super.changeColor(color);
	}

	@Override
	public Shape impl_configShape() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	
	
}
