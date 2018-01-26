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
    
    // array of blocks of the level
    private Rectangle[] blocks;
    // array of kill platforms of the level
    private Rectangle[] killPlats;
    // portal of the level
    private Rectangle portal;
    // jump boost array of the level
    private Rectangle[] jumpBoost;
    // spawn coordinates of the level
    private final float SPAWN_X, SPAWN_Y;

    public Level3() {
        // initializes blocks
        blocks = new Rectangle[8];
        // initializes kill platforms
        killPlats = new Rectangle[2];
        // initializes the portal
        portal = new Rectangle(1915, -275, 25, 25);
        // initializes the jump boost array
        jumpBoost = new Rectangle[0];
        
        // spawn coordinates of the level
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
        
        // level's kill platforms
        killPlats[0] = new Rectangle(400, 300, 320, 21);
        killPlats[1] = new Rectangle(720, -480, 1255, 20);
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
