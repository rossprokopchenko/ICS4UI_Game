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
 * @author Ross, Kwame, Junaid
 */
public class Player {

    // gravity
    private float gravity;
    // x and y coordinates of player
    private float x;
    private float y;
    // x velocity
    private float dx;
    // y velocity
    private float dy;
    // decay x
    private float decayX = 0.9f;
    // max x velocity
    private final float MAX_DX = 13.0f;
    // jumped boolean
    private boolean jumped = false;
    private boolean holdingUp = false;
    private boolean cameraReset;
    // spawn coordinates
    private final float START_X, START_Y;
    // max y velocity
    private final float MAX_DY = 18.1f;
    // sprite 
    private Animation<TextureRegion> runRight;
    private Animation<TextureRegion> runLeft;
    private TextureRegion stand;
    private TextureAtlas atlas;
    // elapsed time for animation
    private float elapsed;
    // shape renderer and world
    private ShapeRenderer shape;
    private World world;
    // rectangle bounds of the player
    private Rectangle bounds;
    // death counter
    private int deaths;

    public Player(float x, float y, World world) {
        this.START_X = x;
        this.START_Y = y;

        this.x = START_X;
        this.y = START_Y;

        this.gravity = 1.9f;

        this.dx = 0;
        this.dy = 0;

        this.elapsed = 0;
        //this.atlas = new TextureAtlas("packed/player.atlas");
        //this.stand = atlas.findRegion("stand");
        this.shape = new ShapeRenderer();
        this.world = world;

        // 
        this.cameraReset = false;

        this.bounds = new Rectangle(x, y, 50, 50);
    }

    public void update(float deltaTime) {
        // if pressing right (right arrow or D)
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            dx = dx + 1; // start ramping up my movement
            // cap my max speed
            if (dx > MAX_DX) {
                dx = MAX_DX;
            }
            this.elapsed = this.elapsed + deltaTime;

            // if pressing left (left arrow or A)
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            dx = dx - 1; // start ramping up my movement
            // cap my max speed
            if (dx < -MAX_DX) {
                dx = -MAX_DX;
            }
            this.elapsed = this.elapsed + deltaTime;
            // if not moving right or left
        } else {
            // slow the player down
            dx = (int) (dx * decayX);
            this.elapsed = 0;
        }

        if ((Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)
                || Gdx.input.isKeyPressed(Input.Keys.SPACE))) {
            holdingUp = true;
        } else {
            holdingUp = false;
        }

        // if jumping (up arrow, W or space)
        if (!jumped && holdingUp) {
            this.dy = 31;
            jumped = true;
        }

        // if pressed R
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            // teleport the player to the start position of the level
            this.x = world.getLevels().get(world.getCurrentLevel()).getSpawnX();
            this.y = world.getLevels().get(world.getCurrentLevel()).getSpawnY();
            deaths++;
            this.cameraReset = true;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.N)) {

            if (world.getCurrentLevel() < world.getNumLevels() - 1) {
                world.setCurrentLevel(world.getCurrentLevel() + 1);

                // teleport the player to the start position of the level
                this.x = world.getLevels().get(world.getCurrentLevel()).getSpawnX();
                this.y = world.getLevels().get(world.getCurrentLevel()).getSpawnY();

                this.cameraReset = true;
            }
        }

        // gravity
        if (dy > -MAX_DY) {
            this.dy -= gravity;
        }

        // update X and Y coordinates according to the velocity
        this.x = this.x + this.dx;
        this.y = this.y + this.dy;

//         System.out.println("x: " + x + "  y: " + y);
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

        // if the player collides with the end portal
        if (bounds.overlaps(world.getPortal()) && world.getCurrentLevel() < world.getNumLevels() - 1) {
            // set the level to the next
            world.setCurrentLevel(world.getCurrentLevel() + 1);

            // teleport the player to the start position of the level
            this.x = world.getLevels().get(world.getCurrentLevel()).getSpawnX();
            this.y = world.getLevels().get(world.getCurrentLevel()).getSpawnY();

            this.cameraReset = true;

            // update the collision box to match the player
            bounds.setX(this.x);
            bounds.setY(this.y);
        } else if (bounds.overlaps(world.getPortal()) && world.getCurrentLevel() >= world.getNumLevels() - 1) {
            // Set the current level to the first levelF
            world.setCurrentLevel(0);
            // teleport the player to the start position of the level
            this.x = world.getLevels().get(world.getCurrentLevel()).getSpawnX();
            this.y = world.getLevels().get(world.getCurrentLevel()).getSpawnY();

            this.cameraReset = true;

            // update the collision box to match the player
            bounds.setX(this.x);
            bounds.setY(this.y);
        }
    }

    public void collisionRect(Rectangle rect) {
        if (bounds.overlaps(rect)) {
            // teleport the player to the start position of the level
            this.x = world.getLevels().get(world.getCurrentLevel()).getSpawnX();
            this.y = world.getLevels().get(world.getCurrentLevel()).getSpawnY();

            this.cameraReset = true;
            this.jumped = true;

            // update the collision box to match the player
            bounds.setX(this.x);
            bounds.setY(this.y);
            deaths++;
        }
    }

    public void collisionJumpBoost(Rectangle rect) {
        if (bounds.overlaps(rect) && !holdingUp) {
            this.jumped = false;
        }
    }

    public void render(SpriteBatch batch) {

        // standing
        if (this.dx == 0) {
            //batch.draw(stand, x, y);
        } else if (this.dx > 0) {
            //batch.draw(runRight.getKeyFrame(elapsed, true), x, y);
        } else if (this.dx < 0) {
            // batch.draw(runLeft.getKeyFrame(elapsed, true), x, y);
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

    public boolean getCameraReset() {
        return cameraReset;
    }

    public void setCameraReset(boolean b) {
        cameraReset = b;
    }

    public int getDeaths() {
        return deaths;
    }

}
