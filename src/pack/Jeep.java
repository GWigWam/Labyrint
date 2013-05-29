package pack;

public class Jeep {
	
	int x;
	int y;
	double doubleX;
	double doubleY;
	int direction;
	boolean alive; // boolean alive = sort of;
	boolean visible;
	int goalX;
	int goalY;
	int explodingState;
	
	public Jeep(int x, int y, int direction){
		this.x = x;
		this.y = y;
		this.doubleX = x;
		this.doubleY = y;
		this.direction = direction;
		this.alive = true;
		this.visible = true;
		this.goalX = x;
		this.goalY= y;
		this.explodingState = 0;
	}	
	
}