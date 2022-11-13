package com.pdn.game.actual.effect;

import com.pdn.game.actual.Entity;
import com.pdn.game.actual.common.Location;
import lombok.RequiredArgsConstructor;

import java.awt.Color;

@RequiredArgsConstructor
public class FootMarkSpawner {
    private final Entity entity;
    private final Color color;
    private final int size;
    private final int shrunkSize;
    private final double spawnTime;
    private final double duration;
    private final int offset;

    private double accumulated = 0;
    private boolean leftFoot = true;

    private void spawn(double deltaTime) {
        accumulated += deltaTime;
        if (accumulated > spawnTime) {
            accumulated -= spawnTime;

            int markX = 0;
            int markY = 0;

            switch (entity.getDirection()) {
                case UP:
                    markY = (int) entity.getLocation().getY() + (entity.getHeight(entity.getDirection()) / 2) - (size / 2);
                    markX = (int) entity.getLocation().getX() + (leftFoot ? offset : entity.getWidth(entity.getDirection()) - size - offset);
                    break;
                case DOWN:
                    markY = (int) entity.getLocation().getY() + (entity.getHeight(entity.getDirection()) / 2) - (size / 2);
                    markX = (int) entity.getLocation().getX() + (!leftFoot ? offset : entity.getWidth(entity.getDirection()) - size - offset);
                    break;
                case LEFT:
                    markY = (int) entity.getLocation().getY() + (!leftFoot ? offset : entity.getHeight(entity.getDirection()) - size - offset);
                    markX = (int) entity.getLocation().getX() + (entity.getWidth(entity.getDirection()) / 2) - (size / 2);
                    break;
                case RIGHT:
                    markY = (int) entity.getLocation().getY() + (leftFoot ? offset : entity.getHeight(entity.getDirection()) - size - offset);
                    markX = (int) entity.getLocation().getX() + (entity.getWidth(entity.getDirection()) / 2) - (size / 2);
                    break;
            }

            FootMark footMark = new FootMark(new Location(markX, markY), color, size, shrunkSize, duration);
            FootMarkManager.getGlobalFootMarkManager().add(footMark);

            leftFoot = !leftFoot;
        }
    }

    public void update(double deltaTime) {
        if (entity.isMoving())
            spawn(deltaTime);
    }
}
