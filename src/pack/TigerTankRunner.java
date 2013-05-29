package pack;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.lwjgl.Sys;

public class TigerTankRunner {
	Randomness Ra = new Randomness();
	int i = 0;
	TigerTank[] tigerTankArray = new TigerTank[16];
	int NoWayCount = 0;
	
	void createTigerTank(int x, int y) {
		if(tigerTankArray[i] == null || !(tigerTankArray[i].visible)){
				System.out.println("Created a tigerTank NR: "+ i + " Properties: "+ x + " "+ y);
				tigerTankArray[i] = new TigerTank(x, y);
			}else{
				createTigerTank(x, y);
			}
		if(i < tigerTankArray.length - 1){
				i++;
			}else{
				i = 0;
			}
	}

	public void moveTigerTank(CreateWorld CW) {
		for(int i = 0; i < tigerTankArray.length; i++){
		if(tigerTankArray[i] != null && tigerTankArray[i].visible){
			TigerTank TT = tigerTankArray[i];
			if(TT.alive){
								
			if(TryPlayer(TT, CW)){
				//nothing
			}else{
				Normal(TT, CW);
			}
				}
			//fix direction
			if(TT.goalX > TT.x && TT.direction != 90){
				TT.direction = 90;
			}
			if(TT.goalX < TT.x && TT.direction != 270){
				TT.direction = 270;
			}			
			if(TT.goalY > TT.y && TT.direction != 0){
				TT.direction = 0;
			}
			if(TT.goalY < TT.y && TT.direction != 180){
				TT.direction = 180;
			}
			}
		}
	}
	

	private void TryToShoot(CreateWorld CW, TigerTank TT) {
		//System.out.println("Trying to shoot from: ("+TT.x+","+TT.y+") Direction: "+TT.direction);
		if(ClearPath(TT.x, TT.y, TT.direction, CW)){
			if(TT.lastShot + 2000 < Sys.getTime()){
				//System.out.println("Shooting the player");
				TT.lastShot = Sys.getTime();
				if(TT.direction == 0 || TT.direction == 180){
					CW.EBR.createEnemyBullit(TT.x + 20, TT.y + 20, 5.5, Math.toRadians(TT.direction + 90));
				}else{
					CW.EBR.createEnemyBullit(TT.x + 20, TT.y + 20, 5.5, Math.toRadians(TT.direction - 90));
				}
			}
		}else{
			//System.out.println("Tried to shoot but couldn't");
		}
	}
	
	private boolean ClearPath(int x, int y, int atemptAngle, CreateWorld CW) {
		//System.out.println("Trying: ("+x+","+y+") direction: "+atemptAngle);
		int tryX = x + 20;
		int tryY = y + 20;
		while(tryX < 800 && tryY < 800 && tryX > 0 && tryY > 0){
			if(WallCollision(tryX, tryY, CW.WR.wallArray)){
				//System.out.println("Return false I");
				return false;
			}else{
				if(PlayerCollision(tryX, tryY, CW.PR.player)){
					return true;
				}else{
					if(atemptAngle == 0){
						tryY += 40;
					}
					if(atemptAngle == 90){
						tryX += 40;
					}
					if(atemptAngle == 180){
						tryY -= 40;
					}
					if(atemptAngle == 270){
						tryX -= 40;
					}
				}
			}
			//System.out.println("Nothing at: ("+tryX+","+tryY+")");
		}
		//System.out.println("Return false II");
		return false;
	}
	
	private boolean PlayerCollision(int tryX, int tryY, Player player) {
		
		if(tryX >= player.x - 20 && tryX < player.x + 20 & tryY >= player.y - 20 && tryY < player.y + 20){
			return true;
		}else{
			return false;
		}
	}

	private boolean WallCollision(int tryX, int tryY, Wall[] WallArray){
		for(int i = 0; i < WallArray.length; i++){
			if(WallArray[i] != null){
				Wall wall = WallArray[i];
				if(tryX >= wall.x && tryX < wall.x + 40 && tryY >= wall.y && tryY < wall.y + 40){
					//System.out.println("WallCollision! ("+tryX+","+tryY+") with wall NR: "+i);
					return true;
				}else{
					continue;
				}
			}
		}
		return false;
	}
	
