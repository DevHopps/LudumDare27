package me.hopps.ld27.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.TimeUtils;
import me.hopps.ld27.game.components.*;
import me.hopps.ld27.screens.GameScreen;

public class PlayerUpdater extends EntityProcessingSystem  {
    @Mapper ComponentMapper<PlayerComponent> player;
    @Mapper ComponentMapper<Position> position;
    @Mapper ComponentMapper<Physics> physics;

    GameScreen game;
    Input input;
    Sound jumpSound;

    long lastPlay;

    public PlayerUpdater(GameScreen game, Input input, Sound jump) {
        super(Aspect.getAspectForAll(PlayerComponent.class, Position.class, Physics.class));
        this.game = game;
        this.input = input;
        this.jumpSound = jump;
    }

    @Override
    protected void process(Entity e) {
        if(game.started) {
            Position pos = position.get(e);
            Physics phy = physics.get(e);
            PlayerComponent p = player.get(e);

            if(phy.collisionEntity != null && phy.collisionEntity.getComponent(DeadComponent.class) != null) {
                p.setLost(true);
            }

            if(pos.getY() > 620 || pos.getX() < -32 || pos.getX() > 832) {
                p.setLost(true);
            }

            if(phy.collisionEntity != null && phy.collisionEntity.getComponent(EndComponent.class) != null) {
                p.setWon(true);
            }

            if(input.isKeyPressed(Input.Keys.D) || input.isKeyPressed(Input.Keys.RIGHT)) {
                pos.addX(250f * world.delta);
            }
            if(input.isKeyPressed(Input.Keys.A) || input.isKeyPressed(Input.Keys.LEFT)) {
                pos.addX(-250f * world.delta);
            }
            if(input.isKeyPressed(Input.Keys.W) || input.isKeyPressed(Input.Keys.UP) || input.isKeyPressed(Input.Keys.SPACE)) {
                if(!phy.falling) {
                    if(TimeUtils.millis() - lastPlay > 170) {
                        lastPlay = TimeUtils.millis();
                        jumpSound.play(0.25f);
                    }
                    phy.setVelY(-400f);
                    phy.falling = true;
                }
            }
        }
    }
}