/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdxgame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
    private final float START_X, START_Y;
    private final float MAX_DY = 18.1f;
    private float elapsed;
    private Animation<TextureRegion> runRight;
    private Animation<TextureRegion> runLeft;
    private TextureRegion stand;
    private TextureAtlas atlas;
    private float dx;
    private float dy;
    private float decayX = 0.9f;
    private final float MAX_DX = 13.0f;
    private boolean jumped = false;
    private Rectangle bounds;
    private ShapeRenderer shape;
    private World world;

    public Player(float x, float y, World world) {
        this.START_X = x;
        this.START_Y = y;

        this.x = START_X;
        this.y = START_Y;

        this.gravity = 1.9f;

        this.dx = 0;
        this.dy = 0;


        this.elapsed = 0;
        this.atlas = new TextureAtlas("packed/player.atlas");
        this.stand = atlas.findRegion("stand");
        this.shape = new ShapeRenderer();
        this.world = world;

        runRight = new Animation(1f / 10f, atlas.findRegions("run"));

        Array<AtlasRegion> runLeftArray = atlas.findRegions("run");

        for (int i = 0; i < runLeftArray.size; i++) {
            runLeftArray.get(i).flip(true, false);

        }

        runLeft = new Animation(1f / 10f, runLeftArray);

        this.bounds = new Rectangle(x, y, 50, 50);
    }

    public void update(float deltaTime) {
        // if I'm pressing right
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            dx = dx + 1; // start ramping up my movement
            // cap my max speed
            if (dx > MAX_DX) {
                dx = MAX_DX;
            }
            this.elapsed = this.elapsed + deltaTime;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            dx = dx - 1; // start ramping up my movement
            // cap my max speed
            if (dx < -MAX_DX) {
                dx = -MAX_DX;
            }
            this.elapsed = this.elapsed + deltaTime;
        } else {
            dx = (int) (dx * decayX);
            this.elapsed = 0;
        }

        if ((Gdx.input.isKeyJustPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.W)
                || Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
                && !jumped) {
            this.dy = 31;
            jumped = true;

        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            this.x = this.START_X;
            this.y = this.START_Y;
        }

        // gravity
        if (dy > -MAX_DY) {
            this.dy -= gravity;

        }

        this.x = this.x + this.dx;
        this.y = this.y + this.dy;

//         System.out.println("dx: " + dx + "  dy: " + dy);

        // update collision rectangle
        this.bounds.setX(this.x);
        this.bounds.setY(this.y);
    }

    public void fixCollision(Rectangle block) {
        // are they colliding?
        if (bounds.overlaps(block)) {
            // calculate how much they are overlaping

            float width = Math.min(bounds.x + bounds.width, block.x + block.width) - Math.max(bounds.x, block.x);
            float height = Math.min(bounds.y + bounds.height, block.y + block.height) - Math.max(bounds.y, block.y);

            // seperate the axis by finding the least amount of collision
            if (width < height) {
                // on the left
                if (this.x < block.x) {
                    // move the player to the left
                    this.x = this.x - width;
                    this.dx = 0;
                    // on the right
                } else {
                    // move the player to the right
                    this.x = this.x + width;
                    this.dx = 0;
                }
            } else {
                // under it
                if (this.y < block.y) {
                    // move the player down
                    this.y = this.y - height;
                    this.dy = 0;
                    // above it
                } else {
                    // move the player up
                    this.y = this.y + height;
                    this.dy = 0;
                    jumped = false;
                }
            }

            // update the collision box to match the player
            bounds.setX(this.x);
            bounds.setY(this.y);
        }
        
        if(bounds.overlaps(world.getPortal())){
            world.setCurrentLevel(world.getCurrentLevel() + 1);
            
            this.x = world.getLevels().get(world.getCurrentLevel()).getSpawnX();
            this.y = world.getLevels().get(world.getCurrentLevel()).getSpawnY();
            
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

    public void render(ShapeRenderer shape, OrthographicCamera camera) {
        shape.setProjectionMatrix(camera.combined);
        shape.begin(ShapeRenderer.ShapeType.Filled);

        shape.setColor(Color.RED);
        shape.rect(bounds.x, bounds.y, bounds.width, bounds.height);

        shape.end();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public ShapeRenderer getShape() {
        return this.shape;
    }
}
