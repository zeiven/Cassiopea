package GameState;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;

public class GameStateManager {
	
	private GameState[] gameStates;
	public int currentState;
	

	public static final int NUMGAMESTATES = 6;
	public static final int MENUSTATE = 0;
	public static final int LEVEL1STATE = 1;
	public static final int LEVEL2STATE = 3;
	public static final int LEVEL1BONUS = 2;
	public static final int LEVEL2BONUS = 4;
	public static final int GAMEOVER=6;
	public static final int EDITOR=5;
	
	public GameStateManager() {
		
		gameStates = new GameState[NUMGAMESTATES];
		
		currentState = MENUSTATE;
		loadState(currentState);
		
	}
	
	 void loadState(int state) {
		if(state == MENUSTATE)
			gameStates[state] = new MenuState(this);
		if(state == LEVEL1STATE)
			gameStates[state] = new Level1State(this);
		if(state== LEVEL2STATE){
			gameStates[state] = new Level2State(this);
		}
		if(state== LEVEL1BONUS){
			gameStates[state] = new LevelBonus(this);
		}
		if(state== LEVEL2BONUS){
			gameStates[state] = new LevelBonus2(this);
		}
	}
	
	private void unloadState(int state) {
		gameStates[state] = null;
	}
	
	public void setState(int state) {
		unloadState(currentState);
		currentState = state;
		loadState(currentState);
	}
	
	public void update() {
		try {
			gameStates[currentState].update();
		} catch(Exception e) {}
	}
	
	public void draw(java.awt.Graphics2D g) {
		try {
			gameStates[currentState].draw(g);
		} catch(Exception e) {}
	}
	
	public void keyPressed(int k) {
		gameStates[currentState].keyPressed(k);
	}
	
	public void keyReleased(int k) {
		gameStates[currentState].keyReleased(k);
	}
	
}









