package com.ooutofmind.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ooutofmind.Const;
import com.ooutofmind.level.Level;

import java.util.List;

public abstract class Entity {
    public float x;
    public float y;
    public float xa;
    public float ya;
    public float w;
    public float h;
    public float bounce = 0.98f; //TODO bounce should be depended on surface
    public Level level;
    public boolean removed = false;
    public boolean onGround = false;

    public Entity(float x, float y) {
        this.x = x;
        this.y = y;
        this.xa = 0;
        this.ya = 0;
    }

    public void init(Level level) {
        this.level = level;
    }

    public void attemptMove() {
        onGround = false;
        int xSteps = (int) Math.abs(xa * 100) + 1;
        for (int i = xSteps; i >= 0; i--) {
            if (canMove(xa * i / (float) xSteps, 0)) {
                x += xa * i / (float) xSteps;
                break;
            } else {
                xa *= -bounce;
            }
        }
        int ySteps = (int) Math.abs(ya * 100) + 1;
        for (int i = ySteps; i >= 0; i--) {
            if (canMove(0, ya * i / (float) ySteps)) {
                y += ya * i / (float) ySteps;
                break;
            } else {
                if (ya > 0) onGround = true;
                ya *= -bounce;
            }
        }
        xa *= 0.97; //friction
        ya *= 0.97; //friction
        ya += level.gravity;
    }

    protected boolean canMove(float xxa, float yya) {
        float xr = w / 2;
        float yr = h / 2;
        float x0 = x + xxa - xr;
        float x1 = x + xxa + xr;
        float y0 = y + yya - yr;
        float y1 = y + yya + yr;

        List<Entity> wasIntersectEntities = level.getEntities(this, x - xr, y - yr, x + xr, y + yr, null);

        List<Entity> intersectEntities = level.getEntities(this, x0, y0, x1, y1, null);

        intersectEntities.removeAll(wasIntersectEntities);

        for (Entity e : intersectEntities) {
            e.touchedBy(this);
            this.touch(e);
            if (e.blocks(this)) {
                return false;
            }
        }

        return !(x0 < 0) && !(x1 > Const.WIDTH);
    }

    public void touchedBy(Entity e) {

    }

    public void touch(Entity e) {

    }

    public boolean blocks(Entity e) {
        return false;
    }


    public boolean intersects(float x0, float y0, float x1, float y1) {
        return !(x - w / 2 > x1) && !(x + w / 2 < x0) && !(y - h / 2 > y1) && !(y + h / 2 < y0);
    }

    public abstract void tick();

    public abstract void render(ShapeRenderer shapeRenderer);
}
