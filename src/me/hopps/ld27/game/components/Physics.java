package me.hopps.ld27.game.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Rectangle;

public class Physics extends Component {
    private Rectangle bounds;
    private float velY;
    private boolean active;
    private boolean touched;

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

    public boolean wasTouched() {
        return touched;
    }

    public void setTouched(boolean touched) {
        this.touched = touched;
    }
}
