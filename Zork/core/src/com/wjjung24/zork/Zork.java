package com.wjjung24.zork;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.w3c.dom.Text;

public class Zork extends Game{
	private LoadingScreen loadingScreen;
	private GameScreen gameScreen;
	private FightScreen fightScreen;
	private MenuScreen menuScreen;
	private EndScreen endScreen;


	public final static int MENU = 0;
	public final static int GAME = 1;
	public final static int FIGHT = 2;
	public final static int END = 3;

	public void changeScreen(int screen) {
		switch(screen){
			case MENU:
				if(menuScreen == null) menuScreen = new MenuScreen();
				this.setScreen(menuScreen);
				break;
			case GAME:
				if(gameScreen == null) gameScreen = new GameScreen(this);
				this.setScreen(gameScreen);
				break;
			case FIGHT:
				if (fightScreen == null) fightScreen = new FightScreen();
				this.setScreen(fightScreen);
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
