package com.wjjung24.zork;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LoadingScreen implements Screen {
    private Zork parent;
    Texture loading = new Texture("gamemap/Loading1.png");
    SpriteBatch batch = new SpriteBatch();
    public LoadingScreen(Zork parentscreen){
        parent = parentscreen;
    }
    @Override
    public void show() {
        // TODO Auto-generated method stub
    }
    float elapsedTime = 0;
    @Override
    public void render(float delta) {
        elapsedTime += delta;
        batch.begin();
        batch.draw(loading, 0, 50);

        batch.end();

        parent.changeScreen(parent.GAME);
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
        // TODO Auto-generated method stub
    }
}
