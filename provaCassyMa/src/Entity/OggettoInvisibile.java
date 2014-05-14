package Entity;


import Entity.*;

import TileMap.mattonciniMap;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;

public class OggettoInvisibile extends Nemico {
	
	private BufferedImage[] sprites;
	
	public OggettoInvisibile(mattonciniMap tm) {
		
		super(tm);
		
		moveSpeed = 0.2; //0.3
		maxSpeed = 0.7; //1.3
		fallSpeed = 0.1; //0.2
		maxFallSpeed = 9.4; //10.0
		
		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;
		
		health = maxHealth = 2;
		damage = 1;
		
		// load sprites
		try {
			
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					"/Sprites/Enemies/onde1.gif"
				)
			);
			
			sprites = new BufferedImage[3];
			for(int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(
					i * width,
					0,
					width,
					height
				);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(300);
		
		right = true;
		facingRight = true;
		
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
		
		// VOLAREEEEEEEEEE
//		if(falling) {
//			dy += fallSpeed;
//		}
		
	}
	
	public void update() {
		
		// update position
		getNextPosition();
		checkmattonciniMapCollision();
		setPosition(xtemp, ytemp);
		
		//  indietreggiaare
//		if(indietreggiare) {
//			double elapsed =
//				(System.nanoTime() - indietreggiareTimer) / 1000000;
//			if(elapsed > 400) {
//				indietreggiare = false;
//			}
//		}
		
		
		if(right && dx == 0) {
			right = false;
			//left = true;
			facingRight = false;
		}
		
		
		// update animation
		animation.update();
		
	}
	
	public void draw(Graphics2D g) {
		
		//if(notOnScreen()) return;
		
		setMapPosition();
		
		super.draw(g);
		
	}
	
}











