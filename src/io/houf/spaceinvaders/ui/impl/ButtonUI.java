package io.houf.spaceinvaders.ui.impl;

import io.houf.spaceinvaders.Game;
import io.houf.spaceinvaders.ui.Selectable;
import io.houf.spaceinvaders.ui.UI;

import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class ButtonUI extends UI implements Selectable {
    private final String text;
    private final int x;
    private final int y;

    private boolean selected = false;

    public ButtonUI(String text, int x, int y) {
        this.text = text;
        this.x = x;
        this.y = y;
    }

    @Override
    public void initialize(Game game) {
    }

    public abstract void click(Game game);

    @Override
    public void draw(Game game, Graphics2D g) {
        String string = (this.selected ? "> " : "  ") + this.text;

        g.setColor(this.selected ? Color.GREEN : Color.WHITE);
        g.drawString(string, this.x, this.y + 10);
    }

    @Override
    public void press(Game game, int code, char key) {
        if (this.selected && code == KeyEvent.VK_ENTER) {
            this.click(game);
        }
    }

    @Override
    public void select(Game game, boolean selected) {
        this.selected = selected;
    }
}
