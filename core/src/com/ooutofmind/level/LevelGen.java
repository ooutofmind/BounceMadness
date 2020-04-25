package com.ooutofmind.level;

import com.ooutofmind.entity.Platform;

import java.util.List;

public interface LevelGen {
    List<Platform> getNextBlockChunk(int chunkSize);
}
