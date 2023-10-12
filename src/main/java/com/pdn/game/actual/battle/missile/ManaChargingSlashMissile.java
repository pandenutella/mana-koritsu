package com.pdn.game.actual.battle.missile;

import com.pdn.game.actual.Unit;
import com.pdn.game.actual.battle.SkillMissile;
import com.pdn.game.actual.common.Direction;
import com.pdn.game.actual.common.Location;
import com.pdn.game.actual.effect.FootMarkSpawner;

import java.awt.Graphics;

public class ManaChargingSlashMissile extends SkillMissile {
    private final FootMarkSpawner footMarkSpawner;

    public ManaChargingSlashMissile(Unit user, Location location, Direction direction) {
        super(user, location, direction, 1200, 350, user.getManaColor());

        footMarkSpawner = new FootMarkSpawner(this, color, 25, 0, 20, 800, 0);
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
