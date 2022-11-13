package com.pdn.game.actual;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class UnitController {

    protected final Unit unit;

    public abstract void update(double deltaTime);
}
