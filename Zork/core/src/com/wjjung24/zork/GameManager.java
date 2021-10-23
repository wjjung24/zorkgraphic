package com.wjjung24.zork;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;

public class GameManager{
    private boolean swordflag = false;
    private boolean keyflag = false;
    private boolean axeflag = false;
    Random rand = new Random();

    Texture boar = new Texture("env/enemy/boar3.png");
    Texture sword = new Texture("env/weapons/sword3.png");
    Texture axe = new Texture("env/weapons/axe3.png");
    Texture apple = new Texture("env/food/apple3.png");
    Texture meat = new Texture("env/food/meat3.png");
    Texture key = new Texture("env/key3.png");

    Texture blank = new Texture("env/blank.png");

    private int posx;
    private int posy;
    public void retrieve(int x, int y){
        this.posx = x;
        this.posy = y;
    }

    Texture env = blank;
    public void generator(){
        int num = rand.nextInt(100);
        if (posx !=3 && posy!=3){
            if (num<5 && !keyflag){
                env = key;
                keyflag = true;
            }
            else if (num > 5 && num <=30){
                if(rand.nextInt(100)<70){
                    env = apple;
                }
                else{
                    env = meat;
                }
            }
            else if (num > 30 && num <= 55){
                env = boar;
            }
            else if (num > 55 && num < 75){
                if (rand.nextInt(100)<50 && !swordflag){
                    env = sword;
                    swordflag = true;
                }
                else if (rand.nextInt(100)<50 && !axeflag){
                    env = axe;
                    axeflag = true;
                }
            }
        }
        else{
            env = blank;
        }
    }
}
