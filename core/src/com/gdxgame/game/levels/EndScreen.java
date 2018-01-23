/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdxgame.game.levels;

import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author kwame
 */
public class EndScreen extends Level {

    // array of blocks of the level
    private Rectangle[] blocks;

    // array of kill platforms of the level
    private Rectangle[] killPlats;

    // portal of the level
    private Rectangle portal;

    private Rectangle[] jumpBoost;

    // spawn coordinates of the level
    private final float SPAWN_X, SPAWN_Y;

    public EndScreen() {
        // initializes blocks
        blocks = new Rectangle[5];
        // initializes kill platforms
        killPlats = new Rectangle[0];
        // initializes the portal
        portal = new Rectangle(775, 220, 25, 25);

        jumpBoost = new Rectangle[0];

        // spawn coordinates of the level
        this.SPAWN_X = 375;
        this.SPAWN_Y = 20;

        // level's bounds
        blocks[0] = new Rectangle(-20, 0, 20, 600);
        blocks[1] = new Rectangle(800, 0, 20, 600);
        // level's blocks
        blocks[2] = new Rectangle(0, 0, 800, 20);
        blocks[3] = new Rectangle(0, 200, 100, 20);
        blocks[4] = new Rectangle(700, 200, 100, 20);

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
    public Rectangle[] getJumpBoosts() {
        return jumpBoost;
    }

    @Override
    public Rectangle getJumpBoost(int i) {
        return jumpBoost[i];
    }

    @Override
    public int getNumJumpBoosts() {
        return jumpBoost.length;
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
            if (x < blocks[i].x) {
                x = blocks[i].x + blocks[i].width;
            }
        }

        return x;
    }

    @Override
    public float getLowestY() {
        float y = 0;

        for (int i = 2; i < blocks.length; i++) {
            if (y < blocks[i].y) {

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
            if (y < blocks[i].y) {
                y = blocks[i].y;
            }
        }

        return y;
    }

}
