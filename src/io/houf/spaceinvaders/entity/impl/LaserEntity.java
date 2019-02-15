package io.houf.spaceinvaders.entity.impl;

import io.houf.spaceinvaders.Game;
import io.houf.spaceinvaders.entity.Entity;

import java.awt.*;

public class LaserEntity extends Entity {
    public LaserEntity(int x, int y) {
        super(x, y, 2, 10, 1.0f);

        this.move(0.0f, -5.0f);
    }

    @Override
    public void initialize(Game game) {
    }

    @Override
    public void draw(Game game, Graphics2D g) {
        g.setColor(Color.GREEN);
        g.fillRect((int) this.getX(), (int) this.getY(), this.width, this.height);
    }
}
