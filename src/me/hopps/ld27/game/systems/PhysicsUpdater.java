package me.hopps.ld27.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.math.Vector2;
import me.hopps.ld27.game.components.*;

public class PhysicsUpdater extends EntityProcessingSystem {
    @Mapper ComponentMapper<Position> pos;
    @Mapper ComponentMapper<Physics> phy;

    ImmutableBag<Entity> bag;

    public PhysicsUpdater() {
        super(Aspect.getAspectForAll(Physics.class, Position.class));
    }

    @Override
    protected void process(Entity e) {
        if(bag == null) {
            bag = world.getSystem(PhysicsUpdater.class).getActives();
        }
        Physics ph = phy.get(e);
        Position p = pos.get(e);

        if(ph.isActive()) {
            ph.falling = true;
            ph.addVelY(1000f * world.delta);
            p.addY(ph.getVelY() * world.delta);
            ph.getBounds().setY(p.getY());

            if(checkCollision(e, ph, bag)) {
                p.addY(-ph.getVelY() * world.delta);
                ph.getBounds().setY(p.getY());
                if(ph.getVelY() >= 0) {
                    ph.falling = false;
                }
                ph.setVelY(0);
            }

            ph.getBounds().setX(p.getX());
            if(!checkCollision(e, ph, bag)) {
                ph.savePosition();
            } else {
                Vector2 old = ph.getOldPos();
                p.setX(old.x);
                ph.getBounds().setX(old.x);
                if(!ph.falling) {
                    p.setY(old.y);
                    ph.getBounds().setY(old.y);
                }
                ph.savePosition();
            }
        }
    }

    private boolean checkCollision(Entity e, Physics ph, ImmutableBag<Entity> bag) {
        for(int i = 0; i < bag.size(); i++) {
            if(e != bag.get(i)) {
                if(ph.getBounds().overlaps(phy.get(bag.get(i)).getBounds())) {
                    ph.collisionEntity = bag.get(i);
                    phy.get(bag.get(i)).setCollision(true);
                    ph.setCollision(true);
                    return true;
                } else {
                    phy.get(bag.get(i)).setCollision(false);
                }
            }
        }
        ph.setCollision(false);
        return false;
    }
}