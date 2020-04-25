package com.ooutofmind.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity {
    public float x;
    public float y;
    public float xa;
    public float ya;

    public Entity(float x, float y) {
        this.x = x;
        this.y = y;
        this.xa = 0;
        this.ya = 0;
    }

    public abstract void tick();

    public abstract void render(SpriteBatch batch);
}
