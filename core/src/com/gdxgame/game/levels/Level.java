/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdxgame.game.levels;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author prokr8056
 */
public abstract class Level {
    public abstract Rectangle[] getBlocks();
    
    public abstract Rectangle getBlock(int i);
    
    public abstract Rectangle getPortal();
    
    public abstract int getNumBlocks();
    
    public abstract float getSpawnX();
    
    public abstract float getSpawnY();
    
    public abstract float getHighestX();
    
    public abstract float getLowestY();
    
    public abstract float getHighestY();
}
