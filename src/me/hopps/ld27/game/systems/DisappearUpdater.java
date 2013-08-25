package me.hopps.ld27.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.TimeUtils;
import me.hopps.ld27.game.components.*;

public class DisappearUpdater extends EntityProcessingSystem {
    @Mapper ComponentMapper<Disappear> dis;

    Sound ouch;

    public DisappearUpdater(Sound sound) {
        super(Aspect.getAspectForAll(Disappear.class));
        ouch = sound;
    }

    @Override
    protected void process(Entity e) {
        Disappear p = dis.get(e);
        if(e.getComponent(Physics.class).wasTouched()) {
            if(!p.isTimerStarted()) {
                p.setTimer(TimeUtils.millis());
                p.setTimerStarted(true);
            } else {
                if(TimeUtils.millis() - p.getTimer() > 700) {
                    e.disable();
                    ouch.play();
                }
            }
        }
    }
}