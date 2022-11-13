package com.pdn.game.actual.skill;

import com.pdn.game.actual.common.Direction;
import com.pdn.game.actual.common.Location;
import com.pdn.game.actual.unit.Unit;

import static com.pdn.game.actual.common.Direction.DOWN;
import static com.pdn.game.actual.common.Direction.LEFT;
import static com.pdn.game.actual.common.Direction.RIGHT;
import static com.pdn.game.actual.common.Direction.UP;

public class ManaDualBlastSkill extends Skill {

    private final SkillMissileManager skillMissileManager;

    public ManaDualBlastSkill(Unit unit, SkillMissileManager skillMissileManager) {
        super(unit);

        this.skillMissileManager = skillMissileManager;
    }

    @Override
    protected void startEffect() {
        double x = unit.getLocation().getX() + (double) (unit.getWidth(unit.getDirection()) / 2) - (double) (25 / 2);
        double y = unit.getLocation().getY() + (double) (unit.getHeight(unit.getDirection()) / 2) - (double) (25 / 2);

        Direction missileDirection = unit.getSkillDirection();

        Location leftMissileLocation = new Location(x, y);
        leftMissileLocation.adjustTowardsDirection(50, getLeftOf(missileDirection));

        Location rightMissileLocation = new Location(x, y);
        rightMissileLocation.adjustTowardsDirection(50, getRightOf(missileDirection));

        skillMissileManager.add(new ManaBlastMissile(unit, leftMissileLocation, missileDirection));
        skillMissileManager.add(new ManaBlastMissile(unit, rightMissileLocation, missileDirection));

        endEffect();
    }

    private Direction getLeftOf(Direction direction) {
        switch (direction) {
            case UP:
                return LEFT;
            case RIGHT:
                return UP;
            case DOWN:
                return RIGHT;
            case LEFT:
                return DOWN;
            default:
                return null;
        }
    }

    private Direction getRightOf(Direction direction) {
        switch (direction) {
            case UP:
                return RIGHT;
            case RIGHT:
                return DOWN;
            case DOWN:
                return LEFT;
            case LEFT:
                return UP;
            default:
                return null;
        }
    }

    @Override
    public void update(double deltaTime) {

    }
}
