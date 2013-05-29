package pack;

import java.util.Random;

public class Randomness {
	
	public int RanInt(int min, int max){
		Random rnd = new Random();
		int r = rnd.nextInt((max - min + 1)) + min;
		return r;
	}
	
	public double RanDouble(double min, double max){
		Random rnd = new Random();
		double r = (max-min)*rnd.nextDouble();
		return r + min;
	}
	
	public float RanFloat(float min, float max){
		Random rnd = new Random();
		float r = (max-min)*rnd.nextFloat();
		return r + min;
	}
	
	public boolean RanBoolean(){
		Random rnd = new Random();
		boolean r = rnd.nextBoolean();
		return r;
	}
}
