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
public class Menu extends Level{
    private Rectangle[] blocks;
    private Rectangle portal;
    private final float SPAWN_X, SPAWN_Y;
    
    private Rectangle[] jumpBoost;
    // array of kill platforms of the level
    private Rectangle[] killPlats;
    
    
    public Menu(){
        blocks = new Rectangle[6];
        portal = new Rectangle(2150, 40, 25, 25);
        this.SPAWN_X = 100;
        this.SPAWN_Y = 100;
        
        // initializes kill platforms
        killPlats = new Rectangle[1];
        
        jumpBoost = new Rectangle[0];
        
        blocks[0] = new Rectangle(0, 0, 800, 20);
        blocks[1] = new Rectangle(600, 150, 200, 20);
        blocks[2] = new Rectangle(0, 150, 200, 20);
        blocks[3] = new Rectangle(350, 325, 100, 20);
        blocks[4] = new Rectangle(-10, 0, 10, 600);
        blocks[5] = new Rectangle(800, 0, 10, 600);

        
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

    @Override
    public Rectangle[] getKillPlats() {
        return null;
    }

    @Override
    public Rectangle getKillPlat(int i) {
        return null;
    }

    @Override
    public int getNumKillPlats() {
        return 0;
    }

    @Override
    public float getHighestX() {
        return 0;
    }

    @Override
    public float getLowestY() {
        return 0;
    
    }

    @Override
    public float getHighestY() {
    return 0;
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
    
    
    
}
