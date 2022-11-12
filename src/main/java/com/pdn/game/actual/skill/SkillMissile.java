package com.pdn.game.actual.skill;

import com.pdn.game.actual.Entity;
import com.pdn.game.actual.common.Direction;
import com.pdn.game.actual.common.Location;
import com.pdn.game.actual.unit.Unit;

import java.awt.Graphics;

public abstract class SkillMissile implements Entity {
    protected final Unit user;
    protected final Location location;
    protected final Direction direction;
    protected final double moveSpeed;
    protected final double maxRange;

    protected double distanceTravelled;
    protected boolean expired = false;

    public SkillMissile(Unit user, Location location, Direction direction, double moveSpeed, double maxRange) {
        this.user = user;
        this.location = location;
        this.direction = direction;
        this.moveSpeed = moveSpeed;
        this.maxRange = maxRange;
    }

    public abstract void update(double deltaTime);

    protected void updateMovement(double deltaTime) {
        double distance = moveSpeed * (deltaTime / 1000);

        location.adjustTowardsDirection(distance, direction);

        distanceTravelled += distance;
        if (distanceTravelled > maxRange)
            expired = true;
    }

    public abstract void render(Graphics graphics, Location screenLocation);

    public boolean isExpired() {
        return expired;
    }

    @Override
    public boolean isMoving() {
        return true;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public Location getLocation() {
        return location;
    }
}