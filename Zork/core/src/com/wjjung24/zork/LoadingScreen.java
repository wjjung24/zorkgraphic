package com.wjjung24.zork;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;

public class LoadingScreen implements Screen {
    private Zork parent;
    public LoadingScreen(Zork parentscreen){
        parent = parentscreen;
    }
    @Override
    public void show() {
        // TODO Auto-generated method stub
    }

    @Override
    public void render(float delta) {
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