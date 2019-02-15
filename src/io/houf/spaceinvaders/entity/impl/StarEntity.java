package io.houf.spaceinvaders.entity.impl;

import io.houf.spaceinvaders.Game;
import io.houf.spaceinvaders.entity.Entity;

import java.awt.*;
import java.util.Random;

public class StarEntity extends Entity {
    private final Color color;

    public StarEntity(Speed speed, int x) {
        super(x, 0, speed.size, speed.size, 1.0f);

        this.color = speed.color;
        this.move((float) Math.random() - 0.5f, speed.velocity);
    }

    @Override
    public void initialize(Game game) {
    }

    @Override
    public void draw(Game game, Graphics2D g) {
        g.setColor(this.color);
        g.fillRect((int) this.getX(), (int) this.getY(), this.width, this.height);
    }

    @Override
    public int priority() {
        return 0;
    }

    public boolean game() {
        return false;
    }

    public enum Speed {
        SLOW(Color.DARK_GRAY, 5.0f, 3),
        NORMAL(Color.GRAY, 10.0f, 4),
        FAST(Color.LIGHT_GRAY, 15.0f, 5);

        public final Color color;
        public final float velocity;
        public final int size;

        Speed(Color color, float velocity, int size) {
            this.color = color;
            this.velocity = velocity;
            this.size = size;
        }

        public static Speed random() {
            var random = new Random();
            var values = Speed.values();

            return values[random.nextInt(values.length)];
        }
    }
}
