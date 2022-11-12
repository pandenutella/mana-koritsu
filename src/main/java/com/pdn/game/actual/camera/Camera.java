package com.pdn.game.actual.camera;

import com.pdn.game.actual.common.Location;

public abstract class Camera {
    protected final Location screenLocation;

    public Camera(Location screenLocation) {
        this.screenLocation = screenLocation;
    }

    public abstract void update(double deltaTime);
}
