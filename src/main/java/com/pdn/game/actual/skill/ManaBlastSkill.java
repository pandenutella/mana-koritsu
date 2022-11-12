package com.pdn.game.actual.skill;

import com.pdn.game.actual.common.Direction;
import com.pdn.game.actual.common.Location;
import com.pdn.game.actual.unit.Unit;

public class ManaBlastSkill extends Skill {

    private final SkillMissileManager skillMissileManager;

    public ManaBlastSkill(Unit unit, SkillMissileManager skillMissileManager) {
        super(unit, 500);

        this.skillMissileManager = skillMissileManager;
    }

    @Override
    protected void startEffect() {
        double x = unit.getLocation().getX() + (double) (unit.getSize() / 2) - (double) (40 / 2);
        double y = unit.getLocation().getY() + (double) (unit.getSize() / 2) - (double) (40 / 2);

        int missileDirectionOrdinal = unit.getDirection().ordinal();
        if (unit.getPeekDirection() != null) {
            switch (unit.getPeekDirection()) {
                case LEFT:
                    missileDirectionOrdinal--;
                    break;
                case RIGHT:
                    missileDirectionOrdinal++;
                    break;
                default:
                    break;
            }

            if (missileDirectionOrdinal < 0)
                missileDirectionOrdinal += 4;
            else if (missileDirectionOrdinal > 3)
                missileDirectionOrdinal -= 4;
        }

        Direction missileDirection = Direction.values()[missileDirectionOrdinal];

        skillMissileManager.add(new ManaBlastMissile(unit, new Location(x, y), missileDirection));
    }
}
