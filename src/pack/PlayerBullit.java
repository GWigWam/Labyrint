package pack;

public class PlayerBullit {
	
	double x;
	double y;
	double speed;
	int rotation;
	boolean inUse;
	int explodingState;
	
	public PlayerBullit(double x, double y, double speed, int rotation){
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.rotation = rotation;
		this.inUse = true;
		this.explodingState = 0;
	}	
	
}