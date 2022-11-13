package com.pdn.game.actual.battle.missile;

import com.pdn.game.actual.Unit;
import com.pdn.game.actual.battle.SkillMissile;
import com.pdn.game.actual.common.Direction;
import com.pdn.game.actual.common.Location;
import com.pdn.game.actual.effect.FootMarkSpawner;

import java.awt.Color;
import java.awt.Graphics;

public class ManaBlockMissile extends SkillMissile {
    private final FootMarkSpawner footMarkSpawner;

    public ManaBlockMissile(Unit user, Location location, Direction direction) {
        super(user, location, direction, 300, 300, new Color(165, 206, 150));

        footMarkSpawner = new FootMarkSpawner(this, color, 30, 20, 50, 300, 0);
    }

    @Override
    public void update(double deltaTime) {
        updateMovement(deltaTime);
        footMarkSpawner.update(deltaTime);
    }

    @Override
    public void render(Graphics graphics, Location screenLocation) {
        renderBody(graphics, screenLocation);
    }

    @Override
    public int getWidth(Direction direction) {
        switch (direction) {
            case UP:
            case DOWN:
                return 100;
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
                return 100;
            default:
                return 0;
        }
    }
}
