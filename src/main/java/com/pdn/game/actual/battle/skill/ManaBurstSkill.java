package com.pdn.game.actual.battle.skill;

import com.pdn.game.actual.Unit;
import com.pdn.game.actual.battle.Skill;
import com.pdn.game.actual.battle.missile.ManaBurstMissile;
import com.pdn.game.actual.common.Direction;
import com.pdn.game.actual.common.Location;

import static com.pdn.game.actual.battle.SkillMissileManager.getGlobalSkillMissileManager;

public class ManaBurstSkill extends Skill {

    public ManaBurstSkill(Unit unit) {
        super(unit);
    }

    @Override
    protected void startEffect() {
        double x = unit.getLocation().getX() + (double) (unit.getWidth(unit.getDirection()) / 2) - (double) (50 / 2);
        double y = unit.getLocation().getY() + (double) (unit.getHeight(unit.getDirection()) / 2) - (double) (50 / 2);

        Direction missileDirection = unit.getSkillDirection();
        getGlobalSkillMissileManager().add(new ManaBurstMissile(unit, new Location(x, y), missileDirection));

        endEffect();
    }

    @Override
    public void update(double deltaTime) {

    }
}
