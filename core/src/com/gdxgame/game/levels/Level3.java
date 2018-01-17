/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdxgame.game.levels;

import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author prokr8056
 */
public class Level3 extends Level{
    
    private Rectangle[] blocks;
    
    private Rectangle[] killPlats;
    private Rectangle portal;
    private final float SPAWN_X, SPAWN_Y;

    public Level3() {
        blocks = new Rectangle[8];
        portal = new Rectangle(1925, -285, 25, 25);
        killPlats = new Rectangle[2];
        
        this.SPAWN_X = 100;
        this.SPAWN_Y = 400;
        
        // level's bounds
        blocks[0] = new Rectangle(-20, 300, 20, 500);
        blocks[1] = new Rectangle(1975, -480, 20, 700);
        // level's blocks
        blocks[2] = new Rectangle(0, 300, 400, 20);
        blocks[3] = new Rectangle(700, -480, 20, 780);
        blocks[4] = new Rectangle(900, -200, 20, 774);
        blocks[5] = new Rectangle(825, 0, 75, 20);
        blocks[6] = new Rectangle(1050, -325, 200, 20);
        blocks[7] = new Rectangle(1775, -325, 200, 20);
        
        // kil plts
        killPlats[0] = new Rectangle(400, 300, 320, 21);
        killPlats[1] = new Rectangle(720, -480, 1255, 20);
    }

    /**
     * Gets the blocks array of the level
     * @return the blocks array
     */
    @Override
    public Rectangle[] getBlocks() {
        return blocks;
    }

    /**
     * Gets a specific block in the blocks array
     * @param i the block's number
     * @return returns the block
     */
    @Override
    public Rectangle getBlock(int i) {
        return blocks[i];
    }

    @Override
    public int getNumBlocks() {
        return blocks.length;
    }
    
    @Override
    public Rectangle[] getKillPlats() {
        return killPlats;
    }

    @Override
    public Rectangle getKillPlat(int i) {
        return killPlats[i];
    }

    @Override
    public int getNumKillPlats() {
        return killPlats.length;
    }

    @Override
    public Rectangle getPortal() {
        return portal;
    }

    @Override
    public float getSpawnX() {
        return SPAWN_X;
    }

    @Override
    public float getSpawnY() {
        return SPAWN_Y;
    }
    
    @Override
    public float getHighestX() {
        float x = 0;
        
        for (int i = 2; i < blocks.length; i++) {
            if(x < blocks[i].x + blocks[i].width){
                x = blocks[i].x + blocks[i].width;
            }
        }
        
        return x;
    }

    @Override
    public float getLowestY() {
        float y = 0;
        
        for (int i = 2; i < blocks.length; i++) {
            if(y < blocks[i].y){
                
            } else {
                y = blocks[i].y - blocks[i].height;
            }
        }
        
        return y;
    }

    @Override
    public float getHighestY() {
        float y = 0;
        
        for (int i = 2; i < blocks.length; i++) {
            if(y < blocks[i].y){
                y = blocks[i].y;
            }
        }
        
        return y;
    }
}
