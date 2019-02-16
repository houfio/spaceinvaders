package io.houf.spaceinvaders;

import io.houf.spaceinvaders.entity.Entity;
import io.houf.spaceinvaders.entity.impl.InvaderEntity;
import io.houf.spaceinvaders.entity.impl.ShipEntity;
import io.houf.spaceinvaders.entity.impl.StarEntity;
import io.houf.spaceinvaders.ui.Selectable;
import io.houf.spaceinvaders.ui.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Game extends JPanel implements Runnable, KeyListener {
    public static final int UPS = 100;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 750;

    public static final Font NORMAL_FONT = new Font("monospaced", Font.BOLD, 16);
    public static final Font BIG_FONT = new Font("monospaced", Font.BOLD, 24);

    public final List<Loopable> loopables;

    private final Set<Integer> pressed;

    private boolean running = true;
    private Current current;
    private int update = 0;
    private int selected = 0;

    public Game() {
        this.setFocusable(true);
        this.addKeyListener(this);
        this.setForeground(Color.WHITE);
        this.setBackground(Color.BLACK);
        this.setFont(Game.NORMAL_FONT);
        this.setFocusTraversalKeysEnabled(false);

        this.loopables = new CopyOnWriteArrayList<>();
        this.pressed = new HashSet<>();
    }

    public void start() {
        new Thread(this).start();
    }

    public void stop() {
        this.running = false;
    }

    public void startGame() {
        this.current = new Current(this);
        this.current.initialize();
    }

    public void stopGame() {
        this.loopables.removeIf(loopable -> loopable instanceof Entity && ((Entity) loopable).sessionOnly());
    }

    public void openUI(UI ui) {
        this.loopables.removeIf(UI.class::isInstance);
        this.addUI(ui);

        this.selected = 0;
        this.updateSelected();
    }

    private void addUI(UI ui) {
        ui.initialize(this);
        this.loopables.add(ui);

        var children = ui.getChildren();

        if (children != null) {
            children.forEach(this::addUI);
        }
    }

    private void updateSelected() {
        var selectables = this.loopables.stream()
            .filter(Selectable.class::isInstance)
            .map(Selectable.class::cast)
            .collect(Collectors.toList());

        selectables.forEach(selectable -> selectable.select(this, selectables.indexOf(selectable) == this.selected));
    }

    public void addEntity(Entity entity) {
        entity.initialize(this);
        this.loopables.add(entity);
    }

    @Override
    public void run() {
        var last = System.nanoTime();
        var updateDelta = 0.0d;
        var upns = 1000000000.0d / Game.UPS;

        while (this.running) {
            var now = System.nanoTime();

            updateDelta += (now - last) / upns;
            last = now;

            if (updateDelta >= 1.0d) {
                this.update();

                updateDelta--;
            }

            this.repaint();
        }

        System.exit(0);
    }

    private void update() {
        this.update++;

        if (this.update % 4 == 0) {
            this.addEntity(new StarEntity((int) (Math.random() * Game.WIDTH)));
        }

        this.loopables.stream()
            .sorted(Comparator.comparingInt(Loopable::priority))
            .forEach(loopable -> loopable.update(this));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.loopables.stream()
            .sorted(Comparator.comparingInt(Loopable::priority))
            .forEach(loopable -> loopable.draw(this, (Graphics2D) g));
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        var code = e.getKeyCode();
        var min = 0;
        var max = (int) this.loopables.stream().filter(Selectable.class::isInstance).count() - 1;

        if (code == KeyEvent.VK_UP) {
            this.selected = this.selected == min ? max : this.selected - 1;
        } else if (code == KeyEvent.VK_DOWN) {
            this.selected = this.selected == max ? min : this.selected + 1;
        }

        this.updateSelected();

        this.loopables.stream()
            .sorted(Comparator.comparingInt(Loopable::priority))
            .forEach(loopable -> loopable.press(this, e.getExtendedKeyCode(), e.getKeyChar()));

        this.pressed.add(e.getExtendedKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.pressed.remove(e.getExtendedKeyCode());
    }

    public Current getCurrent() {
        return this.current;
    }

    public boolean isDown(int code) {
        return this.pressed.contains(code);
    }

    public boolean hasLoopable(Predicate<? super Loopable> predicate) {
        return this.loopables.stream().anyMatch(predicate);
    }
}
