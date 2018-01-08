package com.gdxgame.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ICS4UIgame extends Game {
	private SpriteBatch batch;
        
	
	@Override
	public void create () {
            batch = new SpriteBatch();
            // create the main game screen
            MainGame game = new MainGame(this);
            // set the screen to show it
            this.setScreen(game);
	}

	@Override
	public void render () {
            super.render();
	}
	
	@Override
	public void dispose () {
            batch.dispose();
	}
        
        public SpriteBatch getBatch(){
            return batch;
        }
        
}
