package fr.isl.md.cc.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.JPanel;

public class Grid extends JPanel implements /*Runnable,*/ MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	private int width, height;
	private int rows;
	private int cols;
	private CandyFactory candyFactory;
	Color colors[] = { Color.WHITE, Color.RED, Color.GREEN, Color.BLUE, Color.GRAY, Color.PINK, Color.CYAN };
	Random rand = new Random();

	// coordonnées des cases sélectionnées : -1 = non sélectionné
	int selectedX = -1, selectedY = -1;
	int swappedX = -1, swappedY = -1;

	Grid(int w, int h, int r, int c) {
		setSize(width = w, height = h);
		rows = r;
		cols = c;
		addMouseListener(this);
		addMouseMotionListener(this);
	//	new Thread(this).start();
	}

	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		int i;
		width = getSize().width;
		height = getSize().height;

		g.setColor(Color.BLACK);
		int rowHt = height / (rows);
		for (i = 0; i < rows; i++)
			g.drawLine(0, i * rowHt, width, i * rowHt);

		int rowWid = width / (cols);
		for (i = 0; i < cols; i++)
			g.drawLine(i * rowWid, 0, i * rowWid, height);

		// afficher la première case sélectionnée
		if (selectedX != -1 && selectedY != -1) {
			g.setColor(Color.ORANGE);
			g.fillRect(selectedX * 48 + 1, selectedY * 53 + 1, 47, 52);
		}

		// afficher la deuxième case sélectionnée
		if (swappedX != -1 && swappedY != -1) {
			g.setColor(Color.YELLOW);
			g.fillRect(swappedX * 48 + 1, swappedY * 53 + 1, 47, 52);
		}

		// afficher le contenu de la grille
		// cette partie doit etre gérée par Factory
		for (int i1 = 0; i1 < 8; i1++) {
			g.setColor(Color.GREEN);
			for (int j = 0; j < 8; j++) {
				g.setColor(colors[1 + rand.nextInt(colors.length - 1)]);
				g.fillOval(48 * i1 + 3, 53 * j + 3, 44, 44);
			}
		}
	}

	// gestion des événements souris
	public void mousePressed(MouseEvent e) {
		// on appuie sur le bouton de la souris : récupérer les coordonnées de
		// la première case
		selectedX = e.getX() / 48;
		selectedY = e.getY() / 53;
		repaint();
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {

	}

	// non implémentés
	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
	}

//	// boucle principale
//	public void run() {
//		while (true) {
//			// un pas de simulation toutes les 100ms
//			try {
//				Thread.currentThread();
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//			}
//
//			// // s'il n'y a pas de case vide, chercher des alignements
//			// if (!fill()) {
//			// removeAlignments();
//			// }
//			//
//			// // redessiner
//			repaint();
//		}
//	}
}
