package pack;

import org.lwjgl.Sys;

public class TigerTank {
	
	int x;
	int y;
	double doubleX;
	double doubleY;
	int direction;
	boolean alive = true;
	boolean visible = true;;
	int goalX;
	int goalY;
	int health = 2;
	long lastShot = Sys.getTime();
	
	public TigerTank(int x, int y){
		this.x = x;
		this.y = y;
		this.doubleX = x;
		this.doubleY = y;
		this.goalX = x;
		this.goalY = y;
	}	
	
}