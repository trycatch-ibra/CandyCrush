package md.cc.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import md.cc.model.TimerLabel;
import md.cc.view.Grid;
import md.cc.view.MainWindow;

public class Game {
	public MainWindow mainWindow;
	private JPanel scorePanel;
	private static int scoreValue = 0;
	private Container container;
	private static JLabel scoreLabel;

	private int level;

	static Game INSTANCE = null;

	private Game(int time) {
		this.level = time;
		BorderLayout mgr = new BorderLayout();
		mgr.setHgap(5);
		mainWindow = new MainWindow();
		container = mainWindow.getContentPane();
		container.setLayout(mgr);

		mainWindow.setContainer(container);

		// setting panel for displaying scores and time

		TimerLabel tl = new TimerLabel(level);
		scoreLabel = new JLabel("  Votre score est: " + String.valueOf(scoreValue));

		// add a grid
		Grid cc = new Grid();
		cc.setPreferredSize(new Dimension(385, 385));
		scorePanel = new JPanel();
		mainWindow.setScorePanel(scorePanel);
		mainWindow.getScorePanel().add(tl);
		mainWindow.getScorePanel().add(scoreLabel);

		mainWindow.getScorePanel().add(scoreLabel);

		mainWindow.getContainer().add(scorePanel, BorderLayout.NORTH);

		mainWindow.getContainer().add(cc, BorderLayout.CENTER);
		mainWindow.setTitle("My super candy crush !");
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setPreferredSize(new Dimension(395, 464));
		mainWindow.pack();
		mainWindow.setResizable(false);
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setVisible(true);

	}

	public static Game getInstance(int time) {
		if (INSTANCE == null) {
			INSTANCE = new Game(time);
		}
		return INSTANCE;
	}

	public static void updateScore() {
		scoreValue += 10;
		scoreLabel.setText("  Votre score est: " + String.valueOf(scoreValue));
		scoreLabel.setForeground(Color.blue);
	}

	public static JLabel getScoreLabel() {
		return scoreLabel;
	}

	public static void setScoreLabel(JLabel scoreLabel) {
		Game.scoreLabel = scoreLabel;
	}

	// met le jeu dans une fenêtre
	public static void main(String args[]) {
		new RunLevels();
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void end() {
		JDialog.setDefaultLookAndFeelDecorated(true);
		int response = JOptionPane.showConfirmDialog(null, "Terminé ! voulez vous jouer une nouvelle partie ?",
				"Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.YES_OPTION) {
			Game.INSTANCE = null;
			mainWindow.dispose();
			scoreValue = 0;
			Game.getInstance(level);

		} else if (response == JOptionPane.NO_OPTION) {
			System.exit(0);
		}
	}
}
