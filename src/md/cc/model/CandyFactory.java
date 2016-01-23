package md.cc.model;

public class CandyFactory{	
	
	public Shape getShape(int color, String type){
		Shape candy = null;;
        if(type.equalsIgnoreCase("circle")){
        	candy = new CircleCandy(color);
        }else if(type.equalsIgnoreCase("square")){
        	candy = new SquareCandy(color);
        }
        		
        return candy;
	}
	
	
}
