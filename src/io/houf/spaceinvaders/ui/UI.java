package io.houf.spaceinvaders.ui;

import io.houf.spaceinvaders.Game;
import io.houf.spaceinvaders.Loopable;

import java.util.List;

public abstract class UI implements Loopable {
    @Override
    public void update(Game game) {
    }

    @Override
    public void press(Game game, int code, char key) {
    }

    @Override
    public int priority() {
        return 100;
    }

    public List<UI> getChildren() {
        return null;
    }
}
