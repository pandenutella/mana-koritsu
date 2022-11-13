package com.pdn.game.actual.controller;

import com.pdn.game.actual.Unit;
import com.pdn.game.actual.UnitController;

import java.util.Random;

import static com.pdn.game.actual.common.Direction.DOWN;
import static com.pdn.game.actual.common.Direction.LEFT;
import static com.pdn.game.actual.common.Direction.RIGHT;
import static com.pdn.game.actual.common.Direction.UP;

public class EnemyController extends UnitController {
    public EnemyController(Unit unit) {
        super(unit);
    }

    private final Random random = new Random();

    private double decisionCounterCurrent = 0;
    private double decisionCounter = 1000;

    @Override
    public void update(double deltaTime) {
        decisionCounterCurrent += deltaTime;
        if (decisionCounterCurrent > decisionCounter) {
            decisionCounterCurrent = 0;
            decisionCounter = random.nextInt(1250) + 250;

            int direction = random.nextInt(6);
            switch (direction) {
                case 0:
                    unit.moveTowards(UP);
                    break;
                case 1:
                    unit.moveTowards(DOWN);
                    break;
                case 2:
                    unit.moveTowards(LEFT);
                    break;
                case 3:
                    unit.moveTowards(RIGHT);
                    break;
                case 4:
                    unit.stopMoving();
                    break;
                case 5:
                    unit.useSkill("skill-sphere");
                    break;
                default:
                    break;
            }
        }
    }
}
