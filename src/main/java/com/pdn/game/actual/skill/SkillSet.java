package com.pdn.game.actual.skill;

import java.util.Map;

public class SkillSet {
    private final Map<Integer, Skill> skillTierMap;

    private final double coolDown = 500;
    private final int tier;

    private double coolDownCurrent = 0;
    private boolean onCoolDown = false;
    private int tierCurrent;

    public SkillSet(Map<Integer, Skill> skillTierMap) {
        this.skillTierMap = skillTierMap;

        tier = skillTierMap.size();
        tierCurrent = tier;
    }

    public void useSkill() {
        if (tierCurrent <= 0)
            return;

        if (onCoolDown)
            return;

        onCoolDown = true;

        Skill skill = skillTierMap.get(tierCurrent);
        skill.use();

        tierCurrent--;
    }

    public void update(double deltaTime) {
        if (onCoolDown) {
            coolDownCurrent += deltaTime;

            if (coolDownCurrent >= coolDown) {
                coolDownCurrent = 0;
                onCoolDown = false;
            }
        }

        skillTierMap.values().stream()
                .filter(Skill::isTakingEffect)
                .forEach(skill -> skill.update(deltaTime));
    }

    public int getTierCurrent() {
        return tierCurrent;
    }

    public int getTier() {
        return tier;
    }
}
