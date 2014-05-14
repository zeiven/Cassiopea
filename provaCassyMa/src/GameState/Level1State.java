package GameState;

import Main.GamePanel;
import TileMap.*;
import Entity.*;
import Entity.Enemies.BucoNero;
import Entity.Enemies.Onde;
import Entity.Enemies.PesciolinoCattivo;
import Entity.Enemies.Riccio;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;

public class Level1State extends GameState {
	
	private mattonciniMap mattonciniMap; // mattoncini map
	private Background bg;
	private FireBall fireball;
	private ArrayList<Nemico> nemici;
	private BarraVita barraVita;
	Cassiopea cassiopea;
	private ArrayList<Explosion> explosions;
	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	public void init() {
		
		mattonciniMap = new mattonciniMap(30);
		mattonciniMap.loadmattoncinis("/Tilesets/grasstileset1.gif"); // è l'immagine intera di quello che creerà il mondo, mattoncini ecc ecc
		mattonciniMap.loadMap("/Maps/level1-1.map"); //primo livello..fa na pocu schifu, però CAMINA !!
		mattonciniMap.setPosition(0, 0);
		
		
		bg = new Background("/Backgrounds/acquario2.bmp", 0.1);
		
		cassiopea = new Cassiopea(mattonciniMap);
		if(Cassiopea.livelloBonusEstato==false){
			cassiopea.setPosition(100, 100);}
			else
				cassiopea.setPosition(1030, 390);
		
		
		popoliamoNemici();
		barraVita= new BarraVita(cassiopea);
		explosions= new ArrayList<Explosion>();
	}
	
	
private void popoliamoNemici() {
		
		nemici = new ArrayList<Nemico>();
		
		if(cassiopea.livelloBonusEstato==false){
		PesciolinoCattivo s;
		Point[] points = new Point[] {
			new Point(100, 300),
			new Point(860, 200),
			new Point(1525, 325),
			new Point(1300, 250),
			new Point(1800, 290)
		};
		for(int i = 0; i < points.length; i++) {
			s = new PesciolinoCattivo(mattonciniMap);
			s.setPosition(points[i].x, points[i].y);
			nemici.add(s);
		}
		}
		else if (cassiopea.livelloBonusEstato==true){
			PesciolinoCattivo s;
			Point[] points = new Point[] {
				new Point(100, 300),
				new Point(860, 200),
				new Point(1525, 325),
				new Point(1300, 250),
				new Point(1800, 290)
			};
			
			for(int j = 0; j < Cassiopea.pescCatt.size(); j++) {
				System.out.println("Cassiopea.p.CATTIVO "+Cassiopea.pescCatt.get(j));
			}
			
			for(int i = 0; i < points.length; i++) {
				for(int j = 0; j < Cassiopea.pescCatt.size(); j++) {
					if(points[i].getX()==Cassiopea.pescCatt.get(j).getX() || points[i].getY()==Cassiopea.pescCatt.get(j).getY()){
						points[i].setLocation(0, 0);	
					}
					else{
					
					}
					
				}
				
			}
			for(int i = 0; i < points.length; i++) {
				s = new PesciolinoCattivo(mattonciniMap);
				s.setPosition(points[i].x, points[i].y);
				nemici.add(s);}
		}
		
		if(cassiopea.livelloBonusEstato==false){
		Riccio r;
		Point[] points1 = new Point[] {
			new Point(200, 300),
			new Point(760, 200),
			new Point(1625, 380),
			new Point(1400, 330),
			
		};
		for(int i = 0; i < points1.length; i++) {
			r = new Riccio(mattonciniMap);
			r.setPosition(points1[i].x, points1[i].y);
			nemici.add(r);
		}
		}
		else if(cassiopea.livelloBonusEstato==true){
			Riccio r;
			Point[] points1 = new Point[] {
				new Point(200, 290),
				new Point(760, 200),
				new Point(1625, 380),
				new Point(1400, 330),
				
			};
			for(int j = 0; j < Cassiopea.pescCatt.size(); j++) {
				System.out.println("Cassiopea.RICCIO "+Cassiopea.riccio.get(j));
			}
			for(int i = 0; i < points1.length; i++) {
				for(int j = 0; j < Cassiopea.riccio.size(); j++) {
					if(points1[i].getX()==Cassiopea.riccio.get(j).getX() || points1[i].getY()==Cassiopea.riccio.get(j).getY()){
						points1[i].setLocation(0, 0);	
					}
					else{
					
					}
					
				}
				
			}
			for(int i = 0; i < points1.length; i++) {
				r = new Riccio(mattonciniMap);
				r.setPosition(points1[i].x, points1[i].y);
				nemici.add(r);}
		}
		if(cassiopea.livelloBonusEstato==false){
		Fitoplancton f;
		Point[] points2 = new Point[] {
			new Point(300, 200),
			new Point(660, 200),
			new Point(1300, 300),
			new Point(1400, 300),
			new Point(1100, 300)
		};
		for(int i = 0; i < points2.length; i++) {
			f = new Fitoplancton(mattonciniMap);
			f.setPosition(points2[i].x, points2[i].y);
			nemici.add(f);
		}}
		else if (cassiopea.livelloBonusEstato==true)
		{	Fitoplancton f;
		Point[] points2 = new Point[] {
				new Point(300, 200),
				new Point(660, 200),
				new Point(1300, 300),
				new Point(1400, 300),
				new Point(1100, 300)
			};
//		for(int j = 0; j < Cassiopea.fitopl.size(); j++) {
//			System.out.println("Cassiopea.p. "+Cassiopea.fitopl.get(j));
//		}
			for(int i = 0; i < points2.length; i++) {
				for(int j = 0; j < Cassiopea.fitopl.size(); j++) {
					if(points2[i].getX()==Cassiopea.fitopl.get(j).getX() && points2[i].getY()==Cassiopea.fitopl.get(j).getY()){
						points2[i].setLocation(0, 0);	
					}
					else{
//						System.out.println("NONIIIIIIIIIIII");
					}
					
				}
				
			}
			for(int i = 0; i < points2.length; i++) {
				f = new Fitoplancton(mattonciniMap);
				f.setPosition(points2[i].x, points2[i].y);
				nemici.add(f);}
		}
//		Onde o;
//		Point[] points3 = new Point[] {
//			new Point(565, 400),
//			new Point(540, 400),
//			new Point(525, 400),
//			new Point(500, 400),
//			new Point(580, 400)
//		};
//		for(int i = 0; i < points.length; i++) {
//			o = new Onde(mattonciniMap);
//			o.setPosition(points3[i].x, points3[i].y);
//			nemici.add(o);
//		}

		
		
		
		//gashaaaaaaaaaaaaaaaaaaaaaaaaaaa
//		
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
				new Point(1975, 400), 
				new Point(2000, 400), 
				new Point(2025, 400), 
				new Point(2050, 400), 
				new Point(2075, 400), 
				new Point(2100, 400), 
				new Point(2125, 400), 
				new Point(2150, 400), };
		
