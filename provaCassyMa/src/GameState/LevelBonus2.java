
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

import provaMicrofono.SpeechDetection;

public class LevelBonus2 extends GameState {
	
	private mattonciniMap mattonciniMap; // mattoncini map
	private Background bg;
	OggettoInvisibile o;
	private Cassiopea cassiopea;
	
	private ArrayList<Nemico> nemici;
	private BarraVitaBollicine barraBollicine;
	private BarraVita barraVita;
	
	
	public LevelBonus2(GameStateManager gameStateManagerBONUS) {
		System.out.println("level state bonus");
		this.gsm = gameStateManagerBONUS;
		init();
	}
	
	public void init() {
		SpeechDetection s = new SpeechDetection();
		System.out.println("init state bonus");
		mattonciniMap = new mattonciniMap(30);
		
		mattonciniMap.loadmattoncinis("/Tilesets/mattoncini.gif"); // è l'immagine intera di quello che creerà il mondo, mattoncini ecc ecc
		mattonciniMap.loadMap("/Maps/levelBonus2.map"); //primo livello..fa na pocu schifu, però CAMINA !!
		mattonciniMap.setPosition(0, 0);
		
		
		bg = new Background("/Backgrounds/mareNero.jpg", 0.1);
		
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
		Keys k;
		Point[] points2 = new Point[] {
			
			new Point(1950, 300),
			
		};
		for(int i = 0; i < points2.length; i++) {
			k = new Keys(mattonciniMap);
			k.setPosition(points2[i].x, points2[i].y);
			nemici.add(k);
		}
		
		Onde o;
		Point[] points3 = new Point[] { 
				new Point(565, 400), 
				new Point(540, 400), 
				new Point(525, 400), 
				new Point(500, 400), 
				new Point(580, 400), 
				new Point(600, 400), 
				new Point(630, 400), 
				new Point(1325, 400), 
				new Point(1300, 400), 
				new Point(1275, 400), 
				new Point(1250, 400), 
				new Point(1225, 400), 
				new Point(1200, 400), 
				new Point(1175, 400), 
				new Point(1150, 400), 
				new Point(1950, 400), 
				
				};
		
		for(int i = 0; i < points3.length; i++) {
			o = new Onde(mattonciniMap);
			o.setPosition(points3[i].x, points3[i].y);
			nemici.add(o);
		}
		
		
	}
	
	
	public void update() {
		
		if(SpeechDetection.salta==true)		{
			
			cassiopea.setJumping(true);
	}
	else
		cassiopea.setJumping(false);
		// update cassy<3
		cassiopea.update();
		
		//**********SETPOSITION SERVE A FARE "LA MATRICE A SCORRIMENTO" 
//oggettoInvisibile.update();
		mattonciniMap.setPosition(
			GamePanel.WIDTH / 2 - cassiopea.getx(),
			GamePanel.HEIGHT / 2 - cassiopea.gety()
		);
		
		// set background
		bg.setPosition(mattonciniMap.getx(), mattonciniMap.gety());
		
		for(int i = 0; i < nemici.size(); i++) {
			Nemico e = nemici.get(i);
			e.update();}
cassiopea.checkAttack(nemici);

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
		if(Cassiopea.livelloBonus2Fine==true){
			System.out.println("cosa succede????");	
				gsm.currentState=gsm.LEVEL2STATE;
				gsm.loadState(gsm.currentState);
				
//			this.init();	
				
			}	
		
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_LEFT) cassiopea.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) cassiopea.setRight(true);
		if(k == KeyEvent.VK_UP) cassiopea.setUp(true);
		if(k == KeyEvent.VK_DOWN) cassiopea.setDown(true);
//		if(k == KeyEvent.VK_W) cassiopea.setJumping(true);
		if(k == KeyEvent.VK_E) cassiopea.setGliding(true);
		if(k == KeyEvent.VK_F) cassiopea.setFiring();
		if(k == KeyEvent.VK_Z) cassiopea.setScratching();
		if(k == KeyEvent.VK_P){
			GamePanel.pausa=!GamePanel.pausa;
//			GameStateManager.pause=!GameStateManager.pause;
//			GameStateManager.pauseLock.lock();
//			GameStateManager.pauseCondition.signalAll();
//			GameStateManager.pauseLock.unlock();
		}
		
	}
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT) cassiopea.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) cassiopea.setRight(true);
		if(k == KeyEvent.VK_UP) cassiopea.setUp(false);
		if(k == KeyEvent.VK_DOWN) cassiopea.setDown(false);
//		if(k == KeyEvent.VK_W) cassiopea.setJumping(false);
		if(k == KeyEvent.VK_E) cassiopea.setGliding(false);
		if(k == KeyEvent.VK_D) cassiopea.setFire(false);
	}
}












