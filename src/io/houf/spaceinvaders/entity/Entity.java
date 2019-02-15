package io.houf.spaceinvaders.entity;

import io.houf.spaceinvaders.Game;
import io.houf.spaceinvaders.Loopable;

public abstract class Entity implements Loopable {
    public final int width;
    public final int height;

    private float positionX;
    private float positionY;
    private float velocityX;
    private float velocityY;
    private boolean dead;

    private final float gravity;

    public Entity(int x, int y, int width, int height, float gravity) {
        this.width = width;
        this.height = height;

        this.positionX = x;
        this.positionY = y;

        this.gravity = gravity;
    }

    @Override
    public void update(Game game) {
        if (this.getX() + this.width < 0 || this.getX() - this.width > Game.WIDTH || this.getY() + this.height < 0 || this.getY() - this.height > Game.HEIGHT) {
            this.setDead();
        }

        if (this.dead) {
            this.die(game);
            game.loopables.remove(this);

            return;
        }

        this.positionX += this.velocityX;
        this.positionY += this.velocityY;
        this.velocityX *= this.gravity;
        this.velocityY *= this.gravity;

        game.loopables.stream()
            .filter(Entity.class::isInstance)
            .map(Entity.class::cast)
            .filter(entity -> entity.collides(game, this))
            .forEach(entity -> this.collide(game, entity));
    }

    @Override
    public void press(Game game, int code, char key) {
    }

    @Override
    public int priority() {
        return 50;
    }

    public boolean collides(Game game, Entity entity) {
        return this != entity
            && this.getX() < entity.getX() + entity.width
            && this.getX() + this.width > entity.getX()
            && this.getY() < entity.getY() + entity.height
            && this.height + this.getY() > entity.getY();
    }

    public void collide(Game game, Entity entity) {
    }

    public void die(Game game) {
    }

    public boolean sessionOnly() {
        return true;
    }

    public void move(float x, float y) {
        this.velocityX = x;
        this.velocityY = y;
    }

    public int getX() {
        return (int) this.positionX;
    }

    public int getY() {
        return (int) this.positionY;
    }

    public void setDead() {
        this.dead = true;
    }
}
