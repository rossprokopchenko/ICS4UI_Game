/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdxgame.game.levels;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author malij6756
 */
public class Menu extends Level {

    private Rectangle[] blocks;
    private Rectangle portal;
    private final float SPAWN_X, SPAWN_Y;

    private Rectangle[] jumpBoost;
    // array of kill platforms of the level
    private Rectangle[] killPlats;

    public Menu() {
        blocks = new Rectangle[6];
        portal = new Rectangle(745, 200, 25, 25);
        this.SPAWN_X = 100;
        this.SPAWN_Y = 100;

        // initializes kill platforms
        killPlats = new Rectangle[0];
        // initializes jump boosts
        jumpBoost = new Rectangle[0];
        // create blocks
        blocks[0] = new Rectangle(0, 0, 800, 20);
        blocks[1] = new Rectangle(600, 150, 200, 20);
        blocks[2] = new Rectangle(0, 150, 200, 20);
        blocks[3] = new Rectangle(350, 325, 100, 20);
        blocks[4] = new Rectangle(-20, 0, 20, 600);
        blocks[5] = new Rectangle(800, 0, 20, 600);

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
