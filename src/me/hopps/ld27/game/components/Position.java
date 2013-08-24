package me.hopps.ld27.game.components;

import com.artemis.Component;

public class Position extends Component {
    private float x;
    private float y;

    public Position(float x, float y) {
        setPosition(x, y);
    }

    public void setPosition(float x, float y) {
        setX(x);
        setY(y);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void addY(float y) {
        this.y += y;
    }

    public void addX(float x) {
        this.x += x;
    }
}
