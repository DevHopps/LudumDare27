package me.hopps.ld27;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import me.hopps.ld27.screens.LoadingScreen;
import me.hopps.ld27.utils.ResourceManager;

public class MainStart extends Game {

    @Override
    public void create() {
        Gdx.gl20.glClearColor(0, 0, 0, 0);

        this.setScreen(new LoadingScreen(new ResourceManager()));
    }
}