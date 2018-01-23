package com.gdxgame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.gdxgame.game.levels.EndScreen;

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
   
    private Animation<TextureRegion> portalTurn;
    
    private TextureAtlas atlas;
    
    private float elapsed;

    

    public World() {
        // set the current level to the first
        currentLevel = 0;
        // initializes the levels array
        this.levels = new Array();
        // adds all levels created to the array
        this.levels.add(new Menu());
        this.levels.add(new Level1());
        this.levels.add(new Level2());
        this.levels.add(new Level3());

        
        
        this.atlas = new TextureAtlas("packed/portalPics.atlas");
        
        portalTurn = new Animation(1f / 10f, atlas.findRegions("frame"));
        
        this.levels.add(new Level4());
        this.levels.add(new Level5());
        this.levels.add(new EndScreen());

        // initializes the shape renderer
        this.shape = new ShapeRenderer();

        // get the portal Rectangle of the current level
        portal = levels.get(this.currentLevel).getPortal();
        
        

    }

    public void render(OrthographicCamera camera) {
// BLOCKS

        // render the shapes according to the camera
        shape.setProjectionMatrix(camera.combined);
        // set the shape type
        shape.begin(ShapeRenderer.ShapeType.Line);
        
        // renders all shapes of the level
        for (int i = 0; i < levels.get(this.currentLevel).getNumBlocks(); i++) {

            // set the color of shapes
            if (i > 1) {
                shape.setColor(Color.CHARTREUSE);
            } else {
                shape.setColor(Color.CORAL);
            }

            float x = levels.get(this.currentLevel).getBlock(i).x;
            float y = levels.get(this.currentLevel).getBlock(i).y;
            float width = levels.get(this.currentLevel).getBlock(i).width;
            float height = levels.get(this.currentLevel).getBlock(i).height;

            shape.rect(x, y, width, height);

        }

        // kill platforms
        for (int i = 0; i < levels.get(this.currentLevel).getNumKillPlats(); i++) {

            shape.setColor(Color.RED);

            float x = levels.get(this.currentLevel).getKillPlat(i).x;
            float y = levels.get(this.currentLevel).getKillPlat(i).y;
            float width = levels.get(this.currentLevel).getKillPlat(i).width;
            float height = levels.get(this.currentLevel).getKillPlat(i).height;

            shape.rect(x, y, width, height);
        }

        for (int i = 0; i < levels.get(this.currentLevel).getNumJumpBoosts(); i++) {

            shape.setColor(Color.GOLD);

            float x = levels.get(this.currentLevel).getJumpBoost(i).x;
            float y = levels.get(this.currentLevel).getJumpBoost(i).y;
            float width = levels.get(this.currentLevel).getJumpBoost(i).width;
            float height = levels.get(this.currentLevel).getJumpBoost(i).height;

            shape.rect(x, y, width, height);
        }

        // PORTAL
        // sets the color of the portal
        shape.setColor(Color.SKY);
        // draws the portal
        shape.rect(portal.x, portal.y, portal.width, portal.height);

        shape.setColor(Color.BLACK);

        // get the portal Rectangle of the current level
        portal = levels.get(this.currentLevel).getPortal();

        // end drawing shapes
        shape.end();
    }

    public void render(SpriteBatch batch){
        batch.draw(portalTurn.getKeyFrame(this.elapsed), portal.x - 20, portal.y - 20, 70, 70);
    }
    
    public void update(float deltaTime){
        
        
        if(this.elapsed < 0.8){
            this.elapsed = deltaTime + this.elapsed;
        } else {
            this.elapsed = 0;
        }
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

    public Rectangle getPortal() {
        return portal;
    }
    
    public void dispose(){
        atlas.dispose();
    }
}
