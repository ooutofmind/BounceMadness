package com.ooutofmind.screen;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ooutofmind.Const;
import com.ooutofmind.entity.Ball;
import com.ooutofmind.level.Level;

import java.util.Random;

public class GameScreen extends AbsScreen {
    private static final Random random = new Random();
    private Level level;
    private int tickTime = 0;

    public GameScreen() {
        newGame();
    }

    public void newGame() {
        level = new Level();

    }

    public void tick() {
        tickTime++;
        level.tick();
        if (tickTime % 60 == 0) {
            Ball b = new Ball(Const.WIDTH / 2, Const.HEIGHT / 3);

            b.xa = (random.nextFloat() * 2.0f - 1.0f) * 20;
            b.ya = (random.nextFloat() * 2.0f - 1.0f) * 20;
            level.addEntity(b);
        }
    }

    public void render(ShapeRenderer shapeRenderer) {

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        level.render(shapeRenderer);

        shapeRenderer.end();
    }
}
