package fr.isl.md.cc.game;

import java.awt.Graphics;

import javax.swing.JPanel;

public abstract class Shape extends JPanel {

	private static final long serialVersionUID = 1L;

	public abstract void paint(Graphics g);

}
