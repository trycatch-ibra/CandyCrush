package md.cc.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;

import md.cc.controller.Game;
import md.cc.controller.GameSound;
import md.cc.model.CandyFactory;
import md.cc.model.Shape;

public class Grid extends Panel implements Runnable, MouseListener, MouseMotionListener {
	// grille avec un numéro de couleur par case
	int grid[][] = new int[8][8];
	// pour marquer les cases non alignées
	boolean marked[][] = new boolean[8][8];
	// couleur des cases : 0 = vide
	Color colors[] = { Color.WHITE, Color.RED, Color.GREEN, Color.BLUE, Color.GRAY, Color.PINK, Color.CYAN };
	// coordonnées des cass sélectionnées : -1 = non sélectionné
	int selectedX = -1, selectedY = -1;
	int swappedX = -1, swappedY = -1;

	// image pour le rendu hors écran
	Image buffer;

	// initialisation : événements souris et boucle principale
	public Grid() {
		// remplir une première fois la grille
		while (fill())
			;
		// enlever les alignements existants
		while (removeAlignments()) {
			fill();
		}
		addMouseListener(this);
		addMouseMotionListener(this);
		new Thread(this).start();
	}

	// gestion des événements souris
	public void mousePressed(MouseEvent e) {
		// on appuie sur le bouton de la souris : récupérer les coordonnées de
		// la première case
		selectedX = e.getX() / 48;
		selectedY = e.getY() / 48;
		repaint();
	}

	public void mouseMoved(MouseEvent e) {
		// on bouge la souris : récupérer les coordonnées de la deuxième case
		if (selectedX != -1 && selectedY != -1) {
			swappedX = e.getX() / 48;
			swappedY = e.getY() / 48;
			// si l'échange n'est pas valide, on cache la deuxième case
			if (!isValidSwap(selectedX, selectedY, swappedX, swappedY)) {
				swappedX = swappedY = -1;
			}
		}
		repaint();
	}

	public void mouseReleased(MouseEvent e) {
		// lorsque l'on relâche la souris il faut faire l'échange et cacher les
		// cases
		if (selectedX != -1 && selectedY != -1 && swappedX != -1 && swappedY != -1) {
			swap(selectedX, selectedY, swappedX, swappedY);
			try {
				GameSound.play();
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			}
			// on met à jour le score
			Game.updateScore();

		}
		selectedX = selectedY = swappedX = swappedY = -1;
		repaint();
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

	// est-ce qu'on a trois cases de la même couleur vers le droite depuis (i,
	// j) ?
	boolean horizontalAligned(int i, int j) {
		if (i < 0 || j < 0 || i >= 6 || j >= 8)
			return false;
		if (grid[i][j] == grid[i + 1][j] && grid[i][j] == grid[i + 2][j])
			return true;
		return false;
	}

	// est-ce qu'on a trois cases de la même couleur vers le bas depuis (i, j) ?
	boolean verticalAligned(int i, int j) {
		if (i < 0 || j < 0 || i >= 8 || j >= 6)
			return false;
		if (grid[i][j] == grid[i][j + 1] && grid[i][j] == grid[i][j + 2])
			return true;
		return false;
	}

	// échanger le contenu de deux cases
	void swap(int x1, int y1, int x2, int y2) {
		int tmp = grid[x1][y1];
		grid[x1][y1] = grid[x2][y2];
		grid[x2][y2] = tmp;

	}

	// détermine si l'échange entre deux cases est valide
	boolean isValidSwap(int x1, int y1, int x2, int y2) {
		// il faut que les cases soient dans la grille
		if (x1 == -1 || x2 == -1 || y1 == -1 || y2 == -1)
			return false;
		// que les cases soient à côté l'une de l'autre
		if (Math.abs(x2 - x1) + Math.abs(y2 - y1) != 1)
			return false;
		// et que les couleurs soient différentes
		if (grid[x1][y1] == grid[x2][y2])
			return false;

		// alors on effectue l'échange
		swap(x1, y1, x2, y2);

		// et on vérifie que ça créé un nouvel alignement
		boolean newAlignment = false;
		for (int i = 0; i < 3; i++) {
			newAlignment |= horizontalAligned(x1 - i, y1);
			newAlignment |= horizontalAligned(x2 - i, y2);
			newAlignment |= verticalAligned(x1, y1 - i);
			newAlignment |= verticalAligned(x2, y2 - i);
		}

		// puis on annule l'échange
		swap(x1, y1, x2, y2);
		return newAlignment;
	}

	// supprimer les alignements
	boolean removeAlignments() {
		// passe 1 : marquer tous les alignements
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (grid[i][j] != 0 && horizontalAligned(i, j)) {
					marked[i][j] = marked[i + 1][j] = marked[i + 2][j] = true;
				}
				if (grid[i][j] != 0 && verticalAligned(i, j)) {
					marked[i][j] = marked[i][j + 1] = marked[i][j + 2] = true;
				}
			}
		}
		// passe 2 : supprimer les cases marquées
		boolean modified = false;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (marked[i][j]) {
					grid[i][j] = 0;
					marked[i][j] = false;
					modified = true;
				}
			}
		}
		return modified;
	}

	// remplir les cases vides par gravité, et générer des cases aléatoirement
	// par le haut
	boolean fill() {
		Random rand = new Random();
		boolean modified = false;
		for (int i = 0; i < 8; i++) {
			for (int j = 7; j >= 0; j--) {
				if (grid[i][j] == 0) {
					if (j == 0)
						grid[i][j] = 1 + rand.nextInt(colors.length - 1);
					else {
						grid[i][j] = grid[i][j - 1];
						grid[i][j - 1] = 0;
					}
					modified = true;
				}
			}
		}
		return modified;
	}

	// évite le syntillements
	public void update(Graphics g) {
		paint(g);
	}

	// routine d'affichage : on fait du double buffering
	public void paint(Graphics g2) {
		if (buffer == null)
			buffer = createImage(800, 600);
		Graphics2D g = (Graphics2D) buffer.getGraphics();

		// fond
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());

		// afficher la grille vide
		g.setColor(Color.BLACK);
		for (int i = 0; i < 9; i++) {
			g.drawLine(48 * i, 0, 48 * i, 8 * 48 + 1);
			g.drawLine(0, 48 * i, 8 * 48 + 1, 48 * i);
		}

		// afficher la première case sélectionnée
		if (selectedX != -1 && selectedY != -1) {
			g.setColor(Color.ORANGE);
			g.fillRect(selectedX * 48 + 1, selectedY * 48 + 1, 47, 47);
		}

		// afficher la deuxième case sélectionnée
		if (swappedX != -1 && swappedY != -1) {
			g.setColor(Color.YELLOW);
			g.fillRect(swappedX * 48 + 1, swappedY * 48 + 1, 47, 47);
		}
		
		CandyFactory cf = new CandyFactory();
		// afficher le contenu de la grille
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Shape candy = cf.getShape(grid[i][j], "circle");
				candy.setAbs(i);
				candy.setOrd(j);
				g.setColor(candy.getColor());
				candy.draw(g);
			}
		}

		// copier l'image à l'écran
		g2.drawImage(buffer, 0, 0, null);
	}

	// boucle principale
	public void run() {
		while (true) {
			// un pas de simulation toutes les 100ms
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
			}

			// s'il n'y a pas de case vide, chercher des alignements
			if (!fill()) {
				removeAlignments();
			}

			// redessiner
			repaint();
		}
	}

}
