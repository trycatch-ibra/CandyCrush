package fr.isl.md.cc.game;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class MainMenu extends JFrame {
	private JMenuBar menuBar;
	private Menu menu;
	private int height = 500;
	private int width = 600;
	private String title = "Candy crush";

	public MainMenu() {
		menuBar = new JMenuBar();
		menu = new Menu();
		menuBar.add(menu);
		this.setJMenuBar(menuBar);

		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(width, height));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
