package GameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import TileMap.Background;
import TileMap.mattonciniMap;



public class LevelEditor extends GameState{
	private mattonciniMap mattonciniMap; // mattoncini map
	private Background bg;
	private JRadioButtonMenuItem button1;
	private JRadioButtonMenuItem button2;
	private JRadioButtonMenuItem button3;
	private JRadioButtonMenuItem button5;
	private JRadioButtonMenuItem button10;
	ArrayList<JMenuItem> menuItems;
	
	public LevelEditor(GameStateManager gameStateManager) {
		super();
		this.gsm = gsm;
		init();
	}

	@Override
	public void init() {
		mattonciniMap = new mattonciniMap(30);
		mattonciniMap.loadmattoncinis("/Tilesets/mattoncini.gif"); // è l'immagine intera di quello che creerà il mondo, mattoncini ecc ecc
		mattonciniMap.loadMap("/Maps/editor.map"); //primo livello..fa na pocu schifu, però CAMINA !!
		mattonciniMap.setPosition(0, 0);
		bg = new Background("/Backgrounds/sfondobiancoonde.jpg", 0.1);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void draw(Graphics2D g)  {
		// TODO Auto-generated method stub
		bg.draw(g);
		mattonciniMap.draw(g);
		g.drawString("ciaoo", 30, 30);
		
	
	}

	

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

}
