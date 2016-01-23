package md.cc.view;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

/**
 * This class refers to MainMenu class in the class diagram of the model
 */
public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private JMenuBar menuBar;
	private Menu menu;
	private JPanel scorePanel;
	private Container container = getContentPane();

	public MainWindow() {
		// define the frame layout

		// setting menu bar
		menuBar = new JMenuBar();
		menu = new Menu();
		menuBar.add(menu);
		this.setJMenuBar(menuBar);

		// the main window settings
	}

	public JPanel getScorePanel() {
		return scorePanel;
	}

	public void setScorePanel(JPanel scorePanel) {
		this.scorePanel = scorePanel;
	}

	public Container getContainer() {
		return container;
	}

	public void setContainer(Container container) {
		this.container = container;
	}

}
