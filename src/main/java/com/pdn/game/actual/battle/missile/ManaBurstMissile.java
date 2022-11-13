package com.pdn.game.actual.battle.missile;

import com.pdn.game.actual.Unit;
import com.pdn.game.actual.battle.SkillMissile;
import com.pdn.game.actual.common.Direction;
import com.pdn.game.actual.common.Location;
import com.pdn.game.actual.effect.FootMarkSpawner;

import java.awt.Color;
import java.awt.Graphics;

public class ManaBurstMissile extends SkillMissile {
    private final FootMarkSpawner footMarkSpawner;

    public ManaBurstMissile(Unit user, Location location, Direction direction) {
        super(user, location, direction, 900, 1200, new Color(150, 192, 206));

        footMarkSpawner = new FootMarkSpawner(this, color, 50, 25, 50, 1200, 10);
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
        return 50;
    }

    @Override
    public int getHeight(Direction direction) {
        return 50;
    }
}
