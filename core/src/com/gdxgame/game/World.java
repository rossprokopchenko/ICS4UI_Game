package com.gdxgame.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author prokr8056
 */
public class World {

    private Rectangle[] blocks;
    private Rectangle[] badBlocks;
    private ShapeRenderer shape;

    public World() {
        // Create Blocks
        blocks = new Rectangle[7];
        blocks[0] = new Rectangle(0, 1000, 200, 20);
        blocks[1] = new Rectangle(350, 25, 200, 20);
        blocks[2] = new Rectangle(670, 25, 100, 20);
        blocks[3] = new Rectangle(900, 25, 100, 20);
        blocks[4] = new Rectangle();
        blocks[5] = new Rectangle();
        blocks[6] = new Rectangle();
        shape = new ShapeRenderer();
    }

    public void render(OrthographicCamera camera) {
        shape.setProjectionMatrix(camera.combined);
        shape.begin(ShapeRenderer.ShapeType.Line);
        // Follow the player
        for (int i = 0; i < blocks.length; i++) {
            shape.rect(blocks[i].x, blocks[i].y, blocks[i].width, blocks[i].height);
        }

        shape.end();
    }

    public Rectangle[] getBlocks() {
        return blocks;
    }

}
