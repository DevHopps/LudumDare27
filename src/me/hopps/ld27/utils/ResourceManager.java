package me.hopps.ld27.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class ResourceManager {

    public AssetManager assets;
    public BitmapFont smallFont;
    public BitmapFont mediumFont;
    public BitmapFont bigFont;
    public SpriteBatch spriteBatch;
    public ShapeRenderer shapeRenderer;

    public ResourceManager() {
        assets = new AssetManager();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("res/fnt/arial.ttf"));
        smallFont = generator.generateFont(15);
        mediumFont = generator.generateFont(30);
        bigFont = generator.generateFont(60);
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
    }

    public void dispose() {
        System.out.println("Dispose");
        assets.dispose();
        smallFont.dispose();
        mediumFont.dispose();
        bigFont.dispose();
        spriteBatch.dispose();
        shapeRenderer.dispose();
    }
}
