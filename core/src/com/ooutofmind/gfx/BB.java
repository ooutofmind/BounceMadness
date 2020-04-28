package com.ooutofmind.gfx;

import java.util.ArrayList;
import java.util.List;

public class BB {
    public float x0;
    public float y0;
    public float x1;
    public float y1;

    private static final List<BB> pool = new ArrayList<>();
    private static int c = 0;

    private BB() {
        identity();
    }

    public static BB get() {
        if (c == pool.size()) {
            pool.add(new BB());
        }
        BB result = pool.get(c++);
        return result.identity();
    }

    public static void reset() {
        c = 0;
    }

    public BB identity() {
        this.x0 = Float.MAX_VALUE;
        this.y0 = Float.MAX_VALUE;
        this.x1 = Float.MIN_VALUE;
        this.y1 = Float.MIN_VALUE;
        return this;
    }


    public float w() {
        return x1 - x0;
    }

    public float h() {
        return y1 - y0;
    }

    public BB add(float x, float y) {
        if (x < x0) x0 = x;
        if (x > x1) x1 = x;
        if (y < y0) y0 = y;
        if (y > y1) y1 = y;
        return this;
    }

    public BB set(float x, float y, float w, float h) {
        return identity().add(x, y).add(w, h);
    }

    public boolean intersects(float x0, float y0, float x1, float y1) {
        return !(this.x0 > x1) && !(this.x1 < x0) && !(this.y0 > y1) && !(this.y1 < y0);
    }

    @Override
    public String toString() {
        return "BB{" +
                "x0=" + x0 +
                ", y0=" + y0 +
                ", x1=" + x1 +
                ", y1=" + y1 +
                '}';
    }
}
