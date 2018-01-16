package com.gdxgame.game.levels;

import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author prokr8056
 */
public class Level1 extends Level {

    // array of blocks of the level
    private Rectangle[] blocks;
    
    // portal of the level
    private Rectangle portal;
    // spawn coordinates of the level
    private final float SPAWN_X, SPAWN_Y;

    public Level1() {
        // initializes blocks
        blocks = new Rectangle[6];
        // initializes the portal
        portal = new Rectangle(2550, 40, 25, 25);
        // spawn coordinates of the level
        this.SPAWN_X = 100;
        this.SPAWN_Y = 100;
        
        // level's bounds
        blocks[0] = new Rectangle(-20, 0, 20, 500);
        blocks[1] = new Rectangle(2600, 0, 20, 500);
        // level's blocks
        blocks[2] = new Rectangle(0, 0, 200, 20);
        blocks[3] = new Rectangle(600, 0, 400, 20);
        blocks[4] = new Rectangle(1400, 0, 400, 20);
        blocks[5] = new Rectangle(2200, 0, 400, 20);
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
            if(x < blocks[i].x){
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
