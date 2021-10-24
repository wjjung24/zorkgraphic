package com.wjjung24.zork;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.util.Random;



public class FightScreen implements Screen {
    private Zork parent;
    SpriteBatch batch = new SpriteBatch();
    Texture background = new Texture("gamemap/fightBG.png");
    TextField console;
    Viewport viewport = new FitViewport(1080, 590);
    Stage stage;
    GameManager gameManager = new GameManager();
    BitmapFont font = new BitmapFont(); //or use alex answer to use custom font
    Random rand = new Random();
    mapper mappingAssistant = new mapper();
    float elapsedTime;
    private int runcount = 0;
    int boar_life = 5;
    Texture heart = new Texture("heart2.png");
    Texture boar = new Texture("env/enemy/boar_enemy.png");
    String hitStatus = "";

    Animation animation;
    Texture sword;
    Texture barehand;
    Texture boarvsword;
    Texture boarvhand;
    TextureRegion[] animationFrames_sword= new TextureRegion[5];
    TextureRegion[] animationFrames_barehand= new TextureRegion[3];
    TextureRegion[] animationFrames_boarvsword= new TextureRegion[15];
    TextureRegion[] animationFrames_boarvhand= new TextureRegion[13];

    boolean justentered = true;
    boolean boarcaughtflag = false;

    int num;

