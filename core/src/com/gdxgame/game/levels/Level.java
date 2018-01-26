/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdxgame.game.levels;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author Ross, Kwame, Junaid
 */
public abstract class Level {

    public abstract Rectangle[] getBlocks();

    public abstract Rectangle getBlock(int i);

    public abstract int getNumBlocks();

    public abstract Rectangle[] getKillPlats();
    
    public abstract Rectangle getKillPlat(int i);
    
    public abstract int getNumKillPlats();
    
    public abstract Rectangle[] getJumpBoosts();
    
    public abstract Rectangle getJumpBoost(int i);
    
    public abstract int getNumJumpBoosts();

    public abstract Rectangle getPortal();

    public abstract float getSpawnX();

    public abstract float getSpawnY();

    public abstract float getHighestX();

    public abstract float getLowestY();

    public abstract float getHighestY();
}
