package com.pdn.game.actual.controller;

import com.pdn.game.actual.unit.Unit;
import com.pdn.game.actual.unit.UnitController;

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

    private double decisionCounter = 0;

    @Override
    public void update(double deltaTime) {
        decisionCounter += deltaTime;
        if (decisionCounter > 1000) {
            decisionCounter -= 1000;

            int direction = random.nextInt(5);
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
                    unit.stop();
                    break;
                default:
                    break;
            }
        }
    }
}