    public FightScreen(final Zork game) {
        parent = game; // Store this to call game.setScreen(new MenuScreen(game)) to return to the menu
        parent.currentScreen = parent.FIGHT;
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        sword = new Texture("Character/images/sword2.png");
        barehand = new Texture("Character/images/barehand.png");
        boarvsword = new Texture("Character/images/bvs.png");
        boarvhand = new Texture("Character/images/boarvshand.png");
        console = new TextField("", skin);
        console.setPosition(0, 0);
        console.setSize(799, 50);
        stage = new Stage(viewport);
        stage.addActor(console);
        gameManager.message = "You start a fight with the boar. What do you do? If you run away, you might fail [Choices: ATTACK/RUN AWAY]";

        TextureRegion[][] tmpFrames_sword = TextureRegion.split(sword, 450, 280);
        TextureRegion[][] tmpFrames_barehand = TextureRegion.split(barehand, 450, 280);
        TextureRegion[][] tmpFrames_boarvsword = TextureRegion.split(boarvsword, 450, 280);
        TextureRegion[][] tmpFrames_boarvhand = TextureRegion.split(boarvhand, 450, 280);

        for (int i=0; i<5; i++){
            animationFrames_sword[i] = tmpFrames_sword[0][i];
        }
        for (int i=0; i<3; i++){
            animationFrames_barehand[i] = tmpFrames_barehand[0][i];
        }
        for (int i=0; i<15; i++){
            animationFrames_boarvsword[i] = tmpFrames_boarvsword[0][i];
        }
        for (int i=0; i<13; i++){
            animationFrames_boarvhand[i] = tmpFrames_boarvhand[0][i];
        }

        animation = new Animation(1f/1f, animationFrames_boarvsword[6]);
        stage.addListener(new InputListener() {
          @Override
          public boolean keyDown(InputEvent event, int keycode) {
              if (keycode == Input.Keys.ENTER) {

                  console.setDisabled(true);
                  num = rand.nextInt(100);

                  if(console.getText().equals("ATTACK")){
                      runcount = 0;
                      justentered = false;

                      elapsedTime = 0;
                      animation = new Animation(1f/15f, animationFrames_boarvsword);
                      if(gameManager.charWeapon == gameManager.SWORD){
                        animation = new Animation(1f/15f, animationFrames_boarvsword);
                        if (num<=20){
                            hitStatus = "Miss! Unlucky...";
                            gameManager.message = "MISS! Unlucky...";
                        }
                        else if (num>20 && num<70){

                            boar_life--;
                            if(boar_life <= 0){
                                gameManager.message = "You made it out alive!";
                                gameManager.env = gameManager.blank;
                                parent.changeScreen(Zork.GAME);
                            }
                            else{
                            hitStatus = "Hit!";
                            gameManager.message = "Hit!";}
                        }
                        else{
                            boar_life -= 3;
                            if(boar_life <= 0){
                                gameManager.message = "You made it out alive!";
                                gameManager.env = gameManager.blank;
                                parent.changeScreen(Zork.GAME);
                            }
                            else{
                            hitStatus = "Critical hit!";

                            gameManager.message = "Critical hit!";}
                        }
                    }
                    else if(gameManager.charWeapon == gameManager.AXE){
                          elapsedTime = 0;
                          animation = new Animation(1f/15f, animationFrames_boarvsword);
                          if (num<=10){
                              hitStatus = "Miss! Unlucky...";
                              gameManager.message = "MISS! Unlucky...";
                          }
                          else if (num>10 && num<85){
                              boar_life--;
                              if(boar_life <= 0){
                                  gameManager.message = "You made it out alive!";
                                  gameManager.env = gameManager.blank;
                                  parent.changeScreen(Zork.GAME);
                              }
                              else{
                              hitStatus = "Hit!";
                              gameManager.message = "Hit!";}
                          }
                          else{
                              boar_life -= 3;
                              if(boar_life <= 0){
                                  gameManager.message = "You made it out alive!";
                                  gameManager.env = gameManager.blank;
                                  parent.changeScreen(Zork.GAME);
                              }
                              else {
                              hitStatus = "Critical hit!";

                              gameManager.message = "Critical hit!";}
                          }
                      }
                    else if (gameManager.charWeapon == gameManager.NONE){
                          elapsedTime = 0;
                          animation = new Animation(1f/13f, animationFrames_boarvhand);
                          if (num<=30){
                              hitStatus = "Miss! Unlucky...";
                              gameManager.message = "MISS! Unlucky...";
                          }
                          else if (num>30 && num<90){
                              boar_life--;
                              if(boar_life <= 0){
                                  gameManager.message = "You made it out alive!";
                                  gameManager.env = gameManager.blank;
                                  parent.changeScreen(Zork.GAME);
                              }else{
                              hitStatus = "Hit!";
                              gameManager.message = "Hit!";}
                          }
                          else{
                              boar_life -= 3;
                              if(boar_life <= 0){
                                  gameManager.message = "You made it out alive!";
                                  gameManager.env = gameManager.blank;
                                  parent.changeScreen(Zork.GAME);
                              }else{
                              hitStatus = "Critical hit!";

                              gameManager.message = "Critical hit!";}
                          }
                      }
                  }
                  else if (console.getText().equals("RUN AWAY")){
                      runcount = 0;
                      justentered = false;
                      if (num < 40){
                          GameManager.message = "You failed to run away and the boar caught you! What do you do? [Choices: ATTACK/RUN AWAY]";
                          boarcaughtflag = true;
                      }
                          else{
                          gameManager.env = gameManager.blank;
                          gameManager.message = "You ran away successfully!";
                          parent.changeScreen(Zork.GAME);
                          }

                  }
                  console.setText("");
              }
              return false;
          }
      }
        );
        Gdx.input.setInputProcessor(stage);
    }
    public void boar(){
        if (runcount == 0){
            gameManager.life--;
            if(gameManager.life <= 0){
                parent.changeScreen(Zork.END);
            }
            if (boarcaughtflag){
                GameManager.message = "You failed to run away and the boar caught you! What do you do? [Choices: FIGHT/RUN AWAY]";
                boarcaughtflag=false;
            }
            else{
                GameManager.message = hitStatus + " What do you do? [Choices: FIGHT/RUN AWAY]";

            }
        }
        runcount++;
    }
    @Override
    public void render(float delta) {
        elapsedTime += Gdx.graphics.getDeltaTime();

        batch.begin();
        stage.act();
        stage.draw();
        stage.setKeyboardFocus(console);
        batch.draw(background, 0, 50);
        batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, true), 120, 120);
        System.out.println(num);
        for (int i=0;i<gameManager.life;i++){
            batch.draw(heart, 20+i*50, 520);
        }

        for (int i=0;i<boar_life;i++){
            batch.draw(heart, 520+i*50, 520);
        }

        if (animation.isAnimationFinished(elapsedTime)){
            if (!justentered)
                boar();
            animation = new Animation(1f/1f, animationFrames_boarvsword[6]);
            elapsedTime=0;
            console.setDisabled(false);
        }
//        System.out.println(mappingAssistant.posx);
//        System.out.println(mappingAssistant.posy);
        font.draw(batch, gameManager.message, 10, 75);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        batch.end();
    }

    @Override
    public void show() { }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
    }
}