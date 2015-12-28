package fr.isl.md.cc.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Menu extends JMenu {
	
	private static final long serialVersionUID = 1L;
	private JMenuItem quit;

	public Menu() {
		this.setText("Menu");

		quit = new JMenuItem("Quit");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		this.add(quit);
	}

	public void ouvrir() {

	}

	public void sauvegarder() {

	}

	public void quitter() {

	}
}
