package me.hopps.ld27.screens;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import me.hopps.ld27.game.EntityCreator;
import me.hopps.ld27.game.components.Physics;
import me.hopps.ld27.game.components.Position;
import me.hopps.ld27.game.systems.BlockRenderer;
import me.hopps.ld27.game.systems.PhysicsUpdater;
import me.hopps.ld27.utils.ResourceManager;

public class GameScreen implements Screen {

    ResourceManager resManager;
    OrthographicCamera cam;
    PhysicsUpdater physics;
    boolean pause = false;
    World world;
    Entity player;
    float x, y;

    public GameScreen(ResourceManager resManager) {
        cam = new OrthographicCamera();
        cam.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(400f, 300f, 0f);
        cam.update();
        this.resManager = resManager;

        createLevel(1);
    }

    private void createLevel(int lvl) {
        world = new World();
        world.setSystem(new BlockRenderer(resManager.shapeRenderer));
        physics = new PhysicsUpdater();
        world.setSystem(physics);
        world.initialize();

        Pixmap level = new Pixmap(Gdx.files.internal("res/levels/" + lvl + ".png"));
        /*
         * -1           = Block
         * -16776961    = Start
         * -2139062017  = DeathTrap
         * 2555903      = End
         */
        for(int i = 0; i < level.getWidth(); i++) {
            for(int k = 0; k < level.getHeight(); k++) {
                if(level.getPixel(i, k) == -1) {
                    EntityCreator.createBlock(world, i*32, k*32);
                }
                if(level.getPixel(i, k) == -16776961) {
                    x = i * 32f;
                    y = k * 32f;
                    System.out.println(y);
                    player = EntityCreator.createPlayer(world, x, y);
                }
                if(level.getPixel(i, k) == -2139062017) {
                    EntityCreator.createTrap(world, i * 32, k * 32);
                }
                if(level.getPixel(i, k) == 2555903) {
                    EntityCreator.createEnd(world, i * 32, k * 32);
                }
            }
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(physics.lose) {
            player.disable();
            player = EntityCreator.createPlayer(world, x, y);

            physics.lose = false;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            physics.jump = true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            physics.right = true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            physics.left = true;
        }

        cam.update();
        resManager.shapeRenderer.setProjectionMatrix(cam.combined);
        world.setDelta(delta);
        world.process();
    }

    @Override
    public void resize(int x, int y) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
        this.pause();
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
