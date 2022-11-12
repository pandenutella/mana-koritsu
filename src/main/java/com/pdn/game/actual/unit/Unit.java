package com.pdn.game.actual.unit;

import com.pdn.game.actual.Entity;
import com.pdn.game.actual.common.Direction;
import com.pdn.game.actual.common.Location;
import com.pdn.game.actual.effect.FootMarkSpawner;
import com.pdn.game.actual.skill.ManaBlastMissile;
import com.pdn.game.actual.skill.SkillMissileManager;

import java.awt.Color;
import java.awt.Graphics;

import static com.pdn.game.actual.common.Direction.UP;
import static java.awt.Color.BLACK;

public class Unit implements Entity {
    private final String name;
    private final Location location;
    private final SkillMissileManager skillMissileManager;

    private Direction direction;
    private boolean moving;
    private double moveSpeed = 300;
    private final int size = 50;

    private final FootMarkSpawner footMarkSpawner;

    public Unit(String name, Location location, SkillMissileManager skillMissileManager) {
        this.name = name;
        this.location = location;
        this.skillMissileManager = skillMissileManager;

        direction = UP;

        Color color = new Color(0, 0, 0);
        footMarkSpawner = new FootMarkSpawner(this, color, 30, 30, 150, 750, 5);
    }

    public void moveTowards(Direction direction) {
        this.direction = direction;
        moving = true;
    }

    public void stop() {
        moving = false;
    }

    private boolean onCooldown = false;
    private double cooldownCounter = 0;

    public void useSkill() {
        if (onCooldown)
            return;

        onCooldown = true;

        double x = location.getX() + (double) (size / 2) - (double) (40 / 2);
        double y = location.getY() + (double) (size / 2) - (double) (40 / 2);

        skillMissileManager.add(new ManaBlastMissile(this, new Location(x, y), direction));
    }

    public void update(double deltaTime) {
        if (moving) {
            double distance = moveSpeed * (deltaTime / 1000);

            location.adjustTowardsDirection(distance, direction);
        }

        if (onCooldown) {
            cooldownCounter += deltaTime;

            if (cooldownCounter > 1000) {
                onCooldown = false;
                cooldownCounter = 0;
            }
        }

        footMarkSpawner.update(deltaTime);
    }

    public void render(Graphics graphics, Location screenLocation) {
        footMarkSpawner.render(graphics, screenLocation);

        graphics.setColor(BLACK);
        graphics.fillRect((int) (screenLocation.getX() + location.getX()), (int) (screenLocation.getY() + location.getY()), size, size);

    }

    public String getName() {
        return name;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public boolean isMoving() {
        return moving;
    }

    @Override
    public int getSize() {
        return size;
    }
}
