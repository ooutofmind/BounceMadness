package com.ooutofmind.entity;

import java.util.List;

public class Platform {
    public HoleEntity holeEntity;
    public List<BlockEntity> entities;

    public Platform(HoleEntity holeEntity, List<BlockEntity> entities) {
        this.entities = entities;
        this.holeEntity = holeEntity;
    }

    public List<BlockEntity> getEntities() {
        return this.entities;
    }

    public HoleEntity getHoleEntity() {
        return holeEntity;
    }
}
