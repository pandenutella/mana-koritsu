package com.pdn.game.actual;

import java.awt.Graphics;

import static com.pdn.game.actual.Direction.UP;
import static java.awt.Color.BLACK;

public class Unit {
    public static final int SIZE = 50;

    private final String name;
    private final Location location;

    private Direction direction;
    private boolean moving;
    private double moveSpeed = 300;

    private final UnitFootMarkManager unitFootMarkManager;

    public Unit(String name, Location location) {
        this.name = name;
        this.location = location;

        direction = UP;

        unitFootMarkManager = new UnitFootMarkManager(this);
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

        unitFootMarkManager.update(deltaTime);
    }

    public void render(Graphics graphics, Location screenLocation) {
        unitFootMarkManager.render(graphics, screenLocation);

        graphics.setColor(BLACK);
        graphics.fillRect((int) (screenLocation.getX() + location.getX()), (int) (screenLocation.getY() + location.getY()), SIZE, SIZE);

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
