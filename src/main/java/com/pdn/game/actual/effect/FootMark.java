package com.pdn.game.actual.effect;

import com.pdn.game.actual.common.Location;

import java.awt.Color;
import java.awt.Graphics;

public class FootMark {
    private final Location location;
    private final Color color;
    private final int size;
    private final int shrunkSize;
    private final double markDuration;

    private double markRemaining;
    private boolean expired;

    public FootMark(Location location, Color color, int size, int shrunkSize, double duration) {
        this.location = location;
        this.color = color;
        this.size = size;
        this.shrunkSize = shrunkSize;
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

        float red = (float) color.getRed() / 255;
        float green = (float) color.getGreen() / 255;
        float blue = (float) color.getBlue() / 255;
        float alpha = (float) (markRemaining / markDuration);

        int currentSize = ((int) ((size - (shrunkSize)) * (markRemaining / markDuration))) + shrunkSize;
        int reducedSize = size - currentSize;

        int x = (int) (screenLocation.getX() + location.getX() + (reducedSize / 2));
        int y = (int) (screenLocation.getY() + location.getY() + (reducedSize / 2));

        graphics.setColor(new Color(red, green, blue, alpha));
        graphics.fillRect(x, y, currentSize, currentSize);
    }

    public boolean isExpired() {
        return expired;
    }
}
