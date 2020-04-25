package com.ooutofmind;

import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;
import java.util.List;

public class Reg implements Disposable {
    private static final List<Disposable> toBeRemoved = new ArrayList<>();

    public static final Reg i = new Reg();

    private Reg() {
    }

    public <T extends Disposable> T add(T object) {
        toBeRemoved.add(object);
        return object;
    }

    @Override
    public void dispose() {
        for (Disposable disposable : toBeRemoved) {
            disposable.dispose();
        }
    }


}
