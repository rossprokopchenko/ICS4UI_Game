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

    private Rectangle[] blocks;
    private Rectangle[] killPlats;
    private Rectangle[] jumpBoost;
    private Rectangle portal;
    private final float SPAWN_X, SPAWN_Y;

    public Level5() {
        blocks = new Rectangle[8];
        portal = new Rectangle(400, -2650, 25, 25);
        killPlats = new Rectangle[19];
        jumpBoost = new Rectangle[3];

        this.SPAWN_X = 400;
        this.SPAWN_Y = 400;

        blocks[0] = new Rectangle(0, -2700, 20, 3200);
        blocks[1] = new Rectangle(800, -2700, 20, 3200);
        blocks[2] = new Rectangle(200, 200, 400, 20);
        blocks[3] = new Rectangle(250, -300, 20, 300);
        blocks[4] = new Rectangle(550, -300, 20, 300);
        blocks[5] = new Rectangle(20, -500, 100, 20);
        blocks[6] = new Rectangle(700, -500, 100, 20);
        blocks[7] = new Rectangle(325, -2350, 170, 20);

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
        killPlats[10] = new Rectangle(305, -2150, 20, 300);
        killPlats[11] = new Rectangle(495, -2150, 20, 300);
        killPlats[12] = new Rectangle(20, -1950, 285, 20);
        killPlats[13] = new Rectangle(515, -1950, 285, 20);
        killPlats[14] = new Rectangle(305, -1850, 80, 20);
        killPlats[15] = new Rectangle(445, -2150, 50, 20);
        killPlats[16] = new Rectangle(325, -2680, 20, 100);
        killPlats[17] = new Rectangle(475, -2680, 20, 100);
        killPlats[18] = new Rectangle(20, -2700, 780, 20);

        jumpBoost[0] = new Rectangle(400, -150, 25, 25);
        jumpBoost[1] = new Rectangle(400, -1000, 25, 25);
        jumpBoost[2] = new Rectangle(620, -1900, 25, 25);
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
            if (x < blocks[i].x + blocks[i].width) {
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
