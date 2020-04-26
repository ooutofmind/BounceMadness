package com.ooutofmind.level;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ooutofmind.entity.Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Level {
    private final List<Entity> entities = new ArrayList<>();

    public float gravity = 0.98f;

    public void addEntity(Entity e) {
        entities.add(e);
        e.init(this);
    }

    public void tick() {
        for (Iterator<Entity> it = entities.iterator(); it.hasNext(); ) {
            Entity e = it.next();

            if (e.removed) {
                it.remove();
            } else {
                e.tick();
            }
        }
    }

    public void render(final ShapeRenderer shapeRenderer) {
        entities.forEach(e -> e.render(shapeRenderer));
    }
}