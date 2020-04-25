package com.ooutofmind.screen;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class AbsScreen extends ScreenAdapter {
    public abstract void tick();

    public abstract void render(ShapeRenderer shapeRenderer);
}
