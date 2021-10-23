package com.wjjung24.zork;

import com.badlogic.gdx.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
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

public class GameScreen extends Game implements Screen{
    @Override
    public void create () {

    }

    private Zork game;
    BitmapFont font = new BitmapFont(); //or use alex answer to use custom font

    mapper mappingAssistant = new mapper();
    SpriteBatch batch = new SpriteBatch();
    Viewport viewport = new FitViewport(1080, 590);
    TextField console;
    FightScreen fightScreen = new FightScreen();
    Stage stage;
    private String direction = "";
    private String state = "RIGHT";

    GameManager gameManager = new GameManager();


    Animation animation;
    Texture rightsprite;
    Texture upsprite;
    Texture downsprite;
    Texture leftsprite;
    TextureRegion[] animationFrames_r= new TextureRegion[36];
    TextureRegion[] animationFrames_l= new TextureRegion[36];
    TextureRegion[] animationFrames_u= new TextureRegion[24];
    TextureRegion[] animationFrames_d= new TextureRegion[24];
    TextureRegion[] animationFrames_d_halt = new TextureRegion[20];
    TextureRegion[] animationFrames_u_halt = new TextureRegion[20];
    TextureRegion[] animationFrames_l_halt = new TextureRegion[32];
    TextureRegion[] animationFrames_r_halt = new TextureRegion[32];

    float elapsedTime;



