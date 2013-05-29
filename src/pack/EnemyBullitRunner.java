package pack;

public class EnemyBullitRunner {
	EnemyBullit[] enemyBullitArray = new EnemyBullit[8];
	int i;
	
	void createEnemyBullit(double x, double y, double speed, double rotation) {
		if(enemyBullitArray[i] == null || !enemyBullitArray[i].inUse){
				//System.out.println("Created a enemyBullit NR: "+ i + " Properties: ("+ x + ", "+ y+") direction: "+ rotation+" speed: "+ speed);
				enemyBullitArray[i] = new EnemyBullit(x, y, speed, rotation);
			}else{
				if(i == enemyBullitArray.length - 1){
					i = 0;
				}else{
					i++;
				}
				createEnemyBullit(x, y, speed, rotation);
			}
		}

	public void moveBullit(CreateWorld CW) {
		for(int i = 0; i < enemyBullitArray.length; i++){
			if(enemyBullitArray[i] != null && enemyBullitArray[i].inUse){
				EnemyBullit bullit = enemyBullitArray[i];				
					EnemyBullit EB = enemyBullitArray[i];
					Player player = CW.PR.player;
						if((EB.inUse && EB.x > player.x - 20 && EB.x < player.x + 20 && EB.y > player.y - 20 && EB.y < player.y + 20 && EB.explodingState == 0)){
							//System.out.println("HIT!");
							player.health--;
							bullit.speed = 0;
							bullit.explodingState = 1;
						}					
			if(bullit.explodingState == 0){
			if(CW.PR.collision(bullit.x , bullit.y , CW)){
				//System.out.println("EnemyBullit collision with wall");
				bullit.speed = 0;
				bullit.explodingState = 1;
				
			}else{
				bullit.x = bullit.x - Math.cos(bullit.rotation + Math.PI) * bullit.speed;
				bullit.y = bullit.y - Math.sin(bullit.rotation + Math.PI) * bullit.speed;
			}
			}
			if(bullit.x > 810 || bullit.x < -10 || bullit.y > 810 || bullit.y < -10 && bullit.inUse){
				bullit.inUse = false;
				bullit.speed = 0;
				i = 0;
				}
			}
		}
	}
}

