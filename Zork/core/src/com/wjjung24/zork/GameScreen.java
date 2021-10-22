package com.wjjung24.zork;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import java.util.concurrent.TimeUnit;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen{

    mapper mappingAssistant = new mapper();
    SpriteBatch batch = new SpriteBatch();
    Viewport viewport = new FitViewport(1080, 590);
    TextField console;
    Stage stage;
    private String direction = "";

    TextureAtlas textureAtlas;
    Sprite sprite;
    TextureRegion textureRegion;
    private int currentframe = 1;
    private final int MAX_FRAMES = 8;


    public void moveright(){
        currentframe++;
        if (currentframe>MAX_FRAMES){
            currentframe = 1;
        sprite.setRegion(textureAtlas.findRegion(String.format("%04d", currentframe)));
        }
    }

    public GameScreen(){
        batch = new SpriteBatch();
        textureAtlas = new TextureAtlas(Gdx.files.internal("Character/data/sprite.atlas"));
        textureRegion = textureAtlas.findRegion("0013");
        sprite = new Sprite(textureRegion);
        sprite.setPosition(100, 100);
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        console = new TextField("", skin);
        stage = new Stage(viewport);
        console.setPosition(0, 0);
        console.setSize(799, 50);
        stage.addActor(console);
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.ENTER) {
                    direction=console.getText();
                    if (mapper.check(direction)){
                        mapper.update(direction);
                        moveright();
                    }
                    console.setText("");
                    direction = "";
                }
                return false;
            }
        });
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
    }


    @Override
    public void render(float delta) {

        stage.act();
        stage.draw();
        stage.setKeyboardFocus(console);
        batch.begin();
        batch.draw(mappingAssistant.drawmap(), 0,50);
        sprite.draw(batch);
        batch.end();
        // TODO Auto-generated method stub
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
        // TODO Auto-generated method stub
    }
}
