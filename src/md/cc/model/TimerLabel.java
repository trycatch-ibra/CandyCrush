package md.cc.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

import md.cc.controller.Game;

public class TimerLabel extends JLabel implements ActionListener {
	private Timer t;
	public static int cpt = 0;

	public TimerLabel() {
		t = new Timer(1000, this);
		t.start();

	}

	public void actionPerformed(ActionEvent e) {
		cpt--;
		if (cpt == 0) {
			Game.end();
		}
		this.setText("Il vous reste: " + String.valueOf(cpt) + " s");
	}

}
