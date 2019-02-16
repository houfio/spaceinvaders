package io.houf.spaceinvaders;

import io.houf.spaceinvaders.entity.impl.InvaderEntity;
import io.houf.spaceinvaders.entity.impl.ShipEntity;
import io.houf.spaceinvaders.ui.impl.ResetUI;

public class Current {
    private final Game game;

    private int lives = 3;

    public Current(Game game) {
        this.game = game;
    }

    public void initialize() {
        this.game.addEntity(new ShipEntity());

        for (var x = 0; x < 8; x++) {
            for (var y = 0; y < 5; y++) {
                var type = y > 2 ? InvaderEntity.Type.BOTTOM : y > 0 ? InvaderEntity.Type.MIDDLE : InvaderEntity.Type.TOP;

                this.game.addEntity(new InvaderEntity(type, 35 + 56 * x, 50 + 44 * y));
            }
        }
    }

    public void decreaseLives() {
        if (--this.lives > 0) {
            this.game.addEntity(new ShipEntity());
        } else {
            this.game.openUI(new ResetUI(false));
        }
    }

    public int getLives() {
        return this.lives;
    }
}
