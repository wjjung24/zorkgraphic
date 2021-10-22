package com.wjjung24.zork;
import com.badlogic.gdx.Game;

public class Zork extends Game{
	private LoadingScreen loadingScreen;
	private GameScreen gameScreen;
	private MenuScreen menuScreen;
	private EndScreen endScreen;

	public final static int MENU = 0;
	public final static int GAME = 1;
	public final static int END = 2;

	public void changeScreen(int screen){
		switch(screen){
			case MENU:
				if(menuScreen == null) menuScreen = new MenuScreen();
				this.setScreen(menuScreen);
				break;
			case GAME:
				if(gameScreen == null) gameScreen = new GameScreen();
				this.setScreen(gameScreen);
				break;
			case END:
				if(endScreen == null) endScreen = new EndScreen();
				this.setScreen(endScreen);
				break;
		}
	}
	@Override
	public void create () {
		loadingScreen = new LoadingScreen(this);
		setScreen(loadingScreen);
	}
}
