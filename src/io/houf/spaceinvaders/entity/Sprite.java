package io.houf.spaceinvaders.entity;

import io.houf.spaceinvaders.Asset;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {
    private final BufferedImage image;
    private final int width;
    private final int speed;

    private int update = 0;
    private int index = 0;

    public Sprite(String image, int width, int speed) {
        this.image = Asset.load(image);
        this.width = width;
        this.speed = speed;
    }

    public void update() {
        this.update++;

        if (this.update % this.speed == 0) {
            this.index = (this.index + 1) % (this.image.getWidth() / this.width);
        }
    }

    public void draw(int x, int y, Graphics2D g) {
        var offset = this.index * this.width;
        var height = this.image.getHeight();

        g.drawImage(this.image, x, y, x + this.width, y + height, offset, 0, offset + this.width, height, null, null);
    }
}
