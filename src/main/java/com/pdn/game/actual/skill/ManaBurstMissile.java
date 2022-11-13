package com.pdn.game.actual.skill;

import com.pdn.game.actual.common.Direction;
import com.pdn.game.actual.common.Location;
import com.pdn.game.actual.effect.FootMarkSpawner;
import com.pdn.game.actual.unit.Unit;

import java.awt.Color;
import java.awt.Graphics;

public class ManaBurstMissile extends SkillMissile {
    private final FootMarkSpawner footMarkSpawner;

    public ManaBurstMissile(Unit user, Location location, Direction direction) {
        super(user, location, direction, 900, 1200, new Color(150, 192, 206));

        footMarkSpawner = new FootMarkSpawner(this, color, 80, 40, 100, 1200, 0);
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
    public int getWidth() {
        return 60;
    }

    @Override
    public int getHeight() {
        return 60;
    }
}
