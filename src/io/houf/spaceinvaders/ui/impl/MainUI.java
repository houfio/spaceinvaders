package io.houf.spaceinvaders.ui.impl;

import io.houf.spaceinvaders.Game;
import io.houf.spaceinvaders.ui.UI;

import java.awt.*;
import java.util.List;
import java.util.Arrays;

public class MainUI extends UI {
    private final ButtonUI startButton;
    private final ButtonUI quitButton;

    public MainUI() {
        this.startButton = new ButtonUI("Start", 55, 380) {
            @Override
            public void click(Game game) {
                game.startGame();
                game.openUI(new GameUI());
            }
        };

        this.quitButton = new ButtonUI("Quit", 55, 400) {
            @Override
            public void click(Game game) {
                game.stop();
            }
        };
    }

    @Override
    public void initialize(Game game) {
    }

    @Override
    public void update(Game game) {
    }

    @Override
    public void draw(Game game, Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(Game.BIG_FONT);
        g.drawString("Space Invaders", 55, 320);
        g.setFont(Game.NORMAL_FONT);
        g.drawString("The most disappointing alien shooter game", 55, 340);
    }

    @Override
    public List<UI> getChildren() {
        return Arrays.asList(
            this.startButton,
            this.quitButton
        );
    }
}
