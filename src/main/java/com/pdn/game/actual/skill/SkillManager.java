package com.pdn.game.actual.skill;

import java.util.Map;

public class SkillManager {
    private final Map<String, Skill> skillMap;

    public SkillManager(Map<String, Skill> skillMap) {
        this.skillMap = skillMap;
    }

    public void useSkill(String name) {
        Skill skill = skillMap.get(name);
        if (skill == null)
            return;

        if (skill.isOnCoolDown())
            return;

        skill.use();
    }

    public void update(double deltaTime) {
        skillMap.forEach((name, skill) -> skill.update(deltaTime));
    }
}
