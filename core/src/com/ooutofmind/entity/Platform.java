package com.ooutofmind.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ooutofmind.Const;
import com.ooutofmind.gfx.Art;
import com.ooutofmind.gfx.BB;
import com.ooutofmind.level.Level;

import java.util.ArrayList;
import java.util.List;

public class Platform extends Entity {
    public HoleEntity hole;
    public List<BlockEntity> entities = new ArrayList<>();

    int x0Hole;
    int x1Hole;

    public Platform(HoleEntity hole) {
        super(0, hole.y);

        this.h = hole.h;
        this.hole = hole;
    }

    @Override
    public void init(Level level) {
        super.init(level);

        entities.forEach(e -> e.init(level));
    }

    @Override
    public boolean blocks(Entity e) {
        return true;
    }

    @Override
    public void tick() {
        x0Hole = normalizeCoordinate((int) (hole.x + level.xOffset));
        x1Hole = normalizeCoordinate((int) (x0Hole + hole.w));
    }

    private int normalizeCoordinate(int coord) {
        while (coord < 0) coord += Const.WIDTH;
        while (coord >= Const.WIDTH) coord -= Const.WIDTH;
        return coord;
    }

    @Override
    public boolean intersects(float x0, float y0, float x1, float y1) {

        boolean result;

        if (x0Hole < x1Hole) { //normal state
            result = BB.get().set(0, hole.y, x0Hole, hole.y + Const.BLOCK_HEIGHT).intersects(x0, y0, x1, y1);
            result |= BB.get().set(x1Hole, hole.y, Const.WIDTH, hole.y + Const.BLOCK_HEIGHT).intersects(x0, y0, x1, y1);
        } else { // two holes
            result = BB.get().set(x1Hole, hole.y, x0Hole, hole.y + Const.BLOCK_HEIGHT).intersects(x0, y0, x1, y1);
        }

        return result;
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {

        if (x0Hole < x1Hole) { //normal state
            Art.quad(0, (int) hole.y, x0Hole, Const.BLOCK_HEIGHT, 1, Color.WHITE, shapeRenderer);
            Art.quad(x1Hole, (int) hole.y, Const.WIDTH - x1Hole, Const.BLOCK_HEIGHT, 1, Color.WHITE, shapeRenderer);
        } else { // two holes
            Art.quad(x1Hole, (int) hole.y, x0Hole - x1Hole, Const.BLOCK_HEIGHT, 1, Color.WHITE, shapeRenderer);
        }

        entities.forEach(e -> e.render(shapeRenderer));
    }
}
