package com.pdn.game.actual.skill;

import com.pdn.game.actual.common.Location;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class SkillMissileManager {
    private final List<SkillMissile> skillMissileList = new ArrayList<>();

    public void add(SkillMissile skillMissile) {
        skillMissileList.add(skillMissile);
    }

    public void update(double deltaTime) {
        skillMissileList.forEach(skillMissile -> skillMissile.update(deltaTime));
        skillMissileList.removeIf(SkillMissile::isExpired);
    }

    public void render(Graphics graphics, Location screenLocation) {
        try {
            skillMissileList.forEach(skillMissile -> skillMissile.render(graphics, screenLocation));
        } catch (ConcurrentModificationException ignored) {
        }
    }
}
