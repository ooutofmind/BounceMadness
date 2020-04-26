package com.ooutofmind.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ooutofmind.Const;
import com.ooutofmind.gfx.Art;

public class Ball extends Entity {
    public static final int MAX_SPEED = 15;
    public float bounce = 1f;

    public Ball(float x, float y) {
        super(x, y);
        this.w = Const.BALL_RADIUS * 2;
        this.h = Const.BALL_RADIUS * 2;
    }


    public void tick() {
        attemptMove();
    }

    @Override
    public void attemptMove() {
        int ySteps = (int) Math.abs(ya * 100) + 1;
        for (int i = ySteps; i >= 0; i--) {
            float yya = ya * i / (float) ySteps;
            if (canMove(0, yya)) {
                y += yya;
                break;
            } else {
                ya = MAX_SPEED * -bounce;
            }
        }

        ya += level.gravity;
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        Art.circle((int) (x), (int) (y), Const.BALL_RADIUS, Color.RED, shapeRenderer);
    }
}
