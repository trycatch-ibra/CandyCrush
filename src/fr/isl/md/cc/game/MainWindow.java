package fr.isl.md.cc.game;

import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

/**
 * This class refers to MainMenu class in the class diagram of the model
 */
public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
	private Menu menu;
	private int height = 500;
	private int width = 400;
	private String title = "My super candy crush !";
	private JPanel scorePanel;
	private Grid grid;
	Container container = getContentPane();

	public MainWindow() {
		// define the frame layout
		BorderLayout mgr = new BorderLayout();
		mgr.setHgap(5);
		container.setLayout(mgr);

		// setting menu bar
		menuBar = new JMenuBar();
		menu = new Menu();
		menuBar.add(menu);
		this.setJMenuBar(menuBar);

		// setting panel for displaying scores and time
		scorePanel = new JPanel();
		JLabel time = new JLabel("Time - 00:00");
		JLabel score = new JLabel("Score - 00");
		this.scorePanel.add(time);
		this.scorePanel.add(score);
		container.add(scorePanel, BorderLayout.NORTH);

		// add a grid

		grid = new Grid();
		grid.setRows(8);
		grid.setCols(8);
		grid.setSize(width, height);

		container.add(grid, BorderLayout.CENTER);

		// the main window settings
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(width, height));
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
