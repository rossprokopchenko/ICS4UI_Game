package com.gdxgame.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.gdxgame.game.ICS4UIgame;

public class DesktopLauncher {

    public static void main(String[] arg) {

        TexturePacker.process("raw", "packed", "portalPics");

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new ICS4UIgame(), config);

    }
}
