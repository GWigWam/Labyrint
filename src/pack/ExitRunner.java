package pack;

public class ExitRunner{
	Exit exit;
	
	void createExit(int x, int y, boolean open) {
		//System.out.println("Created an exit "+ x +" "+ y + " Is it open?: " + open);
			exit = new Exit(x, y, open);
	}
	void openExit(){
		exit.open = true;
	}
	void closeExit(){
		exit.open = false;
	}
}