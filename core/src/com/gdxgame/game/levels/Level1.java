package com.gdxgame.game.levels;

import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author prokr8056
 */
public class Level1 extends Level {

    private Rectangle[] blocks;
    private Rectangle portal;
    private final float SPAWN_X, SPAWN_Y;

    public Level1() {
        blocks = new Rectangle[4];
        portal = new Rectangle(2150, 40, 25, 25);

        this.SPAWN_X = 100;
        this.SPAWN_Y = 100;

        blocks[0] = new Rectangle(0, 0, 200, 20);
        blocks[1] = new Rectangle(600, 0, 200, 20);
        blocks[2] = new Rectangle(1200, 0, 200, 20);
        blocks[3] = new Rectangle(1800, 0, 400, 20);
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
