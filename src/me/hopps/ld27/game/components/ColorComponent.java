package me.hopps.ld27.game.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Color;

public class ColorComponent extends Component {

    Color color;

    public ColorComponent(Color col) {
        setColor(col);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color col) {
        color = col;
    }
}
