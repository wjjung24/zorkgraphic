package com.wjjung24.zork;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EndScreen implements Screen {

    private Zork parent;
    SpriteBatch batch = new SpriteBatch();
    Texture win = new Texture("gamemap/Win.png");
    Texture lose = new Texture("gamemap/Lose.png");

    public EndScreen(Zork parentscreen){
        parent = parentscreen;
    }
    @Override
    public void show() {
        // TODO Auto-generated method stub
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        batch.begin();

        if (GameManager.win)
            batch.draw(win, 360, 280);
        else
            batch.draw(lose, 360,280 );

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
