package io.houf.spaceinvaders.entity.impl;

import io.houf.spaceinvaders.Asset;
import io.houf.spaceinvaders.Game;
import io.houf.spaceinvaders.Loopable;
import io.houf.spaceinvaders.entity.Entity;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class ShipEntity extends Entity {
    private final BufferedImage image;

    public ShipEntity() {
        super(Game.WIDTH / 2 - 24, Game.HEIGHT - 124, 49, 26, 0.9f);

        this.image = Asset.load("entity/ship");
    }

    @Override
    public void initialize(Game game) {
    }

    @Override
    public void update(Game game) {
        super.update(game);

        if (game.isDown(KeyEvent.VK_RIGHT) && !game.isDown(KeyEvent.VK_LEFT) && this.getX() < Game.WIDTH - 75 - this.width) {
            this.move(3.0f, 0.0f);
        } else if (game.isDown(KeyEvent.VK_LEFT) && !game.isDown(KeyEvent.VK_RIGHT) && this.getX() > 75) {
            this.move(-3.0f, 0.0f);
        }
    }

    @Override
    public void draw(Game game, Graphics2D g) {
        g.drawImage(this.image, this.getX(), this.getY(), null, null);
    }

    @Override
    public void press(Game game, int code, char key) {
        if (code == KeyEvent.VK_UP && !game.hasLoopable(this::friendlyLaser)) {
            game.addEntity(new LaserEntity(LaserEntity.Type.FRIENDLY, this.getX() + this.width / 2, this.getY()));
        }
    }

    @Override
    public void die(Game game) {
        game.getCurrent().decreaseLives();
    }

    private boolean friendlyLaser(Loopable loopable) {
        return loopable instanceof LaserEntity && ((LaserEntity) loopable).type == LaserEntity.Type.FRIENDLY;
    }
}
