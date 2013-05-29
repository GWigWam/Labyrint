package pack;

import org.lwjgl.Sys;

//Feldkanone Runner
public class FKRunner {
	Feldkanone[] FKArray = new Feldkanone[16];
	int i = 0;
	
	void createFeldkanone(int x, int y) {
		if(FKArray[i] == null || !FKArray[i].inUse){
			//System.out.println("Created a kanon NR: "+ i + " Properties: "+ x + " "+ y);
			FKArray[i] = new Feldkanone(x, y);
			FKArray[i].inUse = true;
		}else{
			createFeldkanone(x, y);
		}
		i++;
	}

	public void DoStuff(CreateWorld CW) {
		for(int i = 0; i < FKArray.length; i++){
			if(FKArray[i] != null){
				Feldkanone FK = FKArray[i];
				Player play = CW.PR.player;
					double dx = play.x - FK.x;
					double dy = play.y - FK.y;
					double atemptAngle = Math.atan(dy / dx); 
					if(play.x < FK.x + 20){
						atemptAngle += Math.PI;
					}
					if(ClearPath(FK.x+20, FK.y+20, atemptAngle, CW)){
						FK.seePlayer = true;
						if(atemptAngle > 0.46*Math.PI && atemptAngle < 1.0 * Math.PI){
							FK.tryAngle = atemptAngle + 0.05;
						}else if(atemptAngle > -0.5*Math.PI && atemptAngle < 0.05 * Math.PI){
							FK.tryAngle = atemptAngle - 0.05;
					}else{
						FK.tryAngle = atemptAngle;
					}
				}else{
					FK.seePlayer = false;
				}
				if(FK.rotation != FK.tryAngle){
					if(FK.rotation > -0.5*Math.PI && FK.rotation < 0 && !(FK.tryAngle > -0.5*Math.PI && FK.tryAngle < 0)){
						FK.rotation = FK.tryAngle;
					}else if(FK.rotation > FK.tryAngle){
						FK.rotation-= 0.016;
					}else{
						FK.rotation+= 0.016;
					}
				if((FK.tryAngle+0.03 > FK.rotation && FK.tryAngle-0.03 < FK.rotation) || (FK.tryAngle < 0 && FK.tryAngle > -0.5*Math.PI && FK.rotation > Math.PI && FK.rotation < 1.5*Math.PI)){
					FK.rotation = FK.tryAngle;
				}
			}
			if(FK.tryAngle == FK.rotation && FK.lastShot + 2000 < Sys.getTime() && FK.seePlayer){
				CW.EBR.createEnemyBullit(FK.x + 20 - Math.cos(FK.rotation + Math.PI) * 60, FK.y + 20 - Math.sin(FK.rotation + Math.PI) * 60, 7.0, FK.rotation);
				FK.lastShot = Sys.getTime();
				}	
			}
		}
	}

	private boolean ClearPath(int x, int y, double atemptAngle, CreateWorld CW) {
		int tryX = x;
		int tryY = y;
		while(tryX < 800 && tryY < 800){
			if(WallCollision(tryX, tryY, CW.WR.wallArray)){
				return false;
			}else{
				if(PlayerCollision(tryX, tryY, CW.PR.player)){
					return true;
				}else{
					tryX += Math.cos(atemptAngle) * 20;
					tryY += Math.sin(atemptAngle) * 20;
				}
			}
		}
		return false;
	}
	
	private boolean PlayerCollision(int tryX, int tryY, Player player) {
		
		if(tryX >= player.x && tryX <= player.x + 40 && tryY >= player.y && tryY <= player.y + 40){
			return true;
		}else{
			return false;
		}
	}

	private boolean WallCollision(int tryX, int tryY, Wall[] WallArray){
		for(int i = 0; i < WallArray.length; i++){
			if(WallArray[i] != null){
				Wall wall = WallArray[i];
				if(tryX >= wall.x && tryX <= wall.x + 40 && tryY >= wall.y && tryY <= wall.y + 40){
					return true;
				}
			}
		}
		return false;
	}
}
