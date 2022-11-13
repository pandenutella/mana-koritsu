package com.pdn.game.actual.battle;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class SkillManager {
    private final Map<String, SkillSet> skillSetMap;

    public void useSkill(String name) {
        SkillSet skillSet = skillSetMap.get(name);
        if (skillSet == null)
            return;

        skillSet.useSkill();
    }

    public void update(double deltaTime) {
        skillSetMap.forEach((name, skillSet) -> skillSet.update(deltaTime));
    }

    public Map<String, SkillSet> getSkillSetMap() {
        return skillSetMap;
    }
}
