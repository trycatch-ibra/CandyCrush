package fr.isl.md.cc.game;

public final class Game {

	private static volatile Game game = null;

	private Game() {

		super();
	}

	public static synchronized Game getInstance() {
		if (game == null) {
			game = new Game();
		}

		return game;
	}

	public void play() {
		System.out.println("Play");
	}

	public void setLevel(Level l) {

	}


}
