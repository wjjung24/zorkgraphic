package com.wjjung24.zork;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.List;
import com.badlogic.gdx.graphics.Texture;

public class GameManager{
    private static boolean swordflag = false;
    private static boolean axeflag = false;
    static Random rand = new Random();

    ArrayList<String> inventory = new ArrayList();
    HashMap<String, Texture> itemsMatch = new HashMap<String, Texture>();

    final int SWORD = 0;
    final int AXE = 1;
    final int NONE = 2;

    static int charWeapon = 2;
    static boolean hasKey = false;

    static int life = 5;

    static Texture boar = new Texture("env/enemy/boar3.png");
    static Texture sword = new Texture("env/weapons/sword3.png");
    static Texture axe = new Texture("env/weapons/axe3.png");
    static Texture apple = new Texture("env/food/apple3.png");
    static Texture meat = new Texture("env/food/meat3.png");
    static Texture key = new Texture("env/key3.png");

    static Texture blank = new Texture("env/blank.png");

    static String message = "";

    static int num;
    static int tmpnum;
    static Texture env = blank;
    public static void generator(){
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
        }
        else if (num > 5 && num <=25){
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
        else if (num > 25 && num <= 60){
            message = "A wild boar appeared! What do you do? [Choices: FIGHT/RUN AWAY]";
            env = boar;
        }
        else if (num > 60 && num < 80){
            if (tmpnum>50 && !swordflag){
                message = "You found a mystical sword! What do you do? [Choices: PICK UP SWORD]";
                env = sword;
                swordflag = true;
            }
            else if (tmpnum<50 && !axeflag){
                message = "You found a viking's axe! What do you do? [Choices: PICK UP AXE]";
                env = axe;
                axeflag = true;
            }
            else{
                message = "";
                env = blank;
            }
        }
        else{
            message = "";
            env = blank;
        }
    }
}
