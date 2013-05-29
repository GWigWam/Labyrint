package pack;

import org.lwjgl.Sys;

public class Feldkanone {
	int x;
	int y;
	double rotation;
	int frame;
	long lastShot = Sys.getTime();
	boolean inUse;
	double tryAngle;
	boolean seePlayer = false;
	
	public Feldkanone(int x, int y){
		this.x = x;
		this.y = y;
	}

}
