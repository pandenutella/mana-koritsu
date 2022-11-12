package com.pdn.game.actual;

import com.pdn.game.actual.common.Direction;
import com.pdn.game.actual.common.Location;

public interface Entity {
    boolean isMoving();

    int getSize();

    Direction getDirection();

    Location getLocation();
}