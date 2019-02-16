package io.houf.spaceinvaders.ui.impl;

import io.houf.spaceinvaders.Game;
import io.houf.spaceinvaders.ui.UI;

import java.awt.*;
import java.util.List;

public class ResetUI extends UI {
    private final boolean success;
    private final ButtonUI backButton;

    public ResetUI(boolean success) {
        this.success = success;
        this.backButton = new ButtonUI("Back to menu", 55, 380) {
            @Override
            public void click(Game game) {
                game.openUI(new MainUI());
            }
        };
    }

    @Override
    public void initialize(Game game) {
        game.stopGame();
    }

    @Override
    public void draw(Game game, Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(Game.BIG_FONT);
        g.drawString(this.success ? "You win!" : "Game over!", 55, 320);
        g.setFont(Game.NORMAL_FONT);
    }

    @Override
    public List<UI> getChildren() {
        return List.of(this.backButton);
    }
}
