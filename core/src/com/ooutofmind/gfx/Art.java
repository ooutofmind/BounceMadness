package com.ooutofmind.gfx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Art {

    public static final Art i = new Art();

    private Art() {
    }

    public static void circle(int x, int y, int r, Color color, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.circle(x, y, r);
    }

    public static void quad(int x, int y, int w, int h, int border, Color color, ShapeRenderer shapeRenderer) {
        if (border > 0) {
            shapeRenderer.setColor(Color.GRAY);
            shapeRenderer.rect(x, y, w, h);
        }
        shapeRenderer.setColor(color);
        shapeRenderer.rect(x - border, y - border, w, h);
    }
}
