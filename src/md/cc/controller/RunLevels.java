package md.cc.controller;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import md.cc.model.Level1;
import md.cc.model.Level2;

public class RunLevels extends JFrame {
	JButton level1 = new JButton("Level 1");
	JButton level2 = new JButton("Level 2");
	FlowLayout levelLayout = new FlowLayout();

	public RunLevels() {

		level1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new Level1().loadLevel();
				Game.getInstance();
				exit();
			}
		});

		level2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new Level2().loadLevel();
				Game.getInstance();
				exit();

			}
		});

		this.getContentPane().setLayout(levelLayout);
		this.getContentPane().add(level1);
		this.getContentPane().add(level2);

		this.setTitle("Choose level !");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(250, 70));
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

	public void exit() {
		this.dispose();
	}
}
