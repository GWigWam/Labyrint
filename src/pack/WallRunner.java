package pack;

public class WallRunner {
	int i = 0;
	Wall[] wallArray = new Wall[512];
	
	void createWall(int x, int y) {
		if(wallArray[i] == null || wallArray[i].overWriteAble){
			//System.out.println("Created a wall NR: "+ i + " Properties: "+ x + " "+ y);
				wallArray[i] = new Wall(x, y);
			}else{
				//System.out.println("Wall NR: "+ i +" is not overwriteable or null");
				createWall(x, y);
			}
		i++;
	}	
}
