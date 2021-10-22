package com.wjjung24.zork;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class mapper {
    Texture middle_tiles = new Texture("gamemap/Open_4.png");
    Texture edge_tiles_1 = new Texture("gamemap/Open_3_1.png");
    Texture edge_tiles_2 = new Texture("gamemap/Open_3_2.png");
    Texture edge_tiles_3 = new Texture("gamemap/Open_3_3.png");
    Texture edge_tiles_4 = new Texture("gamemap/Open_3_4.png");
    Texture corner_tiles_1 = new Texture("gamemap/Open_2_1.png");
    Texture corner_tiles_2 = new Texture("gamemap/Open_2_2.png");
    Texture corner_tiles_3 = new Texture("gamemap/Open_2_3.png");
    Texture corner_tiles_4 = new Texture("gamemap/Open_2_4.png");
    Texture exit_tile = new Texture("gamemap/Final.png");

    private int posx = 1;
    private int posy = 1;

    public boolean check(int x, int y){
        if (x==1 || x==5 || y==1 || y==5){
            return false;
        }
        else {
            return true;
        }
    }

    public void update(String movement){
        switch(movement){
            case "UP":
                posy++;
            case "DOWN":
                posy--;
            case "LEFT":
                posx--;
            case "RIGHT":
                posx++;
        }
    }
    public Texture drawmap(){
        if (posx==3 && posy==3){
            return exit_tile;
        }
        else if(posx==1 && posy==1){
            return corner_tiles_3;
        }
        else if(posx==1 && posy==5){
            return corner_tiles_4;
        }
        else if (posx==5 && posy==1){
            return corner_tiles_2;
        }
        else if (posx==5 && posy==5){
            return corner_tiles_1;
        }
        else if (posx==1){
            return edge_tiles_1;
        }
        else if (posx==5){
            return edge_tiles_2;
        }
        else if (posy==1){
            return edge_tiles_4;
        }
        else if (posy==5){
            return edge_tiles_3;
        }
        else{
            return middle_tiles;
        }

    }

}
