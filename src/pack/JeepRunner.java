package pack;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class JeepRunner {
	Randomness Ra = new Randomness();
	int i = 0;
	Jeep[] jeepArray = new Jeep[16];
	int NoWayCount = 0;
	
	void createJeep(int x, int y, int direction,  boolean visible) {
		if(jeepArray[i] == null || !(jeepArray[i].visible)){
				//System.out.println("Created a jeep NR: "+ i + " Properties: "+ x + " "+ y);
				jeepArray[i] = new Jeep(x, y, direction);
			}else{
				createJeep(x, y, direction, visible);
			}
		if(i < jeepArray.length - 1){
			i++;
		}else{
			i = 0;
		}
	}

	public void moveJeep(CreateWorld CW) {
		for(int i = 0; i < jeepArray.length; i++){
		if(jeepArray[i] != null){
			if(jeepArray[i].alive){
			EatThePlayerXD(CW, jeepArray);
		
			if(TryPlayer(jeepArray[i], CW)){
				//nothing
			}else{
				Normal(jeepArray[i], CW);
			}
				}
			}
		}
	}

	private void EatThePlayerXD(CreateWorld CW, Jeep[] jeepArray2) {
		for(int i = 0; i < jeepArray.length; i++){
			if(jeepArray[i] != null){
				Jeep jeep = jeepArray[i];
				Player play = CW.PR.player;
				if(jeep.alive){
					if(play.x > jeep.x-30 && play.x < jeep.x + 30 && play.y + 30 > jeep.y && play.y < jeep.y + 30){
						explode(jeep, CW);
						jeep.alive = false;
					}
				}
			}
		}
	}

	private void explode(Jeep jeep, CreateWorld CW) {
		//System.out.println("Jeep exploding at: ("+jeep.x+","+jeep.y+")");
		jeep.alive = false;
		jeep.explodingState = 1;
	}

	private boolean TryPlayer(Jeep zom, CreateWorld CW) {
		//System.out.println("TryPlayer started");
			int NoPlayer = 0;
			Player play = CW.PR.player;
				if(zom.y == zom.goalY && zom.x == zom.goalX){
					//System.out.println("Jeep got to his target!");
				if(play.x >= zom.x && play.x < zom.x + 40){
					//System.out.println("jeep and player X are (almost) the same");
					if(play.y > zom.y){
							if(!collision(zom.x, zom.y + 40, CW)){
								zom.direction = 0;
								zom.goalX = zom.x;
								zom.goalY = zom.y + 40;
							}
						}else{
							if(!collision(zom.x, zom.y - 40, CW)){
								zom.direction = 180;
								zom.goalX = zom.x;
								zom.goalY = zom.y - 40;
							}
						}
					}else{
						NoPlayer++;
					}
				if(play.y >= zom.y && play.y < zom.y + 40){
					//System.out.println("jeep and player Y are (almost) the same");
					if(play.x > zom.x){
						if(!collision(zom.x + 40, zom.y, CW)){
							zom.direction = 90;
							zom.goalX = zom.x + 40;
							zom.goalY = zom.y;
						}
					}else{
						if(!collision(zom.x - 40, zom.y, CW)){
							zom.direction = 270;
							zom.goalX = zom.x - 40;
							zom.goalY = zom.y;
						}
					}
				}else{
					NoPlayer++;
		}
	}
			if(NoPlayer < 2){
				//System.out.println("Called walkjeep form TryPlayer");
				WalkJeep(zom);
				return true;
			}else{
			return false;
			}
}

	private void Normal(Jeep zom, CreateWorld CW){
		//System.out.println("Normal (walking) started");
			int WayLength = 1;
			if(zom.y == zom.goalY && zom.x == zom.goalX){
				//System.out.println("Jeep got to his target!");
				int RA = Ra.RanInt(1,4);
				//System.out.println("Random: "+ RA);
				
				switch(RA){
				case 1 :{
					if(zom.direction != 180){
					if(!(collision(zom.x -40, zom.y + 40, CW) && collision(zom.x, zom.y + 80, CW) && collision(zom.x + 40, zom.y + 40, CW))){
					zom.direction = 0;
					WayLength = GetWayLength(zom, CW) - 1;
					if(!collision(zom.x, zom.y  + 40 * WayLength, CW)){
						zom.goalX = zom.x;
						zom.goalY = zom.y + 40*WayLength;
						//System.out.println("Goal: ("+ zom.goalX+","+zom.goalY+")II");
						break;
					}}}
					//System.out.println("Can't do that, collision!");
					zom.direction++;
				}
				case 2 :{
					if(zom.direction != 270){
					if(!(collision(zom.x + 40, zom.y + 40, CW) && collision(zom.x + 80, zom.y, CW) && collision(zom.x + 40, zom.y - 40, CW))){
					zom.direction = 90;
					WayLength = GetWayLength(zom, CW) - 1;
					if(!collision(zom.x + 40 * WayLength, zom.y, CW)){
						zom.goalX = zom.x + 40*WayLength;
						zom.goalY = zom.y;
						//System.out.println("Goal: ("+ zom.goalX+","+zom.goalY+")II");
						break;
					}}}
					//System.out.println("Can't do that, collision!");
					zom.direction++;
				}
				case 3 :{
					if(zom.direction != 0){
					if(!(collision(zom.x - 40, zom.y - 40, CW) && collision(zom.x, zom.y - 80, CW) && collision(zom.x + 40, zom.y - 40, CW))){
					zom.direction = 180;
					WayLength = GetWayLength(zom, CW) - 1;
					if(!collision(zom.x, zom.y - 40 * WayLength, CW)){
						zom.goalX = zom.x;
						zom.goalY = zom.y - 40*WayLength;
						//System.out.println("Goal: ("+ zom.goalX+","+zom.goalY+")II");
						break;
					}}}
					//System.out.println("Can't do that, collision!");
					zom.direction++;
				}
					
				case 4 :{
					if(zom.direction != 90){
					if(!(collision(zom.x - 40, zom.y + 40, CW) && collision(zom.x - 80, zom.y, CW) && collision(zom.x - 40, zom.y - 40, CW))){
					zom.direction = 270;
					WayLength = GetWayLength(zom, CW) - 1;
					if(!collision(zom.x - 40 * WayLength, zom.y, CW)){
						zom.goalX = zom.x - 40*WayLength;
						zom.goalY = zom.y;
						//System.out.println("Goal: ("+ zom.goalX+","+zom.goalY+")II");
						break;
					}}}
					//System.out.println("Can't do that, collision!");
					zom.direction++;
					}
				}
			}
			//System.out.println("Normal, started walkjeep");
			WalkJeep(zom);
			}

	
	private int GetWayLength(Jeep zom, CreateWorld CW) {
		//System.out.println("Started waylength");
		for(int i = 1; i < 10;){

			if(zom.direction == 0){
				//System.out.println("Case 0");
				//System.out.println("I = "+i);
				if(collision(zom.x + 40, zom.y + (i*40), CW) && collision( zom.x - 40, zom.y + (i*40), CW) && !collision(zom.x, zom.y + (40*i), CW)){
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
			if(zom.direction == 90){
				//System.out.println("Case 90");
				//System.out.println("I = "+i);
				if(collision(zom.x +(40*i), zom.y - 40, CW) && collision( zom.x +(40*i), zom.y + 40, CW) && !collision(zom.x + (i*40), zom.y, CW)){
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
			if(zom.direction == 180){
				//System.out.println("Case 180");
				//System.out.println("I = "+i);
				if(collision(zom.x + 40, zom.y - (i*40), CW) && collision( zom.x -40, zom.y - (i*40), CW) && !collision(zom.x, zom.y - (40*i), CW)){
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
			if(zom.direction == 270){
				//System.out.println("Case 270");
				//System.out.println("I = "+i);
				if(collision(zom.x -(40*i), zom.y - 40, CW) && collision( zom.x -(40*i), zom.y + 40, CW) && !collision(zom.x - (40*i), zom.y, CW)){
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

	void WalkJeep(Jeep jeep){
		if(jeep != null){
		if(jeep.y != jeep.goalY || jeep.x != jeep.goalX){
		//System.out.println("WalkJeep! coords: ("+ jeep.x + ","+ jeep.y+") To ("+jeep.goalX+","+jeep.goalY+") Direction: "+ jeep.direction+" ");
		double Speed = 1.6; // 40 must be dividable by speed
		
		//Don't drive off the map
		if(jeep.goalY < 0){
			jeep.goalY = 80;
			jeep.direction = 0;
		}
		if(jeep.goalY > 800){
			jeep.goalY = 720;
			jeep.direction = 180;
		}
		
		//normal driving
		switch(jeep.direction){
		default: {
			if(jeep.y >= jeep.goalY - 1 && jeep.y <= jeep.goalY + 1 && jeep.x >= jeep.goalX - 1 && jeep.x <= jeep.goalX + 1){ //realy close
				jeep.y = (int) jeep.goalY;
				jeep.x = (int) jeep.goalX;
				break;
			}
		}		
		case 0 :{
			jeep.doubleY = jeep.doubleY + Speed;
			jeep.y = (int) Math.round(jeep.doubleY);
			break;
		}
		case 90 :{
			jeep.doubleX = jeep.doubleX + Speed;
			jeep.x = (int) Math.round(jeep.doubleX);
			break;
		}
		case 180 :{
			jeep.doubleY = jeep.doubleY - Speed;
			jeep.y = (int) Math.round(jeep.doubleY);
			//System.out.println(jeep.doubleY);
			//System.out.println(jeep.y);
			break;
		}
		case 270 :{
			jeep.doubleX = jeep.doubleX - Speed;
			jeep.x = (int) Math.round(jeep.doubleX);
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