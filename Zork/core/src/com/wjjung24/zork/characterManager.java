package com.wjjung24.zork;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class characterManager extends Actor {
    private float showTime;
    TextureRegion[] animationFrames_r= new TextureRegion[36];
    TextureRegion[] animationFrames_l= new TextureRegion[36];
    TextureRegion[] animationFrames_u= new TextureRegion[36];
    TextureRegion[] animationFrames_d= new TextureRegion[36];
    Animation animation;
    Animation animation2;
    Animation animation3;
    Animation animation4;
    Texture rightsprite;
    Texture upsprite;
    Texture downsprite;
    Texture leftsprite;

    private boolean state = false;
    private long longCounter = 0;
    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
    public characterManager(){
        rightsprite = new Texture("Character/images/right3.png");
        leftsprite = new Texture("Character/images/left2.png");
        upsprite = new Texture("Character/images/up.png");
        downsprite = new Texture("Character/images/down.png");

        TextureRegion[][] tmpFrames_r = TextureRegion.split(rightsprite, 799, 102);
        TextureRegion[][] tmpFrames_l = TextureRegion.split(leftsprite, 799, 102);
        TextureRegion[][] tmpFrames_u = TextureRegion.split(upsprite, 75, 103);
        TextureRegion[][] tmpFrames_d = TextureRegion.split(downsprite, 75, 103);

        for (int i=0; i<36; i++){
            animationFrames_r[i] = tmpFrames_r[i][0];
            animationFrames_l[i] = tmpFrames_l[i][0];
        }
        animation = new Animation(1f/6f, animationFrames_l);
        animation2 = new Animation(1f/3f, animationFrames_u);
        animation3 = new Animation(1f/6f, animationFrames_r);
        animation4 = new Animation(1f/3f, animationFrames_d);

    }

    float myDelta;
    @Override
    public void act(float delta){
        super.act(delta);
        myDelta = delta;
        showTime += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        longCounter++;

        if(longCounter== 500)
            setState(true);
        super.draw(batch, parentAlpha);

        if(!isState())
            batch .draw((TextureRegion) animation.getKeyFrame(showTime, false), 0, 0);
        else
            batch.draw((TextureRegion) animation2.getKeyFrame(showTime, true), 0, 0);
    }
}
