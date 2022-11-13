package com.pdn.game.actual.camera;

import com.pdn.game.actual.common.Location;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class Camera {
    protected final Location screenLocation;

    public abstract void update(double deltaTime);
}
