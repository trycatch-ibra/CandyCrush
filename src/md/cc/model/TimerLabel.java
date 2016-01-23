package md.cc.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

import md.cc.controller.Game;

public class TimerLabel extends JLabel implements ActionListener {
	private Timer t;
	public int cpt = 0;

	public TimerLabel(int time) {
		this.cpt = time;
		t = new Timer(1000, this);
		t.start();
	}

	public void actionPerformed(ActionEvent e) {
		this.cpt--;
		if (this.cpt == 0) {
			Game.getInstance(cpt).end();
		}
		this.setText("Il vous reste: " + String.valueOf(this.cpt) + " s");
	}

	public int getCpt() {
		return cpt;
	}

	public void setCpt(int cpt) {
		this.cpt = cpt;
	}
}
