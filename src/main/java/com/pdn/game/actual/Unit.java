package com.pdn.game.actual;

import com.pdn.game.actual.battle.Skill;
import com.pdn.game.actual.battle.SkillManager;
import com.pdn.game.actual.battle.SkillSet;
import com.pdn.game.actual.battle.skill.ManaBlastSkill;
import com.pdn.game.actual.battle.skill.ManaBlockSkill;
import com.pdn.game.actual.battle.skill.ManaBurstSkill;
import com.pdn.game.actual.battle.skill.ManaChargingSlashSkill;
import com.pdn.game.actual.battle.skill.ManaChargingStabSkill;
import com.pdn.game.actual.battle.skill.ManaDualBlastSkill;
import com.pdn.game.actual.battle.skill.ManaShieldSkill;
import com.pdn.game.actual.common.Direction;
import com.pdn.game.actual.common.Location;
import com.pdn.game.actual.effect.FootMarkSpawner;
import lombok.Getter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import static com.pdn.game.actual.common.Direction.UP;
import static com.pdn.game.engine.Game.PRIMARY_FONT_NAME;
import static java.awt.Color.WHITE;
import static java.awt.Font.PLAIN;

public class Unit implements Entity {
    private final String name;

    @Getter
    private final Location location;

    @Getter
    private final SkillManager skillManager;

    private final FootMarkSpawner footMarkSpawner;
    private final int size = 50;

    @Getter
    private Direction direction;

    @Getter
    private Direction peekDirection;

    @Getter
    private boolean moving;

    private double moveSpeed = 300;
    private boolean paused = false;

    public Unit(String name, Location location) {
        this.name = name;
        this.location = location;

        direction = UP;

        Map<Integer, Skill> swordSkillTierMap = new HashMap<>();
        swordSkillTierMap.put(1, new ManaChargingStabSkill(this));
        swordSkillTierMap.put(2, new ManaChargingSlashSkill(this));
        swordSkillTierMap.put(3, new ManaChargingStabSkill(this));

        SkillSet swordSkillSet = new SkillSet(swordSkillTierMap);

        Map<Integer, Skill> sphereSkillTierMap = new HashMap<>();
        sphereSkillTierMap.put(1, new ManaBurstSkill(this));
        sphereSkillTierMap.put(2, new ManaDualBlastSkill(this));
        sphereSkillTierMap.put(3, new ManaBlastSkill(this));

        SkillSet sphereSkillSet = new SkillSet(sphereSkillTierMap);

        Map<Integer, Skill> shieldSkillTierMap = new HashMap<>();
        shieldSkillTierMap.put(1, new ManaShieldSkill(this));
        shieldSkillTierMap.put(2, new ManaBlockSkill(this));
        shieldSkillTierMap.put(3, new ManaShieldSkill(this));

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
        int x = (int) (screenLocation.getX() + location.getX());
        int y = (int) (screenLocation.getY() + location.getY());

        graphics.setColor(WHITE);

        graphics.fillRect(x, y, size, size);

        Font oldFont = graphics.getFont();
        graphics.setFont(new Font(PRIMARY_FONT_NAME, PLAIN, 60));

        int nameWidth = graphics.getFontMetrics().stringWidth(name);
        graphics.drawString(name, x + (getWidth(direction) / 2) - (nameWidth / 2), y - 20);

        graphics.setFont(oldFont);
    }

    public String getName() {
        return name;
    }

    @Override
    public int getWidth(Direction direction) {
        return size;
    }

    @Override
    public int getHeight(Direction direction) {
        return size;
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
