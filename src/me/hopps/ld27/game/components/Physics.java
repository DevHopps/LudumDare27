package me.hopps.ld27.game.components;

import com.artemis.Component;
import com.artemis.Entity;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Physics extends Component {
    private Rectangle bounds;
    private float velY;
    private boolean active;
    private boolean collision;
    private Vector2 oldPos;
    public boolean falling;
    public Entity collisionEntity;

    public Physics(Rectangle bounds) {
        setBounds(bounds);
    }

    public Physics(Rectangle bounds, boolean active) {
        this(bounds);
        setActive(active);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public void addVelY(float add) {
        this.velY += add;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public void savePosition() {
        oldPos = new Vector2(bounds.x, bounds.y);
    }

    public Vector2 getOldPos() {
        return oldPos;
    }
}
