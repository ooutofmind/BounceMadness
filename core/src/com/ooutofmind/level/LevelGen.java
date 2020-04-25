package com.ooutofmind.level;

import com.ooutofmind.entity.DamageBlockEntity;

import java.util.List;

public interface LevelGen {
    List<List<DamageBlockEntity>> getNextBlockChunk(int chunkSize);
}
