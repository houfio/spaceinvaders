package io.houf.spaceinvaders.entity.impl;

import io.houf.spaceinvaders.Game;
import io.houf.spaceinvaders.entity.Entity;

import java.awt.*;

public class StarEntity extends Entity {
    private final int opacity;

    public StarEntity(int x) {
        super(x, 0, 5, 5, 1.0f);

        var velocity = (float) Math.random() * 5.0f;

        this.opacity = (int) (velocity / 10 * 255);
        this.move(0.0f, velocity + 5.0f);
    }

    @Override
    public void initialize(Game game) {
    }

    @Override
    public void draw(Game game, Graphics2D g) {
        g.setColor(new Color(255, 255, 255, this.opacity));
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
}
