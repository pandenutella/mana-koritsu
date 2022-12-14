package com.pdn.game.actual.common;

import lombok.Getter;

public class Location {

    @Getter
    private double x;

    @Getter
    private double y;

    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void adjustTowardsDirection(double distance, Direction direction) {
        switch (direction) {
            case UP:
                y -= distance;
                break;
            case DOWN:
                y += distance;
                break;
            case LEFT:
                x -= distance;
                break;
            case RIGHT:
                x += distance;
                break;
        }
    }

    public void adjustX(double x) {
        this.x += x;
    }

    public void adjustY(double y) {
        this.y += y;
    }
}
