package pack;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;

public class PlayerRunner{
	Player player;
	long lastTime = 0;
	int reloadTime = 1500;
	
	public void HealthMinusOne() {
		if(Sys.getTime() - 2000 > lastTime){
			player.health--;
			lastTime = Sys.getTime();
			if(player.health <= 0){
				player.alive = false;
			}
		}
	}
	
	void createPlayer(int x, int y) {
			System.out.println("Creating Player:  -  "+ x +" "+ y);
			player = new Player(x+20, y+20, 3, 270, 1);
	}

	
	public void moveLeft(CreateWorld CW) {
			player.rotation = player.rotation + 2;
	}
	
	public void moveRight(CreateWorld CW) {
		player.rotation = player.rotation - 2;
	}
	
	public void moveUp(CreateWorld CW) {
		double goalX = player.x - (Math.cos(Math.toRadians(player.rotation)) * player.speed);
		double goalY = player.y - (Math.sin(Math.toRadians(player.rotation)) * player.speed);
		//System.out.println(Math.sin(Math.toRadians(player.rotation)));
		
	if(player.speed > 0.06 ){
		if(	collision(goalX - Math.cos(Math.toRadians(player.rotation + 45)) * 17, player.y - (Math.sin(Math.toRadians(player.rotation + 45)) * 17), CW) ||
				collision(goalX - Math.cos(Math.toRadians(player.rotation - 45)) * 17, player.y - (Math.sin(Math.toRadians(player.rotation - 45)) * 17), CW) ||
				collision(goalX - Math.cos(Math.toRadians(player.rotation)) * 13, player.y - (Math.sin(Math.toRadians(player.rotation)) * 17), CW)){
			if(player.speed > 0.1){
				player.speed -= 0.07;
			}
			}else{
				player.x = goalX;
			}
		
		if(	collision(player.x - Math.cos(Math.toRadians(player.rotation + 45)) * 17, goalY - (Math.sin(Math.toRadians(player.rotation + 45)) * 17), CW) ||
				collision(player.x - Math.cos(Math.toRadians(player.rotation - 45)) * 17, goalY - (Math.sin(Math.toRadians(player.rotation - 45)) * 17), CW) ||
				collision(player.x - Math.cos(Math.toRadians(player.rotation)) * 13, goalY - (Math.sin(Math.toRadians(player.rotation)) * 17), CW)){
			if(player.speed > 0.1){
				player.speed -= 0.07;
			}
			}else{
				player.y = goalY;
			}
		
	}
	if(player.speed < -0.06 ){
		if(	collision(goalX - Math.cos(Math.toRadians(player.rotation + 45 + 180)) * 17, player.y - (Math.sin(Math.toRadians(player.rotation + 45 + 180)) * 17), CW) ||
				collision(goalX - Math.cos(Math.toRadians(player.rotation - 45 + 180)) * 17, player.y - (Math.sin(Math.toRadians(player.rotation - 45 + 180)) * 17), CW) ||
				collision(goalX - Math.cos(Math.toRadians(player.rotation + 180)) * 13, player.y - (Math.sin(Math.toRadians(player.rotation + 180)) * 17), CW)){
			if(player.speed < -0.1){
				player.speed += 0.07;
			}
			}else{
				player.x = goalX;
			}
		
		if(	collision(player.x - Math.cos(Math.toRadians(player.rotation + 45 + 180)) * 17, goalY - (Math.sin(Math.toRadians(player.rotation + 45 + 180)) * 17), CW) ||
				collision(player.x - Math.cos(Math.toRadians(player.rotation - 45 + 180)) * 17, goalY - (Math.sin(Math.toRadians(player.rotation - 45 + 180)) * 17), CW) ||
				collision(player.x - Math.cos(Math.toRadians(player.rotation + 180)) * 13, goalY - (Math.sin(Math.toRadians(player.rotation + 180)) * 17), CW)){
			if(player.speed < -0.1){
				player.speed += 0.07;
			}
			}else{
				player.y = goalY;
			}
		}
	}

	boolean collision(double tryX, double tryY, CreateWorld CW){
		Wall[] WallArray = CW.WR.wallArray;
		Dot[] dotArray = CW.DR.dotArray;
		Exit exit = CW.ER.exit;
		//System.out.println("Goal: ("+ tryX+","+tryY+")");
		
		for(int i = 0; i < WallArray.length; i++){
	//Collision with dot
		if(i < dotArray.length ){
			if(!(dotArray[i] == null ) && dotArray[i].visible && player.y >= dotArray[i].y && player.y <= (dotArray[i].y + dotArray[i].height) && player.x >= dotArray[i].x && player.x <= dotArray[i].x + dotArray[i].width){
				dotArray[i].visible = false;
				player.ammo++;
				player.score += 30;
			}
		}
	//Collision with exit
		if(player.y >= exit.y && player.y < exit.y + 20 && player.x >= exit.x && player.x <= exit.x + 40 && !(exit.open)){
			player.y = player.y - 1;
			return true;
		}
	
	//Collision with wall
		if(WallArray[i] != null){
			Wall wall = WallArray[i];
				
	if(tryX >= wall.x && tryX < wall.x + 40 && tryY > wall.y && tryY < wall.y + 40){
				return true;
			}
		}
	}
		return false;
}

	public void Shoot(CreateWorld CW) {
		if(lastTime + reloadTime   < Sys.getTime()){
			//System.out.println("firing ma laz0r");
			player.firing = true;
			lastTime = Sys.getTime();
			CW.PBR.createPlayerBullit(player.x, player.y, 10, player.rotation, CW.PR.player);
		}
	}

	public void speedRegulator(CreateWorld CW) {
		
		//if no keys are pressed
		if(!Keyboard.isKeyDown(Keyboard.KEY_UP) && !Keyboard.isKeyDown(Keyboard.KEY_W) && !Keyboard.isKeyDown(Keyboard.KEY_DOWN) && !Keyboard.isKeyDown(Keyboard.KEY_S)){
			if(player.speed > 0){
				player.speed -= 0.07;
				moveUp(CW);
			}
			if(player.speed < 0){
				player.speed += 0.07;
				moveUp(CW);
			}
			if((player.speed < 0.5 && player.speed > 0) || (player.speed > -0.5 && player.speed < 0)){
				player.speed = 0;
			}
		}	
		//if keys are pressed
		else{
			if(Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_W)){
				if(player.speed < player.MaxForwardSpeed){
					player.speed += 0.03;
				}
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_DOWN) || Keyboard.isKeyDown(Keyboard.KEY_S)){
				if(player.speed > player.MaxBackSpeed){
					player.speed -= 0.03;
				}
			}
			moveUp(CW);
		}
	}
}