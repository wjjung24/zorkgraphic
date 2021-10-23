package com.wjjung24.zork;

import com.badlogic.gdx.*;

import java.util.concurrent.TimeUnit;

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
    private int FRAMES = 36;
    private String direction = "";


    Animation animation;
    Texture rightsprite;
    Texture upsprite;
    Texture downsprite;
    Texture leftsprite;
    TextureRegion[] animationFrames_r= new TextureRegion[36];
    TextureRegion[] animationFrames_l= new TextureRegion[36];
    TextureRegion[] animationFrames_u= new TextureRegion[24];
    TextureRegion[] animationFrames_d= new TextureRegion[24];

    float elapsedTime;



    public GameScreen(){
        batch = new SpriteBatch();
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        console = new TextField("", skin);
        console.setPosition(0, 0);
        console.setSize(799, 50);
        stage = new Stage(viewport);
        stage.addActor(console);

        rightsprite = new Texture("Character/images/right3.png");
        leftsprite = new Texture("Character/images/left2.png");
        upsprite = new Texture("Character/images/up2.png");
        downsprite = new Texture("Character/images/down.png");

        TextureRegion[][] tmpFrames_r = TextureRegion.split(rightsprite, 799, 102);
        TextureRegion[][] tmpFrames_l = TextureRegion.split(leftsprite, 799, 102);
        TextureRegion[][] tmpFrames_u = TextureRegion.split(upsprite, 76, 540);
        TextureRegion[][] tmpFrames_d = TextureRegion.split(downsprite, 75, 540);

        for (int i=0; i<FRAMES; i++){
            animationFrames_r[i] = tmpFrames_r[i][0];
            animationFrames_l[i] = tmpFrames_l[i][0];
        }

        for (int i=0; i<23; i++){
            animationFrames_u[i] = tmpFrames_u[0][i];
        }


        animation = new Animation(1f/3f, animationFrames_u);

        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.ENTER) {
                    direction=console.getText();
                    if (mapper.check(direction)){
                        mapper.update(direction);
                        if (direction.equals("LEFT")){
                            animation = new Animation(1f/6f, animationFrames_l);
                        }
                        else if (direction.equals("UP")){
                            animation = new Animation(1f/3f, animationFrames_u);
                        }
                        else if (direction.equals("RIGHT")){
                            animation = new Animation(1f/6f, animationFrames_r);
                        }
                        else if (direction.equals("DOWN")){
                            animation = new Animation(1f/3f, animationFrames_d);
                        }
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
        elapsedTime += Gdx.graphics.getDeltaTime();

        stage.act();
        stage.draw();
        stage.setKeyboardFocus(console);
        batch.begin();
        batch.draw(mappingAssistant.drawmap(), 0,50);
        batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, true), 0, 280);
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
