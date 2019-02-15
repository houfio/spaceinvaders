package io.houf.spaceinvaders.entity.impl;

import io.houf.spaceinvaders.Game;
import io.houf.spaceinvaders.entity.Entity;

import java.awt.*;

public class ExplosionEntity extends Entity {
    private final int green;

    private int update;
    private int opacity = 255;

    public ExplosionEntity(int x, int y) {
        super(x, y, 5, 5, 0.925f);

        var angle = Math.random() * 360.0f;
        var multiplier = (float) Math.random() * 5.0f;
        var velocityX = (float) Math.cos(angle) * multiplier;
        var velocityY = (float) Math.sin(angle) * multiplier;

        this.green = 255 - (int) (multiplier * 51);
        this.move(velocityX, velocityY);
    }

    @Override
    public void initialize(Game game) {
    }

    @Override
    public void update(Game game) {
        super.update(game);

        this.update++;

        if (this.update > 100) {
            this.setDead();
        } else if (this.update > 50) {
            this.opacity -= 5;
        }
    }

    @Override
    public void draw(Game game, Graphics2D g) {
        g.setColor(new Color(255, this.green, 0, this.opacity));
        g.fillRect(this.getX(), this.getY(), this.width, this.height);
    }
}
