package pack;

public class Player {
	
	double x;
	double y;
	int health;
	boolean alive;
	int rotation;
	boolean firing;
	int ammo;
	double speed = 0;
	double MaxBackSpeed = -1.0;
	double MaxForwardSpeed = 1.5;
	int score;
	
	public Player(double x, double y,int health, int rotation, int ammo){
		this.x = x;
		this.y = y;
		this.health = health;
		this.alive = true;
		this.rotation = rotation;
		this.firing = false;
		this.ammo = ammo;
		this.score = 0;
	}	
	
}