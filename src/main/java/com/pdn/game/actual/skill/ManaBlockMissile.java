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
        super(user, location, direction, 400, 400);

        Color color = new Color(165, 206, 150);
        footMarkSpawner = new FootMarkSpawner(this, color, 80, 40, 200, 400, 0);
    }

    @Override
    public void update(double deltaTime) {
        updateMovement(deltaTime);
        footMarkSpawner.update(deltaTime);
    }

    @Override
    public void render(Graphics graphics, Location screenLocation) {
        footMarkSpawner.render(graphics, screenLocation);

        Color color = new Color(165, 206, 150);

        graphics.setColor(color);
        graphics.fillRect((int) (screenLocation.getX() + location.getX()), (int) (screenLocation.getY() + location.getY()),
                getSize(), getSize());
    }

    @Override
    public int getSize() {
        return 60;
    }
}
