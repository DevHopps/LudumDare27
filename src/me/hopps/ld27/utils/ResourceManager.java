package me.hopps.ld27.utils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import me.hopps.ld27.screens.*;

public class ResourceManager {

    public Game game;

    public static final int NUMBEROFLEVELS = 1;

    public AssetManager assets;
    public BitmapFont smallFont;
    public BitmapFont mediumFont;
    public BitmapFont bigFont;
    public SpriteBatch spriteBatch;
    public ShapeRenderer shapeRenderer;

    public MenuScreen menuScreen;
    public LoadLevelScreen loadLevelScreen;
    public GameScreen gameScreen;
    public GamePauseScreen gamePauseScreen;
    public GameOverScreen gameOverScreen;

    public ResourceManager(Game g) {
        game = g;
        assets = new AssetManager();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("res/fnt/arial.ttf"));
        smallFont = generator.generateFont(15);
        mediumFont = generator.generateFont(30);
        bigFont = generator.generateFont(60);
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
    }

    public void dispose() {
        assets.dispose();
        smallFont.dispose();
        mediumFont.dispose();
        bigFont.dispose();
        spriteBatch.dispose();
        shapeRenderer.dispose();
    }
}
