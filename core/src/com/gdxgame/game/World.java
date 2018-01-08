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
    private ShapeRenderer shape;
    
    public World(){
        blocks = new Rectangle[7];
        blocks[0] = new Rectangle(280, 25, 20, 100);
        blocks[1] = new Rectangle(80, 25, 100, 20);
        blocks[2] = new Rectangle(180, 25, 100, 20);
        blocks[3] = new Rectangle(60, 25, 20, 100);
        blocks[4] = new Rectangle(-100, 125, 100, 20);
        blocks[5] = new Rectangle(-120, 175, 20, 100);
        blocks[6] = new Rectangle(-120, 275, 20, 100);
        shape = new ShapeRenderer();
    }
    
    public void render(OrthographicCamera camera){
        shape.setProjectionMatrix(camera.combined);
        shape.begin(ShapeRenderer.ShapeType.Line);
        
        for (int i = 0; i < blocks.length; i++) {
            shape.rect(blocks[i].x, blocks[i].y, blocks[i].width, blocks[i].height);
        }
        
        shape.end();
    }
    
    public Rectangle[] getBlocks(){
        return blocks;
    }
    
}
