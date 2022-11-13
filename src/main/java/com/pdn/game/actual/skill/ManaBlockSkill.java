package com.pdn.game.actual.skill;

import com.pdn.game.actual.common.Direction;
import com.pdn.game.actual.common.Location;
import com.pdn.game.actual.unit.Unit;

import static com.pdn.game.actual.common.Direction.DOWN;
import static com.pdn.game.actual.common.Direction.UP;

public class ManaBlockSkill extends Skill {

    private final SkillMissileManager skillMissileManager;

    public ManaBlockSkill(Unit unit, SkillMissileManager skillMissileManager) {
        super(unit);

        this.skillMissileManager = skillMissileManager;
    }

    @Override
    protected void startEffect() {
        Direction missileDirection = unit.getSkillDirection();

        int missileWidth = (unit.getSkillDirection() == UP || unit.getSkillDirection() == DOWN) ? 75 : 25;
        int missileHeight = (unit.getSkillDirection() == UP || unit.getSkillDirection() == DOWN) ? 25 : 75;

        double x = unit.getLocation().getX() + (double) (unit.getWidth(missileDirection) / 2) - (double) (missileWidth / 2);
        double y = unit.getLocation().getY() + (double) (unit.getHeight(missileDirection) / 2) - (double) (missileHeight / 2);

        double offset = ((double) unit.getHeight(missileDirection) / 2) + (double) (25 / 2);

        Location location = new Location(x, y);
        location.adjustTowardsDirection(offset, missileDirection);

        skillMissileManager.add(new ManaBlockMissile(unit, location, missileDirection));

        endEffect();
    }

    @Override
    public void update(double deltaTime) {

    }
}
