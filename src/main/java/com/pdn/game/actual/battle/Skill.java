package com.pdn.game.actual.battle;

import com.pdn.game.actual.Unit;
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
