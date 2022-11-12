package com.pdn.game.actual.unit;

public abstract class UnitController {

    protected final Unit unit;

    public UnitController(Unit unit) {
        this.unit = unit;
    }

    public abstract void update(double deltaTime);
}
