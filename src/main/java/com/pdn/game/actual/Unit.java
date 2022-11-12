package com.pdn.game.actual;

import java.awt.Graphics;

import static com.pdn.game.actual.Direction.UP;
import static java.awt.Color.BLACK;

public class Unit {
    private final String name;
    private final Location location;

    private Direction direction;
    private boolean moving;
    private double moveSpeed = 200;

    public Unit(String name, Location location) {
        this.name = name;
        this.location = location;

        direction = UP;
    }

    public void moveTowards(Direction direction) {
        this.direction = direction;
        moving = true;
    }

    public void stop() {
        moving = false;
    }

    public void update(double deltaTime) {
        if (moving) {
            double distance = moveSpeed * (deltaTime / 1000);

            location.adjustTowardsDirection(distance, direction);
        }
    }

    public void render(Graphics graphics) {
        graphics.setColor(BLACK);
        graphics.fillRect((int) location.getX(), (int) location.getY(), 50, 50);
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isMoving() {
        return moving;
    }
}
