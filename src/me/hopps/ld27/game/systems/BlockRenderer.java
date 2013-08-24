package me.hopps.ld27.game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import me.hopps.ld27.game.components.ColorComponent;
import me.hopps.ld27.game.components.Position;
import me.hopps.ld27.game.components.Size;

public class BlockRenderer extends EntityProcessingSystem {
    @Mapper ComponentMapper<Position> pos;
    @Mapper ComponentMapper<Size> size;
    @Mapper ComponentMapper<ColorComponent> color;

    ShapeRenderer shapeRenderer;

    public BlockRenderer(ShapeRenderer shapeRenderer) {
        super(Aspect.getAspectForAll(Position.class, Size.class, ColorComponent.class));

        this.shapeRenderer = shapeRenderer;
    }

    @Override
    protected void process(Entity e) {
        Position p = pos.get(e);
        Size s = size.get(e);
        ColorComponent c = color.get(e);
        shapeRenderer.setColor(c.getColor());
        shapeRenderer.filledRect(p.getX(), p.getY(), s.getWidth(), s.getHeight());
    }
}