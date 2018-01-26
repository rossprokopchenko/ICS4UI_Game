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
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author Ross, Kwame, Junaid
 */
public class Player {

    // shape renderer and world
    private ShapeRenderer shape;
    private World world;
    // rectangle bounds of the player
    private Rectangle bounds;

    // sprite 
    private Animation<TextureRegion> runRight;
    private Animation<TextureRegion> runLeft;
    private TextureRegion stand;
    private TextureAtlas atlas;
    // elapsed time for animation
    private float elapsed;

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

    // death counter
    private int deaths;

    /**
     * Player constructor
     *
     * @param x start X coordinate of the player
     * @param y start Y coordinate of the player
     * @param world the world class that the player uses
     */
    public Player(float x, float y, World world) {
        // link the worlds
        this.world = world;
        // intialize shape renderer
        this.shape = new ShapeRenderer();
        // initialize the start coordinates
        this.START_X = x;
        this.START_Y = y;

        this.x = START_X;
        this.y = START_Y;

        // gravity force
        this.gravity = 1.9f;

        this.dx = 0;
        this.dy = 0;
        this.elapsed = 0;

        this.cameraReset = false;

        this.bounds = new Rectangle(x, y, 50, 50);
    }

    /**
     * Input listener for the player
     *
     * @param deltaTime
     */
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

        // if player presses the up arrow, W or space
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
            // update death counter
            deaths++;
            // reset camera
            this.cameraReset = true;
        }

        // if pressed N
        if (Gdx.input.isKeyJustPressed(Input.Keys.N)) {
            // as long as the player is not in the last level
            if (world.getCurrentLevel() < world.getNumLevels() - 1) {
                // set the level to the next
                world.setCurrentLevel(world.getCurrentLevel() + 1);
                // teleport the player to the start position of the level
                this.x = world.getLevels().get(world.getCurrentLevel()).getSpawnX();
                this.y = world.getLevels().get(world.getCurrentLevel()).getSpawnY();
                // reset camera to start position of the level
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

        // update collision rectangle
        this.bounds.setX(this.x);
        this.bounds.setY(this.y);
    }

    /**
     * Fixes collision bounds of the player
     *
     * @param block the block that the player collides with
     */
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
            // reset camera
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
            // reset camera
            this.cameraReset = true;

            // update the collision box to match the player
            bounds.setX(this.x);
            bounds.setY(this.y);
        }
    }

    /**
     * Player collision with the red platforms
     * @param rect the red platform that the player collides with
     */
    public void collisionRect(Rectangle rect) {
        // if player overlaps one of the red platforms
        if (bounds.overlaps(rect)) {
            // teleport the player to the start position of the level
            this.x = world.getLevels().get(world.getCurrentLevel()).getSpawnX();
            this.y = world.getLevels().get(world.getCurrentLevel()).getSpawnY();
            // reset camera
            this.cameraReset = true;
            // reset player's jump
            this.jumped = true;

            // update the collision box to match the player
            bounds.setX(this.x);
            bounds.setY(this.y);
            // update death counter
            deaths++;
        }
    }

    /**
     * If player collides with the jump boost block
     * @param rect the jump boost block
     */
    public void collisionJumpBoost(Rectangle rect) {
        // if player collides with the jump boost
        if (bounds.overlaps(rect) && !holdingUp) {
            // reset player's jump
            this.jumped = false;
        }
    }

    /**
     * Render the player
     * @param shape the shape renderer to use
     * @param camera the camera that the world is using
     */
    public void render(ShapeRenderer shape, OrthographicCamera camera) {
        // link the shape and the camera
        shape.setProjectionMatrix(camera.combined);
        // set the shape type to filled
        shape.begin(ShapeRenderer.ShapeType.Filled);

        // set the color
        shape.setColor(Color.SKY);
        // render the player considering the bounds
        shape.rect(bounds.x, bounds.y, bounds.width, bounds.height);

        shape.end();
    }

    /**
     * Gets the X coordinate of the player
     * @return the X coordinate of the player
     */
    public float getX() {
        return x;
    }

    /**
     * Gets the Y coordinate of the player
     * @return the Y coordinate of the player
     */
    public float getY() {
        return y;
    }

    /**
     * Gets the shape renderer that the player class uses
     * @return the shape renderer
     */
    public ShapeRenderer getShape() {
        return this.shape;
    }

    /**
     * Get the camera reset boolean
     * @return the boolean regarding to what cameraReset is set to
     */
    public boolean getCameraReset() {
        return cameraReset;
    }

    /**
     * Set the camera reset boolean to true/false
     * @param b true/false statement
     */
    public void setCameraReset(boolean b) {
        cameraReset = b;
    }

    /**
     * Get the amount of deaths that the player has
     * @return the integer of deaths 
     */
    public int getDeaths() {
        return deaths;
    }

}
