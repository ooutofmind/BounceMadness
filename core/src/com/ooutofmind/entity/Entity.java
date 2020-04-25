package com.ooutofmind.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ooutofmind.level.Level;

public abstract class Entity {
    public float x;
    public float y;
    public float xa;
    public float ya;
    public Level level;

    public Entity(float x, float y) {
        this.x = x;
        this.y = y;
        this.xa = 0;
        this.ya = 0;
    }

    public void init(Level level) {
        this.level = level;
    }


    public abstract void tick();

    public abstract void render(SpriteBatch batch);
}
