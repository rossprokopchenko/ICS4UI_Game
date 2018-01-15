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
 * @author Ross, Kwame, Junaid
 */
public class World {

    // end portal of the level
    private Rectangle portal;
    // shape renderer
    private ShapeRenderer shape;
    // array of levels
    private Array<Level> levels;
    // current level number
    private int currentLevel;

    public World() {
        // set the current level to the first
        currentLevel = 0;
        // initializes the levels array
        this.levels = new Array();

        // adds all levels created to the array
        this.levels.add(new Level1());
        this.levels.add(new Level2());

        // initializes the shape renderer
        this.shape = new ShapeRenderer();
        
        // get the portal Rectangle of the current level
        portal = levels.get(this.currentLevel).getPortal();

    }

    public void render(OrthographicCamera camera) {
        // BLOCKS
        
        // render the shapes according to the camera
        shape.setProjectionMatrix(camera.combined);
        // set the color of shapes
        shape.setColor(Color.CHARTREUSE);
        // set the shape type
        shape.begin(ShapeRenderer.ShapeType.Line);

        // renders all shapes of the level
        for (int i = 0; i < levels.get(this.currentLevel).getNumBlocks(); i++) {
            float x = levels.get(this.currentLevel).getBlock(i).x;
            float y = levels.get(this.currentLevel).getBlock(i).y;
            float width = levels.get(this.currentLevel).getBlock(i).width;
            float height = levels.get(this.currentLevel).getBlock(i).height;

            shape.rect(x, y, width, height);

        }
        
        // PORTAL
        // sets the color of the portal
        shape.setColor(Color.RED);
        // draws the portal
        shape.rect(portal.x, portal.y, portal.width, portal.height);
        
        // get the portal Rectangle of the current level
        portal = levels.get(this.currentLevel).getPortal();
      
        // end drawing shapes
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
