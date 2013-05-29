package pack;

public class DotRunner {
	int index = 0;
	Dot[] dotArray = new Dot[128];
	
	void createDot(int x, int y, int width, int height) {
		if(index <= dotArray.length){
				dotArray[index] = new Dot(x, y, width, height);
				index++;
		}
	}
	int getAmountOfDots(){
		int amount = 0;
		for(int i = 0; i < dotArray.length; i++){
			if(dotArray[i] != null){
				if(dotArray[i].visible){
					amount++;
				}
			}
		}
		return amount;
	}
}
