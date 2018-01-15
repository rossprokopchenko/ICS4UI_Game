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
        blocks = new Rectangle[4];
        // initializes the portal
        portal = new Rectangle(2350, 40, 25, 25);
        // spawn coordinates of the level
        this.SPAWN_X = 100;
        this.SPAWN_Y = 100;
        
        // level's blocks
        blocks[0] = new Rectangle(0, 0, 200, 20);
        blocks[1] = new Rectangle(600, 0, 400, 20);
        blocks[2] = new Rectangle(1400, 0, 400, 20);
        blocks[3] = new Rectangle(2200, 0, 400, 20);
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
}
