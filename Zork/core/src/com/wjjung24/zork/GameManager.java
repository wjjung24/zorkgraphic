package com.wjjung24.zork;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.List;
import com.badlogic.gdx.graphics.Texture;

public class GameManager{
    static Random rand = new Random();

    static ArrayList<String> inventory = new ArrayList(){
        {
            add("Bare Hand");
        }
    };

    static weapons Sword = new weapons(new Texture("env/weapons/sword3.png"), "This is a magical sword. \nChance of Critical Hit Increased 10%, \nChance of Miss Increased 10%");
    static weapons Axe = new weapons(new Texture("env/weapons/axe3.png"), "This is a viking's axe. \nChance of Critical Hit Increased 5%, \nChance of Miss Decreased 5%");
    static weapons bareHand = new weapons(new Texture("env/weapons/hand.png"), "You don't have a weapon. \nChance of Critical Hit Low, \nChance of Miss Increased High");
    static weapons Key = new weapons(new Texture("env/key3.png"), "This is the magical key.\nTake it to the treasure room!");

    static HashMap<String, weapons> itemsMatch = new HashMap<String, weapons>(){{
        put("Sword", Sword);
        put("Axe", Axe);
        put("Bare Hand", bareHand);
        put("Key", Key);
    }};


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
        if (num<10 && !inventory.contains("Key")){
            message = "YOU FOUND THE MAGICAL KEY! [Choices: PICK UP KEY]";
            env = key;
        }
        else if (num > 10 && num <=35){
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
        else if (num > 35 && num <= 60){
            message = "A wild boar appeared! What do you do? [Choices: FIGHT/RUN AWAY]";
            env = boar;
        }
        else if (num > 60 && num < 90){
            if (tmpnum>50 && !GameManager.inventory.contains("Sword")){
                message = "You found a mystical sword! What do you do? [Choices: PICK UP SWORD]";
                env = sword;
            }
            else if (tmpnum<50 && !GameManager.inventory.contains("Axe")){
                message = "You found a viking's axe! What do you do? [Choices: PICK UP AXE]";
                env = axe;
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
