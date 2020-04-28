package com.ooutofmind.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ooutofmind.gfx.Art;
import com.ooutofmind.gfx.BB;

import java.util.List;

import static com.badlogic.gdx.graphics.Color.GREEN;

public class Platform extends Entity {
    public HoleEntity hole;
    public List<BlockEntity> entities;
    public BB bb = BB.get();

    public Platform(float y, HoleEntity hole, List<BlockEntity> entities) {
        super(0, y);
        this.entities = entities;
        this.hole = hole;
    }

    @Override
    public boolean blocks(Entity e) {
        return true;
    }

    @Override
    public void tick() {

    }

    @Override
    public boolean intersects(float x0, float y0, float x1, float y1) {
        this.bb = BB.get();

        float xx = hole.x + level.xOffset;
        float yy = y;
        bb.add(xx, yy);
        bb.add(xx + hole.w, yy + hole.h);


        return bb.intersects(x0, y0, x1, y1);
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        // float cx = level.xOffset + hole.x;
        //Art.quad((int) x, (int) y, (int) (cx - (hole.w / 2F)), Const.BLOCK_HEIGHT, 1, WHITE, shapeRenderer);
        // Art.quad((int) (cx + (hole.w / 2F)), (int) y, (int) (Const.WIDTH - cx + (hole.w / 2F)), Const.BLOCK_HEIGHT, 1, WHITE, shapeRenderer);
        Art.quad((int) bb.x0, (int) bb.y0, (int) bb.w(), (int) bb.h(), 1, GREEN, shapeRenderer);
    }
}
