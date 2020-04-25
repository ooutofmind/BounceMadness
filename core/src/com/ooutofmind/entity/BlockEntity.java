package com.ooutofmind.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ooutofmind.Const;

public class BlockEntity extends Entity {
    public int w;
    public int h;


    public BlockEntity(float x, float y, int w) {
        super(x, y);
        this.w = w;
        this.h = Const.BLOCK_HEIGHT;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(SpriteBatch batch) {

    }
}
