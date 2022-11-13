package com.pdn.game.actual.skill;

import com.pdn.game.actual.common.Direction;
import com.pdn.game.actual.common.Location;
import com.pdn.game.actual.unit.Unit;

public class ManaChargingStabSkill extends Skill {

    private final SkillMissileManager skillMissileManager;

    private Direction missileDirection;

    public ManaChargingStabSkill(Unit unit, SkillMissileManager skillMissileManager) {
        super(unit);

        this.skillMissileManager = skillMissileManager;
    }

    @Override
    protected void startEffect() {
        unit.pause();

        double x = unit.getLocation().getX() + (double) (unit.getWidth(unit.getDirection()) / 2) - (double) (40 / 2);
        double y = unit.getLocation().getY() + (double) (unit.getHeight(unit.getDirection()) / 2) - (double) (40 / 2);

        missileDirection = unit.getSkillDirection();
        skillMissileManager.add(new ManaChargingStabMissile(unit, new Location(x, y), missileDirection));
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
