package io.houf.spaceinvaders.entity.impl;

import io.houf.spaceinvaders.Asset;
import io.houf.spaceinvaders.Game;
import io.houf.spaceinvaders.entity.Entity;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class PlayerEntity extends Entity {
    private final BufferedImage image;

    public PlayerEntity() {
        super(Game.WIDTH / 2 - 24, Game.HEIGHT - 124, 49, 26, 0.9f);

        this.image = Asset.load("entity/player");
    }

    @Override
    public void initialize(Game game) {
    }

    @Override
    public void update(Game game) {
        super.update(game);

        if (game.isDown(KeyEvent.VK_X)) {
            this.move(3.0f, 0.0f);
        } else if (game.isDown(KeyEvent.VK_Z)) {
            this.move(-3.0f, 0.0f);
        }

        this.setPosition(Math.max(50.0f, Math.min(Game.WIDTH - 50.0f - this.width, this.getX())), this.getY());
    }

    @Override
    public void draw(Game game, Graphics2D g) {
        g.drawImage(this.image, (int) this.getX(), (int) this.getY(), null, null);
    }

    @Override
    public void press(Game game, int code, char key) {
        if (code == KeyEvent.VK_SPACE && !game.hasLoopable(LaserEntity.class::isInstance)) {
            game.addEntity(new LaserEntity((int) this.getX() + this.width / 2, (int) this.getY()));
        }
    }
}
