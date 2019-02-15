package io.houf.spaceinvaders;

import io.houf.spaceinvaders.ui.impl.MainUI;

import javax.swing.*;

public class Main extends JFrame {
    public Main(Game game) {
        this.setTitle("Space Invaders");
        this.setSize(Game.WIDTH, Game.HEIGHT);
        this.setResizable(false);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);

        var listener = new MoveListener(this);
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);

        this.add(game);
    }

    public static void main(String[] args) {
        var game = new Game();
        var main = new Main(game);

        game.start();
        game.openUI(new MainUI());
        main.setVisible(true);
    }
}
