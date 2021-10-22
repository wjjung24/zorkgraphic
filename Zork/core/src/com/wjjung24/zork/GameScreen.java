package com.wjjung24.zork;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen{

    mapper mappingAssistant = new mapper();
    SpriteBatch batch = new SpriteBatch();
    Viewport viewport = new FitViewport(1080, 720);
    TextField console;
    Stage stage;

    public GameScreen(){
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        console = new TextField("", skin);
        stage = new Stage(viewport);
        console.setPosition(0, 0);
        stage.addActor(console);
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
        batch.draw(mappingAssistant.drawmap(), 0,180);

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
        // TODO Auto-generated method stub
    }
}
