package com.ooutofmind.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ooutofmind.Const;
import com.ooutofmind.gfx.Art;

public class Ball extends Entity {
    public static final int MAX_SPEED = 7;
    public float minY;

    public Ball(float x, float y) {
        super(x, y);
        this.minY = y;
        this.w = Const.BALL_RADIUS * 2 * 0.8f;
        this.h = Const.BALL_RADIUS * 2 * 0.8f;
        this.bounce = 0.1f;
    }


    public void tick() {
        attemptMove();
        if (minY < y) {
            minY = y;
        }
        if (onGround) {
            jump();
        }
    }

    public void jump() {
        ya -= MAX_SPEED;
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        Art.circle((int) (x), (int) (y), Const.BALL_RADIUS, Color.RED, shapeRenderer);
    }
}
