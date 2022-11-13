package com.pdn.game.actual.skill;

import com.pdn.game.actual.unit.Unit;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class Skill {
    protected final Unit unit;

    @Getter
    private boolean takingEffect;

    public void use() {
        takingEffect = true;

        startEffect();
    }

    protected abstract void startEffect();

    public abstract void update(double deltaTime);

    public void endEffect() {
        takingEffect = false;
    }
}
