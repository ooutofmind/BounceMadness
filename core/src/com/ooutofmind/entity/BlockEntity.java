package com.ooutofmind.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ooutofmind.Const;

public class BlockEntity extends Entity {


    public BlockEntity(float x, float y, int w) {
        super(x, y);
        this.h = Const.BLOCK_HEIGHT;
        this.w = w;
        this.h = Const.BLOCK_HEIGHT;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {

    }
}
