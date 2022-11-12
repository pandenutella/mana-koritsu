package com.pdn.game.actual.skill;

import com.pdn.game.actual.common.Direction;
import com.pdn.game.actual.common.Location;
import com.pdn.game.actual.effect.FootMarkSpawner;
import com.pdn.game.actual.unit.Unit;

import java.awt.Color;
import java.awt.Graphics;

public class ManaChargingStabMissile extends SkillMissile {
    private final FootMarkSpawner footMarkSpawner;

    public ManaChargingStabMissile(Unit user, Location location, Direction direction) {
        super(user, location, direction, 1200, 350);

        Color color = new Color(206, 150, 150);
        footMarkSpawner = new FootMarkSpawner(this, color, 40, 0, 20, 800, 10);
    }

    @Override
    public void update(double deltaTime) {
        updateMovement(deltaTime);
        footMarkSpawner.update(deltaTime);
    }

    @Override
    public void render(Graphics graphics, Location screenLocation) {
        footMarkSpawner.render(graphics, screenLocation);

        Color color = new Color(206, 150, 150);

        graphics.setColor(color);
        graphics.fillRect((int) (screenLocation.getX() + location.getX()), (int) (screenLocation.getY() + location.getY()),
                getSize(), getSize());
    }

    @Override
    public int getSize() {
        return 40;
    }
}
