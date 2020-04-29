package com.ooutofmind.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ooutofmind.Const;
import com.ooutofmind.gfx.Art;

import static com.badlogic.gdx.graphics.Color.GREEN;

public class BlockEntity extends Entity {

    public BlockEntity(float x, float y, int w) {
        super(x, y);
        this.w = w;
        this.h = Const.BLOCK_HEIGHT;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        int xOffset = level == null ? 0 : level.xOffset;
        Art.quad((int) x + xOffset, (int) y, (int) w, (int) h, 1, GREEN, shapeRenderer);
    }
}
