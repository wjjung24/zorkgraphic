package com.wjjung24.zork;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Objects;

public class mapper {
    static Texture middle_tiles = new Texture("gamemap/Open_4.png");
    static Texture edge_tiles_1 = new Texture("gamemap/Open_3_1.png");
    static Texture edge_tiles_2 = new Texture("gamemap/Open_3_2.png");
    static Texture edge_tiles_3 = new Texture("gamemap/Open_3_3.png");
    static Texture edge_tiles_4 = new Texture("gamemap/Open_3_4.png");
    static Texture corner_tiles_1 = new Texture("gamemap/Open_2_1.png");
    static Texture corner_tiles_2 = new Texture("gamemap/Open_2_2.png");
    static Texture corner_tiles_3 = new Texture("gamemap/Open_2_3.png");
    static Texture corner_tiles_4 = new Texture("gamemap/Open_2_4.png");
    static Texture exit_tile = new Texture("gamemap/Final.png");


    protected static int posx = 1;
    protected static int posy = 1;


    public static boolean check(String movement){
        if (posx==1 && movement.equals("LEFT")){
            return false;
        }
        else if (posx==5 && movement.equals("RIGHT")){
            return false;
        }
        else if (posy==1 && movement.equals("DOWN")){
            return false;
        }
        else if (posy==5 && movement.equals("UP")){
            return false;
        }
        else {
            return true;
        }
    }

    public static void update(String movement){

        switch(movement){
            case "UP":
                posy++;
                break;
            case "DOWN":
                posy--;
                break;
            case "LEFT":
                posx--;
                break;
            case "RIGHT":
                posx++;
        }
    }
    static Texture gamemap = corner_tiles_3;
    public static void drawmap(){
        if (posx==3 && posy==3){
            gamemap = exit_tile;
        }
        else if(posx==1 && posy==1){
            gamemap = corner_tiles_3;
        }
        else if(posx==1 && posy==5){
            gamemap = corner_tiles_4;
        }
        else if (posx==5 && posy==1){
            gamemap = corner_tiles_2;
        }
        else if (posx==5 && posy==5){
            gamemap = corner_tiles_1;
        }
        else if (posx==1){
            gamemap = edge_tiles_1;
        }
        else if (posx==5){
            gamemap = edge_tiles_2;
        }
        else if (posy==1){
            gamemap = edge_tiles_4;
        }
        else if (posy==5){
            gamemap = edge_tiles_3;
        }
        else{
            gamemap = middle_tiles;
        }

    }

}
