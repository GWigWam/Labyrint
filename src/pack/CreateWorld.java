package pack;

import java.io.*;

import javax.swing.JFrame;
//import java.util.Scanner;
import javax.swing.JOptionPane;

public class CreateWorld{
	
	int level = 0;
	boolean won = false;
    boolean isCreated = false;
	
    WallRunner WR = new WallRunner();
    DotRunner DR = new DotRunner();
    PlayerRunner PR = new PlayerRunner();
    ExitRunner ER = new ExitRunner();
    JeepRunner JR = new JeepRunner();
    PlayerBullitRunner PBR = new PlayerBullitRunner();
    FKRunner FKR = new FKRunner();
    EnemyBullitRunner EBR = new EnemyBullitRunner();
    TigerTankRunner TTR = new TigerTankRunner();
    ButtonRunner BR = new ButtonRunner();
    
	public WallRunner GetWR(){
		//System.out.println("GettingWR");
		if(isCreated){
			return WR;
		}else{
			start();
			return WR;
		}
	}
	
	public DotRunner GetDR(){
		//System.out.println("GettingDR");
		if(isCreated){
			return DR;
		}else{
			start();
			return DR;
		}
	}
	
	public PlayerRunner GetPR() {
		//System.out.println("GettingPR");
		if(isCreated){
			return PR;
		}else{
			start();
			return PR;
		}
	}
	
	public ExitRunner GetER(){
		//System.out.println("GettingER");
		if(isCreated){
			return ER;
		}else{
			start();
			return ER;
		}
	}

	public JeepRunner GetJR() {
		//System.out.println("GettingJR");
		if(isCreated){
			return JR;
		}else{
			start();
			return JR;
		}
	}
    
	public PlayerBullitRunner GetPBR(){
		//System.out.println("GettingPBR");
		if(isCreated){
			return PBR;
		}else{
			start();
			return PBR;
		}
	}
	
    public void start(){
    if(!won){
    	System.out.println("Start in Createworld fired!");
    	try{
    		File file = new File("files/world/world"+ Integer.toString(level) +".txt");
    		System.out.println("Loading new world! NR: "+(level+1));
    		StringBuffer strContent = new StringBuffer("");
		    FileInputStream fin = null;
		    int ch;
		    String data;
		    fin = new FileInputStream(file);

	      while((ch = fin.read()) != -1){
	        strContent.append((char)ch);
	        }
	      fin.close();
	      
		    data = strContent.toString();	

		    readAndCreate(data, WR, DR);
		    isCreated = true;
	      }catch(FileNotFoundException e){
	    		won = true; // THIS THING HERE SAYS YOU WIN--------epic-------WIN!
	      }catch(IOException e){
	    	  System.out.println(e);
	      }
    	}
    }
    
	private void readAndCreate(String data, WallRunner WR, DotRunner DR) {
		//System.out.println("read and create");
		int index = 0;
		int truePlace = 0;
		
		try{
			//System.out.println("try to readAndCreate in CreatWorld.java");
			while(index < 472){
		switch(data.charAt(index)){
			case Entity.AIR:{
				index++;
				truePlace++;
				break;
			}
			
			case Entity.WALL:{
				//create block
				WR.createWall(calcX(truePlace), calcY(truePlace));
				
				index++;
				truePlace++;
				break;
				}
			
			case Entity.DOT:{
				DR.createDot(calcX(truePlace), calcY(truePlace), 45, 45);
				
				index++;
				truePlace++;
				break;
			}
			
			case Entity.PLAYER:{
				if(PR.player == null){
					PR.createPlayer(calcX(truePlace), calcY(truePlace));
				}else{
					PR.player.x = calcX(truePlace)+20;
					PR.player.y = calcY(truePlace);
				}
				index++;
				truePlace++;
				break;
			}
			
			case Entity.EXIT:{
				if(ER.exit == null){
					ER.createExit(calcX(truePlace), calcY(truePlace), false);
				}else{
					ER.exit.x = calcX(truePlace);
					ER.exit.y = calcY(truePlace);
					ER.closeExit();
				}
				
				index++;
				truePlace++;
				break;
			}
			
			case Entity.JEEP:{
				JR.createJeep(calcX(truePlace), calcY(truePlace), 0, true);
				index++;
				truePlace++;
				break;
			}
			
			case Entity.FELDKANONE :{
				FKR.createFeldkanone(calcX(truePlace), calcY(truePlace));
				index++;
				truePlace++;
				break;
			}
			
			case Entity.TIGERTANK :{
				TTR.createTigerTank(calcX(truePlace), calcY(truePlace));
				index++;
				truePlace++;
				break;
			}
			
			case Entity.BUTTON :{
				BR.createButton(calcX(truePlace), calcY(truePlace));
				index++;
				truePlace++;
				break;
			}
			
			default:{
				index++;
				break;
				}
			}
		}
			
		}catch(StringIndexOutOfBoundsException e){
		//e.printStackTrace();
		}
		if(PR.player == null){
			System.out.println("No player found in the world file");
			System.exit(0);
		}
	}

	private int calcX(int truePlace) {
		int X = 0;
		
		X = truePlace % 20;
		X = X*40;
		
		return X;
	}
	
	private int calcY(int truePlace) {
		//System.out.print("calcY "+ truePlace+" - ");
		int Y = 0;
		
		Y = (int) Math.floor(truePlace / 20);
		Y = Y*40;
		
		//System.out.println(Y);
		return Y;
	}
}


