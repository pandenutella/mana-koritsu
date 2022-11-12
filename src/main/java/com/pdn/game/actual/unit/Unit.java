package com.pdn.game.actual.unit;

import com.pdn.game.actual.Entity;
import com.pdn.game.actual.common.Direction;
import com.pdn.game.actual.common.Location;
import com.pdn.game.actual.effect.FootMarkSpawner;
import com.pdn.game.actual.skill.ManaBlastSkill;
import com.pdn.game.actual.skill.ManaBurstSkill;
import com.pdn.game.actual.skill.Skill;
import com.pdn.game.actual.skill.SkillManager;
import com.pdn.game.actual.skill.SkillMissileManager;
import com.pdn.game.actual.skill.SkillSet;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import static com.pdn.game.actual.common.Direction.UP;
import static java.awt.Color.WHITE;

public class Unit implements Entity {
    private final String name;
    private final Location location;
    private final SkillMissileManager skillMissileManager;

    private final SkillManager skillManager;
    private final FootMarkSpawner footMarkSpawner;

    private final int size = 50;

    private Direction moveDirection;
    private Direction peekDirection;
    private boolean moving;
    private double moveSpeed = 300;

    public Unit(String name, Location location, SkillMissileManager skillMissileManager) {
        this.name = name;
        this.location = location;
        this.skillMissileManager = skillMissileManager;

        moveDirection = UP;

        Map<Integer, Skill> sphereSkillTierMap = new HashMap<>();
        sphereSkillTierMap.put(1, new ManaBurstSkill(this, skillMissileManager));
        sphereSkillTierMap.put(2, new ManaBlastSkill(this, skillMissileManager));
        sphereSkillTierMap.put(3, new ManaBlastSkill(this, skillMissileManager));

        SkillSet sphereSkillSet = new SkillSet(sphereSkillTierMap);

        Map<String, SkillSet> skillMap = new HashMap<>();
        skillMap.put("skill-sphere", sphereSkillSet);

        skillManager = new SkillManager(skillMap);

        Color color = new Color(255, 255, 255);
        footMarkSpawner = new FootMarkSpawner(this, color, 30, 30, 150, 750, 5);
    }

    public void moveTowards(Direction direction) {
        this.moveDirection = direction;
        moving = true;
    }

    public void stopMoving() {
        moving = false;
    }

    public void useSkill(String skillName) {
        skillManager.useSkill(skillName);
    }

    public void peekTowards(Direction direction) {
        peekDirection = direction;
    }

    public void stopPeeking() {
        peekDirection = null;
    }

    public void update(double deltaTime) {
        if (moving) {
            double distance = moveSpeed * (deltaTime / 1000);

            location.adjustTowardsDirection(distance, moveDirection);
        }

        skillManager.update(deltaTime);
        footMarkSpawner.update(deltaTime);
    }

    public void render(Graphics graphics, Location screenLocation) {
        footMarkSpawner.render(graphics, screenLocation);

        graphics.setColor(WHITE);
        graphics.fillRect((int) (screenLocation.getX() + location.getX()), (int) (screenLocation.getY() + location.getY()), size, size);

    }

    public String getName() {
        return name;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public Direction getDirection() {
        return moveDirection;
    }

    @Override
    public boolean isMoving() {
        return moving;
    }

    @Override
    public int getSize() {
        return size;
    }

    public Direction getPeekDirection() {
        return peekDirection;
    }

    public SkillManager getSkillManager() {
        return skillManager;
    }
}
