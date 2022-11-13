package com.pdn.game.actual.skill;

import com.pdn.game.actual.common.Direction;
import com.pdn.game.actual.common.Location;
import com.pdn.game.actual.effect.FootMarkSpawner;
import com.pdn.game.actual.unit.Unit;

import java.awt.Color;
import java.awt.Graphics;

public class ManaBlockMissile extends SkillMissile {
    private final FootMarkSpawner footMarkSpawner;

    public ManaBlockMissile(Unit user, Location location, Direction direction) {
        super(user, location, direction, 300, 400, new Color(165, 206, 150));

        footMarkSpawner = new FootMarkSpawner(this, color, 80, 20, 100, 200, 20);
    }

    @Override
    public void update(double deltaTime) {
        updateMovement(deltaTime);
        footMarkSpawner.update(deltaTime);
    }

    @Override
    public void render(Graphics graphics, Location screenLocation) {
        footMarkSpawner.render(graphics, screenLocation);
        renderBody(graphics, screenLocation);
    }

    @Override
    public int getWidth(Direction direction) {
        switch (direction) {
            case UP:
            case DOWN:
                return 75;
            case LEFT:
            case RIGHT:
                return 25;
            default:
                return 0;
        }
    }

    @Override
    public int getHeight(Direction direction) {
        switch (direction) {
            case UP:
            case DOWN:
                return 25;
            case LEFT:
            case RIGHT:
                return 75;
            default:
                return 0;
        }
    }
}
