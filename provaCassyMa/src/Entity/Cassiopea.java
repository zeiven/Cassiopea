package Entity;

import Entity.Enemies.BucoNero;
import Entity.Enemies.Onde;
import Entity.Enemies.PesciolinoCattivo;
import Entity.Enemies.Riccio;
import TileMap.*;

import java.util.ArrayList;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;


public class Cassiopea extends MapObject {
	public static ArrayList<Point> fitopl= new ArrayList<Point>();
	public static ArrayList<Point> pescCatt= new ArrayList<Point>();
	public static ArrayList<Point> riccio= new ArrayList<Point>();
	public static boolean livelloBonus;
	public static boolean livello1FINE;
	public static int vita;
	public static boolean livelloBonusFine;
	public static boolean livelloBonusEstato;
	public static boolean checkLoad=true;
	//CASSY
	public int lives=5;
	public int health;
	private int maxHealth;
	int highscore;
	private int maxFire;	
	// fireball
	private boolean firing;
	private int fireCost;
	private int fireBallDamage;
	private ArrayList<FireBall> fireBalls;

	// VEDIAMO SE CASSYMINCHIA VOLA
	private boolean gliding;
	
	
	private boolean scratching;
	private int scratchDamage;
	private int scratchRange;
	
	private boolean flinching;
	private long flinchTimer;
	private int fire;
	
//	// animations
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {
		2, 8, 1, 2, 4, 2, 5
	};
//	
//	// animation actions
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int JUMPING = 2;
	private static final int FALLING = 3;
	private static final int GLIDING = 4;
	private static final int FIREBALL = 5;
	private static final int SCRATCHING = 6;
//	
	
	
	public Cassiopea(mattonciniMap tm) {
		
		super(tm);
		
		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;
		
		moveSpeed = 0.3;
		maxSpeed = 1.6;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		jumpStart = -4.8;
		stopJumpSpeed = 0.3;
		
		facingRight = true;
		
		health = maxHealth = 5;
		fire = maxFire = 2500;
		
		fireCost = 200;
		fireBallDamage = 5;
		fireBalls = new ArrayList<FireBall>();
		
		scratchDamage = 8;
		scratchRange = 40;
		
		// load sprites
		try {
			
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					"/Sprites/Player/cassiopea5.gif"
				)
			);
			
			sprites = new ArrayList<BufferedImage[]>();
			
			for(int i = 0; i < 7; i++) {
				
				BufferedImage[] bi =
					new BufferedImage[numFrames[i]];
				
				for(int j = 0; j < numFrames[i]; j++) {
					
					if(i != SCRATCHING) {
						bi[j] = spritesheet.getSubimage(
								j * width,
								i * height,
								width,
								height
						);
					}
					else {
						bi[j] = spritesheet.getSubimage(
								j * width * 2,
								i * height,
								width * 2,
								height
						);
					}
					
				}
				
				sprites.add(bi);
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(400);
		
		
	}
	
	public int getHealth() { return health; }
	public int getMaxHealth() { return maxHealth; }
	public int getFire() { return fire; }
	public int getMaxFire() { return maxFire; }

	public void setFiring() { 
		firing = true;
	}
	public void setScratching() {
		scratching = true;
	}
	public void setGliding(boolean b) { 
		gliding = b;
	}
	
	public void checkAttack(ArrayList<Nemico> nemici) {
		
		
		// loop through enemies
		for(int i = 0; i < nemici.size(); i++) {
			
			Nemico e = nemici.get(i);
			
			// scratch attack
			if(scratching) {
				if(facingRight) {
					if(
						e.getx() > x &&
						e.getx() < x + scratchRange && 
						e.gety() > y - height / 2 &&
						e.gety() < y + height / 2
					) {
						e.hit(scratchDamage);
					}
				}
				else {
					if(
						e.getx() < x &&
						e.getx() > x - scratchRange &&
						e.gety() > y - height / 2 &&
						e.gety() < y + height / 2
					) {
						e.hit(scratchDamage);
					}
				}
			}
	
			for(int j = 0; j < fireBalls.size(); j++) {
				if(fireBalls.get(j).intersects(e)) {
					e.hit(fireBallDamage);
					fireBalls.get(j).setHit();
					break;
				}
			}

			
			// check enemy collision
			if(!(e instanceof Fitoplancton) && !(e instanceof Bollicina) && !(e instanceof FineLivello) && !(e instanceof OggettoInvisibile)){
			if(intersects(e)) {
				hit(e.getDamage());}
			}
			
			if(e instanceof Fitoplancton){
//				pointsFI[ind]= new Point(e.getx(),e.gety()); 
				if(intersects(e)) {
					Point p1= new Point(e.getx(),e.gety());
					fitopl.add(p1);
					 
//						writer.write("uuu"+e.getx()+" "+e.gety());

					
					System.out.println(e.getx()+" "+e.gety());
					e.setPosition(-1, -1);
					e.setContaCibo();
					e.setVivo(false);
//					for (int j = 0; j < nemici.size(); j++) {


				}
				
					
				if(e.getContaCibo()==10){
					health++;
				
					((Fitoplancton) e).ResetContaCibo();
					}
			}
//			
			if(e instanceof Bollicina){
				if(intersects(e)) {
					e.setPosition(-1, -1);
					e.setContaBollicine();
				}
				if(e.getContaBollicine()==2){
//					health++;
					((Bollicina) e).ResetContaBollicine();
					setPosition(100, 100);
					livelloBonusFine=true;
					livelloBonus=false;
					checkLoad=false;
					}
			}
				
			if(e instanceof Onde){
				if(intersects(e)) {
					setPosition(100, 100);
					health--;
					
	
				}
			}
			if(e instanceof BucoNero){
				if(intersects(e)) {
					
//					setPosition(100, 100);
				livelloBonusEstato=true;
				livelloBonus=true;
				
				livelloBonusFine=false;
				setPosition(1000, 500);
//				vita=health;
				System.out.println("viataaaaaaaaaaaaaaaaaaaa ");
				}
				
			}
			
			if(e instanceof FineLivello){
				if(intersects(e)) {
//					System.out.println("intersezione");
				livello1FINE=true;}
			}
			
			for(int j = 0; j < fireBalls.size(); j++) {
				if(fireBalls.get(j).intersects(e)) {
					e.hit(fireBallDamage);
					fireBalls.get(j).setHit();
					if(e instanceof PesciolinoCattivo){
						System.out.println("e.getx(),e.gety() "+ e.getx()+e.gety()+ " " +e);
						Point p1= new Point(e.getx(),e.gety());
						pescCatt.add(p1);}
					else if(e instanceof Riccio){
						Point p1= new Point(e.getx(),e.gety());
						riccio.add(p1);}
					break;
				}
			}

			
			
//			if(health<=0){
//				setPosition(100, 100);
//				health=5;
//				
//			}
			
//			if(e.getContaCibo()==1){
//				health++;
//				continue;
//			}
			
			
		}
	
		
	}
	
	
	public void hit(int damage) {

		if(flinching) return;
		health -= damage;
		if(health < 0) health = 0;
//		if(health == 0) dead = true;
		flinching = true;
		flinchTimer = System.nanoTime();
	}
	
