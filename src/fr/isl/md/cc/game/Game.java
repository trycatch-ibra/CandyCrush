package fr.isl.md.cc.game;

import java.sql.Time;

public class Game {
	private Time duration;
	private static Game game;
	
	private Game(){
		
	}
	
	public Game getInstance(){
		if(game == null){
			game =  new Game();
		}
		
		return game;
	}
	
	public void play() {
		
	}
	
	public void setLevel(Level l) {
		
	}
	
	
}
