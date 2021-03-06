package me.hopps.ld27.screens;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.utils.TimeUtils;
import me.hopps.ld27.game.EntityCreator;
import me.hopps.ld27.game.components.PlayerComponent;
import me.hopps.ld27.game.systems.BlockRenderer;
import me.hopps.ld27.game.systems.DisappearUpdater;
import me.hopps.ld27.game.systems.PhysicsUpdater;
import me.hopps.ld27.game.systems.PlayerUpdater;
import me.hopps.ld27.utils.InputListener;
import me.hopps.ld27.utils.ResourceManager;

public class GameScreen implements Screen {

    private static final int MAXLVL = 7;
    int lvl = 1;

    public int fails;
    ResourceManager resManager;
    OrthographicCamera cam;
    public boolean started;
    World world;
    Entity player;
    float x, y, r;
    long startTime;

    public GameScreen(final ResourceManager resManager) {
        cam = new OrthographicCamera();
        cam.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(400f, 300f, 0f);
        cam.update();
        this.resManager = resManager;

        Gdx.input.setInputProcessor(new InputListener() {
            @Override
            public boolean keyUp(int i) {
                if(i == Input.Keys.SPACE) {
                    if(!started) {
                        started = true;
                        resManager.assets.get("res/sounds/select.wav", Sound.class).play(0.25f);
                        resManager.assets.get("res/sounds/beep.wav", Sound.class).play(0.25f);
                    }
                    return true;
                }
                return false;
            }
        });

        createLevel(lvl);
    }

    private void createLevel(int lvl) {
        if(lvl <= MAXLVL) {
            world = new World();
            world.setSystem(new BlockRenderer(resManager.shapeRenderer));
            world.setSystem(new DisappearUpdater(resManager.assets.get("res/sounds/ouch.wav", Sound.class)));
            world.setSystem(new PlayerUpdater(this, Gdx.input, resManager.assets.get("res/sounds/jump.wav", Sound.class)));
            world.setSystem(new PhysicsUpdater());
            world.setManager(new TagManager());
            world.initialize();

            Pixmap level = new Pixmap(Gdx.files.internal("res/levels/" + lvl + ".png"));
            /*
             * -1           = Block
             * -16776961    = Start
             * -2139062017  = DeathTrap
             * 2555903      = End
             * -2621185     = Disappearing Block
             */
            for(int i = 0; i < level.getWidth(); i++) {
                for(int k = 0; k < level.getHeight(); k++) {
                    if(level.getPixel(i, k) == -1) {
                        EntityCreator.createBlock(world, i*32, k*32);
                    }
                    if(level.getPixel(i, k) == -2621185) {
                        EntityCreator.createDisappearingBlock(world, i * 32, k * 32);
                    }
                    if(level.getPixel(i, k) == -16776961) {
                        x = i * 32f + 4;
                        y = k * 32f + 4;
                        player = EntityCreator.createPlayer(world, x, y);
                        world.getManager(TagManager.class).register("PLAYER", player);
                    }
                    if(level.getPixel(i, k) == -2139062017) {
                        EntityCreator.createTrap(world, i * 32, k * 32);
                    }
                    if(level.getPixel(i, k) == 2555903) {
                        EntityCreator.createEnd(world, i * 32, k * 32);
                    }
                }
            }
            level.dispose();
        } else {
            resManager.game.setScreen(resManager.gameOverScreen);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        r += delta;

        if(world.getManager(TagManager.class).getEntity("PLAYER").getComponent(PlayerComponent.class).isLost()) {
            started = false;
            resManager.assets.get("res/sounds/boom.wav", Sound.class).play(0.25f);
            createLevel(lvl);
            fails++;
        }
        if(world.getManager(TagManager.class).getEntity("PLAYER").getComponent(PlayerComponent.class).isWon()) {
            started = false;
            resManager.assets.get("res/sounds/jay.wav", Sound.class).play(0.25f);
            createLevel(++lvl);
        }
        if(!started) {
            startTime = TimeUtils.millis();
            r = 0;
        }

        if(r > 1f) {
            resManager.assets.get("res/sounds/beep.wav", Sound.class).play(0.25f);
            r -= 1f;
        }

        if(10000 + startTime - TimeUtils.millis() < 0) {
            world.getManager(TagManager.class).getEntity("PLAYER").getComponent(PlayerComponent.class).setLost(true);
        }

        cam.update();
        resManager.shapeRenderer.setProjectionMatrix(cam.combined);
        world.setDelta(delta);
        world.process();

        resManager.spriteBatch.begin();
        resManager.bigFont.draw(resManager.spriteBatch,"" + ((10100 + startTime - TimeUtils.millis())/1000), 400 - resManager.bigFont.getBounds("" + ((10100 + startTime - TimeUtils.millis())/1000)).width/2, 325);
        if(!started) {
            resManager.smallFont.setColor(Color.GRAY);
            resManager.smallFont.draw(resManager.spriteBatch,"Press Space to start, then use W/A/D to move", Gdx.graphics.getWidth()/2 - resManager.smallFont.getBounds("Press Space to start, then use W/A/D to move").width/2, 265);
            resManager.smallFont.setColor(Color.WHITE);
        }
        if(lvl == 7) {
            resManager.spriteBatch.draw(resManager.assets.get("res/img/looser.png", Texture.class), 550, 180);
        }
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
