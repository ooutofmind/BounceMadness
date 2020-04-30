package com.ooutofmind.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ooutofmind.Const;
import com.ooutofmind.entity.Ball;
import com.ooutofmind.level.Level;

import java.util.Random;

public class GameScreen extends AbsScreen {
    private static final Random random = new Random();
    private Level level;
    private float xOffsetA = 0;
    private float xOffset = 0;
    private Ball b;

    public GameScreen() {
        newGame();
    }

    public void newGame() {
        level = new Level();

        b = new Ball(Const.WIDTH / 2F, 0);
        level.addEntity(b);
    }

    public void tick() {
        level.tick();

        float scrollSpeed = 1.82f;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) xOffsetA += scrollSpeed;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) xOffsetA -= scrollSpeed;

        xOffset += xOffsetA;
        xOffsetA *= 0.77;

        level.setOffset((int) xOffset, (int)b.minY);
    }

    public void render(ShapeRenderer shapeRenderer, Camera camera) {



        camera.position.y = b.minY;

        camera.update();

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        level.render(shapeRenderer);

        shapeRenderer.end();
    }
}
