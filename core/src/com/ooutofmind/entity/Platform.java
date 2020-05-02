package com.ooutofmind.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ooutofmind.Const;
import com.ooutofmind.gfx.Art;
import com.ooutofmind.gfx.BB;
import com.ooutofmind.level.Level;

import java.util.ArrayList;
import java.util.List;

import static com.ooutofmind.Const.BLOCK_HEIGHT;
import static com.ooutofmind.level.SimpleLevelGen.PLATFORM_SPACING;

public class Platform extends Entity {
    public HoleEntity hole;
    public List<BlockEntity> entities = new ArrayList<>();
    private final WallType wallType;

    int x0Hole;
    int x1Hole;
    public static final int LOW_WALL_HEIGHT = (int) ((PLATFORM_SPACING - BLOCK_HEIGHT) * .3);
    public static final int HIGH_WALL_HEIGHT = (int) ((PLATFORM_SPACING - BLOCK_HEIGHT) * .8);

    public Platform(HoleEntity hole, WallType wallType) {
        super(0, hole.y);

        this.h = hole.h;
        this.hole = hole;
        this.wallType = wallType;
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
            result = BB.get().set(0, hole.y, x0Hole, hole.y + BLOCK_HEIGHT).intersects(x0, y0, x1, y1);
            result |= BB.get().set(x1Hole, hole.y, Const.WIDTH, hole.y + BLOCK_HEIGHT).intersects(x0, y0, x1, y1);
        } else { // two holes
            result = BB.get().set(x1Hole, hole.y, x0Hole, hole.y + BLOCK_HEIGHT).intersects(x0, y0, x1, y1);
        }

        return result;
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {

        Color color = Color.GREEN;
        if (x0Hole < x1Hole) { //normal state
            Art.quad(0, (int) hole.y, x0Hole, BLOCK_HEIGHT, 1, color, shapeRenderer);
            Art.quad(x1Hole, (int) hole.y, Const.WIDTH - x1Hole, BLOCK_HEIGHT, 1, color, shapeRenderer);
        } else { // two holes
            Art.quad(x1Hole, (int) hole.y, x0Hole - x1Hole, BLOCK_HEIGHT, 1, color, shapeRenderer);
        }

        entities.forEach(e -> e.render(shapeRenderer));

        renderWalls(shapeRenderer);
    }

    private void renderWalls(ShapeRenderer shapeRenderer) {
        int x0 = x0Hole - BLOCK_HEIGHT;
        int x1 = x1Hole;

        switch (wallType) {
            case R_LOWER:
                renderLowerWall(x1, shapeRenderer);
                break;
            case L_LOWER:
                renderLowerWall(x0, shapeRenderer);
                break;
            case LOWER:
                renderLowerWall(x0, shapeRenderer);
                renderLowerWall(x1, shapeRenderer);
                break;
            case L_HIGHER:
                renderHigherWall(x0, shapeRenderer);
                renderLowerWall(x1, shapeRenderer);
                break;
            case R_HIGHER:
                renderLowerWall(x0, shapeRenderer);
                renderHigherWall(x1, shapeRenderer);
                break;
        }
    }

    private void renderHigherWall(int x, ShapeRenderer shapeRenderer) {
        Art.quad(x, (int) y - HIGH_WALL_HEIGHT + BLOCK_HEIGHT, BLOCK_HEIGHT, HIGH_WALL_HEIGHT, 1, Color.FOREST, shapeRenderer);
    }

    private void renderLowerWall(int x, ShapeRenderer shapeRenderer) {
        Art.quad(x, (int) y - LOW_WALL_HEIGHT + BLOCK_HEIGHT, BLOCK_HEIGHT, LOW_WALL_HEIGHT, 1, Color.FOREST, shapeRenderer);
    }

    public enum WallType {
        NO, L_HIGHER, R_HIGHER, R_LOWER, L_LOWER, LOWER
    }
}
