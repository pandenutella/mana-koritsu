package com.pdn.game.actual.skill;

import com.pdn.game.actual.common.Direction;
import com.pdn.game.actual.common.Location;
import com.pdn.game.actual.unit.Unit;

public class ManaBurstSkill extends Skill {

    private final SkillMissileManager skillMissileManager;

    public ManaBurstSkill(Unit unit, SkillMissileManager skillMissileManager) {
        super(unit);

        this.skillMissileManager = skillMissileManager;
    }

    @Override
    protected void startEffect() {
        double x = unit.getLocation().getX() + (double) (unit.getWidth() / 2) - (double) (60 / 2);
        double y = unit.getLocation().getY() + (double) (unit.getWidth() / 2) - (double) (60 / 2);

        Direction missileDirection = unit.getSkillDirection();
        skillMissileManager.add(new ManaBurstMissile(unit, new Location(x, y), missileDirection));

        endEffect();
    }

    @Override
    public void update(double deltaTime) {

    }
}
