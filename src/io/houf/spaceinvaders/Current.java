package io.houf.spaceinvaders;

import io.houf.spaceinvaders.ui.impl.ResetUI;

public class Current {
    private final Game game;

    private int lives = 3;

    public Current(Game game) {
        this.game = game;
    }

    public boolean decreaseLives() {
        this.lives--;

        if (this.lives > 0) {
            return true;
        }

        this.game.stopGame();
        this.game.openUI(new ResetUI());

        return false;
    }

    public int getLives() {
        return this.lives;
    }
}
