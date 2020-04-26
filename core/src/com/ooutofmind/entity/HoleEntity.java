package com.ooutofmind.entity;

import com.ooutofmind.Const;

public class HoleEntity extends BlockEntity {
    public static final int MIN_WIDTH = Const.BALL_RADIUS * 2 + 2;

    public HoleEntity(float x, float y, int w) {
        super(x, y, w);
    }

    @Override
    public String toString() {
        return "HoleEntity{" +
                "w=" + w +
                ", h=" + h +
                ", x=" + x +
                ", y=" + y +
                ", ya=" + ya +
                ", level=" + level +
                '}';
    }
}
