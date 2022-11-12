package com.pdn.game.actual.camera;

import com.pdn.game.actual.common.Location;
import com.pdn.game.actual.unit.Unit;

public class FocusedCamera extends Camera {

    private final Unit unit;

    public FocusedCamera(Location screenLocation, Unit unit) {
        super(screenLocation);

        this.unit = unit;
    }

    @Override
    public void update(double deltaTime) {
        double targetX = -unit.getLocation().getX() + 500 - (double) (Unit.SIZE / 2);
        double targetY = -unit.getLocation().getY() + 350 - (double) (Unit.SIZE / 2);

        double diffX = targetX - screenLocation.getX();
        double diffY = targetY - screenLocation.getY();

        screenLocation.adjustX(diffX / 0.25 * (deltaTime / 1000));
        screenLocation.adjustY(diffY / 0.25 * (deltaTime / 1000));
    }
}
