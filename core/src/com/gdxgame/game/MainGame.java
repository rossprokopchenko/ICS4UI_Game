/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdxgame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 *
 * @author prokr8056
 */
public class MainGame implements Screen {

    private ICS4UIgame game;
    private World world;
    private Player p1;
    // sprite batch
    private SpriteBatch batch;
    private ShapeRenderer shape;
    // camera and viewport
    private OrthographicCamera camera;
    private Viewport view;
    private float offsetX, offsetY;
    // game units
    private final int WIDTH = 800, HEIGHT = 600;
    private final int START_X = 100, START_Y = 100;
    

    public MainGame(ICS4UIgame game) {
        this.game = game;
        this.world = new World();
        this.p1 = new Player(START_X, START_Y, world);
        
        // initialize the spritebatch
        this.batch = game.getBatch();
        this.shape = p1.getShape();

        // set up the camera and view
        this.offsetX = 0;
        this.offsetY = 0;

        this.camera = new OrthographicCamera(WIDTH, HEIGHT);
        this.view = new ExtendViewport(WIDTH, HEIGHT, camera);
        // move the camera to the center
        this.camera.position.set(WIDTH / 2, HEIGHT / 2, 0);
        // make sure to apply the changes
        this.camera.update();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float deltaTime) {
        // update the player
        p1.update(deltaTime);

        for (Rectangle block : world.getLevels().get(world.getCurrentLevel()).getBlocks()) {
            p1.fixCollision(block);
        }
        
        // get the SpriteBatch from the Game
        SpriteBatch batch = game.getBatch();

        //draw the player
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // render the world
        world.render(camera);


        camera.position.set(p1.getX(), p1.getY(), 0);


        camera.update();

        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        // p1.render(batch);
        p1.render(shape, camera);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        view.update(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}
