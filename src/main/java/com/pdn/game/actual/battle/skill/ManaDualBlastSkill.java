package com.pdn.game.actual.battle.skill;

import com.pdn.game.actual.Unit;
import com.pdn.game.actual.battle.Skill;
import com.pdn.game.actual.battle.missile.ManaBlastMissile;
import com.pdn.game.actual.common.Direction;
import com.pdn.game.actual.common.Location;

import static com.pdn.game.actual.battle.SkillMissileManager.getGlobalSkillMissileManager;
import static com.pdn.game.actual.utility.DirectionUtility.getLeftOf;
import static com.pdn.game.actual.utility.DirectionUtility.getRightOf;

public class ManaDualBlastSkill extends Skill {

    public ManaDualBlastSkill(Unit unit) {
        super(unit);
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

        getGlobalSkillMissileManager().add(new ManaBlastMissile(unit, leftMissileLocation, missileDirection));
        getGlobalSkillMissileManager().add(new ManaBlastMissile(unit, rightMissileLocation, missileDirection));

        endEffect();
    }

    @Override
    public void update(double deltaTime) {

    }
}
