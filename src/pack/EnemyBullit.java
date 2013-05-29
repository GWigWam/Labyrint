package pack;

public class EnemyBullit {
	
	double x;
	double y;
	double speed;
	double rotation;
	boolean inUse = true;
	int explodingState = 0;
	
	public EnemyBullit(double x, double y, double speed, double rotation){
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.rotation = rotation;
	}	
	
}