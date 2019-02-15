package io.houf.spaceinvaders.entity.impl;

import io.houf.spaceinvaders.Game;
import io.houf.spaceinvaders.entity.Entity;

import java.awt.*;
import java.util.function.Function;

public class LaserEntity extends Entity {
    public final Type type;

    public LaserEntity(Type type, int x, int y) {
        super(x, y, 2, 10, 1.0f);

        this.type = type;
        this.move(0.0f, type.velocity);
    }

    @Override
    public void initialize(Game game) {
    }

    @Override
    public void draw(Game game, Graphics2D g) {
        g.setColor(this.type.color);
        g.fillRect(this.getX(), this.getY(), this.width, this.height);
    }

    @Override
    public void collide(Game game, Entity entity) {
        if (this.type.kill.apply(entity)) {
            entity.setDead();
            this.setDead();

            for (var i = 0; i < 100; i++) {
                game.addEntity(new ExplosionEntity(entity.getX() + entity.width / 2, entity.getY() + entity.height / 2));
            }
        }
    }

    public enum Type {
        FRIENDLY(Color.GREEN, -5.0f, InvaderEntity.class::isInstance),
        HOSTILE(Color.WHITE, 5.0f, ShipEntity.class::isInstance);

        public final Color color;
        public final float velocity;
        public final Function<Entity, Boolean> kill;

        Type(Color color, float velocity, Function<Entity, Boolean> kill) {
            this.color = color;
            this.velocity = velocity;
            this.kill = kill;
        }
    }
}
