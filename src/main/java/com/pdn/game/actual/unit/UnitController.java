package com.pdn.game.actual.unit;

import com.pdn.game.actual.common.Direction;

import java.util.Locale;

import static com.pdn.game.engine.key.KeyManager.getGlobalKeyManager;

public class UnitController {

    private final Unit unit;

    public UnitController(Unit unit) {
        this.unit = unit;
    }

    public void update() {
        String latestPressedMove = getGlobalKeyManager().getLatestPressedKey("move-");

        if (latestPressedMove == null) {
            if (unit.isMoving())
                unit.stop();

            return;
        }

        Direction direction = Direction.valueOf(latestPressedMove.substring(5).toUpperCase(Locale.ENGLISH));

        unit.moveTowards(direction);
    }
}
