package me.hopps.ld27.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import me.hopps.ld27.utils.ResourceManager;

public class GameOverScreen implements Screen {

    ResourceManager resManager;

    public GameOverScreen(ResourceManager resManager) {
        this.resManager = resManager;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        resManager.spriteBatch.begin();
        resManager.bigFont.draw(resManager.spriteBatch, "Congratulations", Gdx.graphics.getWidth() / 2 - resManager.bigFont.getBounds("Congratulations").width / 2, Gdx.graphics.getHeight() - 100);
        resManager.smallFont.draw(resManager.spriteBatch, "Thank you for actually finishing this game!", 100, Gdx.graphics.getHeight() - 200);
        resManager.smallFont.draw(resManager.spriteBatch, "You failed " + resManager.gameScreen.fails + " times.", 100, Gdx.graphics.getHeight() - 230);
        resManager.smallFont.draw(resManager.spriteBatch, "If you leave a comment when rating, I'm going to rate your game as well ;)", 100, Gdx.graphics.getHeight() - 300);
        resManager.smallFont.draw(resManager.spriteBatch, "- Hopps", 100, Gdx.graphics.getHeight() - 330);
        resManager.spriteBatch.end();
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
