package com.wjjung24.zork;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class characterManager extends GameScreen {
    SpriteBatch batch;
    TextureAtlas textureAtlas;
    Sprite sprite;
    TextureRegion textureRegion;
    private int currentframe = 1;
    private final int MAX_FRAMES = 8;

    public characterManager(){
        batch = new SpriteBatch();
        textureAtlas = new TextureAtlas(Gdx.files.internal("Character/data/sprite.atlas"));
        textureRegion = textureAtlas.findRegion("0013");
        sprite = new Sprite(textureRegion);
        sprite.setPosition(100, 100);
    }

    public void moveright(){
        currentframe++;
        if (currentframe>MAX_FRAMES){
            currentframe = 1;
        }
        sprite.setRegion(textureAtlas.findRegion(String.format("%04d", currentframe)));
    }


}
