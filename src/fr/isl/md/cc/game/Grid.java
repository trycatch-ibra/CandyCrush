package fr.isl.md.cc.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.JPanel;

public class Grid extends JPanel implements /* Runnable, */ MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	private int width, height;
	private int rows;
	private int cols;
	private CandyFactory candyFactory = new CandyFactory();
	Random rand = new Random();
	Graphics graphic;

	// coordonnées des cases sélectionnées : -1 = non sélectionné
	int selectedX = -1, selectedY = -1;
	int swappedX = -1, swappedY = -1;

	Grid() {
		addMouseListener(this);
		addMouseMotionListener(this);
		// new Thread(this).start();
	}

	public void paint(Graphics g) {
		this.graphic = g;
		graphic.setColor(Color.WHITE);
		graphic.fillRect(0, 0, getWidth(), getHeight());
		int i;
		width = getSize().width;
		height = getSize().height;

		graphic.setColor(Color.BLACK);
		int rowHt = height / (rows);
		for (i = 0; i < rows; i++)
			graphic.drawLine(0, i * rowHt, width, i * rowHt);

		int rowWid = width / (cols);
		for (i = 0; i < cols; i++)
			graphic.drawLine(i * rowWid, 0, i * rowWid, height);

		// afficher la première case sélectionnée
		if (selectedX != -1 && selectedY != -1) {
			graphic.setColor(Color.ORANGE);
			graphic.fillRect(selectedX * 48 + 1, selectedY * 53 + 1, 47, 52);
		}

		// afficher la deuxième case sélectionnée
		if (swappedX != -1 && swappedY != -1) {
			graphic.setColor(Color.YELLOW);
			graphic.fillRect(swappedX * 48 + 1, swappedY * 53 + 1, 47, 52);
		}

		remplir(g);
	}

	// gestion des événements souris
	public void mousePressed(MouseEvent e) {
		// on appuie sur le bouton de la souris : récupérer les coordonnées de
		// la première case
		selectedX = e.getX() / 48;
		selectedY = e.getY() / 53;
		repaint();
		System.out.println("clic candy");
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

	public void remplir(Graphics g) {
		// afficher le contenu de la grille
		// cette partie doit etre gérée par Factory
		for (int i1 = 0; i1 < 8; i1++) {
			// g.setColor(Color.GREEN);

			// loading candies with candy factory
			for (int j = 0; j < 8; j++) {
				Candy c = (Candy) candyFactory.getShape();
				c.setAbs(i1);
				c.setOrd(j);
				g.setColor(c.getC());

				c.draw(g);
			}
		}
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public Graphics getGraphic() {
		return graphic;
	}

	public void setGraphic(Graphics graphic) {
		this.graphic = graphic;
	}

}
