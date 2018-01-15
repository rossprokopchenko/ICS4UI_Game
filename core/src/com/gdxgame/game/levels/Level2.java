package com.gdxgame.game.levels;

import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author prokr8056
 */
public class Level2 extends Level {

    private Rectangle[] blocks;
    private Rectangle portal;
    private final float SPAWN_X, SPAWN_Y;

    public Level2() {
        blocks = new Rectangle[2];
        portal = new Rectangle(0, 0, 25, 25);

        this.SPAWN_X = 100;
        this.SPAWN_Y = 400;

        blocks[0] = new Rectangle(-100, 25, 400, 20);
        blocks[1] = new Rectangle(250, 25, 20, 100);
    }

    @Override
    public Rectangle[] getBlocks() {
        return blocks;
    }

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
