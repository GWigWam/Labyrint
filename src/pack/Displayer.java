package pack;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;
import org.lwjgl.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.Font;

public class Displayer{

	CreateWorld CW = new CreateWorld();
	
	WallRunner WR = CW.GetWR();
	DotRunner DR = CW.GetDR();
	PlayerRunner PR = CW.GetPR();
	ExitRunner ER = CW.GetER();
	JeepRunner JR = CW.GetJR();
	FKRunner FKR = CW.FKR;
	TigerTankRunner TTR = CW.TTR;
	
	int score = 0;
	int scoreTimer = 0;
	int frameNumber = 0;
	int FPS = 0;
	long time = System.currentTimeMillis();
	int screenNumber = 1; //1=welcome 2=game 3=buy-screen 4=win
	int SelectedUpgrade = 1;
	long LastButtonPress = Sys.getTime();
	
	boolean boughtDamage = false;
	boolean boughtSpeed = false;
	boolean boughtRapidFire = false;
	
	Displayer(){
		start();
	}
	
	private void start() {
		initGL(800,800);
//--------------------------------------------\\
		Texture WelcomeScreen = null;
		Texture TX_player = null;
		Texture TX_player_firing = null;
		Texture TX_wall = null;
		Texture TX_dot = null;
		Texture TX_background = null;
		Texture TX_Exit_Closed = null;
		Texture TX_Exit_Open = null;
		Texture TX_Jeep_Alive = null;
		Texture TX_Jeep_Dead = null;
		Texture TX_Health = null;
		Texture TX_Lose = null;
		Texture TX_Health_One = null;
		Texture TX_Health_Two = null;
		Texture TX_Health_Three = null;
		Texture TX_Ball = null;
		Texture TX_No_Ammo = null;
		Texture E1 = null;
		Texture E2 = null;
		Texture E3 = null;
		Texture E4 = null;
		Texture E21 = null;
		Texture E22 = null;
		Texture E23 = null;
		Texture E24 = null;
		Texture E25 = null;
		Texture E26 = null;
		Texture E27 = null;
		Texture FKBarrel = null;
		Texture FK1 = null;
		Texture TX_Tiger_Tank = null;
		Texture TX_Dead_Tiger_Tank = null;
		Texture score30 = null;
		Texture score100 = null;
		Texture score250 = null;
		Texture Button = null;
		Texture ButtonPressed = null;
		Texture WinScreen = null;
		Texture Upgrade_Health = null;
		Texture Upgrade_Ammo = null;
		Texture Upgrade_Damage = null;
		Texture Upgrade_Speed = null;
		Texture Upgrade_RapidFire = null;
		Texture Upgrade_NotSelected = null;
		Texture Upgrade_Impossible = null;
		Texture ButtonContinue = null;
		Texture BuyScreen = null;
	

		try {
			WelcomeScreen = TextureLoader.getTexture("PNG" ,ResourceLoader.getResourceAsStream("files/img/UI/WelcomeScreen.png"));
			TX_wall = TextureLoader.getTexture("PNG" ,ResourceLoader.getResourceAsStream("files/img/Texture_Wall.png"));
			TX_dot = TextureLoader.getTexture("PNG" ,ResourceLoader.getResourceAsStream("files/img/Texture_Dot.png"));
			TX_background = TextureLoader.getTexture("PNG" ,ResourceLoader.getResourceAsStream("files/img/Background.png"));
			TX_player = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("files/img/Texture_Player.png"));
			TX_player_firing = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("files/img/Texture_Player_Firing.png"));
			TX_Exit_Closed = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("files/img/Texture_Exit_Closed.png"));
			TX_Exit_Open = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("files/img/Texture_Exit_Open.png"));
			TX_Jeep_Alive = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("files/img/Texture_Jeep_Alive.png"));
			TX_Jeep_Dead = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("files/img/Texture_Jeep_Dead.png"));
			TX_Health = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("files/img/UI/Health.png"));
			TX_Lose = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("files/img/Lose.png"));
			TX_Health_One = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("files/img/UI/Health_Bar_One.png"));
			TX_Health_Two = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("files/img/UI/Health_Bar_Two.png"));
			TX_Health_Three = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("files/img/UI/Health_Bar_Three.png"));
			TX_Ball = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("files/img/Ball.png"));
			TX_No_Ammo = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("files/img/UI/No_Ammo.png"));
			E1 = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("files/img/explosion/E1.png"));
			E2 = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("files/img/explosion/E2.png"));
			E3 = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("files/img/explosion/E3.png"));
			E4 = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("files/img/explosion/E4.png"));
			E21 = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("files/img/explosion/E21.png"));
			E22 = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("files/img/explosion/E22.png"));
			E23 = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("files/img/explosion/E23.png"));
			E24 = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("files/img/explosion/E24.png"));
			E25 = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("files/img/explosion/E25.png"));
			E26 = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("files/img/explosion/E26.png"));
			E27 = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("files/img/explosion/E27.png"));
			FKBarrel = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("files/img/FKBarrel.png"));
			FK1 = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("files/img/FK1.png"));
			TX_Tiger_Tank = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("files/img/TigerTank.png"));
			TX_Dead_Tiger_Tank = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("files/img/Dead_TigerTank.png"));
			score30 = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("files/img/UI/30.png"));
			score100 = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("files/img/UI/100.png"));
			score250 = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("files/img/UI/250.png"));
			Button = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("files/img/Button.png"));
			ButtonPressed = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("files/img/ButtonPressed.png"));
			WinScreen = TextureLoader.getTexture("PNG" ,ResourceLoader.getResourceAsStream("files/img/UI/WinScreen.png"));
			Upgrade_Health = TextureLoader.getTexture("PNG" ,ResourceLoader.getResourceAsStream("files/img/UI/Upgrade_Health.png"));
			Upgrade_Ammo = TextureLoader.getTexture("PNG" ,ResourceLoader.getResourceAsStream("files/img/UI/Upgrade_Ammo.png"));
			Upgrade_Damage = TextureLoader.getTexture("PNG" ,ResourceLoader.getResourceAsStream("files/img/UI/Upgrade_Damage.png"));
			Upgrade_Speed = TextureLoader.getTexture("PNG" ,ResourceLoader.getResourceAsStream("files/img/UI/Upgrade_Speed.png"));
			Upgrade_RapidFire = TextureLoader.getTexture("PNG" ,ResourceLoader.getResourceAsStream("files/img/UI/Upgrade_RapidFire.png"));
			Upgrade_NotSelected = TextureLoader.getTexture("PNG" ,ResourceLoader.getResourceAsStream("files/img/UI/Upgrade_NotSelected.png"));
			Upgrade_Impossible = TextureLoader.getTexture("PNG" ,ResourceLoader.getResourceAsStream("files/img/UI/Upgrade_Impossible.png"));
			ButtonContinue = TextureLoader.getTexture("PNG" ,ResourceLoader.getResourceAsStream("files/img/UI/Continue.png"));
			BuyScreen = TextureLoader.getTexture("PNG" ,ResourceLoader.getResourceAsStream("files/img/UI/BuyScreen.png"));
		}catch(FileNotFoundException e){
			System.out.println("Texture not found!");
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		while(!Display.isCloseRequested()){
					
		if(screenNumber == 1){
			RenderWelcomeScreen(WelcomeScreen);
		}
		
		if(screenNumber == 2){
		if(CW.GetPR().player.alive){
			//Movement, check etc
			CheckForLevelChange();
			JR.moveJeep(CW);
			TTR.moveTigerTank(CW);
			FKR.DoStuff(CW);
			PR.speedRegulator(CW);
			CW.GetPBR().moveBullit(CW);
			CW.EBR.moveBullit(CW);
			CheckGateShouldOpen();
			
			//Rendering
			RenderBackground(TX_background);
			RenderWall(TX_wall , WR.wallArray);
			RenderDot(TX_dot, DR.dotArray);
			RenderButton(Button, ButtonPressed);
			RenderExit(TX_Exit_Closed, TX_Exit_Open, ER.exit);
			RenderJeep(TX_Jeep_Alive, TX_Jeep_Dead, JR.jeepArray, E21, E22, E23, E24, E25, E26, E27);
			RenderTigerTank(TX_Tiger_Tank, TX_Dead_Tiger_Tank);
			RenderFeldkanone(FKBarrel, FK1);
			RenderPlayerBullit(TX_Ball, E1, E2, E3, E4);
			RenderEnemyBullit(TX_Ball, E1, E2, E3, E4);
			RenderPlayer(TX_player, TX_player_firing, PR.player);
			RenderScore(score30, score100, score250);
			RenderAmmoBar(TX_dot, TX_Health_One, TX_Health_Two, TX_Health_Three, PR.player);
			
			/*SHOW PLAYER COORDS
			TX_wall.bind();
			glBegin(GL_QUADS);
				glTexCoord2f(0,0);
				glVertex2d(player.x-1, player.y-1);
				glTexCoord2f(1,0);
				glVertex2d(player.x+1, player.y-1);
				glTexCoord2f(1,1);
				glVertex2d(player.x+1, player.y+1);
				glTexCoord2f(0,1);
				glVertex2d(player.x-1, player.y+1);
			glEnd();
				*/
		}
			PlayerLive(TX_Health, TX_Lose, TX_Health_One, TX_Health_Two, TX_Health_Three, PR.player);
		}
		
		if(screenNumber == 3){
			RenderBuyScreen(Upgrade_Health, Upgrade_Ammo, Upgrade_Damage, Upgrade_Speed, Upgrade_RapidFire, Upgrade_NotSelected, BuyScreen, ButtonContinue, Upgrade_Impossible);
			RenderAmmoBar(TX_dot, TX_Health_One, TX_Health_Two, TX_Health_Three, PR.player);
			PlayerLive(TX_Health, TX_Lose, TX_Health_One, TX_Health_Two, TX_Health_Three, PR.player);
		}
		
		if(screenNumber == 4){
			RenderWinScreen(WinScreen);
		}
		
			CheckKeys(CW, TX_No_Ammo);//---------CheckKeys-------------
			Display.update();
			
			//FPS meter
			if (time <= System.currentTimeMillis() - 1000) {			
					if(screenNumber != 1){
						Display.setTitle("Labyrint @ " + frameNumber + " FPS"+"   ---   Score: "+ CW.GetPR().player.score); //Normal
					}else{
						Display.setTitle("Labyrint @ " + frameNumber + " FPS"); //At Intro screen
					}

				FPS = frameNumber;
				frameNumber = 0;
				time = System.currentTimeMillis();
			} else {
				frameNumber++;
			}
			Display.sync(60);
		}
	}
	
	private void RenderBuyScreen(Texture Health, Texture Ammo, Texture Damage, Texture Speed, Texture RapidFire, Texture NotSel, Texture background, Texture Continue, Texture Impossible) {
		background.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0,1);
			glVertex2f(0, 0);
			glTexCoord2f(1,1);
			glVertex2f(800, 0);
			glTexCoord2f(1,0);
			glVertex2f(800, 800);
			glTexCoord2f(0,0);
			glVertex2f(0, 800);
		glEnd();
		
		
	//----------------Upgrades:--------------------
		Health.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0,1);
			glVertex2f(0+100, 0+400);
			glTexCoord2f(1,1);
			glVertex2f(200+100, 0+400);
			glTexCoord2f(1,0);
			glVertex2f(200+100, 200+400);
			glTexCoord2f(0,0);
			glVertex2f(0+100, 200+400);
		glEnd();
		
		if(!(SelectedUpgrade == 1)){
			NotSel.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0,1);
			glVertex2f(0+100, 0+400);
			glTexCoord2f(1,1);
			glVertex2f(200+100, 0+400);
			glTexCoord2f(1,0);
			glVertex2f(200+100, 200+400);
			glTexCoord2f(0,0);
			glVertex2f(0+100, 200+400);
		glEnd();
		}
		if(PR.player.score < 150){
			Impossible.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0,1);
			glVertex2f(0+100, 0+400);
			glTexCoord2f(1,1);
			glVertex2f(200+100, 0+400);
			glTexCoord2f(1,0);
			glVertex2f(200+100, 200+400);
			glTexCoord2f(0,0);
			glVertex2f(0+100, 200+400);
		glEnd();
		}
		//---
		Ammo.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0,1);
			glVertex2f(0+300, 0+400);
			glTexCoord2f(1,1);
			glVertex2f(200+300, 0+400);
			glTexCoord2f(1,0);
			glVertex2f(200+300, 200+400);
			glTexCoord2f(0,0);
			glVertex2f(0+300, 200+400);
		glEnd();
		
		if(!(SelectedUpgrade == 2)){
			NotSel.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0,1);
			glVertex2f(0+300, 0+400);
			glTexCoord2f(1,1);
			glVertex2f(200+300, 0+400);
			glTexCoord2f(1,0);
			glVertex2f(200+300, 200+400);
			glTexCoord2f(0,0);
			glVertex2f(0+300, 200+400);
		glEnd();
		}
		if(PR.player.score < 100){
			Impossible.bind();
		glBegin(GL_QUADS);
		glTexCoord2f(0,1);
			glVertex2f(0+300, 0+400);
			glTexCoord2f(1,1);
			glVertex2f(200+300, 0+400);
			glTexCoord2f(1,0);
			glVertex2f(200+300, 200+400);
			glTexCoord2f(0,0);
			glVertex2f(0+300, 200+400);
		glEnd();
		}
		//---
		Damage.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0,1);
			glVertex2f(0+500, 0+400);
			glTexCoord2f(1,1);
			glVertex2f(200+500, 0+400);
			glTexCoord2f(1,0);
			glVertex2f(200+500, 200+400);
			glTexCoord2f(0,0);
			glVertex2f(0+500, 200+400);
		glEnd();
		
		if(!(SelectedUpgrade == 3)){
			NotSel.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0,1);
			glVertex2f(0+500, 0+400);
			glTexCoord2f(1,1);
			glVertex2f(200+500, 0+400);
			glTexCoord2f(1,0);
			glVertex2f(200+500, 200+400);
			glTexCoord2f(0,0);
			glVertex2f(0+500, 200+400);
		glEnd();
		}
		if(boughtDamage || PR.player.score < 500){
			Impossible.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0,1);
			glVertex2f(0+500, 0+400);
			glTexCoord2f(1,1);
			glVertex2f(200+500, 0+400);
			glTexCoord2f(1,0);
			glVertex2f(200+500, 200+400);
			glTexCoord2f(0,0);
			glVertex2f(0+500, 200+400);
		glEnd();
		}
		//---
		Speed.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0,1);
			glVertex2f(0+200, 0+200);
			glTexCoord2f(1,1);
			glVertex2f(200+200, 0+200);
			glTexCoord2f(1,0);
			glVertex2f(200+200, 200+200);
			glTexCoord2f(0,0);
			glVertex2f(0+200, 200+200);
		glEnd();
		
		if(!(SelectedUpgrade == 4)){
			NotSel.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0,1);
			glVertex2f(0+200, 0+200);
			glTexCoord2f(1,1);
			glVertex2f(200+200, 0+200);
			glTexCoord2f(1,0);
			glVertex2f(200+200, 200+200);
			glTexCoord2f(0,0);
			glVertex2f(0+200, 200+200);
		glEnd();
		}
		if(boughtSpeed || PR.player.score < 250){
			Impossible.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0,1);
			glVertex2f(0+200, 0+200);
			glTexCoord2f(1,1);
			glVertex2f(200+200, 0+200);
			glTexCoord2f(1,0);
			glVertex2f(200+200, 200+200);
			glTexCoord2f(0,0);
			glVertex2f(0+200, 200+200);
		glEnd();
		}
		//---
		RapidFire.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0,1);
			glVertex2f(0+400, 0+200);
			glTexCoord2f(1,1);
			glVertex2f(200+400, 0+200);
			glTexCoord2f(1,0);
			glVertex2f(200+400, 200+200);
			glTexCoord2f(0,0);
			glVertex2f(0+400, 200+200);
		glEnd();
		
		if(!(SelectedUpgrade == 5)){
			NotSel.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0,1);
			glVertex2f(0+400, 0+200);
			glTexCoord2f(1,1);
			glVertex2f(200+400, 0+200);
			glTexCoord2f(1,0);
			glVertex2f(200+400, 200+200);
			glTexCoord2f(0,0);
			glVertex2f(0+400, 200+200);
		glEnd();
		}
		if(boughtRapidFire || PR.player.score < 500){
			Impossible.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0,1);
			glVertex2f(0+400, 0+200);
			glTexCoord2f(1,1);
			glVertex2f(200+400, 0+200);
			glTexCoord2f(1,0);
			glVertex2f(200+400, 200+200);
			glTexCoord2f(0,0);
			glVertex2f(0+400, 200+200);
		glEnd();
		}
		//---
		Continue.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2f(680, 20+100);
			glTexCoord2f(1,0);
			glVertex2f(680+100, 20+100);
			glTexCoord2f(1,1);
			glVertex2f(680+100, 20);
			glTexCoord2f(0,1);
			glVertex2f(680, 20);
		glEnd();
		
		if(!(SelectedUpgrade == 6)){
			NotSel.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2f(680, 20+100);
			glTexCoord2f(1,0);
			glVertex2f(680+100, 20+100);
			glTexCoord2f(1,1);
			glVertex2f(680+100, 20);
			glTexCoord2f(0,1);
			glVertex2f(680, 20);
		glEnd();
		}
		//---
	}

	private void RenderWelcomeScreen(Texture texture) {
		texture.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0,1);
			glVertex2f(0, 0);
			glTexCoord2f(1,1);
			glVertex2f(800, 0);
			glTexCoord2f(1,0);
			glVertex2f(800, 800);
			glTexCoord2f(0,0);
			glVertex2f(0, 800);
		glEnd();
		//---
		
	}

	private void CheckForLevelChange() {
		
		if(CW.won){
			System.out.println("Research shows, we won the game!");
			screenNumber = 4;
		}else{
		
			//The method GetX() should always be called before all other stuff
		if(CW.GetPR().player.y > 790){
			CW.GetWR().i = 0;
			for(int i = 0; i < CW.GetWR().wallArray.length; i++){
				if(CW.GetWR().wallArray[i] != null){
					CW.GetWR().wallArray[i].overWriteAble = true;
					CW.GetWR().wallArray[i].x = -20;
					CW.GetWR().wallArray[i].y = -20;
				}
			}
			for(int i = 0; i < CW.GetJR().jeepArray.length; i++){
				if(CW.GetJR().jeepArray[i] != null){
					CW.GetJR().jeepArray[i].visible= false;
					CW.GetJR().jeepArray[i].x = -100;
					CW.GetJR().jeepArray[i].y = -100;
				}
			}
			for(int i = 0; i < CW.GetDR().dotArray.length; i++){
				if(CW.GetDR().dotArray[i] != null){
					CW.GetDR().dotArray[i].visible= false;
					CW.GetDR().dotArray[i].x = -100;
					CW.GetDR().dotArray[i].y = -100;
				}
			}
			for(int i = 0; i < CW.FKR.FKArray.length; i++){
				if(CW.FKR.FKArray[i] != null){
					CW.FKR.FKArray[i].inUse = false;
					CW.FKR.FKArray[i].x = -100;
					CW.FKR.FKArray[i].y = -100;
				}
			}
			for(int i = 0; i < CW.TTR.tigerTankArray.length; i++){
				if(CW.TTR.tigerTankArray[i] != null){
					System.out.println("TT NR: "+i+" is now invisible");
					CW.TTR.tigerTankArray[i].visible = false;
					CW.TTR.tigerTankArray[i].x = -100;
					CW.TTR.tigerTankArray[i].y = -100;
				}
			}
			
			CW.BR.button.x = 840;
			CW.BR.button.y = 840;
			CW.GetER().closeExit();
			
			CW.level++;
			CW.isCreated = false;
			screenNumber = 3;			
		}
		if(CW.GetPR().player.y < 0){
			CW.GetPR().player.y = 1;
		}
	}
	}

	private void RenderBackground(Texture texture) {
		texture.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2f(0, 0);
			glTexCoord2f(1,0);
			glVertex2f(800, 0);
			glTexCoord2f(1,1);
			glVertex2f(800, 800);
			glTexCoord2f(0,1);
			glVertex2f(0, 800);
		glEnd();
	}
	
	private void RenderDot(Texture texture, Dot[] dot) {
		for(int i = 0; i < dot.length; i++)
		if(dot[i] != null){
			if(dot[i].visible){
			texture.bind();
			glBegin(GL_QUADS);
			glTexCoord2f(0,1);
			glVertex2i(dot[i].x, dot[i].y);
			glTexCoord2f(1, 1);
			glVertex2i(dot[i].x+dot[i].width, dot[i].y);
			glTexCoord2f(1, 0);
			glVertex2i(dot[i].x+dot[i].width, dot[i].y+dot[i].height);
			glTexCoord2f(0, 0);
			glVertex2i(dot[i].x, dot[i].y+dot[i].height);
		glEnd();
	}
		}else{
			break;
		}
	}
	
	private void RenderWall(Texture texture, Wall[] wall) {
	for(int i = 0; i < wall.length; i++){
	if(wall[i] != null){
		texture.bind();	
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2i(wall[i].x, wall[i].y);
		glTexCoord2f(1, 0);
		glVertex2i(wall[i].x+wall[i].width, wall[i].y);
		glTexCoord2f(1, 1);
		glVertex2i(wall[i].x+wall[i].width, wall[i].y+wall[i].height);
		glTexCoord2f(0, 1);
		glVertex2i(wall[i].x, wall[i].y+wall[i].height);
	glEnd();
		}else{
			break;
		}
	}
}
	
	private void RenderButton(Texture button, Texture buttonPressed) {
		Button bu = CW.BR.button;
		double px = CW.GetPR().player.x;
		double py = CW.GetPR().player.y;
		double bx = CW.BR.button.x;
		double by = CW.BR.button.y;
		
	if(px > bx+10 && px < bx+30 && py > by+10 && py < by+30){
		buttonPressed.bind();
	glBegin(GL_QUADS);
		glTexCoord2f(0, 1);
		glVertex2i(bu.x, bu.y);
		glTexCoord2f(1, 1);
		glVertex2i(bu.x + 40, bu.y);
		glTexCoord2f(1, 0);
		glVertex2i(bu.x+40, bu.y+40);
		glTexCoord2f(0, 0);
		glVertex2i(bu.x, bu.y+40);
	glEnd();
	}else{
		button.bind();
	glBegin(GL_QUADS);
		glTexCoord2f(0, 1);
		glVertex2i(bu.x, bu.y);
		glTexCoord2f(1, 1);
		glVertex2i(bu.x + 40, bu.y);
		glTexCoord2f(1, 0);
		glVertex2i(bu.x+40, bu.y+40);
		glTexCoord2f(0, 0);
		glVertex2i(bu.x, bu.y+40);
	glEnd();
		}
	}
	
	private void RenderExit(Texture Closed, Texture Open, Exit exit) {
		Texture texture = null;
			if(exit.open){
				texture = Open;
			}else{
				texture = Closed;
			}
		texture.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2i(exit.x, exit.y);
			glTexCoord2f(1, 0);
			glVertex2i(exit.x+40, exit.y);
			glTexCoord2f(1, 1);
			glVertex2i(exit.x+40, exit.y+40);
			glTexCoord2f(0, 1);
			glVertex2i(exit.x, exit.y+40);
		glEnd();
	}
	
	private void RenderJeep(Texture Alive, Texture Dead, Jeep[] Jeep, Texture e21, Texture e22, Texture e23, Texture e24, Texture e25, Texture e26, Texture e27) {
		for(int i = 0; i < Jeep.length; i++){
			if(Jeep[i] != null){
				
				int x = (int) Jeep[i].x;
				int y = (int) Jeep[i].y;
				Jeep jeep = Jeep[i];
				
				if(Jeep[i].alive){
				Alive.bind();
				switch(Jeep[i].direction){
					case 0:{
					glBegin(GL_QUADS);
						glTexCoord2f(0, 0);
						glVertex2i(x , y + 40);
						glTexCoord2f(1, 0);
						glVertex2i(x , y);
						glTexCoord2f(1, 1);
						glVertex2i(x + 40, y);
						glTexCoord2f(0, 1);
						glVertex2i(x + 40, y + 40);
					glEnd();
						break;
					}
					case 90:{
					glBegin(GL_QUADS);
						glTexCoord2f(0, 0);
						glVertex2i(x + 40, y);
						glTexCoord2f(1 , 0);
						glVertex2i(x , y);
						glTexCoord2f(1, 1);
						glVertex2i(x, y + 40);
						glTexCoord2f(0, 1);
						glVertex2i(x + 40, y + 40);
					glEnd();
						break;
					}
					case 180:{
					glBegin(GL_QUADS);
						glTexCoord2f(0, 0);
						glVertex2i(x + 40, y);
						glTexCoord2f(1, 0);
						glVertex2i(x + 40, y + 40);
						glTexCoord2f(1, 1);
						glVertex2i(x, y + 40);
						glTexCoord2f(0, 1);
						glVertex2i(x, y);
					glEnd();
						break;
					}
					case 270:{
					glBegin(GL_QUADS);
						glTexCoord2f(0, 0);
						glVertex2i(x , y);
						glTexCoord2f(1 , 0);
						glVertex2i(x + 40, y);
						glTexCoord2f(1, 1);
						glVertex2i(x + 40, y + 40);
						glTexCoord2f(0, 1);
						glVertex2i(x, y + 40);
					glEnd();
						break;
					}
				}
				}else if(!jeep.alive){
				Dead.bind();
					glBegin(GL_QUADS);
					glTexCoord2f(0, 0);
					glVertex2i(x , y);
					glTexCoord2f(1 , 0);
					glVertex2i(x + 40, y);
					glTexCoord2f(1, 1);
					glVertex2i(x + 40, y + 40);
					glTexCoord2f(0, 1);
					glVertex2i(x, y + 40);
				glEnd();
				
				if(jeep.explodingState >= 1 && jeep.explodingState < 5){
					e21.bind();
					glBegin(GL_QUADS);
						glTexCoord2f(0, 0);
						glVertex2d(jeep.x-50, jeep.y-50);
						glTexCoord2f(1, 0);
						glVertex2d(jeep.x+50, jeep.y-50);
						glTexCoord2f(1, 1);
						glVertex2d(jeep.x+50, jeep.y+50);
						glTexCoord2f(0, 1);
						glVertex2d(jeep.x-50, jeep.y+50);
					glEnd();
					jeep.explodingState++;
				}else if(jeep.explodingState >= 5 && jeep.explodingState < 12){
					e22.bind();
				glBegin(GL_QUADS);
					glTexCoord2f(0, 0);
					glVertex2d(jeep.x-50, jeep.y-50);
					glTexCoord2f(1, 0);
					glVertex2d(jeep.x+50, jeep.y-50);
					glTexCoord2f(1, 1);
					glVertex2d(jeep.x+50, jeep.y+50);
					glTexCoord2f(0, 1);
					glVertex2d(jeep.x-50, jeep.y+50);
				glEnd();
					jeep.explodingState++;
				}else if(jeep.explodingState >= 12 && jeep.explodingState < 17){
					e23.bind();
				glBegin(GL_QUADS);
					glTexCoord2f(0, 0);
					glVertex2d(jeep.x-50, jeep.y-50);
					glTexCoord2f(1, 0);
					glVertex2d(jeep.x+50, jeep.y-50);
					glTexCoord2f(1, 1);
					glVertex2d(jeep.x+50, jeep.y+50);
					glTexCoord2f(0, 1);
					glVertex2d(jeep.x-50, jeep.y+50);
				glEnd();
					jeep.explodingState++;
				}else if(jeep.explodingState >= 17 && jeep.explodingState < 22){
					e24.bind();
				glBegin(GL_QUADS);
					glTexCoord2f(0, 0);
					glVertex2d(jeep.x-50, jeep.y-50);
					glTexCoord2f(1, 0);
					glVertex2d(jeep.x+50, jeep.y-50);
					glTexCoord2f(1, 1);
					glVertex2d(jeep.x+50, jeep.y+50);
					glTexCoord2f(0, 1);
					glVertex2d(jeep.x-50, jeep.y+50);
				glEnd();
				jeep.explodingState++;
				}else if(jeep.explodingState >= 22 && jeep.explodingState < 26){
					e25.bind();
				glBegin(GL_QUADS);
					glTexCoord2f(0, 0);
					glVertex2d(jeep.x-50, jeep.y-50);
					glTexCoord2f(1, 0);
					glVertex2d(jeep.x+50, jeep.y-50);
					glTexCoord2f(1, 1);
					glVertex2d(jeep.x+50, jeep.y+50);
					glTexCoord2f(0, 1);
					glVertex2d(jeep.x-50, jeep.y+50);
				glEnd();
				jeep.explodingState++;
				}else if(jeep.explodingState >= 26 && jeep.explodingState < 31){
					e26.bind();
				glBegin(GL_QUADS);
					glTexCoord2f(0, 0);
					glVertex2d(jeep.x-50, jeep.y-50);
					glTexCoord2f(1, 0);
					glVertex2d(jeep.x+50, jeep.y-50);
					glTexCoord2f(1, 1);
					glVertex2d(jeep.x+50, jeep.y+50);
					glTexCoord2f(0, 1);
					glVertex2d(jeep.x-50, jeep.y+50);
				glEnd();
				jeep.explodingState++;
				}else if(jeep.explodingState >= 31 && jeep.explodingState < 36){
					e27.bind();
				glBegin(GL_QUADS);
					glTexCoord2f(0, 0);
					glVertex2d(jeep.x-50, jeep.y-50);
					glTexCoord2f(1, 0);
					glVertex2d(jeep.x+50, jeep.y-50);
					glTexCoord2f(1, 1);
					glVertex2d(jeep.x+50, jeep.y+50);
					glTexCoord2f(0, 1);
					glVertex2d(jeep.x-50, jeep.y+50);
				glEnd();
				jeep.explodingState++;
				}
				if(jeep.explodingState >= 36){
					CW.GetPR().player.health -= 1;
					jeep.explodingState = 0;
				}
			}
		}
	}			
}
	
	private void RenderTigerTank(Texture TX_Tiger_Tank, Texture TX_Dead) {
		for(int i = 0; i < TTR.tigerTankArray.length; i++){
			if(CW.TTR.tigerTankArray[i] != null && CW.TTR.tigerTankArray[i].visible){
				
				int x = (int) CW.TTR.tigerTankArray[i].x;
				int y = (int) CW.TTR.tigerTankArray[i].y;
				TigerTank TT = CW.TTR.tigerTankArray[i];
		
				if(TT.alive){
		TX_Tiger_Tank.bind();
		switch(TT.direction){
			case 0:{
			glBegin(GL_QUADS);
				glTexCoord2f(0, 0);
				glVertex2i(x , y + 40);
				glTexCoord2f(1, 0);
				glVertex2i(x , y);
				glTexCoord2f(1, 1);
				glVertex2i(x + 40, y);
				glTexCoord2f(0, 1);
				glVertex2i(x + 40, y + 40);
			glEnd();
				break;
			}
			case 90:{
			glBegin(GL_QUADS);
				glTexCoord2f(0, 0);
				glVertex2i(x + 40, y);
				glTexCoord2f(1 , 0);
				glVertex2i(x , y);
				glTexCoord2f(1, 1);
				glVertex2i(x, y + 40);
				glTexCoord2f(0, 1);
				glVertex2i(x + 40, y + 40);
			glEnd();
				break;
			}
			case 180:{
			glBegin(GL_QUADS);
				glTexCoord2f(0, 0);
				glVertex2i(x + 40, y);
				glTexCoord2f(1, 0);
				glVertex2i(x + 40, y + 40);
				glTexCoord2f(1, 1);
				glVertex2i(x, y + 40);
				glTexCoord2f(0, 1);
				glVertex2i(x, y);
			glEnd();
				break;
			}
			case 270:{
			glBegin(GL_QUADS);
				glTexCoord2f(0, 0);
				glVertex2i(x , y);
				glTexCoord2f(1 , 0);
				glVertex2i(x + 40, y);
				glTexCoord2f(1, 1);
				glVertex2i(x + 40, y + 40);
				glTexCoord2f(0, 1);
				glVertex2i(x, y + 40);
			glEnd();
				break;
					}
				}
			}else{
			TX_Dead.bind();
			glBegin(GL_QUADS);
				glTexCoord2f(0, 0);
				glVertex2i(x , y);
				glTexCoord2f(1 , 0);
				glVertex2i(x + 40, y);
				glTexCoord2f(1, 1);
				glVertex2i(x + 40, y + 40);
				glTexCoord2f(0, 1);
				glVertex2i(x, y + 40);
			glEnd();
			}
		}
	}		
}
	
	private void RenderFeldkanone(Texture Barrel, Texture FK1) {
		for(int i = 0; i < CW.FKR.FKArray.length; i++){
			if(CW.FKR.FKArray[i] != null && CW.FKR.FKArray[i].inUse){
				Feldkanone FK = CW.FKR.FKArray[i];
				if(FK.frame == 0){
					GL11.glTranslatef(FK.x, FK.y, 0);
					FK1.bind();
					
				glBegin(GL_QUADS);
					glTexCoord2f(0, 0);
					glVertex2i(0, 0);
					glTexCoord2f(1, 0);
					glVertex2i(40, 0);
					glTexCoord2f(1, 1);
					glVertex2i(40, 40);
					glTexCoord2f(0, 1);
					glVertex2i(0, 40);
				glEnd();
				glLoadIdentity();
				
			GL11.glTranslatef(FK.x+20, FK.y+20, 0);
			glRotatef(Math.round(Math.toDegrees(FK.rotation)), 0f, 0f, 1f);
				Barrel.bind();
			glBegin(GL_QUADS);
				glTexCoord2f(0, 0);
				glVertex2i(-10, -10);
				glTexCoord2f(1, 0);
				glVertex2i(70, -10);
				glTexCoord2f(1, 1);
				glVertex2i(70, 10);
				glTexCoord2f(0, 1);
				glVertex2i(-10, 10);
			glEnd();
				}
			}
			glLoadIdentity();
		}
		glLoadIdentity();
	}
	
	private void RenderPlayer(Texture texture, Texture firing, Player player) {
		int tempX = (int) Math.round(player.x);
		int tempY = (int) Math.round(player.y);
		
		if(!player.firing){
		GL11.glTranslatef(tempX, tempY, 0);
		glRotatef(player.rotation, 0f, 0f, 1f);
		texture.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2i(-16, -16);
			glTexCoord2f(1, 0);
			glVertex2i(-16+32, -16);
			glTexCoord2f(1, 1);
			glVertex2i(-16+32, -16+32);
			glTexCoord2f(0, 1);
			glVertex2i(-16, -16+32);
		glEnd();
		}else{
			GL11.glTranslatef(tempX, tempY, 0);
			glRotatef(player.rotation, 0f, 0f, 1f);
			firing.bind();
			glBegin(GL_QUADS);
				glTexCoord2f(0, 0);
				glVertex2i(-16, -16);
				glTexCoord2f(1, 0);
				glVertex2i(-16+32, -16);
				glTexCoord2f(1, 1);
				glVertex2i(-16+32, -16+32);
				glTexCoord2f(0, 1);
				glVertex2i(-16, -16+32);
			glEnd();
		}
		
		if(CW.GetPR().lastTime + 300 < Sys.getTime()){
			CW.GetPR().player.firing = false;
		}
		
		glLoadIdentity();	
	}
	
	private void RenderPlayerBullit(Texture ball, Texture E1, Texture E2, Texture E3, Texture E4) {
		for(int i = 0; i < CW.GetPBR().playerBullitArray.length; i++){
			if(CW.GetPBR().playerBullitArray[i] != null && CW.GetPBR().playerBullitArray[i].inUse){
				PlayerBullit bullit = CW.GetPBR().playerBullitArray[i];
				
				if(CW.GetPBR().playerBullitArray[i].explodingState == 0){
					ball.bind();
					glBegin(GL_QUADS);
						glTexCoord2f(0, 0);
						glVertex2d(bullit.x, bullit.y);
						glTexCoord2f(1, 0);
						glVertex2d(bullit.x+4, bullit.y);
						glTexCoord2f(1, 1);
						glVertex2d(bullit.x+4, bullit.y+4);
						glTexCoord2f(0, 1);
						glVertex2d(bullit.x, bullit.y+4);
					glEnd();
					//break;
				}else if(CW.GetPBR().playerBullitArray[i].explodingState >= 1 && CW.GetPBR().playerBullitArray[i].explodingState < 6){
					E1.bind();
					glBegin(GL_QUADS);
						glTexCoord2f(0, 0);
						glVertex2d(bullit.x-25, bullit.y-25);
						glTexCoord2f(1, 0);
						glVertex2d(bullit.x+25, bullit.y-25);
						glTexCoord2f(1, 1);
						glVertex2d(bullit.x+25, bullit.y+25);
						glTexCoord2f(0, 1);
						glVertex2d(bullit.x-25, bullit.y+25);
					glEnd();
					CW.GetPBR().playerBullitArray[i].explodingState++;
					//break;
				}else if(CW.GetPBR().playerBullitArray[i].explodingState >= 6 && CW.GetPBR().playerBullitArray[i].explodingState < 10){
					E2.bind();
				glBegin(GL_QUADS);
					glTexCoord2f(0, 0);
					glVertex2d(bullit.x-25, bullit.y-25);
					glTexCoord2f(1, 0);
					glVertex2d(bullit.x+25, bullit.y-25);
					glTexCoord2f(1, 1);
					glVertex2d(bullit.x+25, bullit.y+25);
					glTexCoord2f(0, 1);
					glVertex2d(bullit.x-25, bullit.y+25);
				glEnd();
					CW.GetPBR().playerBullitArray[i].explodingState++;
					//break;
				}else if(CW.GetPBR().playerBullitArray[i].explodingState >= 10 && CW.GetPBR().playerBullitArray[i].explodingState < 15){
					E3.bind();
				glBegin(GL_QUADS);
					glTexCoord2f(0, 0);
					glVertex2d(bullit.x-25, bullit.y-25);
					glTexCoord2f(1, 0);
					glVertex2d(bullit.x+25, bullit.y-25);
					glTexCoord2f(1, 1);
					glVertex2d(bullit.x+25, bullit.y+25);
					glTexCoord2f(0, 1);
					glVertex2d(bullit.x-25, bullit.y+25);
				glEnd();
					CW.GetPBR().playerBullitArray[i].explodingState++;
					//break;
				}else if(CW.GetPBR().playerBullitArray[i].explodingState >= 15){
					E4.bind();
				glBegin(GL_QUADS);
					glTexCoord2f(0, 0);
					glVertex2d(bullit.x-25, bullit.y-25);
					glTexCoord2f(1, 0);
					glVertex2d(bullit.x+25, bullit.y-25);
					glTexCoord2f(1, 1);
					glVertex2d(bullit.x+25, bullit.y+25);
					glTexCoord2f(0, 1);
					glVertex2d(bullit.x-25, bullit.y+25);
				glEnd();
					CW.GetPBR().playerBullitArray[i].inUse = false;
					//break;
				}
					/*System.out.println("Thou should nev4r see this");
					CW.GetPBR().playerBullitArray[i].explodingState++;
					//break;*/
				}
			}
		}
	

	private void RenderEnemyBullit(Texture TX_Ball, Texture E1, Texture E2, Texture E3, Texture E4) {
		for(int i = 0; i < CW.EBR.enemyBullitArray.length; i++){
			if(CW.EBR.enemyBullitArray[i] != null && CW.EBR.enemyBullitArray[i].inUse){
				EnemyBullit bullit = CW.EBR.enemyBullitArray[i];
				
				if(CW.EBR.enemyBullitArray[i].explodingState == 0){
					TX_Ball.bind();
					glBegin(GL_QUADS);
						glTexCoord2f(0, 0);
						glVertex2d(bullit.x-3, bullit.y-3);
						glTexCoord2f(1, 0);
						glVertex2d(bullit.x+3, bullit.y-3);
						glTexCoord2f(1, 1);
						glVertex2d(bullit.x+3, bullit.y+3);
						glTexCoord2f(0, 1);
						glVertex2d(bullit.x-3, bullit.y+3);
					glEnd();
					
				}else if(CW.EBR.enemyBullitArray[i].explodingState >= 1 && CW.EBR.enemyBullitArray[i].explodingState < 6){
					E1.bind();
					glBegin(GL_QUADS);
						glTexCoord2f(0, 0);
						glVertex2d(bullit.x-25, bullit.y-25);
						glTexCoord2f(1, 0);
						glVertex2d(bullit.x+25, bullit.y-25);
						glTexCoord2f(1, 1);
						glVertex2d(bullit.x+25, bullit.y+25);
						glTexCoord2f(0, 1);
						glVertex2d(bullit.x-25, bullit.y+25);
					glEnd();
					CW.EBR.enemyBullitArray[i].explodingState++;
					
				}else if(CW.EBR.enemyBullitArray[i].explodingState >= 6 && CW.EBR.enemyBullitArray[i].explodingState < 10){
					E2.bind();
				glBegin(GL_QUADS);
					glTexCoord2f(0, 0);
					glVertex2d(bullit.x-25, bullit.y-25);
					glTexCoord2f(1, 0);
					glVertex2d(bullit.x+25, bullit.y-25);
					glTexCoord2f(1, 1);
					glVertex2d(bullit.x+25, bullit.y+25);
					glTexCoord2f(0, 1);
					glVertex2d(bullit.x-25, bullit.y+25);
				glEnd();
					CW.EBR.enemyBullitArray[i].explodingState++;
					
				}else if(CW.EBR.enemyBullitArray[i].explodingState >= 10 && CW.EBR.enemyBullitArray[i].explodingState < 15){
					E3.bind();
				glBegin(GL_QUADS);
					glTexCoord2f(0, 0);
					glVertex2d(bullit.x-25, bullit.y-25);
					glTexCoord2f(1, 0);
					glVertex2d(bullit.x+25, bullit.y-25);
					glTexCoord2f(1, 1);
					glVertex2d(bullit.x+25, bullit.y+25);
					glTexCoord2f(0, 1);
					glVertex2d(bullit.x-25, bullit.y+25);
				glEnd();
					CW.EBR.enemyBullitArray[i].explodingState++;
					
				}else if(CW.EBR.enemyBullitArray[i].explodingState >= 15){
					E4.bind();
				glBegin(GL_QUADS);
					glTexCoord2f(0, 0);
					glVertex2d(bullit.x-25, bullit.y-25);
					glTexCoord2f(1, 0);
					glVertex2d(bullit.x+25, bullit.y-25);
					glTexCoord2f(1, 1);
					glVertex2d(bullit.x+25, bullit.y+25);
					glTexCoord2f(0, 1);
					glVertex2d(bullit.x-25, bullit.y+25);
				glEnd();
					CW.EBR.enemyBullitArray[i].inUse = false;
				}
			}
		}		
	}
	
	private void CheckKeys(CreateWorld CW, Texture TX_No_Ammo) {
		while (Keyboard.next()) {
		    if (!Keyboard.getEventKeyState()){    	
		        if (Keyboard.getEventKey() == Keyboard.KEY_V) {
					if(CW.GetPR().player.MaxForwardSpeed != 5.5){
						CW.GetPR().player.MaxForwardSpeed = 5.5;
						CW.GetPR().player.MaxBackSpeed = -5.5;
					}else{
						CW.GetPR().player.MaxForwardSpeed = 1.5;
						CW.GetPR().player.MaxBackSpeed = 0.7;
					}
		        }
		        if (Keyboard.getEventKey() == Keyboard.KEY_C) {
					CW.GetPR().player.ammo = 5;
					CW.GetPR().player.health = 5;
					CW.GetER().openExit();
		        }
		        if (Keyboard.getEventKey() == Keyboard.KEY_B) {
		        	CW.GetPR().player.score+= 100;
		        }
		        //Always
		        if (Keyboard.getEventKey() == Keyboard.KEY_END) {
		        	System.exit(0);
		    }
		
		//On screenNumber
	if(screenNumber == 1){
		        if (Keyboard.getEventKey() == Keyboard.KEY_SPACE) {
					screenNumber++;
					PR.lastTime = Sys.getTime();
		        }
		    }
				
			//ScreenNumber 2 is outside the while loop!
	
			if(screenNumber == 3){
		        if (Keyboard.getEventKey() == Keyboard.KEY_SPACE) {
					screenNumber = 2;
		        }
		        if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT) {
		    		if(SelectedUpgrade < 6){
		    			SelectedUpgrade++;
		    		}else{
		    			SelectedUpgrade = 1;
		    			}
		        }
		        if (Keyboard.getEventKey() == Keyboard.KEY_LEFT) {
		    		if(SelectedUpgrade > 1){
		    			SelectedUpgrade--;
		    		}else{
		    			SelectedUpgrade = 6;
		    		}
		    	}
		        if (Keyboard.getEventKey() == Keyboard.KEY_RETURN) {
		        	BuyUpgrade(SelectedUpgrade);
		        }
		    }

			if(screenNumber == 4){
		       if (Keyboard.getEventKey() == Keyboard.KEY_SPACE) {
		        	System.exit(0);
		        }
			}
	    }
	}
		if(screenNumber == 2){
			if(Keyboard.isKeyDown(Keyboard.KEY_END)){
				System.exit(0);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_A)){
				PR.moveLeft(CW);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_D)){
				PR.moveRight(CW);
			}

			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
				if(CW.GetPR().player.ammo != 0){
					CW.GetPR().Shoot(CW);
				}else{
					if(PR.player.alive){
						RenderNoAmmoSign(TX_No_Ammo);
					}
				}
			}
		}
}

	private void RenderNoAmmoSign(Texture TX_No_Ammo) {
		TX_No_Ammo.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2i(272, 368);
			glTexCoord2f(1, 0);
			glVertex2i(272 + 256, 368);
			glTexCoord2f(1, 1);
			glVertex2i(272 + 256, 368 - 64);
			glTexCoord2f(0, 1);
			glVertex2i(272, 368 - 64);
		glEnd();
	}

	private void CheckGateShouldOpen() {
		int px = (int) CW.GetPR().player.x;
		int py = (int) CW.GetPR().player.y;
		int bx = CW.BR.button.x;
		int by = CW.BR.button.y;
		
		if(px > bx+10 && px < bx+30 && py > by+10 && py < by+30){
			CW.GetER().openExit();
		}
	}
		
	private void PlayerLive(Texture TX_hearth, Texture TX_lose, Texture Bar1, Texture Bar2, Texture Bar3, Player player) {
		for(int i = 1; i <= player.health; i++){
			if(player.health != 1){
			if(i == 1){
			Bar3.bind();
			glBegin(GL_QUADS);
				glTexCoord2f(0, 0);
				glVertex2i(0, 760 - 40 * i);
				glTexCoord2f(1, 0);
				glVertex2i(0 + 40, 760 - 40 * i);
				glTexCoord2f(1, 1);
				glVertex2i(0 + 40, 800 - 40 * i);
				glTexCoord2f(0, 1);
				glVertex2i(0, 800 - 40 * i);
			glEnd();
			}else if(i == player.health){
				Bar1.bind();
				glBegin(GL_QUADS);
					glTexCoord2f(0, 0);
					glVertex2i(0, 760 - 40 * i);
					glTexCoord2f(1, 0);
					glVertex2i(0 + 40, 760 - 40 * i);
					glTexCoord2f(1, 1);
					glVertex2i(0 + 40, 800 - 40 * i);
					glTexCoord2f(0, 1);
					glVertex2i(0, 800 - 40 * i);
				glEnd();
			}else{
				Bar2.bind();
				glBegin(GL_QUADS);
					glTexCoord2f(0, 0);
					glVertex2i(0, 760 - 40 * i);
					glTexCoord2f(1, 0);
					glVertex2i(0 + 40, 760 - 40 * i);
					glTexCoord2f(1, 1);
					glVertex2i(0 + 40, 800 - 40 * i);
					glTexCoord2f(0, 1);
					glVertex2i(0, 800 - 40 * i);
				glEnd();
			}}else{                 // 1 health
				Bar3.bind();
				glBegin(GL_QUADS);
					glTexCoord2f(0, 0);
					glVertex2i(0, 760 - 40);
					glTexCoord2f(1, 0);
					glVertex2i(0 + 40, 760 - 40);
					glTexCoord2f(1, 1);
					glVertex2i(0 + 40, 800 - 40);
					glTexCoord2f(0, 1);
					glVertex2i(0, 800 - 40);
				glEnd();
				
				Bar1.bind();
				glBegin(GL_QUADS);
					glTexCoord2f(0, 0);
					glVertex2i(0, 760 - 80);
					glTexCoord2f(1, 0);
					glVertex2i(0 + 40, 760 - 80);
					glTexCoord2f(1, 1);
					glVertex2i(0 + 40, 800 - 80);
					glTexCoord2f(0, 1);
					glVertex2i(0, 800 - 80);
				glEnd();
			}
			
			TX_hearth.bind();
			glBegin(GL_QUADS);
				glTexCoord2f(0, 0);
				glVertex2i(0, 760 - 40 * i);
				glTexCoord2f(1, 0);
				glVertex2i(0 + 40, 760 - 40 * i);
				glTexCoord2f(1, 1);
				glVertex2i(0 + 40, 800 - 40 * i);
				glTexCoord2f(0, 1);
				glVertex2i(0, 800 - 40 * i);
			glEnd();
		}
		if(player.health == 0){
			TX_lose.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2i(0, 800);
			glTexCoord2f(1, 0);
			glVertex2i(800, 800);
			glTexCoord2f(1, 1);
			glVertex2i(800, 0);
			glTexCoord2f(0, 1);
			glVertex2i(0, 0);
		glEnd();
		player.alive = false;
		}
	}
	
	private void RenderScore(Texture score30, Texture score100, Texture score250) {
		if(score != CW.GetPR().player.score){
			
		if(CW.GetPR().player.score - score == 30){
			score30.bind();
		}else if(CW.GetPR().player.score - score == 100){
			score100.bind();
		}else if(CW.GetPR().player.score - score == 250){
			score250.bind();
		}else{
			score30.bind();
		}
		scoreTimer++;
		
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2i(272, 368);
			glTexCoord2f(1, 0);
			glVertex2i(272 + 256, 368);
			glTexCoord2f(1, 1);
			glVertex2i(272 + 256, 368 - 64);
			glTexCoord2f(0, 1);
			glVertex2i(272, 368 - 64);
		glEnd();
		}
		if(scoreTimer > 50){
			scoreTimer = 0;
			score = CW.GetPR().player.score;
		}
	}
	
	private void RenderAmmoBar(Texture TX_Ammo, Texture Bar1, Texture Bar2, Texture Bar3, Player player) {
		for(int i = 1; i <= player.ammo; i++){
			if(player.ammo != 1){
			if(i == 1){
			Bar3.bind();
			glBegin(GL_QUADS);
				glTexCoord2f(0, 0);
				glVertex2i(760, 760 - 40 * i);
				glTexCoord2f(1, 0);
				glVertex2i(760 + 40, 760 - 40 * i);
				glTexCoord2f(1, 1);
				glVertex2i(760 + 40, 800 - 40 * i);
				glTexCoord2f(0, 1);
				glVertex2i(760, 800 - 40 * i);
			glEnd();
			}else if(i == player.ammo){
				Bar1.bind();
				glBegin(GL_QUADS);
					glTexCoord2f(0, 0);
					glVertex2i(760, 760 - 40 * i);
					glTexCoord2f(1, 0);
					glVertex2i(760 + 40, 760 - 40 * i);
					glTexCoord2f(1, 1);
					glVertex2i(760 + 40, 800 - 40 * i);
					glTexCoord2f(0, 1);
					glVertex2i(760, 800 - 40 * i);
				glEnd();
			}else{
				Bar2.bind();
				glBegin(GL_QUADS);
					glTexCoord2f(0, 0);
					glVertex2i(760, 760 - 40 * i);
					glTexCoord2f(1, 0);
					glVertex2i(760 + 40, 760 - 40 * i);
					glTexCoord2f(1, 1);
					glVertex2i(760 + 40, 800 - 40 * i);
					glTexCoord2f(0, 1);
					glVertex2i(760, 800 - 40 * i);
				glEnd();
			}}else{                 // 1 health
				Bar3.bind();
				glBegin(GL_QUADS);
					glTexCoord2f(0, 0);
					glVertex2i(760, 760 - 40);
					glTexCoord2f(1, 0);
					glVertex2i(760 + 40, 760 - 40);
					glTexCoord2f(1, 1);
					glVertex2i(760 + 40, 800 - 40);
					glTexCoord2f(0, 1);
					glVertex2i(760, 800 - 40);
				glEnd();
				
				Bar1.bind();
				glBegin(GL_QUADS);
					glTexCoord2f(0, 0);
					glVertex2i(760, 760 - 80);
					glTexCoord2f(1, 0);
					glVertex2i(760 + 40, 760 - 80);
					glTexCoord2f(1, 1);
					glVertex2i(760 + 40, 800 - 80);
					glTexCoord2f(0, 1);
					glVertex2i(760, 800 - 80);
				glEnd();
			}
			
			TX_Ammo.bind();
			glBegin(GL_QUADS);
				glTexCoord2f(0, 0);
				glVertex2i(760, 760 - 40 * i);
				glTexCoord2f(1, 0);
				glVertex2i(760 + 40, 760 - 40 * i);
				glTexCoord2f(1, 1);
				glVertex2i(760 + 40, 800 - 40 * i);
				glTexCoord2f(0, 1);
				glVertex2i(760, 800 - 40 * i);
			glEnd();
		}
	}
	
	private void RenderWinScreen(Texture texture) {
		texture.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0,1);
			glVertex2f(0, 0);
			glTexCoord2f(1,1);
			glVertex2f(800, 0);
			glTexCoord2f(1,0);
			glVertex2f(800, 800);
			glTexCoord2f(0,0);
			glVertex2f(0, 800);
		glEnd();
	}
	
	private void BuyUpgrade(int selectedUpgrade) {
		switch(selectedUpgrade){
		
			case 1:{//Health
				if(PR.player.score >= 150){
					PR.player.score -= 150;
					PR.player.health++;
				}
				break;
			}
			case 2:{//Ammo
				if(PR.player.score >= 100){
					PR.player.score -= 100;
					PR.player.ammo++;
				}
				break;
			}
			case 3:{//Damage
				if(PR.player.score >= 500){
					PR.player.score -= 500;
					CW.GetPBR().damage++;
					boughtDamage = true;
				}
				break;
			}
			case 4:{//Speed
				if(PR.player.score >= 250){
					PR.player.score -= 250;
					PR.player.MaxForwardSpeed += 0.5;
					boughtSpeed = true;
				}
				break;
			}
			case 5:{//RapidFire
				if(PR.player.score >= 500){
					PR.player.score -= 500;
					PR.reloadTime -= 300;
					boughtRapidFire = true;
				}
				break;
			}
			case 6:{//Continue
				screenNumber = 2;
				break;
			}
			default:{
				System.out.println("You should NEVER see this. BuyUpgrade in Displayer");
			}
		}		
	}
	
	private void initGL(int width, int height) {
		try {
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.create();
			Display.setVSyncEnabled(true);
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
 
		GL11.glEnable(GL11.GL_TEXTURE_2D); 
    	//GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
    	//GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
 
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		
        	// enable alpha blending
        	GL11.glEnable(GL11.GL_BLEND);
        	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
 
        GL11.glViewport(0,0,width,height);
		//GL11.glMatrixMode(GL11.GL_MODELVIEW);
 
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		
        	GL11.glOrtho(0, width, 0, height, -1, 1);
        
        	//GL11.glOrtho(0, 10, 0, 10, -1, 1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
       		
        }	
	}

