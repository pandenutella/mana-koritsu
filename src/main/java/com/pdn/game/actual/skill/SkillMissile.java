package com.pdn.game.actual.skill;

import com.pdn.game.actual.Entity;
import com.pdn.game.actual.common.Direction;
import com.pdn.game.actual.common.Location;
import com.pdn.game.actual.unit.Unit;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.Color;
import java.awt.Graphics;

@RequiredArgsConstructor
public abstract class SkillMissile implements Entity {
    protected final Unit user;

    @Getter
    protected final Location location;

    @Getter
    protected final Direction direction;
    protected final double moveSpeed;
    protected final double maxRange;
    protected final Color color;

    protected double distanceTravelled;

    @Getter
    protected boolean expired = false;

    public abstract void update(double deltaTime);

    protected void updateMovement(double deltaTime) {
        double distance = moveSpeed * (deltaTime / 1000);

        location.adjustTowardsDirection(distance, direction);

        distanceTravelled += distance;
        if (distanceTravelled > maxRange)
            expired = true;
    }

    public abstract void render(Graphics graphics, Location screenLocation);

    protected void renderBody(Graphics graphics, Location screenLocation) {
        int x = (int) (screenLocation.getX() + location.getX());
        int y = (int) (screenLocation.getY() + location.getY());

        graphics.setColor(color);
        graphics.fillRect(x, y, getWidth(direction), getHeight(direction));
    }

    @Override
    public boolean isMoving() {
        return true;
    }
}
