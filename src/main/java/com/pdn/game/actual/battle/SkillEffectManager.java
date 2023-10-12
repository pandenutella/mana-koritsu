package com.pdn.game.actual.battle;

import com.pdn.game.actual.common.Location;
import lombok.NoArgsConstructor;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class SkillEffectManager {
    private static SkillEffectManager instance;

    private final List<SkillEffect> skillEffectList = new ArrayList<>();

    public static void initialize() {
        instance = new SkillEffectManager();
    }

    public static SkillEffectManager getGlobalSkillEffectManager() {
        return instance;
    }

    public void add(SkillEffect skillEffect) {
        skillEffectList.add(skillEffect);
    }

    public void update(double deltaTime) {
        skillEffectList.forEach(skillEffect -> skillEffect.update(deltaTime));
        skillEffectList.removeIf(SkillEffect::isExpired);
    }

    public void render(Graphics graphics, Location screenLocation) {
        List<SkillEffect> clonedSkillEffects = new ArrayList<>(skillEffectList);

        for (SkillEffect skillEffect : clonedSkillEffects) {
            if (skillEffect == null)
                continue;

            skillEffect.render(graphics, screenLocation);
        }
    }
}
