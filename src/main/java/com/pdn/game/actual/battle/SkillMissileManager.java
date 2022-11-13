package com.pdn.game.actual.battle;

import com.pdn.game.actual.common.Location;
import lombok.NoArgsConstructor;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class SkillMissileManager {
    private static SkillMissileManager instance;

    private final List<SkillMissile> skillMissileList = new ArrayList<>();

    public static void initialize() {
        instance = new SkillMissileManager();
    }

    public static SkillMissileManager getGlobalSkillMissileManager() {
        return instance;
    }

    public void add(SkillMissile skillMissile) {
        skillMissileList.add(skillMissile);
    }

    public void update(double deltaTime) {
        skillMissileList.forEach(skillMissile -> skillMissile.update(deltaTime));
        skillMissileList.removeIf(SkillMissile::isExpired);
    }

    public void render(Graphics graphics, Location screenLocation) {
        List<SkillMissile> clonedSkillMissile = new ArrayList<>(skillMissileList);

        clonedSkillMissile.forEach(skillMissile -> skillMissile.render(graphics, screenLocation));
    }
}
