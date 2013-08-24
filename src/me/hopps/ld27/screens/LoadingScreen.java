package me.hopps.ld27.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import me.hopps.ld27.utils.ResourceManager;

public class LoadingScreen implements Screen {

    ResourceManager resManager;

    public LoadingScreen(ResourceManager resManager) {
        this.resManager = resManager;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int x, int y) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
