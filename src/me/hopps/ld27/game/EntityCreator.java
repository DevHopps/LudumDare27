package me.hopps.ld27.game;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import me.hopps.ld27.game.components.*;

public class EntityCreator {

    public static void createBlock(World world, float x, float y) {
        Entity e = world.createEntity();
        e.addComponent(new Position(x, y));
        e.addComponent(new Size(32, 32));
        e.addComponent(new ColorComponent(Color.WHITE));
        e.addComponent(new Physics(new Rectangle(x,y,32,32), false));
        e.addToWorld();
    }

    public static Entity createPlayer(World world, float x, float y) {
        Entity e = world.createEntity();
        e.addComponent(new Position(x, y));
        e.addComponent(new Size(24, 24));
        e.addComponent(new ColorComponent(Color.GREEN));
        e.addComponent(new Physics(new Rectangle(x,y,24,24), true));
        e.addToWorld();
        return e;
    }

    public static void createEnd(World world, float x, float y) {
        Entity e = world.createEntity();
        e.addComponent(new Position(x, y));
        e.addComponent(new Size(32, 32));
        e.addComponent(new ColorComponent(Color.BLUE));
        e.addComponent(new Physics(new Rectangle(x,y,32,32), false));
        e.addComponent(new EndComponent());
        e.addToWorld();
    }

    public static void createTrap(World world, float x, float y) {
        Entity e = world.createEntity();
        e.addComponent(new Position(x, y + 16));
        e.addComponent(new Size(32, 16));
        e.addComponent(new ColorComponent(Color.RED));
        e.addComponent(new Physics(new Rectangle(x,y + 16,32, 16), false));
        e.addComponent(new DeadComponent());
        e.addToWorld();
    }
}