package com.ooutofmind.level;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ooutofmind.Const;
import com.ooutofmind.entity.Entity;
import com.ooutofmind.entity.Platform;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Level {
    private final List<Entity> entities = new ArrayList<>();

    public float gravity = 0.98f;
    public int xOffset = 0;
    public int yOffset = 0;

    private SimpleLevelGen levelGen = new SimpleLevelGen();

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void addEntity(Entity e) {
        entities.add(e);
        e.init(this);
    }

    public void tick() {
        if (yOffset == 0 || yOffset + Const.HEIGHT / 2 > levelGen.getYOffset()) {
            System.out.println("WELL, Let's generate");
            levelGen.getNextBlockChunk(1)
                    .forEach(this::addEntity);

        }

        for (Iterator<Entity> it = entities.iterator(); it.hasNext(); ) {
            Entity e = it.next();

            if (!e.removed) {
                e.tick();
                if (e instanceof Platform && e.y < yOffset - Const.HEIGHT / 2) {
                    System.out.println("Sorry.. Please get out of here");
                    e.removed = true;
                }
            }

            if (e.removed) {
                it.remove();
            }
        }
    }

    public void render(final ShapeRenderer shapeRenderer) {
        entities.forEach(e -> e.render(shapeRenderer));
    }

    public interface EntityFilter {
        boolean accept(Entity e);
    }

    public List<Entity> getEntities(Entity owner, float x0, float y0, float x1, float y1, EntityFilter filter) {
        List<Entity> result = EntityListPool.get();
        for (Entity e : entities) {
            if (e == owner) continue;
            if (filter != null && !filter.accept(e)) continue;
            if (e.intersects(x0, y0, x1, y1)) {
                result.add(e);
            }
        }

        return result;
    }
}