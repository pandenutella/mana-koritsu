package com.pdn.game.actual.battle.skill;

import com.pdn.game.actual.Unit;
import com.pdn.game.actual.battle.Skill;
import com.pdn.game.actual.battle.missile.ManaChargingSlashMissile;
import com.pdn.game.actual.common.Direction;
import com.pdn.game.actual.common.Location;

import static com.pdn.game.actual.battle.SkillMissileManager.getGlobalSkillMissileManager;
import static com.pdn.game.actual.common.Direction.DOWN;
import static com.pdn.game.actual.common.Direction.UP;
import static com.pdn.game.actual.utility.DirectionUtility.getLeftOf;
import static com.pdn.game.actual.utility.DirectionUtility.getRightOf;

public class ManaChargingSlashSkill extends Skill {

    private Direction missileDirection;

    public ManaChargingSlashSkill(Unit unit) {
        super(unit);
    }

    @Override
    protected void startEffect() {
        unit.pause();

        int missileWidth = (unit.getSkillDirection() == UP || unit.getSkillDirection() == DOWN) ? 75 : 25;
        int missileHeight = (unit.getSkillDirection() == UP || unit.getSkillDirection() == DOWN) ? 25 : 75;

        double x = unit.getLocation().getX() + (double) (unit.getWidth(unit.getDirection()) / 2) - (double) (missileWidth / 2);
        double y = unit.getLocation().getY() + (double) (unit.getHeight(unit.getDirection()) / 2) - (double) (missileHeight / 2);

        missileDirection = unit.getSkillDirection();

        Location leftMissileLocation = new Location(x, y);
        leftMissileLocation.adjustTowardsDirection(75, getLeftOf(missileDirection));

        Location rightMissileLocation = new Location(x, y);
        rightMissileLocation.adjustTowardsDirection(75, getRightOf(missileDirection));

        getGlobalSkillMissileManager().add(new ManaChargingSlashMissile(unit, leftMissileLocation, missileDirection));
        getGlobalSkillMissileManager().add(new ManaChargingSlashMissile(unit, rightMissileLocation, missileDirection));
    }

    private double effectCounter = 0;

    @Override
    public void update(double deltaTime) {
        unit.getLocation().adjustTowardsDirection(1.2 * deltaTime, missileDirection);
        effectCounter += deltaTime;

        if (effectCounter > 175) {
            effectCounter = 0;

            unit.unpause();
            endEffect();
        }
    }
}
