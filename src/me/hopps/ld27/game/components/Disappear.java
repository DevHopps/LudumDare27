package me.hopps.ld27.game.components;

import com.artemis.Component;

public class Disappear extends Component {
    private long timer;
    private boolean timerStarted;

    public void setTimer(long timer) {
        this.timer = timer;
    }

    public boolean isTimerStarted() {
        return timerStarted;
    }

    public void setTimerStarted(boolean timerStarted) {
        this.timerStarted = timerStarted;
    }

    public long getTimer() {
        return timer;
    }
}
