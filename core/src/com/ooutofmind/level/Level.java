package com.ooutofmind.level;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ooutofmind.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private final List<Entity> entities = new ArrayList<>();

    public float gravity = 0.16554f;

    public void addEntity(Entity e) {
        entities.add(e);
        e.init(this);
    }

    public void tick() {
        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            if (!e.removed) e.tick();
            if (e.removed) {
                entities.remove(i--);
            }
        }
    }

    public void render(ShapeRenderer shapeRenderer) {
        for (Entity e: entities) {
            e.render(shapeRenderer);
        }
    }
}
