package me.hopps.ld27.game.components;

import com.artemis.Component;
import com.artemis.Entity;

public class PlayerComponent extends Component {
    private boolean won, lost;

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public boolean isLost() {
        return lost;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }
}