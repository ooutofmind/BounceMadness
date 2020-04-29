package com.ooutofmind.level;

import com.ooutofmind.Const;
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
    private Platform lastPlatform;

    @Override
    public List<Platform> getNextBlockChunk(int chunkSize) {
        List<Platform> generated = new ArrayList<>(chunkSize);
        for (int i = 0; i < chunkSize; i++) {
            if (lastPlatform == null) {
                int holeW = genHoleWidth();
                float center = (Const.WIDTH - holeW) / 2f;
                lastPlatform = new Platform(new HoleEntity(center, 10, holeW));
            } else {
                HoleEntity holeEntity = createHole();
                lastPlatform = new Platform(holeEntity);
            }

            generated.add(lastPlatform);
        }

        return generated;
    }

    private HoleEntity createHole() {
        HoleEntity lastHole = lastPlatform.hole;
        float newX;
        do {
            newX = lastHole.x + genShift();
        } while (newX < 0 || newX > Const.WIDTH);

        return new HoleEntity(newX, lastHole.y + platformYOffset, genHoleWidth());
    }

    private int genHoleWidth() {
        return getRandomIntBetween(HoleEntity.MIN_WIDTH, Math.round(HoleEntity.MIN_WIDTH * 1.8f));
    }

    private int genShift() {
        if (directionCountDown == 0) {
            directionCountDown = getRandomIntBetween(1, 3);
            if (direction == 0) {
                direction = random.nextBoolean() ? 1 : -1;
            } else {
                direction = -direction;
            }
        }

        directionCountDown--;

        return direction * getRandomIntBetween(HoleEntity.MIN_WIDTH/2, HoleEntity.MIN_WIDTH*2);
    }

    private int getRandomIntBetween(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
}
