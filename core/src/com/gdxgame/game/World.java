package com.gdxgame.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import com.gdxgame.game.levels.Level;
import com.gdxgame.game.levels.Level1;
import com.gdxgame.game.levels.Level2;

/**
 *
 * @author prokr8056
 */
public class World {

    private Rectangle[] blocks;
    private Rectangle portal;
    private ShapeRenderer shape;
    private Level level;
    private Array<Level> levels;
    private int currentLevel;

    public World() {
        currentLevel = 0;
        this.levels = new Array();

        this.levels.add(new Level1());
        this.levels.add(new Level2());

        this.shape = new ShapeRenderer();
        
        portal = levels.get(this.currentLevel).getPortal();

    }

    public void render(OrthographicCamera camera) {
        shape.setProjectionMatrix(camera.combined);
        shape.setColor(Color.CHARTREUSE);
        shape.begin(ShapeRenderer.ShapeType.Line);

        for (int i = 0; i < levels.get(this.currentLevel).getNumBlocks(); i++) {
            float x = levels.get(this.currentLevel).getBlock(i).x;
            float y = levels.get(this.currentLevel).getBlock(i).y;
            float width = levels.get(this.currentLevel).getBlock(i).width;
            float height = levels.get(this.currentLevel).getBlock(i).height;

            shape.rect(x, y, width, height);

        }
        
        shape.setColor(Color.RED);
        shape.rect(portal.x, portal.y, portal.width, portal.height);
        
        portal = levels.get(this.currentLevel).getPortal();
        
        shape.end();
    }

    public Rectangle[] getBlocks(int i) {
        return levels.get(i).getBlocks();
    }

    public int getNumLevels() {
        return levels.size;
    }

    public Array<Level> getLevels() {
        return levels;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
    
    public Rectangle getPortal(){
        return portal;
    }
}
