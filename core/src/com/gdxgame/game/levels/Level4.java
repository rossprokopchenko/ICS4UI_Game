package com.gdxgame.game.levels;

import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author bonsk5852
 */
public class Level4 extends Level {

    // array of blocks of the level
    private Rectangle[] blocks;
    // array of kill platforms of the level
    private Rectangle[] killPlats;
    // array of Jump boosts
    private Rectangle[] jumpBoost;
    // portal of the level
    private Rectangle portal;
    // spawn coordinates of the level
    private final float SPAWN_X, SPAWN_Y;

    public Level4() {
        // initializes blocks
        blocks = new Rectangle[19];
        // initializes kill platforms
        killPlats = new Rectangle[10];
        // initializes the portal
        portal = new Rectangle(1345, 950, 25, 25);
        // jump boost array
        jumpBoost = new Rectangle[1];
        
        // spawn coordinates of the level
        this.SPAWN_X = -20;
        this.SPAWN_Y = 430;
        
        // Level bounds
        blocks[0] = new Rectangle(-40, 420, 20, 400);
        blocks[1] = new Rectangle(1300, 900, 20, 400);
        // level's blocks
        blocks[2] = new Rectangle(-20, 400, 400, 20);
        blocks[3] = new Rectangle(790, -50, 40, 20);
        blocks[4] = new Rectangle(990, 0, 40, 20);
        blocks[5] = new Rectangle(1190, 50, 40, 20);
        blocks[6] = new Rectangle(1400, 100, 400, 20);
        blocks[7] = new Rectangle(2000, 420, 20, 190);
        blocks[8] = new Rectangle(1990, 200, 40, 20);
        blocks[9] = new Rectangle(2100, 150, 20, 20);
        blocks[10] = new Rectangle(2200, 100, 20, 20);
        blocks[11] = new Rectangle(2300, 50, 20, 20);
        blocks[12] = new Rectangle(2400, 0, 100, 20);
        blocks[13] = new Rectangle(2300, 200, 20, 600);
        blocks[14] = new Rectangle(2600, 0, 20, 800);
        blocks[15] = new Rectangle(2320, 200, 20, 20);
        blocks[16] = new Rectangle(2580, 400, 20, 20);
        blocks[17] = new Rectangle(2320, 620, 20, 20);
        blocks[18] = new Rectangle(1320, 900, 200, 20);

        // Kill platforms
        killPlats[0] = new Rectangle(380, -70, 20, 490);
        killPlats[1] = new Rectangle(380, -90, 1020, 20);
        killPlats[2] = new Rectangle(1400, -90, 20, 190);
        killPlats[3] = new Rectangle(1780, 20, 20, 80);
        killPlats[4] = new Rectangle(1780, 0, 620, 20);
        killPlats[5] = new Rectangle(2500, 0, 100, 20);
        killPlats[6] = new Rectangle(2600, 800, 20, 300);
        killPlats[7] = new Rectangle(1500, 580, 20, 320);
        killPlats[8] = new Rectangle(1520, 580, 480, 20);
        killPlats[9] = new Rectangle(2000, 400, 300, 20);

        // Jump boosts
        jumpBoost[0] = new Rectangle(1720, 750, 25, 25);
    }

    /**
     * Gets the blocks array of the level
     *
     * @return the blocks array
     */
    @Override
    public Rectangle[] getBlocks() {
        return blocks;
    }

    /**
     * Gets a specific block in the blocks array
     *
     * @param i the block's number
     * @return returns the block
     */
    @Override
    public Rectangle getBlock(int i) {
        return blocks[i];
    }

    /**
     * Get the number of green blocks in the level
     * @return the blocks array length
     */
    @Override
    public int getNumBlocks() {
        return blocks.length;
    }

    /**
     * Gets the red platform array
     * @return the killPlats array 
     */
    @Override
    public Rectangle[] getKillPlats() {
        return killPlats;
    }

    /**
     * Gets a specific red platform in the killPlats array
     * @param i the index of the platform
     * @return the red platform at the index
     */
    @Override
    public Rectangle getKillPlat(int i) {
        return killPlats[i];
    }

    /**
     * Get the amount of red platforms in the level
     * @return the killPlats array length
     */
    @Override
    public int getNumKillPlats() {
        return killPlats.length;
    }

    /**
     * Get the jump boost array of the level
     * @return the jumpBoosts array
     */
    @Override
    public Rectangle[] getJumpBoosts() {
        return jumpBoost;
    }

    /**
     * Get a specific jump boost block
     * @param i the index of the jump boost in the array
     * @return the jump boost at the index in the array
     */
    @Override
    public Rectangle getJumpBoost(int i) {
        return jumpBoost[i];
    }

    /**
     * Get the amount of jump boosts
     * @return the jumpBoost array length
     */
    @Override
    public int getNumJumpBoosts() {
        return jumpBoost.length;
    }

    /**
     * Get the end portal rectangle of the level
     * @return the portal rectangle
     */
    @Override
    public Rectangle getPortal() {
        return portal;
    }

    /**
     * Get the spawn X coordinate of the level
     * @return SPAWN_X
     */
    @Override
    public float getSpawnX() {
        return SPAWN_X;
    }

    /**
     * Get the spawn Y coordinate of the level
     * @return SPAWN_Y
     */
    @Override
    public float getSpawnY() {
        return SPAWN_Y;
    }

    /**
     * Get the highest X coordinate of the level
     * @return the calculated highest X coordinate
     */
    @Override
    public float getHighestX() {
        float x = 0;

        // scan through all the green blocks of the level
        for (int i = 2; i < blocks.length; i++) {
            // get the highest X + WIDTH value
            if (x < blocks[i].x) {
                x = blocks[i].x + blocks[i].width;
            }
        }

        return x;
    }

    /**
     * Get the lowest Y coordinate of the level
     * @return the calculated lowest Y coordinate
     */
    @Override
    public float getLowestY() {
        float y = 0;
        
        // scan through all the green blocks of the level
        for (int i = 2; i < blocks.length; i++) {
            // if Y is lower than the Y of the block - do nothing
            if (y < blocks[i].y) {

            } else {
                // set the lowest Y value
                y = blocks[i].y - blocks[i].height;
            }
        }

        return y;
    }

    /**
     * Get the highest Y coordinate of the level
     * @return the calculated highest Y coordinate
     */
    @Override
    public float getHighestY() {
        float y = 0;

        // scan through all the green blocks of the level
        for (int i = 2; i < blocks.length; i++) {
            // calculates the highest Y value of the level
            if (y < blocks[i].y) {
                y = blocks[i].y;
            }
        }

        return y;
    }
}