    public GameScreen(Zork parent){
        this.game = parent;
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
        downsprite = new Texture("Character/images/down2.png");

        TextureRegion[][] tmpFrames_r = TextureRegion.split(rightsprite, 799, 102);
        TextureRegion[][] tmpFrames_l = TextureRegion.split(leftsprite, 799, 102);
        TextureRegion[][] tmpFrames_u = TextureRegion.split(upsprite, 76, 540);
        TextureRegion[][] tmpFrames_d = TextureRegion.split(downsprite, 76, 540);
        TextureRegion[][] tmpFrames_d_halt = TextureRegion.split(downsprite, 76, 540);
        TextureRegion[][] tmpFrames_u_halt = TextureRegion.split(upsprite, 76, 540);
        TextureRegion[][] tmpFrames_l_halt = TextureRegion.split(leftsprite, 799, 102);
        TextureRegion[][] tmpFrames_r_halt = TextureRegion.split(rightsprite, 799, 102);

        for (int i=0; i<36; i++){
            animationFrames_r[i] = tmpFrames_r[i][0];
            animationFrames_l[i] = tmpFrames_l[i][0];
        }

        for (int i=0; i<24; i++){
            animationFrames_u[i] = tmpFrames_u[0][i];
            animationFrames_d[i] = tmpFrames_d[0][i];
        }

        for (int i=0; i<20; i++){
            animationFrames_d_halt[i] = tmpFrames_d_halt[0][i];
            animationFrames_u_halt[i] = tmpFrames_u_halt[0][i];
        }

        for (int i=0; i<32; i++){
            animationFrames_l_halt[i] = tmpFrames_l_halt[i][0];
            animationFrames_r_halt[i] = tmpFrames_r_halt[i][0];
        }

        animation = new Animation(1f/1f, animationFrames_r[0]);


        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.ENTER) {

                    if (!console.getText().isEmpty() && (console.getText().equals("LEFT") || console.getText().equals("RIGHT") || console.getText().equals("UP") || console.getText().equals("DOWN"))){
                        console.setDisabled(true);
                        direction=console.getText();
                        state = console.getText();

                    }
                    if (mappingAssistant.check(direction)){
                        mappingAssistant.update(direction);

                        if (!console.getText().isEmpty() && (console.getText().equals("LEFT") || console.getText().equals("RIGHT") || console.getText().equals("UP") || console.getText().equals("DOWN"))){
                        gameManager.generator();}

                        if (direction.equals("LEFT")&& (gameManager.env == gameManager.blank)){
                            animation = new Animation(1f/6f, animationFrames_l);
                        }
                        else if ((direction.equals("LEFT")) && (gameManager.env != gameManager.blank)){
                            animation = new Animation(1f/6f, animationFrames_l_halt);
                        }
                        else if (direction.equals("UP")&& (gameManager.env == gameManager.blank)){
                            animation = new Animation(1f/6f, animationFrames_u);
                        }
                        else if ((direction.equals("UP")) && (gameManager.env != gameManager.blank)){
                            animation = new Animation(1f/6f, animationFrames_u_halt);
                        }
                        else if (direction.equals("RIGHT")&& (gameManager.env == gameManager.blank)){
                            animation = new Animation(1f/6f, animationFrames_r);
                        }
                        else if ((direction.equals("RIGHT")) && (gameManager.env != gameManager.blank)){
                            animation = new Animation(1f/6f, animationFrames_r_halt);
                        }
                        else if (direction.equals("DOWN")&& (gameManager.env == gameManager.blank)){
                            animation = new Animation(1f/6f, animationFrames_d);
                        }
                        else if ((direction.equals("DOWN")) && (gameManager.env != gameManager.blank)){
                            animation = new Animation(1f/6f, animationFrames_d_halt);
                        }
                    }
                    if(gameManager.env == gameManager.key && console.getText().equals("PICK UP KEY")){
                        gameManager.message = "You picked up the key! Now, find the room with the treasure chest!";
                        gameManager.env = gameManager.blank;
                        gameManager.hasKey = true;
                    }
                    else if(gameManager.env == gameManager.sword && console.getText().equals("PICK UP SWORD")){
                        gameManager.message = "You picked up the sword! It may come in handy later, perhaps in a fight?";
                        gameManager.env = gameManager.blank;
                        gameManager.charWeapon = 0;
                    }
                    else if(gameManager.env == gameManager.axe && console.getText().equals("PICK UP AXE")){
                        gameManager.message = "You picked up the axe! It may come in handy later, perhaps in a fight?";
                        gameManager.charWeapon = 1;
                        gameManager.env = gameManager.blank;

                    }
                    else if(gameManager.env == gameManager.apple && console.getText().equals("EAT")){
                        gameManager.message = "You are feeling healthy!?";
                        gameManager.env = gameManager.blank;

                    }
                    else if(gameManager.env == gameManager.meat && console.getText().equals("EAT")){
                        gameManager.message = "You are feeling healthy!?";
                        gameManager.env = gameManager.blank;

                    }

                    if(gameManager.env == gameManager.boar) {
                        if (console.getText().equals("RUN AWAY")) {
//                            gameManager.env = gameManager.blank;

                        } else if (console.getText().equals("FIGHT")) {
//                          game.setScreen(new FightScreen());
//                            gameManager.env = gameManager.blank;

                        }
                    }

                    console.setText("");
                    direction = "";
//                    gameManager.message = "";
                }
                return false;
            }
        }
        );
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

        batch.draw(mappingAssistant.gamemap, 0,50);
        System.out.println(gameManager.env);
        System.out.println(gameManager.num);

        if ((state.equals("RIGHT") || state.equals("LEFT")) && animation.getKeyFrameIndex(elapsedTime) >=18)
            mappingAssistant.drawmap();

        if ((state.equals("UP") || state.equals("DOWN")) && animation.getKeyFrameIndex(elapsedTime) >=12)
            mappingAssistant.drawmap();

        if ((state.equals("RIGHT") || state.equals("LEFT")) && (animation.getKeyFrameIndex(elapsedTime) >=18 || animation.getKeyFrameIndex(elapsedTime)==0)){
            batch.draw(gameManager.env, 360, 280);
            font.draw(batch, gameManager.message, 10, 75);
        }

        if ((state.equals("UP") || state.equals("DOWN")) && (animation.getKeyFrameIndex(elapsedTime) >=12 || animation.getKeyFrameIndex(elapsedTime)==0)) {
            batch.draw(gameManager.env, 360, 280);
            font.draw(batch, gameManager.message, 10, 75);
        }

        if(state.equals("RIGHT") || state.equals("LEFT"))
            batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, true), 0, 280);
        else if(state.equals("UP") || state.equals("DOWN"))
            batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, true), 350, 50);

        //System.out.println(animation.getKeyFrameIndex(elapsedTime));
        System.out.println(mappingAssistant.posx);
        System.out.println(mappingAssistant.posy);
        if (animation.isAnimationFinished(elapsedTime)){
             if (state.equals("RIGHT")&& gameManager.env == gameManager.blank)
                animation = new Animation(1f/1f, animationFrames_r[0]);
            else if (state.equals("RIGHT") && gameManager.env != gameManager.blank)
                animation = new Animation(1f/1f, animationFrames_r_halt[31]);
            else if (state.equals("LEFT")&& gameManager.env == gameManager.blank)
                animation = new Animation(1f/1f, animationFrames_l[0]);
            else if (state.equals("LEFT") && gameManager.env != gameManager.blank)
                animation = new Animation(1f/1f, animationFrames_l_halt[31]);
            else if (state.equals("UP")&& gameManager.env == gameManager.blank)
                animation = new Animation(1f/1f, animationFrames_u[0]);
            else if (state.equals("UP") && gameManager.env != gameManager.blank)
                animation = new Animation(1f/1f, animationFrames_u_halt[19]);
            else if (state.equals("DOWN")&& gameManager.env == gameManager.blank)
                animation = new Animation(1f/1f, animationFrames_d[0]);
            else if (state.equals("DOWN") && gameManager.env != gameManager.blank)
                animation = new Animation(1f/1f, animationFrames_d_halt[19]);

            elapsedTime=0;
            console.setDisabled(false);

        }

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
