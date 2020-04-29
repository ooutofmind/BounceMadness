package com.ooutofmind.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ooutofmind.Const;
import com.ooutofmind.gfx.BB;
import com.ooutofmind.level.Level;

import java.util.ArrayList;
import java.util.List;

public class Platform extends Entity {
    public HoleEntity hole;
    public List<BlockEntity> entities = new ArrayList<>();
    public BlockEntity left;
    public BlockEntity right;

    public BB bb = BB.get();

    public Platform(HoleEntity hole) {
        super(0, hole.y);

        this.h = hole.h;
        this.hole = hole;

        left = new BlockEntity(0, y, (int) hole.x);
        right = new BlockEntity(hole.x + hole.w, y, Const.WIDTH - (int) (hole.x + hole.w));
    }

    @Override
    public void init(Level level) {
        super.init(level);

        left.init(level);
        right.init(level);

        entities.forEach(e -> e.init(level));
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

        boolean intersects;

        for (Entity e : entities) {
            intersects = bbOf(e).intersects(x0, y0, x1, y1);
            if (intersects) return true;
        }

        intersects = bbOf(left).intersects(x0, y0, x1, y1);
        if (intersects) return true;

        intersects = bbOf(right).intersects(x0, y0, x1, y1);
        return intersects;
    }

    private BB bbOf(Entity e) {
        bb = bb.identity();
        float xx = e.x + level.xOffset;
        float yy = e.y;
        bb.add(xx, yy);
        bb.add(xx + e.w, yy + e.h);
        return bb;
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        left.render(shapeRenderer);
        right.render(shapeRenderer);

        entities.forEach(e -> e.render(shapeRenderer));
    }
}
