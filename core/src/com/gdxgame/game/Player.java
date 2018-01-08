/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdxgame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author lamon
 */
public class Player {

    private float gravity;
    private float x;
    private float y;
    private final float MAX_DY = 15.0f;
    private float elapsed;
    private Animation<TextureRegion> runRight;
    private Animation<TextureRegion> runLeft;
    private TextureRegion stand;
    private TextureAtlas atlas;
    private float dx;
    private float dy;
    private Rectangle bounds;
    
    private boolean jumped = false;

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
        this.gravity = 0.9f;

        this.dx = 0;
        this.dy = 0;

        this.elapsed = 0;
        this.atlas = new TextureAtlas("packed/player.atlas");
        this.stand = atlas.findRegion("stand");
        
        runRight = new Animation(1f / 10f, atlas.findRegions("run"));
        
        Array<AtlasRegion> runLeftArray = atlas.findRegions("run");
        
        for (int i = 0; i < runLeftArray.size; i++) {
            runLeftArray.get(i).flip(true, false);
            
        }
        
        runLeft = new Animation(1f / 10f, runLeftArray);

        this.bounds = new Rectangle(x, y, stand.getRegionWidth(), stand.getRegionHeight());
    }

    public void update(float deltaTime) {
        // if I'm pressing right
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            this.dx = 3;
            this.elapsed = this.elapsed + deltaTime;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            this.dx = -3;
            this.elapsed = this.elapsed + deltaTime;
        } else {
            this.dx = 0;
            this.elapsed = 0;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.W)
                || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            this.dy = 15;
         
        }

        // gravity
        if (dy > -MAX_DY) {
            this.dy -= gravity;
        } 

        this.x = this.x + this.dx;
        this.y = this.y + this.dy;


        // update collision rectangle
        this.bounds.setX(this.x);
        this.bounds.setY(this.y);
    }

    public void fixCollision(Rectangle block) {
        // are they colliding?
        if (bounds.overlaps(block)) {
            // calculate how much the are overlaping
            
            float width = Math.min(bounds.x + bounds.width, block.x + block.width) - Math.max(bounds.x, block.x);
            float height = Math.min(bounds.y + bounds.height, block.y + block.height) - Math.max(bounds.y, block.y);
            // seperate the axis by finding the least amount of collision
            if (width < height) {
                // on the left
                if (this.x < block.x) {
                    // move the player to the left
                    this.x = this.x - width;
                    // on the right
                } else {
                    // move the player to the right
                    this.x = this.x + width;
                }
            } else {
                // under it
                if (this.y < block.y) {
                    // move the player down
                    this.y = this.y - height;
                    // above it
                } else {
                    // move the player up
                    this.y = this.y + height;
                    this.dy = 0;
                }
            }
            
            if(x == block.x && dx < 0){
                this.x = this.x - 1;
            }
            // update the collision box to match the player
            bounds.setX(this.x);
            bounds.setY(this.y);
        }
    }

    public void render(SpriteBatch batch) {
        // standing
        if (this.dx == 0) {
            batch.draw(stand, x, y);
        } else if (this.dx > 0) {
            batch.draw(runRight.getKeyFrame(elapsed, true), x, y);
        } else if (this.dx < 0) {
            batch.draw(runLeft.getKeyFrame(elapsed, true), x, y);
            
            
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
