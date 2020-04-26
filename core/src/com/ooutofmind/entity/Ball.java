package com.ooutofmind.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ooutofmind.Const;
import com.ooutofmind.gfx.Art;

public class Ball extends Entity {

    public Ball(float x, float y) {
        super(x, y);
        this.w = Const.BALL_RADIUS * 2;
        this.h = Const.BALL_RADIUS * 2;
    }


    public void tick() {
        attemptMove();
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        Art.circle((int) (x ), (int) (y ), Const.BALL_RADIUS, Color.RED, shapeRenderer);
    }
}
