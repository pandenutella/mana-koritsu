package com.pdn.game.actual.battle.skill;

import com.pdn.game.actual.Unit;
import com.pdn.game.actual.battle.Skill;
import com.pdn.game.actual.battle.effect.ManaShieldEffect;
import com.pdn.game.actual.common.Direction;
import com.pdn.game.actual.common.Location;

import static com.pdn.game.actual.battle.SkillEffectManager.getGlobalSkillEffectManager;
import static com.pdn.game.actual.common.Direction.DOWN;
import static com.pdn.game.actual.common.Direction.UP;

public class ManaShieldSkill extends Skill {

    private Location location;

    private double offsetX;
    private double offsetY;

    public ManaShieldSkill(Unit unit) {
        super(unit);
    }

    @Override
    protected void startEffect() {
        Direction effectDirection = unit.getSkillDirection();

        int effectWidth = (unit.getSkillDirection() == UP || unit.getSkillDirection() == DOWN) ? 100 : 25;
        int effectHeight = (unit.getSkillDirection() == UP || unit.getSkillDirection() == DOWN) ? 25 : 100;

        double x = unit.getLocation().getX() + (double) (unit.getWidth(effectDirection) / 2) - (double) (effectWidth / 2);
        double y = unit.getLocation().getY() + (double) (unit.getHeight(effectDirection) / 2) - (double) (effectHeight / 2);

        double offset = ((double) unit.getHeight(effectDirection) / 2) + (double) (25 / 2) + 10;

        location = new Location(x, y);
        location.adjustTowardsDirection(offset, effectDirection);

        offsetX = unit.getLocation().getXDifference(location);
        offsetY = unit.getLocation().getYDifference(location);

        getGlobalSkillEffectManager().add(new ManaShieldEffect(unit, location, effectDirection, this::endEffect));
    }

    @Override
    public void update(double deltaTime) {
        location.setX(unit.getLocation().getX() - offsetX);
        location.setY(unit.getLocation().getY() - offsetY);
    }
}
