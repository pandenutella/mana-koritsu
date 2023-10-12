package com.pdn.game.actual.screen;

import com.pdn.game.actual.Unit;
import com.pdn.game.actual.UnitController;
import com.pdn.game.actual.battle.SkillEffectManager;
import com.pdn.game.actual.battle.SkillMissileManager;
import com.pdn.game.actual.battle.SkillSet;
import com.pdn.game.actual.camera.Camera;
import com.pdn.game.actual.camera.FocusedCamera;
import com.pdn.game.actual.common.Location;
import com.pdn.game.actual.controller.EnemyController;
import com.pdn.game.actual.controller.PlayerController;
import com.pdn.game.engine.ui.Painter;
import com.pdn.game.engine.ui.Screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import static com.pdn.game.actual.battle.SkillEffectManager.getGlobalSkillEffectManager;
import static com.pdn.game.actual.battle.SkillMissileManager.getGlobalSkillMissileManager;
import static com.pdn.game.actual.effect.FootMarkManager.getGlobalFootMarkManager;
import static com.pdn.game.actual.effect.FootMarkManager.initialize;
import static com.pdn.game.engine.Game.PRIMARY_FONT_NAME;
import static com.pdn.game.engine.key.KeyManager.getGlobalKeyManager;
import static com.pdn.game.engine.ui.ScreenManager.getGlobalScreenManager;
import static java.awt.Color.WHITE;
import static java.awt.Font.PLAIN;

public class BattleScreen implements Screen {

    private final Camera camera;
    private final Location screenLocation = new Location(0, 0);

    private final Unit player;

    private final List<Unit> unitList = new ArrayList<>();
    private final List<UnitController> unitControllerList = new ArrayList<>();

    public BattleScreen() {
        player = new Unit("Pan de Nutella", new Location(100, 325));
        unitList.add(player);
        unitControllerList.add(new PlayerController(player));

        spawnEnemy("Enemy 1", new Location(850, 325));
        spawnEnemy("Enemy 2", new Location(650, 325));
        spawnEnemy("Enemy 3", new Location(450, 325));

        camera = new FocusedCamera(screenLocation, player);

        SkillMissileManager.initialize();
        SkillEffectManager.initialize();
        initialize();
    }

    private void spawnEnemy(String name, Location location) {
        Unit enemy = new Unit(name, location);
        unitList.add(enemy);
        unitControllerList.add(new EnemyController(enemy));
    }

    @Override
    public void update(double deltaTime) {
        if (getGlobalKeyManager().isKeyPressed("back"))
            getGlobalScreenManager().goTo("home");

        unitControllerList.forEach(unitController -> unitController.update(deltaTime));
        unitList.forEach(unit -> unit.update(deltaTime));

        getGlobalSkillMissileManager().update(deltaTime);
        getGlobalSkillEffectManager().update(deltaTime);
        getGlobalFootMarkManager().update(deltaTime);

        camera.update(deltaTime);
    }

    @Override
    public void render(Graphics graphics) {
        unitList.forEach(unit -> unit.render(graphics, screenLocation));

        getGlobalFootMarkManager().render(graphics, screenLocation);
        getGlobalSkillMissileManager().render(graphics, screenLocation);
        getGlobalSkillEffectManager().render(graphics, screenLocation);

        Color swordColor = new Color(206, 150, 150);
        SkillSet swordSkillSet = player.getSkillManager().getSkillSetMap().get("skill-sword");

        Color sphereColor = new Color(150, 192, 206);
        SkillSet sphereSkillSet = player.getSkillManager().getSkillSetMap().get("skill-sphere");

        Color shieldColor = new Color(165, 206, 150);
        SkillSet shieldSkillSet = player.getSkillManager().getSkillSetMap().get("skill-shield");

        renderSkillSet(graphics, 1, swordColor, swordSkillSet);
        renderSkillSet(graphics, 2, sphereColor, sphereSkillSet);
        renderSkillSet(graphics, 3, shieldColor, shieldSkillSet);

        Font oldFont = graphics.getFont();

        graphics.setColor(WHITE);
        graphics.setFont(new Font(PRIMARY_FONT_NAME, PLAIN, 72));
        graphics.drawString("[ESC]", 30, 50);

        graphics.setFont(oldFont);
    }

    private void renderSkillSet(Graphics graphics, int setCount, Color color, SkillSet skillSet) {
        int pointWidth = 30;
        int pointHeight = 10;

        int gaugeWidth = pointWidth + 3;
        int gaugeHeight = (pointHeight * skillSet.getTier()) + (skillSet.getTier() + 2);

        int gaugeX = 10 + (gaugeWidth * (setCount - 1)) + (3 * (setCount - 1));
        int gaugeY = Painter.HEIGHT - 10 - gaugeHeight;

        graphics.setColor(color);
        graphics.drawRect(gaugeX, gaugeY, gaugeWidth, gaugeHeight);

        if (skillSet.isReplenishing() || skillSet.isOnCoolDown())
            graphics.setColor(new Color(255, 255, 255));

        if (skillSet.isReplenishing()) {
            int replenishMaxHeight = gaugeHeight - 3;
            int replenishHeight = (int) (replenishMaxHeight * (skillSet.getReplenishCurrent() / skillSet.getReplenish()));

            graphics.fillRect(gaugeX + 2, Painter.HEIGHT - 10 - 1 - replenishHeight, pointWidth, replenishHeight);

            return;
        }

        for (int i = 0; i < skillSet.getTierCurrent(); i++)
            graphics.fillRect(gaugeX + 2, Painter.HEIGHT - 10 - ((pointHeight + 1) * (i + 1)), pointWidth, pointHeight);
    }
}
