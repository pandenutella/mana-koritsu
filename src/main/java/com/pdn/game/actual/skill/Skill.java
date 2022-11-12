package com.pdn.game.actual.skill;

import com.pdn.game.actual.unit.Unit;

public abstract class Skill {
    protected final Unit unit;
    private final double coolDown;

    private boolean onCoolDown;
    private double coolDownCounter;

    public Skill(Unit unit, double coolDown) {
        this.unit = unit;
        this.coolDown = coolDown;
    }

    public void use() {
        onCoolDown = true;

        startEffect();
    }

    protected abstract void startEffect();

    public void update(double deltaTime) {
        updateCoolDown(deltaTime);
    }

    protected void updateCoolDown(double deltaTime) {
        if (onCoolDown) {
            coolDownCounter += deltaTime;

            if (coolDownCounter > coolDown) {
                onCoolDown = false;
                coolDownCounter = 0;
            }
        }
    }

    public boolean isOnCoolDown() {
        return onCoolDown;
    }
}
