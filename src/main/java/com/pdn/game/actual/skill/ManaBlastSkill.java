package com.pdn.game.actual.skill;

import com.pdn.game.actual.common.Direction;
import com.pdn.game.actual.common.Location;
import com.pdn.game.actual.unit.Unit;

import static com.pdn.game.actual.skill.SkillMissileManager.getGlobalSkillMissileManager;

public class ManaBlastSkill extends Skill {

    public ManaBlastSkill(Unit unit) {
        super(unit);
    }

    @Override
    protected void startEffect() {
        double x = unit.getLocation().getX() + (double) (unit.getWidth(unit.getDirection()) / 2) - (double) (25 / 2);
        double y = unit.getLocation().getY() + (double) (unit.getHeight(unit.getDirection()) / 2) - (double) (25 / 2);

        Direction missileDirection = unit.getSkillDirection();
        getGlobalSkillMissileManager().add(new ManaBlastMissile(unit, new Location(x, y), missileDirection));

        endEffect();
    }

    @Override
    public void update(double deltaTime) {

    }
}
