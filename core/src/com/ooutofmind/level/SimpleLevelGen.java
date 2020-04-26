package com.ooutofmind.level;

import com.ooutofmind.entity.HoleEntity;
import com.ooutofmind.entity.Platform;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimpleLevelGen implements LevelGen {


    private final Random random = new Random();
    private int directionCountDown;
    private int direction = 0;
    private int platformYOffset = 100;
    private Platform lastPlatform = new Platform(0, new HoleEntity(0, 0, HoleEntity.MIN_WIDTH), null);

    @Override
    public List<Platform> getNextBlockChunk(int chunkSize) {
        List<Platform> generated = new ArrayList<>(chunkSize);
        for (int i = 0; i < chunkSize; i++) {
            HoleEntity holeEntity = createHole();
            lastPlatform = new Platform(lastPlatform.y + platformYOffset, holeEntity, null);
            generated.add(lastPlatform);
        }

        return generated;
    }

    private HoleEntity createHole() {
        HoleEntity lastHole = lastPlatform.getHoleEntity();
        return new HoleEntity(lastHole.x + genShift(), lastHole.y + platformYOffset, genHoleWidth());
    }

    private int genHoleWidth() {
        return getRandomIntBetween(HoleEntity.MIN_WIDTH, Math.round(HoleEntity.MIN_WIDTH * 1.5F));
    }

    private int genShift() {
        if (directionCountDown == 0) {
            directionCountDown = getRandomIntBetween(2, 5);
            if(direction == 0) {
                direction = random.nextBoolean() ? 1 : -1;
            } else {
                direction = -direction;
            }
        }

        directionCountDown--;

        return direction * getRandomIntBetween(2, HoleEntity.MIN_WIDTH);
    }

    private int getRandomIntBetween(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
}