		for(int i = 0; i < points3.length; i++) {
			o = new Onde(mattonciniMap);
			o.setPosition(points3[i].x, points3[i].y);
			nemici.add(o);
		}
	
		FineLivello fi;
		fi= new FineLivello(mattonciniMap);
		fi.setPosition(460, 280);
		nemici.add(fi);
//		Bollicina bol;
//		Point[] points5 = new Point[] {
//			new Point(1100, 600)
//		};
//		for(int i = 0; i < points5.length; i++) {
//			bol = new Bollicina(mattonciniMap);
//			bol.setPosition(points5[i].x, points5[i].y);
//			nemici.add(bol);
//		}
if(Cassiopea.livelloBonusEstato==false){
		BucoNero b;
		Point[] points4 = new Point[] {
			new Point(1000, 390),
			
		};
		for(int i = 0; i < points4.length; i++) {
			b = new BucoNero(mattonciniMap);
			b.setPosition(points4[i].x, points4[i].y);
			nemici.add(b);
		}
}
		
		
	}
	
	
	public void update() {
		
		// update cassy<3
		cassiopea.update();
		
		//**********SETPOSITION SERVE A FARE "LA MATRICE A SCORRIMENTO" 

		mattonciniMap.setPosition(
			GamePanel.WIDTH / 2 - cassiopea.getx(),
			GamePanel.HEIGHT / 2 - cassiopea.gety()
		);
//*****************************************************************///
		
		
		
		// set background
		bg.setPosition(mattonciniMap.getx(), mattonciniMap.gety());
		
		for(int i = 0; i < nemici.size(); i++) {
			Nemico e = nemici.get(i);
			e.update();
			if(e.isDead()) {
				nemici.remove(i);
				i--;
				explosions.add(
					new Explosion(e.getx(), e.gety()));
				}
			}
		
		for(int i = 0; i < explosions.size(); i++) {
			explosions.get(i).update();
			if(explosions.get(i).shouldRemove()) {
				explosions.remove(i);
				i--;
			}
		}
		
cassiopea.checkAttack(nemici);	

if( cassiopea.health==0 && cassiopea.lives>0)
{
	cassiopea.health=5;
	
	cassiopea.setPosition(100, 100);
	cassiopea.lives--;
}

if(cassiopea.lives==0)
{
	
	gsm=new GameStateManager();
	Cassiopea.livelloBonusEstato=false;
	gsm.setState(1);
	this.init();
}
	
if(Cassiopea.livelloBonus==true){
	System.out.println("MA COSAAAAAAAAAAAAAAAAAAA");
	
	gsm.currentState=gsm.LEVEL2STATE;
	gsm.loadState(gsm.currentState);
	this.init();
//	
//	gsm=new GameStateManager();
//	gsm.setState(2);
//	this.init();
}


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
//		fireball.draw(g);
		for(int i = 0; i < explosions.size(); i++) {
			explosions.get(i).setMapPosition(
				(int)mattonciniMap.getx(), (int)mattonciniMap.gety());
			explosions.get(i).draw(g);
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
		}
		
	}
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT) cassiopea.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) cassiopea.setRight(false);
		if(k == KeyEvent.VK_UP) cassiopea.setUp(false);
		if(k == KeyEvent.VK_DOWN) cassiopea.setDown(false);
		if(k == KeyEvent.VK_W) cassiopea.setJumping(false);
		if(k == KeyEvent.VK_E) cassiopea.setGliding(false);
		
	}
}












