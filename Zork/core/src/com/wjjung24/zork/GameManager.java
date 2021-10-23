package com.wjjung24.zork;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;

public class GameManager{
    private boolean swordflag = false;
    private boolean keyflag = false;
    private boolean axeflag = false;
    Random rand = new Random();

    final int SWORD = 0;
    final int AXE = 1;
    final int NONE = 2;

    int charWeapon = 2;
    boolean hasKey = false;

    Texture boar = new Texture("env/enemy/boar3.png");
    Texture sword = new Texture("env/weapons/sword3.png");
    Texture axe = new Texture("env/weapons/axe3.png");
    Texture apple = new Texture("env/food/apple3.png");
    Texture meat = new Texture("env/food/meat3.png");
    Texture key = new Texture("env/key3.png");

    Texture blank = new Texture("env/blank.png");

    String message = "";
    mapper mappingAssistant = new mapper();

    int num;
    int tmpnum;
    Texture env = blank;
    public void generator(){
        tmpnum = rand.nextInt(100);
        if (mapper.posx ==3 && mapper.posy==3){
            num = 100;
        }
        else{
            num = rand.nextInt(100);
        }
        if (num<5 && !hasKey){
            message = "YOU FOUND THE MAGICAL KEY! [Choices: PICK UP KEY]";
            env = key;
            keyflag = true;
        }
        else if (num > 5 && num <=30){
            if(tmpnum<70){
                message = "You found an apple! What do you do? [Choices: EAT]";

                env = apple;
//                    env = boar;
            }
            else{
                message = "You found some meat! What do you do? [Choices: EAT]";

                env = meat;
//                    env = boar;
            }
        }
        else if (num > 30 && num <= 55){
            message = "A wild boar appeared! What do you do? [Choices: FIGHT/RUN AWAY]";
            env = boar;
        }
        else if (num > 55 && num < 75){
            if (tmpnum>50){
                message = "You found a mystical sword! What do you do? [Choices: PICK UP SWORD]";
                env = sword;
            }
            else if (tmpnum<50){
                message = "You found a viking's axe! What do you do? [Choices: PICK UP AXE]";
                env = axe;
            }
        }
        else{
            message = "";
            env = blank;
        }
    }
}
