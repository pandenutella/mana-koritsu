package com.pdn.game.actual.unit;

import com.pdn.game.actual.Entity;
import com.pdn.game.actual.common.Direction;
import com.pdn.game.actual.common.Location;
import com.pdn.game.actual.effect.FootMarkSpawner;
import com.pdn.game.actual.skill.ManaBlastSkill;
import com.pdn.game.actual.skill.ManaBlockSkill;
import com.pdn.game.actual.skill.ManaBurstSkill;
import com.pdn.game.actual.skill.ManaChargingStabSkill;
import com.pdn.game.actual.skill.ManaDualBlastSkill;
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

    private Direction direction;
    private Direction peekDirection;
    private boolean moving;
    private double moveSpeed = 300;
    private boolean paused = false;

    public Unit(String name, Location location, SkillMissileManager skillMissileManager) {
        this.name = name;
        this.location = location;
        this.skillMissileManager = skillMissileManager;

        direction = UP;

        Map<Integer, Skill> swordSkillTierMap = new HashMap<>();
        swordSkillTierMap.put(1, new ManaChargingStabSkill(this, skillMissileManager));
        swordSkillTierMap.put(2, new ManaChargingStabSkill(this, skillMissileManager));
        swordSkillTierMap.put(3, new ManaChargingStabSkill(this, skillMissileManager));

        SkillSet swordSkillSet = new SkillSet(swordSkillTierMap);

        Map<Integer, Skill> sphereSkillTierMap = new HashMap<>();
        sphereSkillTierMap.put(1, new ManaBurstSkill(this, skillMissileManager));
        sphereSkillTierMap.put(2, new ManaDualBlastSkill(this, skillMissileManager));
        sphereSkillTierMap.put(3, new ManaBlastSkill(this, skillMissileManager));

        SkillSet sphereSkillSet = new SkillSet(sphereSkillTierMap);

        Map<Integer, Skill> shieldSkillTierMap = new HashMap<>();
        shieldSkillTierMap.put(1, new ManaBlockSkill(this, skillMissileManager));
        shieldSkillTierMap.put(2, new ManaBlockSkill(this, skillMissileManager));
        shieldSkillTierMap.put(3, new ManaBlockSkill(this, skillMissileManager));

        SkillSet shieldSkillSet = new SkillSet(shieldSkillTierMap);

        Map<String, SkillSet> skillMap = new HashMap<>();
        skillMap.put("skill-sword", swordSkillSet);
        skillMap.put("skill-sphere", sphereSkillSet);
        skillMap.put("skill-shield", shieldSkillSet);

        skillManager = new SkillManager(skillMap);

        Color color = new Color(255, 255, 255);
        footMarkSpawner = new FootMarkSpawner(this, color, 30, 30, 150, 750, 5);
    }

    public void moveTowards(Direction direction) {
        if (paused)
            return;

        this.direction = direction;
        moving = true;
    }

    public void stopMoving() {
        moving = false;
    }

    public void useSkill(String skillName) {
        if (paused)
            return;

        skillManager.useSkill(skillName);
    }

    public void peekTowards(Direction direction) {
        peekDirection = direction;
    }

    public void stopPeeking() {
        peekDirection = null;
    }

    public void pause() {
        paused = true;
    }

    public void unpause() {
        paused = false;
    }

    public void update(double deltaTime) {
        if (!paused && moving) {
            double distance = moveSpeed * (deltaTime / 1000);

            location.adjustTowardsDirection(distance, direction);
        }

        skillManager.update(deltaTime);
        footMarkSpawner.update(deltaTime);
    }

    public void render(Graphics graphics, Location screenLocation) {
        footMarkSpawner.render(graphics, screenLocation);

        int x = (int) (screenLocation.getX() + location.getX());
        int y = (int) (screenLocation.getY() + location.getY());

        graphics.setColor(WHITE);
        graphics.fillRect(x, y, size, size);
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
        return direction;
    }

    @Override
    public boolean isMoving() {
        return moving;
    }

    @Override
    public int getWidth(Direction direction) {
        return size;
    }

    @Override
    public int getHeight(Direction direction) {
        return size;
    }

    public Direction getPeekDirection() {
        return peekDirection;
    }

    public SkillManager getSkillManager() {
        return skillManager;
    }

    public Direction getSkillDirection() {
        int missileDirectionOrdinal = direction.ordinal();
        if (peekDirection != null) {
            switch (peekDirection) {
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

        return Direction.values()[missileDirectionOrdinal];
    }
}
