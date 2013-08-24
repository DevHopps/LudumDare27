package me.hopps.ld27.game.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Rectangle;

public class Size extends Component {
    private int width;
    private int height;

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
