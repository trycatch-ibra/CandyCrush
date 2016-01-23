package md.cc.model;

public class Level1 implements Level {
	
	public int degree = 0;

	public Level1() {
	
	}
	
	public int increaseDegree(int number) {
		degree = degree + number;
		return degree ;
	}

}