	private boolean TryPlayer(TigerTank TT, CreateWorld CW) {
		//System.out.println("TryPlayer started");
			int NoPlayer = 0;
			Player play = CW.PR.player;
				if(TT.y == TT.goalY && TT.x == TT.goalX){
					//System.out.println("TigerTank got to his target!");
				if(play.x >= TT.x && play.x < TT.x + 40){
					//System.out.println("tigerTank and player X are (almost) the same");
					if(play.y > TT.y){
							if(!collision(TT.x, TT.y + 40, CW)){
								TT.direction = 0;
								TT.goalX = TT.x;
								TT.goalY = TT.y + 40;
							}
						}else{
							if(!collision(TT.x, TT.y - 40, CW)){
								TT.direction = 180;
								TT.goalX = TT.x;
								TT.goalY = TT.y - 40;
							}
						}
					//TryToShoot(CW, TT);
					}else{
						NoPlayer++;
					}
				if(play.y >= TT.y && play.y < TT.y + 40){
					//System.out.println("tigerTank and player Y are (almost) the same");
					if(play.x > TT.x){
						if(!collision(TT.x + 40, TT.y, CW)){
							TT.direction = 90;
							TT.goalX = TT.x + 40;
							TT.goalY = TT.y;
						}
					}else{
						if(!collision(TT.x - 40, TT.y, CW)){
							TT.direction = 270;
							TT.goalX = TT.x - 40;
							TT.goalY = TT.y;
						}
					}
					//TryToShoot(CW, TT);
				}else{
					NoPlayer++;
		}
	}
		if((play.x >= TT.x && play.x < TT.x + 40) || (play.y >= TT.y && play.y < TT.y + 40)){
			TryToShoot(CW, TT);
		}				

			if(NoPlayer < 2){
				//System.out.println("Called walktigerTank form TryPlayer");
				WalkTigerTank(TT);
				return true;
			}else{
			return false;
			}
	}

	private void Normal(TigerTank TT, CreateWorld CW){
		//System.out.println("Normal (walking) started");
			int WayLength = 1;
			if(TT.y == TT.goalY && TT.x == TT.goalX){
				//System.out.println("TigerTank got to his target!");
				int RA = Ra.RanInt(1,4);
				//System.out.println("Random: "+ RA);
				
				switch(RA){
				case 1 :{
					if(TT.direction != 180){
					if(!(collision(TT.x -40, TT.y + 40, CW) && collision(TT.x, TT.y + 80, CW) && collision(TT.x + 40, TT.y + 40, CW))){
					TT.direction = 0;
					WayLength = GetWayLength(TT, CW) - 1;
					if(!collision(TT.x, TT.y  + 40 * WayLength, CW)){
						TT.goalX = TT.x;
						TT.goalY = TT.y + 40*WayLength;
						//System.out.println("Goal: ("+ TT.goalX+","+TT.goalY+")II");
						break;
					}}}
					//System.out.println("Can't do that, collision!");
					TT.direction++;
				}
				case 2 :{
					if(TT.direction != 270){
					if(!(collision(TT.x + 40, TT.y + 40, CW) && collision(TT.x + 80, TT.y, CW) && collision(TT.x + 40, TT.y - 40, CW))){
					TT.direction = 90;
					WayLength = GetWayLength(TT, CW) - 1;
					if(!collision(TT.x + 40 * WayLength, TT.y, CW)){
						TT.goalX = TT.x + 40*WayLength;
						TT.goalY = TT.y;
						//System.out.println("Goal: ("+ TT.goalX+","+TT.goalY+")II");
						break;
					}}}
					//System.out.println("Can't do that, collision!");
					TT.direction++;
				}
				case 3 :{
					if(TT.direction != 0){
					if(!(collision(TT.x - 40, TT.y - 40, CW) && collision(TT.x, TT.y - 80, CW) && collision(TT.x + 40, TT.y - 40, CW))){
					TT.direction = 180;
					WayLength = GetWayLength(TT, CW) - 1;
					if(!collision(TT.x, TT.y - 40 * WayLength, CW)){
						TT.goalX = TT.x;
						TT.goalY = TT.y - 40*WayLength;
						//System.out.println("Goal: ("+ TT.goalX+","+TT.goalY+")II");
						break;
					}}}
					//System.out.println("Can't do that, collision!");
					TT.direction++;
				}
					
				case 4 :{
					if(TT.direction != 90){
					if(!(collision(TT.x - 40, TT.y + 40, CW) && collision(TT.x - 80, TT.y, CW) && collision(TT.x - 40, TT.y - 40, CW))){
					TT.direction = 270;
					WayLength = GetWayLength(TT, CW) - 1;
					if(!collision(TT.x - 40 * WayLength, TT.y, CW)){
						TT.goalX = TT.x - 40*WayLength;
						TT.goalY = TT.y;
						//System.out.println("Goal: ("+ TT.goalX+","+TT.goalY+")II");
						break;
					}}}
					//System.out.println("Can't do that, collision!");
					TT.direction++;
					}
				}
			}
			//System.out.println("Normal, started walktigerTank");
			WalkTigerTank(TT);
			}

	
	private int GetWayLength(TigerTank TT, CreateWorld CW) {
		//System.out.println("Started waylength");
		for(int i = 1; i < 10;){

			if(TT.direction == 0){
				//System.out.println("Case 0");
				//System.out.println("I = "+i);
				if(collision(TT.x + 40, TT.y + (i*40), CW) && collision( TT.x - 40, TT.y + (i*40), CW) && !collision(TT.x, TT.y + (40*i), CW)){
					//System.out.println("Het is een gang");
					i++;
					//System.out.println("i++ : "+ i);
				}else{
					//System.out.println("het is geen gang");
					NoWayCount++;
					if(NoWayCount > 1){
						//System.out.println("Geen gang te bekenen");
						NoWayCount = 0;
						return 2;
					}else{
						return i;
					}
				}
			}
			if(TT.direction == 90){
				//System.out.println("Case 90");
				//System.out.println("I = "+i);
				if(collision(TT.x +(40*i), TT.y - 40, CW) && collision( TT.x +(40*i), TT.y + 40, CW) && !collision(TT.x + (i*40), TT.y, CW)){
					//System.out.println("Het is een gang");
					i++;
					//System.out.println("i++ : "+ i);
				}else{
					//System.out.println("het is geen gang");
					NoWayCount++;
					if(NoWayCount > 1){
						//System.out.println("Geen gang te bekenen");
						NoWayCount = 0;
						return 2;
					}else{
						return i;
					}
				}
			}
			if(TT.direction == 180){
				//System.out.println("Case 180");
				//System.out.println("I = "+i);
				if(collision(TT.x + 40, TT.y - (i*40), CW) && collision( TT.x -40, TT.y - (i*40), CW) && !collision(TT.x, TT.y - (40*i), CW)){
					//System.out.println("Het is een gang");
					i++;
					//System.out.println("i++ : "+ i);
				}else{
					//System.out.println("het is geen gang");
					NoWayCount++;
					if(NoWayCount > 1){
						//System.out.println("Geen gang te bekenen");
						NoWayCount = 0;
						return 2;
					}else{
						return i;
					}
				}
			}
			if(TT.direction == 270){
				//System.out.println("Case 270");
				//System.out.println("I = "+i);
				if(collision(TT.x -(40*i), TT.y - 40, CW) && collision( TT.x -(40*i), TT.y + 40, CW) && !collision(TT.x - (40*i), TT.y, CW)){
					//System.out.println("Het is een gang");
					i++;
					//System.out.println("i++ : "+ i);
				}else{
					//System.out.println("het is geen gang");
					NoWayCount++;
					if(NoWayCount > 1){
						//System.out.println("Geen gang te bekenen");
						NoWayCount = 0;
						return 2;
					}else{
						return i;
					}
				}
			}
		}
		//System.out.println("dit hoor je neit te zien");
		return 1;
	}

