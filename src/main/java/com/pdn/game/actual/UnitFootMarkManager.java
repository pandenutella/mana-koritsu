package com.pdn.game.actual;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class UnitFootMarkManager {
    private static final double MILLISECONDS_TO_SPAWN = 100;
    private static final double DURATION = 3000;

    private final Unit unit;

    private final List<UnitFootMark> unitFootMarkList = new ArrayList<>();
    private double accumulated = 0;
    private boolean leftFoot = true;

    public UnitFootMarkManager(Unit unit) {
        this.unit = unit;
    }


    private void spawn(double deltaTime) {
        accumulated += deltaTime;
        if (accumulated > MILLISECONDS_TO_SPAWN) {
            accumulated -= MILLISECONDS_TO_SPAWN;

            int offset = 5;

            int footX = 0;
            int footY = 0;

            switch (unit.getDirection()) {
                case UP:
                    footY = (int) unit.getLocation().getY() + (Unit.SIZE / 2) - (UnitFootMark.SIZE / 2);
                    footX = (int) unit.getLocation().getX() + (leftFoot ? offset : Unit.SIZE - UnitFootMark.SIZE - offset);
                    break;
                case DOWN:
                    footY = (int) unit.getLocation().getY() + (Unit.SIZE / 2) - (UnitFootMark.SIZE / 2);
                    footX = (int) unit.getLocation().getX() + (!leftFoot ? offset : Unit.SIZE - UnitFootMark.SIZE - offset);
                    break;
                case LEFT:
                    footY = (int) unit.getLocation().getY() + (!leftFoot ? offset : Unit.SIZE - UnitFootMark.SIZE - offset);
                    footX = (int) unit.getLocation().getX() + (Unit.SIZE / 2) - (UnitFootMark.SIZE / 2);
                    break;
                case RIGHT:
                    footY = (int) unit.getLocation().getY() + (leftFoot ? offset : Unit.SIZE - UnitFootMark.SIZE - offset);
                    footX = (int) unit.getLocation().getX() + (Unit.SIZE / 2) - (UnitFootMark.SIZE / 2);
                    break;
            }

            unitFootMarkList.add(new UnitFootMark(new Location(footX, footY), DURATION));

            leftFoot = !leftFoot;
        }
    }

    public void update(double deltaTime) {
        if (unit.isMoving())
            spawn(deltaTime);

        unitFootMarkList.forEach(unitFootMark -> unitFootMark.update(deltaTime));
        unitFootMarkList.removeIf(UnitFootMark::isExpired);
    }

    public void render(Graphics graphics) {
        unitFootMarkList.forEach(unitFootMark -> unitFootMark.render(graphics));
    }
}
