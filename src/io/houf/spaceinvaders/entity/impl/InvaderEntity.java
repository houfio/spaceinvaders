package io.houf.spaceinvaders.entity.impl;

import io.houf.spaceinvaders.Game;
import io.houf.spaceinvaders.entity.Entity;
import io.houf.spaceinvaders.entity.Sprite;

import java.awt.*;

public class InvaderEntity extends Entity {
    public final Type type;

    private final Sprite sprite;

    public InvaderEntity(Type type, int x, int y) {
        super(x, y, 36, 24, 0.0f);

        this.type = type;
        this.sprite = new Sprite(this.type.asset, this.width, 50);
    }

    @Override
    public void initialize(Game game) {
    }

    @Override
    public void update(Game game) {
        super.update(game);

        this.sprite.update();

        if (Math.random() < 0.001f) {
            game.addEntity(new LaserEntity(LaserEntity.Type.HOSTILE, this.getX() + this.width / 2, this.getY() + this.height));
        }
    }

    @Override
    public void draw(Game game, Graphics2D g) {
       this.sprite.draw(this.getX(), this.getY(), g);
    }

    public enum Type {
        TOP("entity/invader0"),
        MIDDLE("entity/invader1"),
        BOTTOM("entity/invader2");

        public final String asset;

        Type(String asset) {
            this.asset = asset;
        }
    }
}
