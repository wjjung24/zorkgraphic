package com.wjjung24.zork;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class LoadingScreen implements Screen {
    private Zork parent;
    Texture loading = new Texture("gamemap/LoadSprite.png");
    SpriteBatch batch = new SpriteBatch();
    Animation animation;
    TextureRegion[] animationFrames= new TextureRegion[4];

    public LoadingScreen(Zork parentscreen){
        parent = parentscreen;
        TextureRegion[][] tmpFrames = TextureRegion.split(loading, 1080, 590);
        for (int i=0; i<4; i++){
            animationFrames[i] = tmpFrames[0][i];
        }
        animation = new Animation(1f/4f, animationFrames);

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
        batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, true), 0, 0);

        batch.end();

        if (elapsedTime > 3)
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
