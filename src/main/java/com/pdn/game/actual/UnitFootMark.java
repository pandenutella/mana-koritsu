package com.pdn.game.actual;

import java.awt.Color;
import java.awt.Graphics;

public class UnitFootMark {
    public static final int SIZE = 26;

    private final Location location;
    private final double markDuration;
    private double markRemaining;
    private boolean expired;

    public UnitFootMark(Location location, double duration) {
        this.location = location;
        this.markDuration = duration;

        markRemaining = duration;
        expired = false;
    }

    public void update(double deltaTime) {
        markRemaining -= deltaTime;
        if (markRemaining < 0)
            expired = true;
    }

    public void render(Graphics graphics, Location screenLocation) {
        if (expired)
            return;

        float alpha = (float) (markRemaining / markDuration);

        graphics.setColor(new Color(0, 0, 0, alpha));
        graphics.fillRect((int) (screenLocation.getX() + location.getX()), (int) (screenLocation.getY() + location.getY()), SIZE, SIZE);
    }

    public boolean isExpired() {
        return expired;
    }
}
