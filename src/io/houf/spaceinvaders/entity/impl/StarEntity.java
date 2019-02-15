package io.houf.spaceinvaders.entity.impl;

import io.houf.spaceinvaders.Game;
import io.houf.spaceinvaders.entity.Entity;

import java.awt.*;
import java.util.Random;

public class StarEntity extends Entity {
    public final Type type;

    public StarEntity(Type type, int x) {
        super(x, 0, type.size, type.size, 1.0f);

        this.type = type;
        this.move((float) Math.random() * 0.5f - 0.25f, type.velocity);
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
    public int priority() {
        return 0;
    }

    @Override
    public boolean collides(Game game, Entity entity) {
        return false;
    }

    @Override
    public boolean sessionOnly() {
        return false;
    }

    public enum Type {
        SLOW(Color.DARK_GRAY, 5.0f, 3),
        NORMAL(Color.GRAY, 10.0f, 4),
        FAST(Color.LIGHT_GRAY, 15.0f, 5);

        public final Color color;
        public final float velocity;
        public final int size;

        Type(Color color, float velocity, int size) {
            this.color = color;
            this.velocity = velocity;
            this.size = size;
        }

        public static Type random() {
            var random = new Random();
            var values = Type.values();

            return values[random.nextInt(values.length)];
        }
    }
}
