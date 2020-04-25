package com.ooutofmind.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ooutofmind.Const;
import com.ooutofmind.gfx.Art;
import com.ooutofmind.level.Level;

public class GameScreen extends AbsScreen {

    private Level level;

    public GameScreen() {
        this.level = new Level();
    }

    public void newGame() {

    }

    public void tick() {

    }

    public void render(ShapeRenderer shapeRenderer) {

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        Art.circle((Const.WIDTH - (Const.BALL_RADIUS << 1)) >> 1, Const.HEIGHT / 3, Const.BALL_RADIUS, Color.RED, shapeRenderer);

        Art.quad(10, Const.HEIGHT / 2, 100, 8, 2, Color.WHITE, shapeRenderer);

        shapeRenderer.end();
    }
}