	private void getNextPosition() {
		
		// movement
		if(left) {
			dx -= moveSpeed;
			if(dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		}
		else if(right) {
			dx += moveSpeed;
			if(dx > maxSpeed) {
				dx = maxSpeed;
			}
		}
		else {
			if(dx > 0) {
				dx -= stopSpeed;
				if(dx < 0) {
					dx = 0;
				}
			}
			else if(dx < 0) {
				dx += stopSpeed;
				if(dx > 0) {
					dx = 0;
				}
			}
		}
	
		if( (currentAction == SCRATCHING || currentAction == FIREBALL) &&
				!(jumping || falling)) {
					dx = 0;
				}
				
		
		// jumping
		if(jumping && !falling) {
	
			dy = jumpStart;
			falling = true;
		}
		
		// falling
		if(falling) {
			
			if(dy > 0 && gliding) dy += fallSpeed * 0.1;
			else dy += fallSpeed;
			
			if(dy > 0) jumping = false;
			if(dy < 0 && !jumping) dy += stopJumpSpeed;
			
			if(dy > maxFallSpeed) dy = maxFallSpeed;
			
		}
		
	}
	
	public void update() {
		
		// update position
		getNextPosition();
		checkmattonciniMapCollision();
		setPosition(xtemp, ytemp);
		if(currentAction == SCRATCHING) {
			if(animation.hasPlayedOnce()) scratching = false;
		}
		if(currentAction == FIREBALL) {
			if(animation.hasPlayedOnce()) firing = false;
		}
		// fireball attack
				fire += 1;
				if(fire > maxFire) fire = maxFire;
				if(firing && currentAction != FIREBALL) {
					if(fire > fireCost) {
						fire -= fireCost;
						FireBall fb = new FireBall(mattonMap, facingRight);
						fb.setPosition(x, y);
						fireBalls.add(fb);
					}
				}
				
				// update fireballs
				for(int i = 0; i < fireBalls.size(); i++) {
					fireBalls.get(i).update();
					if(fireBalls.get(i).shouldRemove()) {
						fireBalls.remove(i);
						i--;
					}
				}
				if(scratching) {
					if(currentAction != SCRATCHING) {

						currentAction = SCRATCHING;
						animation.setFrames(sprites.get(SCRATCHING));
						animation.setDelay(50);
						width = 60;
					}
				}
		
				else if(firing) {
				if(currentAction != FIREBALL) {
					currentAction = FIREBALL;
					animation.setFrames(sprites.get(FIREBALL));
					animation.setDelay(100);
					width = 30;
				}
		 }
		else if(dy > 0) {
			
			if(gliding) {
				if(currentAction != GLIDING) {
					currentAction = GLIDING;
					animation.setFrames(sprites.get(GLIDING));
					animation.setDelay(100);
					width = 30;
				}
			}
			else if(currentAction != FALLING) {
				currentAction = FALLING;
				animation.setFrames(sprites.get(FALLING));
				animation.setDelay(100);
				width = 30;
			}
		}
		else if(dy < 0) {
			
			if(currentAction != JUMPING) {
				currentAction = JUMPING;
				animation.setFrames(sprites.get(JUMPING));
				animation.setDelay(-1);
				width = 30;
			}
		}
		else if(left || right) {
			
			if(currentAction != WALKING) {
				currentAction = WALKING;
				animation.setFrames(sprites.get(WALKING));
				animation.setDelay(40);
				width = 30;
			}
		}
		else {
			if(currentAction != IDLE) {
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(400);
				width = 30;
			}
			
		}
		
		animation.update();
		
		// set direction
		if(currentAction != SCRATCHING && currentAction != FIREBALL) {
			if(right) facingRight = true;
			if(left) facingRight = false;
		}
		 
		if(flinching) {
			long elapsed =
				(System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed > 1000) {
				flinching = false;
			}
		}
	
	}
	
	public void draw(Graphics2D g) {
		
		setMapPosition();
		for(int i = 0; i < fireBalls.size(); i++) {
			fireBalls.get(i).draw(g);
		}

		if(flinching) {
			long elapsed =
				(System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed / 100 % 2 == 0) {
				return;
			}
		}
		
		super.draw(g);
		
	}


}	

















