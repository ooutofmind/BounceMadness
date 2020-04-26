package com.ooutofmind.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ooutofmind.Const;
import com.ooutofmind.level.Level;

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
                ya *= -bounce;
            }
        }

        xa *= 0.97; //friction
        ya *= 0.97; //friction
        ya += level.gravity;
    }

    protected boolean canMove(float xxa, float yya) {
        float x0 = x + xxa - w / 2;
        float x1 = x + xxa + w / 2;
        float y0 = y + yya - h / 2;
        float y1 = y + yya + h / 2;

        return !(y1 > Const.HEIGHT) && !(x0 < 0) && !(x1 > Const.WIDTH);
    }

    public abstract void tick();

    public abstract void render(ShapeRenderer shapeRenderer);
}
