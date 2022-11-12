package com.pdn.game.actual.skill;

import com.pdn.game.actual.unit.Unit;

public abstract class Skill {
    protected final Unit unit;
    private boolean takingEffect;

    public Skill(Unit unit) {
        this.unit = unit;
    }

    public void use() {
        takingEffect = true;

        startEffect();
    }

    protected abstract void startEffect();

    public abstract void update(double deltaTime);

    public void endEffect() {
        takingEffect = false;
    }

    public boolean isTakingEffect() {
        return takingEffect;
    }
}
