package com.ooutofmind.screen;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ooutofmind.Const;
import com.ooutofmind.entity.Ball;
import com.ooutofmind.level.Level;
import com.ooutofmind.level.SimpleLevelGen;

import java.util.Random;

public class GameScreen extends AbsScreen {
    private static final Random random = new Random();
    private Level level;
    private int tickTime = 0;
    private SimpleLevelGen levelGen = new SimpleLevelGen();

    public GameScreen() {
        newGame();
    }

    public void newGame() {
        level = new Level();

        Ball b = new Ball(Const.WIDTH / 2F, 0);
        level.addEntity(b);

        levelGen.getNextBlockChunk(5)
                .forEach(level::addEntity);
    }

    public void tick() {
        tickTime++;
        level.tick();
    }

    public void render(ShapeRenderer shapeRenderer) {

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        level.render(shapeRenderer);

        shapeRenderer.end();
    }
}
