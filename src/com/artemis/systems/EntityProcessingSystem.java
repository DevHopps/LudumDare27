package com.artemis.systems;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.utils.ImmutableBag;

/**
 * A typical entity system. Use this when you need to process entities possessing the
 * provided component types.
 * 
 * @author Arni Arent
 *
 */
public abstract class EntityProcessingSystem extends EntitySystem {
	
	public EntityProcessingSystem(Aspect aspect) {
		super(aspect);
	}

	/**
	 * Process a entity this system is interested in.
	 * @param e the entity to process.
	 */
	protected abstract void process(Entity e);

    /**
     * What should happen before all entities in the System are processed?
     *
     * Override if needed,
     * Default: Nothing
     */
    protected void before() {
    }

    /**
     * What should happen after all entities in the System were processed?
     *
     * Override if needed,
     * Default: Nothing
     */
    protected void after() {
    }

	@Override
	protected final void processEntities(ImmutableBag<Entity> entities) {
        before();
		for (int i = 0, s = entities.size(); s > i; i++) {
			process(entities.get(i));
		}
        after();
	}
	
	@Override
	protected boolean checkProcessing() {
		return true;
	}
	
}
