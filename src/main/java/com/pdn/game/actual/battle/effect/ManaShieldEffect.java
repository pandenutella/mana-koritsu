package com.pdn.game.actual.battle.effect;

import com.pdn.game.actual.Unit;
import com.pdn.game.actual.battle.SkillEffect;
import com.pdn.game.actual.common.Direction;
import com.pdn.game.actual.common.EffectCleanup;
import com.pdn.game.actual.common.Location;
import com.pdn.game.actual.effect.FootMarkSpawner;

import java.awt.Color;
import java.awt.Graphics;

public class ManaShieldEffect extends SkillEffect {

    private final FootMarkSpawner footMarkSpawner;

    public ManaShieldEffect(Unit user, Location location, Direction direction, EffectCleanup effectCleanup) {
        super(user, location, direction, effectCleanup, 1, new Color(165, 206, 150));

        footMarkSpawner = new FootMarkSpawner(this, color, 30, 20, 50, 300, 0);
    }

    @Override
    public void update(double deltaTime) {
        updateDuration(deltaTime);
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
