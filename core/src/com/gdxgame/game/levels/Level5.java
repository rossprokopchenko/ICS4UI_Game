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
public class Level5 extends Level {

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

    public Level5() {
        // initializes blocks
        blocks = new Rectangle[8];
        // initializes kill platforms
        killPlats = new Rectangle[21];
        // initializes the portal
        portal = new Rectangle(400, -2650, 25, 25);
        // jump boost array
        jumpBoost = new Rectangle[4];

        // spawn coordinates of the level
        this.SPAWN_X = 400;
        this.SPAWN_Y = 400;

        // level's bounds
        blocks[0] = new Rectangle(0, -2700, 20, 3200);
        blocks[1] = new Rectangle(800, -2700, 20, 3200);
        // level's blocks
        blocks[2] = new Rectangle(200, 200, 400, 20);
        blocks[3] = new Rectangle(250, -300, 20, 300);
        blocks[4] = new Rectangle(550, -300, 20, 300);
        blocks[5] = new Rectangle(20, -500, 100, 20);
        blocks[6] = new Rectangle(700, -500, 100, 20);
        blocks[7] = new Rectangle(325, -2350, 170, 20);

        // level's kill platforms
        killPlats[0] = new Rectangle(20, 0, 250, 20);
        killPlats[1] = new Rectangle(550, 0, 250, 20);
        killPlats[2] = new Rectangle(250, -500, 320, 20);
        killPlats[3] = new Rectangle(250, -850, 75, 20);
        killPlats[4] = new Rectangle(495, -850, 75, 20);
        killPlats[5] = new Rectangle(305, -1150, 20, 300);
        killPlats[6] = new Rectangle(495, -1150, 20, 300);
        killPlats[7] = new Rectangle(305, -1650, 20, 300);
        killPlats[8] = new Rectangle(495, -1650, 20, 300);
        killPlats[9] = new Rectangle(325, -1650, 170, 20);
        killPlats[10] = new Rectangle(20, -1150, 285, 20); //
        killPlats[11] = new Rectangle(515, -1150, 285, 20); //
        killPlats[12] = new Rectangle(305, -2150, 20, 300);
        killPlats[13] = new Rectangle(495, -2150, 20, 300);
        killPlats[14] = new Rectangle(20, -1950, 285, 20);
        killPlats[15] = new Rectangle(515, -1950, 285, 20);
        killPlats[16] = new Rectangle(305, -1850, 80, 20);
        killPlats[17] = new Rectangle(445, -2150, 50, 20);
        killPlats[18] = new Rectangle(325, -2680, 20, 100);
        killPlats[19] = new Rectangle(475, -2680, 20, 100);
        killPlats[20] = new Rectangle(20, -2700, 780, 20);

        // level's kill platforms
        jumpBoost[0] = new Rectangle(400, -150, 25, 25);
        jumpBoost[1] = new Rectangle(400, -1000, 25, 25);
        jumpBoost[2] = new Rectangle(150, -1900, 25, 25);
        jumpBoost[3] = new Rectangle(620, -1900, 25, 25);
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
