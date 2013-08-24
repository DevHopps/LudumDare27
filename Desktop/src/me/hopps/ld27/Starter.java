package me.hopps.ld27;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Starter {
    public static void main(String[] args) {

        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Ludum Dare 27";
        cfg.useGL20 = true;
        cfg.width = 800;
        cfg.height = 600;
        cfg.resizable = false;
        cfg.fullscreen = false;
        cfg.vSyncEnabled = true;
        new LwjglApplication(new MainStart(), cfg);
    }
}