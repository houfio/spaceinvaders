package io.houf.spaceinvaders.ui.impl;

import io.houf.spaceinvaders.Game;
import io.houf.spaceinvaders.ui.UI;

import java.awt.*;

public class GameUI extends UI {
    @Override
    public void initialize(Game game) {
    }

    @Override
    public void draw(Game game, Graphics2D g) {
        g.setColor(Color.GREEN);
        g.fillRect(0, Game.HEIGHT - 50, Game.WIDTH, 2);
    }
}
