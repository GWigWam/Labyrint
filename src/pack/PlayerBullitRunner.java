package pack;

public class PlayerBullitRunner {
	Randomness Ra = new Randomness();
	PlayerBullit[] playerBullitArray = new PlayerBullit[8];
	int i = 0;
	int damage = 1;
	
	void createPlayerBullit(double x, double y, double speed, int rotation, Player player) {
		if(playerBullitArray[i] == null || playerBullitArray[i].inUse == false){
				//System.out.println("Created a playerBullit NR: "+ i + " Properties: ("+ x + ", "+ y+") direction: "+ rotation+" speed: "+ speed);
				player.ammo--;
				playerBullitArray[i] = new PlayerBullit(x, y, speed, rotation);
			}else{
				if(i < 8){
					i++;
				}else{
					i=0;
				}				
				createPlayerBullit(x, y, speed, rotation, player);
			}
		i = 0;
		}

	public void moveBullit(CreateWorld CW) {
		for(int i = 0; i < playerBullitArray.length; i++){
			if(playerBullitArray[i] != null){
				PlayerBullit bullit = playerBullitArray[i];
//-------------------------------------------------------------------------------------
				
					for(int II = 0; II < CW.JR.jeepArray.length; II++){
						if(CW.JR.jeepArray[II] != null){
							Jeep jeep = CW.JR.jeepArray[II];
							PlayerBullit pb = playerBullitArray[i];
								if((pb.inUse && pb.x > jeep.x && pb.x < jeep.x + 40 && pb.y > jeep.y && pb.y < jeep.y + 40 && jeep.alive)){
									//System.out.println("HIT!");
									jeep.alive = false;
									bullit.speed = 0;
									bullit.explodingState = 1;
									CW.PR.player.score += 100;
							}
						}
					}
					
					for(int III = 0; III < CW.TTR.tigerTankArray.length; III++){
						if(CW.TTR.tigerTankArray[III] != null){
							TigerTank tigerTank = CW.TTR.tigerTankArray[III];
							PlayerBullit pb = playerBullitArray[i];
								if((pb.inUse && pb.x > tigerTank.x && pb.x < tigerTank.x + 40 && pb.y > tigerTank.y && pb.y < tigerTank.y + 40 && tigerTank.alive && bullit.explodingState == 0)){
									//System.out.println("HIT!");
									tigerTank.health-= damage;
									bullit.speed = 0;
									bullit.explodingState = 1;
									if(tigerTank.health <= 0){
										tigerTank.alive = false;
										CW.PR.player.score += 250;
									}
							}
						}
					}
				
				
				if(bullit.explodingState == 0){
				if(CW.PR.collision(bullit.x , bullit.y , CW)){
					//System.out.println("PlayerBullit collision with wall");
					bullit.speed = 0;
					bullit.explodingState = 1;
					
				}else{
					bullit.x = bullit.x - Math.cos(Math.toRadians(bullit.rotation)) * bullit.speed;
					bullit.y = bullit.y - Math.sin(Math.toRadians(bullit.rotation)) * bullit.speed;
					//System.out.println("Bullit moved to: ("+bullit.x+", "+bullit.y+")");
				}
				}
				if(bullit.x > 810 || bullit.x < -10 || bullit.y > 810 || bullit.y < -10 && bullit.inUse){
					bullit.inUse = false;
					bullit.speed = 0;
					i = 0;
					//System.out.println("Bullit NR: "+ i+" is no longer in use");
				}
//-------------------------------------------------------------------------------------
			}
		}
	}
}
