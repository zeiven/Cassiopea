
package GameState;

import Main.GamePanel;
import TileMap.*;
import Entity.*;
import Entity.Enemies.Onde;
import Entity.Enemies.PesciolinoCattivo;
import Entity.Enemies.Riccio;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class LevelBonus extends GameState {
	
	private mattonciniMap mattonciniMap; // mattoncini map
	private Background bg;
	OggettoInvisibile o;
	private Cassiopea cassiopea;
	
	private ArrayList<Nemico> nemici;
	private BarraVitaBollicine barraBollicine;
	private BarraVita barraVita;
	
	
	public LevelBonus(GameStateManager gameStateManagerBONUS) {
		System.out.println("level state bonus");
		this.gsm = gameStateManagerBONUS;
		init();
	}
	
	public void init() {
		System.out.println("init state bonus");
		mattonciniMap = new mattonciniMap(30);
		
		mattonciniMap.loadmattoncinis("/Tilesets/mattoncini.gif"); // è l'immagine intera di quello che creerà il mondo, mattoncini ecc ecc
		mattonciniMap.loadMap("/Maps/levelBonus.map"); //primo livello..fa na pocu schifu, però CAMINA !!
		mattonciniMap.setPosition(0, 0);
		
		
		bg = new Background("/Backgrounds/spiaggia2.jpg", 0.1);
		
		cassiopea = new Cassiopea(mattonciniMap);
		
		cassiopea.setPosition(200, 80);
		
		
		popoliamoNemici();
		barraVita=new BarraVita(cassiopea);
		barraBollicine= new BarraVitaBollicine(cassiopea);
		System.out.println("hai caricato il mondo di mammata???");
	}
	
	
private void popoliamoNemici() {
		
		nemici = new ArrayList<Nemico>();
		
//		PesciolinoCattivo s;
//		Point[] points = new Point[] {
//			new Point(100, 300),
//			new Point(860, 200),
//			new Point(1525, 300),
//			new Point(1300, 300),
//			new Point(1800, 300)
//		};
//		for(int i = 0; i < points.length; i++) {
//			s = new PesciolinoCattivo(mattonciniMap);
//			s.setPosition(points[i].x, points[i].y);
//			nemici.add(s);
//		}
//		
//		Riccio r;
//		Point[] points1 = new Point[] {
//			new Point(200, 300),
//			new Point(760, 200),
//			new Point(1325, 300),
//			new Point(1400, 300),
//			new Point(1600, 300)
//		};
//		for(int i = 0; i < points.length; i++) {
//			r = new Riccio(mattonciniMap);
//			r.setPosition(points1[i].x, points1[i].y);
//			nemici.add(r);
//		}
//		
		Bollicina f;
		Point[] points2 = new Point[] {
			new Point(500, 100),
			new Point(1950, 160),
			
		};
		for(int i = 0; i < points2.length; i++) {
			f = new Bollicina(mattonciniMap);
			f.setPosition(points2[i].x, points2[i].y);
			nemici.add(f);
		}
		
		o= new OggettoInvisibile(mattonciniMap);
		o.setPosition(200, 100);
		nemici.add(o);
		Onde o;
		Point[] points3 = new Point[] {
			new Point(400, 250),
			new Point(430, 250),
			new Point(460, 250),
			new Point(490, 250),
			new Point(520, 250),
			new Point(550, 250),
			new Point(580, 250),
			new Point(510, 250),
			new Point(540, 250),
			new Point(570, 250),
			new Point(600, 250),
			new Point(630, 250),
			new Point(660, 250),
			new Point(690, 250),
			new Point(620, 250),
			new Point(650, 250),
			
			
			new Point(710, 250),
			new Point(740, 250),
			new Point(770, 250),
			new Point(790, 250),
			new Point(810, 250),
			//c'è lo spazietto...........lo ha deciso PAOLA
			
			
			new Point(910, 250),
			new Point(940, 250),
			new Point(970, 250),
			new Point(1000, 250),
			new Point(1030, 250),
			new Point(1060, 250),
			new Point(1090, 250),
			new Point(1120, 250),
			new Point(1150, 250),
			new Point(1180, 250),
			new Point(1210, 250),
			new Point(1240, 250),
			new Point(1270, 250),
			new Point(1300, 250),
			
			
			
			new Point(1630, 250),
			new Point(1660, 250),
			new Point(1690, 250),
			new Point(1720, 250),
			new Point(1750, 250),
			new Point(1780, 250),
		

			new Point(1890, 250),
			new Point(1920, 250),
			new Point(1940, 250),
			new Point(1970, 250)
			
		};
		for(int i = 0; i < points3.length; i++) {
			o = new Onde(mattonciniMap);
			o.setPosition(points3[i].x, points3[i].y);
			nemici.add(o);
		}
		
		
	}
	
	
	public void update() {
		
		// update cassy<3
		cassiopea.update();
			
			o.update();
			
		//**********SETPOSITION SERVE A FARE "LA MATRICE A SCORRIMENTO" 
//oggettoInvisibile.update();
		mattonciniMap.setPosition(
			GamePanel.WIDTH / 2 - o.getx(),
			GamePanel.HEIGHT / 2 - o.gety()
		);
		
		// set background
		bg.setPosition(mattonciniMap.getx(), mattonciniMap.gety());
		
		for(int i = 0; i < nemici.size(); i++) {
			Nemico e = nemici.get(i);
			e.update();
			}
cassiopea.checkAttack(nemici);
//System.out.println("GamePanel.WIDTH / 2 -cassiopea.getx() "+ (GamePanel.WIDTH / 2 -cassiopea.getx()));
//System.out.println("GamePanel.WIDTH / 2 -o.getx() "+ (GamePanel.WIDTH / 2 -o.getx()));
int provxCass=GamePanel.WIDTH / 2 -cassiopea.getx();
int provxOgg=GamePanel.WIDTH / 2 -o.getx();
int sottr=provxCass-provxOgg;

if((sottr>250) ){
	System.out.println("MORTA");
	cassiopea.setPosition(200, 80);
	o.setPosition(200, 100);
	if(cassiopea.health>1)
	cassiopea.health--;
	else {
		cassiopea.lives--;
		cassiopea.health=5;}
	
}

//if(GamePanel.HEIGHT / 2 -cassiopea.gety()<GamePanel.HEIGHT / 2 -o.gety() ){
//	System.out.println("MORTA Height");
//	cassiopea.setPosition(100, 80);
//	o.setPosition(200, 100);
//}

	}
	
	public void draw(Graphics2D g) {
		
		// draw background
		bg.draw(g);
		
		// draw 
		mattonciniMap.draw(g);
		
		// draw cassy
		cassiopea.draw(g);
		
		for(int i = 0; i < nemici.size(); i++) {
			nemici.get(i).draw(g);
		}
		barraVita.draw(g);
		barraBollicine.draw(g);
		if(Cassiopea.livelloBonusFine==true){
			Cassiopea.health++;
			System.out.println("cosa succede????");	
				gsm.currentState=gsm.LEVEL1STATE;
				gsm.loadState(gsm.currentState);
				
//			this.init();	
				
			}	
		
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_LEFT) cassiopea.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) cassiopea.setRight(true);
		if(k == KeyEvent.VK_UP) cassiopea.setUp(true);
		if(k == KeyEvent.VK_DOWN) cassiopea.setDown(true);
		if(k == KeyEvent.VK_W) cassiopea.setJumping(true);
		if(k == KeyEvent.VK_E) cassiopea.setGliding(true);
		if(k == KeyEvent.VK_F) cassiopea.setFiring();
		if(k == KeyEvent.VK_Z) cassiopea.setScratching();
		if(k == KeyEvent.VK_P){
			GamePanel.pausa=!GamePanel.pausa;
//			GameStateManager.pause=!GameStateManager.pause;
//			GameStateManager.pauseLock.lock();
//			GameStateManager.pauseCondition.signalAll();
//			GameStateManager.pauseLock.unlock();
			GamePanel.lock.lock();
			GamePanel.condition.signalAll();
			GamePanel.lock.unlock();
		}
		
	}
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT) cassiopea.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) cassiopea.setRight(false);
		if(k == KeyEvent.VK_UP) cassiopea.setUp(false);
		if(k == KeyEvent.VK_DOWN) cassiopea.setDown(false);
		if(k == KeyEvent.VK_W) cassiopea.setJumping(false);
		if(k == KeyEvent.VK_E) cassiopea.setGliding(false);
		if(k == KeyEvent.VK_D) cassiopea.setFire(false);
	}
}












