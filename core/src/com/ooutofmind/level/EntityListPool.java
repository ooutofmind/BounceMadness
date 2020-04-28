package com.ooutofmind.level;

import com.ooutofmind.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class EntityListPool {
    private static final List<List<Entity>> pool = new ArrayList<>();
    private static int c;

    public static List<Entity> get() {
        if (c == pool.size()) {
            pool.add(new ArrayList<>());
        }
        List<Entity> result = pool.get(c++);
        result.clear();
        return result;
    }

    public static void reset() {
        c = 0;
    }


}
