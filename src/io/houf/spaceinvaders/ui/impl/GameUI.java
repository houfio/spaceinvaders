package io.houf.spaceinvaders.ui.impl;

import io.houf.spaceinvaders.Asset;
import io.houf.spaceinvaders.Game;
import io.houf.spaceinvaders.ui.UI;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class GameUI extends UI {
    private final BufferedImage image;

    public GameUI() {
        this.image = Asset.load("entity/ship");
    }

    @Override
    public void initialize(Game game) {
    }

    @Override
    public void draw(Game game, Graphics2D g) {
        g.setColor(Color.GREEN);
        g.fillRect(0, Game.HEIGHT - 50, Game.WIDTH, 2);

        g.setColor(Color.WHITE);
        g.setFont(Game.BIG_FONT);
        g.drawString(String.valueOf(game.getCurrent().getLives()), 18, Game.HEIGHT - 18);
        g.setFont(Game.NORMAL_FONT);

        for (var i = 0; i < game.getCurrent().getLives() - 1; i++) {
            g.drawImage(this.image, 50 + i * 60, Game.HEIGHT - 38, null, null);
        }
    }

    @Override
    public void press(Game game, int code, char key) {
        if (code == KeyEvent.VK_ESCAPE) {
            game.stopGame();
            game.openUI(new MainUI());
        }
    }
}
