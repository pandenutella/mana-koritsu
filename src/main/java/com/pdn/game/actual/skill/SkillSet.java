package com.pdn.game.actual.skill;

import java.util.Map;

public class SkillSet {
    private final Map<Integer, Skill> skillTierMap;

    private final double coolDown = 500;
    private final double replenish = 5000;
    private final int tier;

    private double coolDownCurrent = 0;
    private boolean onCoolDown = false;

    private double replenishCurrent = 0;
    private boolean replenishing = false;

    private int tierCurrent;

    public SkillSet(Map<Integer, Skill> skillTierMap) {
        this.skillTierMap = skillTierMap;

        tier = skillTierMap.size();
        tierCurrent = tier;
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

    public int getTierCurrent() {
        return tierCurrent;
    }

    public int getTier() {
        return tier;
    }

    public boolean isOnCoolDown() {
        return onCoolDown;
    }

    public double getReplenish() {
        return replenish;
    }

    public double getReplenishCurrent() {
        return replenishCurrent;
    }

    public boolean isReplenishing() {
        return replenishing;
    }
}