	void WalkTigerTank(TigerTank TT){
		if(TT != null){
		if(TT.y != TT.goalY || TT.x != TT.goalX){
			double speed = 0.8;
		//System.out.println("WalkTigerTank! coords: ("+ TT.x + ","+ TT.y+") To ("+TT.goalX+","+TT.goalY+") Direction: "+ TT.direction+" ");
		double Speed = speed; // 40 must be dividable by speed
		
		//Don't drive off the map
		if(TT.goalY <= 0){
			TT.goalY = 40;
			TT.direction = 0;
		}
		if(TT.goalY > 800){
			TT.goalY = 720;
			TT.direction = 180;
		}
		
		//normal driving
		switch(TT.direction){
		default: {
			if(TT.y >= TT.goalY - 1 && TT.y <= TT.goalY + 1 && TT.x >= TT.goalX - 1 && TT.x <= TT.goalX + 1){ //realy close
				TT.y = (int) TT.goalY;
				TT.x = (int) TT.goalX;
				break;
			}
		}		
		case 0 :{
			TT.doubleY = TT.doubleY + Speed;
			TT.y = (int) Math.round(TT.doubleY);
			break;
		}
		case 90 :{
			TT.doubleX = TT.doubleX + Speed;
			TT.x = (int) Math.round(TT.doubleX);
			break;
		}
		case 180 :{
			TT.doubleY = TT.doubleY - Speed;
			TT.y = (int) Math.round(TT.doubleY);
			break;
		}
		case 270 :{
			TT.doubleX = TT.doubleX - Speed;
			TT.x = (int) Math.round(TT.doubleX);
			break;
			}		
			}
		}else{
			//System.out.println("Target reached!");
			}
		}
	}
	
	private boolean collision(int goalX, int goalY, CreateWorld CW) {
			//Collision with wall
			for(int i = 0; i < CW.WR.wallArray.length; i++){
				if(CW.WR.wallArray[i] != null){
				if(CW.WR.wallArray[i].x == goalX && CW.WR.wallArray[i].y == goalY){
					return true;
				}
			}
		}
			//Collision with Feldkanone
			for(int i = 0; i < CW.FKR.FKArray.length; i++){
				if(CW.FKR.FKArray[i] != null){
					if(CW.FKR.FKArray[i].x == goalX && CW.FKR.FKArray[i].y == goalY){
						return true;
					}
				}
			}
		return false;
	}
}