package com.ooutofmind.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BlockEntity extends Entity {
    public int w;
    public int h;

    public BlockEntity(float x, float y, int w, int h) {
        super(x, y);
        this.w = w;
        this.h = h;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(SpriteBatch batch) {

    }
}
