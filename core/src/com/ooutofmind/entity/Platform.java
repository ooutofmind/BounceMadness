package com.ooutofmind.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ooutofmind.Const;
import com.ooutofmind.gfx.Art;

import java.util.List;

import static com.badlogic.gdx.graphics.Color.GRAY;

public class Platform extends Entity {
    public HoleEntity holeEntity;
    public List<BlockEntity> entities;

    public Platform(float y, HoleEntity holeEntity, List<BlockEntity> entities) {
        super(0, y);

        this.entities = entities;
        this.holeEntity = holeEntity;
    }

    public List<BlockEntity> getEntities() {
        return this.entities;
    }

    public HoleEntity getHoleEntity() {
        return holeEntity;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        float holeCenterX = Const.WIDTH / 2F + holeEntity.x;
        Art.quad((int) x, (int) y, (int)(holeCenterX - (holeEntity.w /2F)), 5, 0, GRAY, shapeRenderer);
        Art.quad((int)(holeCenterX + (holeEntity.w /2F)), (int) y, (int)(Const.WIDTH - holeCenterX + (holeEntity.w /2F)), 5, 0, GRAY, shapeRenderer);
    }
}
