package com.pdn.game.actual.battle;

import lombok.Getter;

import java.util.Map;

public class SkillSet {
    private final Map<Integer, Skill> skillTierMap;

    @Getter
    private final double replenish;

    @Getter
    private final int tier;

    private double coolDownCurrent = 0;

    @Getter
    private boolean onCoolDown = false;

    @Getter
    private double replenishCurrent = 0;

    private boolean replenishing = false;

    @Getter
    private int tierCurrent;

    public SkillSet(Map<Integer, Skill> skillTierMap) {
        this.skillTierMap = skillTierMap;

        tier = skillTierMap.size();
        tierCurrent = tier;

        replenish = skillTierMap.size() * 2000;
    }

    public void useSkill() {
        if (replenishing || onCoolDown)
            return;

        onCoolDown = true;

        Skill skill = skillTierMap.get(tierCurrent);
        skill.use();

        tierCurrent--;
        if (tierCurrent <= 0)
            replenishing = true;
    }

    public void update(double deltaTime) {
        if (onCoolDown) {
            coolDownCurrent += deltaTime;

            double coolDown = 500;
            if (coolDownCurrent >= coolDown) {
                coolDownCurrent = 0;
                onCoolDown = false;
            }
        }

        if (replenishing) {
            replenishCurrent += deltaTime;

            if (replenishCurrent >= replenish) {
                replenishCurrent = 0;
                replenishing = false;

                tierCurrent = tier;
            }
        }

        skillTierMap.values().stream()
                .filter(Skill::isTakingEffect)
                .forEach(skill -> skill.update(deltaTime));
    }

    public boolean isReplenishing() {
        return replenishing;
    }
}
