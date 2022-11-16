package com.pdn.game.actual.battle.skill;

import com.pdn.game.actual.Unit;
import com.pdn.game.actual.battle.Skill;
import com.pdn.game.actual.battle.missile.ManaChargingStabMissile;
import com.pdn.game.actual.common.Direction;
import com.pdn.game.actual.common.Location;

import static com.pdn.game.actual.battle.SkillMissileManager.getGlobalSkillMissileManager;

public class ManaChargingStabSkill extends Skill {

    private Direction missileDirection;

    public ManaChargingStabSkill(Unit unit) {
        super(unit);
    }

    @Override
    protected void startEffect() {
        unit.pause();

        double x = unit.getLocation().getX() + (double) (unit.getWidth(unit.getDirection()) / 2) - (double) (40 / 2);
        double y = unit.getLocation().getY() + (double) (unit.getHeight(unit.getDirection()) / 2) - (double) (40 / 2);

        missileDirection = unit.getSkillDirection();

        Location location = new Location(x, y);
        location.adjustTowardsDirection(40, missileDirection);

        getGlobalSkillMissileManager().add(new ManaChargingStabMissile(unit, location, missileDirection));
    }

    private double effectCounter = 0;

    @Override
    public void update(double deltaTime) {
        unit.getLocation().adjustTowardsDirection(1.2 * deltaTime, missileDirection);
        effectCounter += deltaTime;

        if (effectCounter > 275) {
            effectCounter = 0;

            unit.unpause();
            endEffect();
        }
    }
}
