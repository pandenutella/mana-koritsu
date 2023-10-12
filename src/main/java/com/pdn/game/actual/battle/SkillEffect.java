package com.pdn.game.actual.battle;

import com.pdn.game.actual.Entity;
import com.pdn.game.actual.Unit;
import com.pdn.game.actual.common.Direction;
import com.pdn.game.actual.common.EffectCleanup;
import com.pdn.game.actual.common.Location;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.Color;
import java.awt.Graphics;

@RequiredArgsConstructor
public abstract class SkillEffect implements Entity {
    protected final Unit user;

    @Getter
    protected final Location location;

    @Getter
    protected final Direction direction;

    protected final EffectCleanup effectCleanup;

    protected final double duration;

    protected final Color color;

    private double elapsed;

    @Getter
    protected boolean expired = false;

    public abstract void update(double deltaTime);

    protected void updateDuration(double deltaTime) {
        elapsed += deltaTime / 1000;
        if (elapsed > duration) {
            expired = true;
            effectCleanup.cleanUp();
        }
    }

    public abstract void render(Graphics graphics, Location screenLocation);

    protected void renderBody(Graphics graphics, Location screenLocation) {
        int x = (int) (screenLocation.getX() + location.getX());
        int y = (int) (screenLocation.getY() + location.getY());

        graphics.setColor(color);
        graphics.fillRect(x, y, getWidth(direction), getHeight(direction));
    }

    @Override
    public boolean isMoving() {
        return true;
    }
}
