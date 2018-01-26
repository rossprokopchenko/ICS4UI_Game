/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdxgame.game.levels;

import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author Ross, Junaid, Kwame
 */
public class Menu extends Level {

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

    public Menu() {
        // initializes blocks
        blocks = new Rectangle[6];

        // initializes kill platforms
        killPlats = new Rectangle[1];
        // initializes the portal
        portal = new Rectangle(745, 200, 25, 25);
        // initializes jump boosts
        jumpBoost = new Rectangle[1];
        
        // spawn coordinates of the level
        this.SPAWN_X = 100;
        this.SPAWN_Y = 100;

        // create blocks
        blocks[0] = new Rectangle(-20, 0, 20, 600);
        blocks[1] = new Rectangle(800, 0, 20, 600);
        blocks[2] = new Rectangle(0, 150, 200, 20);
        blocks[3] = new Rectangle(600, 125, 200, 20);
        blocks[4] = new Rectangle(0, 0, 800, 20);
        blocks[5] = new Rectangle(360, 250, 100, 20);

        // Create a Kill Platform and a Jump Boost
        killPlats[0] = new Rectangle(600, 325, 200, 20);
        jumpBoost[0] = new Rectangle(395, 380, 20, 20);
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
     *
     * @return the blocks array length
     */
    @Override
    public int getNumBlocks() {
        return blocks.length;
    }

    /**
     * Gets the red platform array
     *
     * @return the killPlats array
     */
    @Override
    public Rectangle[] getKillPlats() {
        return killPlats;
    }

    /**
     * Gets a specific red platform in the killPlats array
     *
     * @param i the index of the platform
     * @return the red platform at the index
     */
    @Override
    public Rectangle getKillPlat(int i) {
        return killPlats[i];
    }

    /**
     * Get the amount of red platforms in the level
     *
     * @return the killPlats array length
     */
    @Override
    public int getNumKillPlats() {
        return killPlats.length;
    }

    /**
     * Get the jump boost array of the level
     *
     * @return the jumpBoosts array
     */
    @Override
    public Rectangle[] getJumpBoosts() {
        return jumpBoost;
    }

    /**
     * Get a specific jump boost block
     *
     * @param i the index of the jump boost in the array
     * @return the jump boost at the index in the array
     */
    @Override
    public Rectangle getJumpBoost(int i) {
        return jumpBoost[i];
    }

    /**
     * Get the amount of jump boosts
     *
     * @return the jumpBoost array length
     */
    @Override
    public int getNumJumpBoosts() {
        return jumpBoost.length;
    }

    /**
     * Get the end portal rectangle of the level
     *
     * @return the portal rectangle
     */
    @Override
    public Rectangle getPortal() {
        return portal;
    }

    /**
     * Get the spawn X coordinate of the level
     *
     * @return SPAWN_X
     */
    @Override
    public float getSpawnX() {
        return SPAWN_X;
    }

    /**
     * Get the spawn Y coordinate of the level
     *
     * @return SPAWN_Y
     */
    @Override
    public float getSpawnY() {
        return SPAWN_Y;
    }

    /**
     * Get the highest X coordinate of the level
     *
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
     *
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
     *
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
