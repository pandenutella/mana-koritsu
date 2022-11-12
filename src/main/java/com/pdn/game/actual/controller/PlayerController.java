package com.pdn.game.actual.controller;

import com.pdn.game.actual.common.Direction;
import com.pdn.game.actual.unit.Unit;
import com.pdn.game.actual.unit.UnitController;

import java.util.Locale;

import static com.pdn.game.engine.key.KeyManager.getGlobalKeyManager;

public class PlayerController extends UnitController {
    public PlayerController(Unit unit) {
        super(unit);
    }

    @Override
    public void update(double deltaTime) {
        checkMovementInputs();
        checkSkillInputs();
    }

    private void checkMovementInputs() {
        String latestPressedMove = getGlobalKeyManager().getLatestPressedKey("move-");
        if (latestPressedMove == null) {
            if (unit.isMoving())
                unit.stop();

            return;
        }

        Direction direction = Direction.valueOf(latestPressedMove.substring(5).toUpperCase(Locale.ENGLISH));
        unit.moveTowards(direction);
    }

    private void checkSkillInputs() {
        String latestPressedSkill = getGlobalKeyManager().getLatestPressedKey("skill-");
        if (latestPressedSkill == null)
            return;

        unit.useSkill();
    }
}