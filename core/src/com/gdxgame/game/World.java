package com.gdxgame.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import com.gdxgame.game.levels.Level;
import com.gdxgame.game.levels.Level1;
import com.gdxgame.game.levels.Level2;
import com.gdxgame.game.levels.Level3;
import com.gdxgame.game.levels.Menu;
import com.gdxgame.game.levels.Level4;
import com.gdxgame.game.levels.Level5;

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

    // portal animation variables
    private Animation<TextureRegion> portalTurn;
    private TextureAtlas atlas;
    private float elapsed;

    /**
     * World constructor
     */
    public World() {
        // set the current level to the first
        currentLevel = 0;
        // initializes the levels array
        levels = new Array();
        // adds all levels created to the array
        levels.add(new Menu());
        levels.add(new Level1());
        levels.add(new Level2());
        levels.add(new Level3());
        levels.add(new Level4());
        levels.add(new Level5());

        // load in the portal frames
        atlas = new TextureAtlas("portalPics.atlas");
        portalTurn = new Animation(1f / 10f, atlas.findRegions("frame"));
        // get the portal Rectangle of the current level
        portal = levels.get(currentLevel).getPortal();

        // initializes the shape renderer
        shape = new ShapeRenderer();
    }

    /**
     * Render all shapes
     * @param camera the camera that will show the rendered shapes
     */
    public void render(OrthographicCamera camera) {
        // BLOCKS
        // render the shapes according to the camera
        shape.setProjectionMatrix(camera.combined);
        // set the shape type
        shape.begin(ShapeRenderer.ShapeType.Line);

        // renders all green blocks of the level
        for (int i = 0; i < levels.get(this.currentLevel).getNumBlocks(); i++) {

            // set the color of shapes
            if (i > 1) {
                shape.setColor(Color.CHARTREUSE);
            } else {
                shape.setColor(Color.CORAL);
            }

            // x, y, width, height of each block in the level
            float x = levels.get(this.currentLevel).getBlock(i).x;
            float y = levels.get(this.currentLevel).getBlock(i).y;
            float width = levels.get(this.currentLevel).getBlock(i).width;
            float height = levels.get(this.currentLevel).getBlock(i).height;

            // draw the block
            shape.rect(x, y, width, height);

        }

        // renders all red blocks of the level
        for (int i = 0; i < levels.get(this.currentLevel).getNumKillPlats(); i++) {
            // set the color of the blocks
            shape.setColor(Color.RED);

            // x, y, width, height of each block in the level
            float x = levels.get(this.currentLevel).getKillPlat(i).x;
            float y = levels.get(this.currentLevel).getKillPlat(i).y;
            float width = levels.get(this.currentLevel).getKillPlat(i).width;
            float height = levels.get(this.currentLevel).getKillPlat(i).height;

            // draw the block
            shape.rect(x, y, width, height);
        }

        // render all jump boosts of the level
        for (int i = 0; i < levels.get(this.currentLevel).getNumJumpBoosts(); i++) {
            // set the color of the jump boost block
            shape.setColor(Color.GOLD);

            // x, y, width, height of each block in the level
            float x = levels.get(this.currentLevel).getJumpBoost(i).x;
            float y = levels.get(this.currentLevel).getJumpBoost(i).y;
            float width = levels.get(this.currentLevel).getJumpBoost(i).width;
            float height = levels.get(this.currentLevel).getJumpBoost(i).height;

            // draw the block
            shape.rect(x, y, width, height);
        }

        // PORTAL
        // sets the color of the portal
        shape.setColor(Color.CLEAR);
        // draws the portal
        shape.rect(portal.x, portal.y, portal.width, portal.height);

        // get the portal Rectangle of the current level
        portal = levels.get(this.currentLevel).getPortal();

        // end drawing shapes
        shape.end();
    }

    /**
     * Render the portal key frames
     * @param batch the spritebatch to use
     */
    public void render(SpriteBatch batch) {
        batch.draw(portalTurn.getKeyFrame(this.elapsed), portal.x - 20, portal.y - 20, 70, 70);
    }

    /**
     * Update the elapsed
     * @param deltaTime 
     */
    public void update(float deltaTime) {
        if (this.elapsed < 0.8) {
            this.elapsed = deltaTime + this.elapsed;
        } else {
            this.elapsed = 0;
        }
    }

    /**
     * Return a green block of the level
     * @param i index of the block according to the block array of the level
     * @return the block
     */
    public Rectangle[] getBlocks(int i) {
        return levels.get(i).getBlocks();
    }

    /**
     * Get the amount of levels added to the level
     * @return the size of the level array
     */
    public int getNumLevels() {
        return levels.size;
    }

    /**
     * Get the level array created
     * @return the level array
     */
    public Array<Level> getLevels() {
        return levels;
    }

    /**
     * Get the current level that the player is playing
     * @return the current level variable
     */
    public int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Set the current level
     * @param currentLevel the level to set to
     */
    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    /**
     * Get the portal rectangle
     * @return the portal rectangle
     */
    public Rectangle getPortal() {
        return portal;
    }

    /**
     * Dispose of the atlas
     */
    public void dispose() {
        atlas.dispose();
    }
}
