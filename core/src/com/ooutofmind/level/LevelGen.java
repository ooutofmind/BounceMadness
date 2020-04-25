package com.ooutofmind.level;

import com.ooutofmind.entity.BlockEntity;

import java.util.List;

public interface LevelGen {
    List<BlockEntity> getNextBlockChunk(int chunkSize);
}
