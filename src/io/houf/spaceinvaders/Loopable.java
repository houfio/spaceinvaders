package io.houf.spaceinvaders;

import java.awt.*;

public interface Loopable {
    void initialize(Game game);

    void update(Game game);

    void draw(Game game, Graphics2D g);

    void press(Game game, int code, char key);

    default int priority() {
        return 0;
    }
}
