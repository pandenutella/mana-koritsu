package com.pdn.game.actual.battle.missile;

import com.pdn.game.actual.Unit;
import com.pdn.game.actual.battle.SkillMissile;
import com.pdn.game.actual.common.Direction;
import com.pdn.game.actual.common.Location;
import com.pdn.game.actual.effect.FootMarkSpawner;

import java.awt.Graphics;

public class ManaBlastMissile extends SkillMissile {
    private final FootMarkSpawner footMarkSpawner;

    public ManaBlastMissile(Unit user, Location location, Direction direction) {
        super(user, location, direction, 600, 900, user.getManaColor());

        footMarkSpawner = new FootMarkSpawner(this, color, 30, 0, 45, 900, 0);
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
        return 25;
    }

    @Override
    public int getHeight(Direction direction) {
        return 25;
    }
}
