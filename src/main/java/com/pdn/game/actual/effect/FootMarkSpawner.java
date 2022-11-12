package com.pdn.game.actual.effect;

import com.pdn.game.actual.Entity;
import com.pdn.game.actual.common.Location;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class FootMarkSpawner {
    private final Entity entity;
    private final Color color;
    private final int size;
    private final int shrunkSize;
    private final double spawnTime;
    private final double duration;
    private final int offset;

    private final List<FootMark> footMarkList = new ArrayList<>();

    private double accumulated = 0;
    private boolean leftFoot = true;

    public FootMarkSpawner(Entity entity, Color color, int size, int shrunkSize, double spawnTime, double duration, int offset) {
        this.entity = entity;
        this.color = color;
        this.size = size;
        this.shrunkSize = shrunkSize;
        this.spawnTime = spawnTime;
        this.duration = duration;
        this.offset = offset;
    }

    private void spawn(double deltaTime) {
        accumulated += deltaTime;
        if (accumulated > spawnTime) {
            accumulated -= spawnTime;

            int markX = 0;
            int markY = 0;

            switch (entity.getDirection()) {
                case UP:
                    markY = (int) entity.getLocation().getY() + (entity.getSize() / 2) - (size / 2);
                    markX = (int) entity.getLocation().getX() + (leftFoot ? offset : entity.getSize() - size - offset);
                    break;
                case DOWN:
                    markY = (int) entity.getLocation().getY() + (entity.getSize() / 2) - (size / 2);
                    markX = (int) entity.getLocation().getX() + (!leftFoot ? offset : entity.getSize() - size - offset);
                    break;
                case LEFT:
                    markY = (int) entity.getLocation().getY() + (!leftFoot ? offset : entity.getSize() - size - offset);
                    markX = (int) entity.getLocation().getX() + (entity.getSize() / 2) - (size / 2);
                    break;
                case RIGHT:
                    markY = (int) entity.getLocation().getY() + (leftFoot ? offset : entity.getSize() - size - offset);
                    markX = (int) entity.getLocation().getX() + (entity.getSize() / 2) - (size / 2);
                    break;
            }

            footMarkList.add(new FootMark(new Location(markX, markY), color, size, shrunkSize, duration));

            leftFoot = !leftFoot;
        }
    }

    public void update(double deltaTime) {
        if (entity.isMoving())
            spawn(deltaTime);

        footMarkList.forEach(footMark -> footMark.update(deltaTime));
        footMarkList.removeIf(FootMark::isExpired);
    }

    public void render(Graphics graphics, Location screenLocation) {
        try {
            footMarkList.forEach(footMark -> footMark.render(graphics, screenLocation));
        } catch (ConcurrentModificationException | NullPointerException ignored) {
        }
    }
}
