package md.cc.model;

public class Level2 implements Level {

	public int degree = 0;

	public Level2() {
	
	}
	
	public int increaseDegree(int number) {
		degree = degree + number + 2;
		return degree ;
	}

}
