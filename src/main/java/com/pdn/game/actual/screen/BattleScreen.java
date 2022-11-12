package com.pdn.game.actual.screen;

import com.pdn.game.actual.camera.Camera;
import com.pdn.game.actual.camera.FocusedCamera;
import com.pdn.game.actual.common.Location;
import com.pdn.game.actual.controller.EnemyController;
import com.pdn.game.actual.controller.PlayerController;
import com.pdn.game.actual.skill.SkillMissileManager;
import com.pdn.game.actual.skill.SkillSet;
import com.pdn.game.actual.unit.Unit;
import com.pdn.game.actual.unit.UnitController;
import com.pdn.game.engine.ui.Screen;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import static com.pdn.game.engine.key.KeyManager.getGlobalKeyManager;
import static com.pdn.game.engine.ui.ScreenManager.getGlobalScreenManager;
import static java.awt.Color.WHITE;

public class BattleScreen implements Screen {

    private final Camera camera;
    private final Location screenLocation = new Location(0, 0);

    private final Unit player;

    private final List<Unit> unitList = new ArrayList<>();
    private final List<UnitController> unitControllerList = new ArrayList<>();

    private final SkillMissileManager skillMissileManager = new SkillMissileManager();

    private static final int SCREEN_HEIGHT = 653;

    public BattleScreen() {
        player = new Unit("Mk", new Location(100, 325), skillMissileManager);
        unitList.add(player);
        unitControllerList.add(new PlayerController(player));

        spawnEnemy(850, 325);
        spawnEnemy(650, 325);
        spawnEnemy(450, 325);

        camera = new FocusedCamera(screenLocation, player);
    }

    private void spawnEnemy(int x, int y) {
        Unit enemy = new Unit("", new Location(x, y), skillMissileManager);
        unitList.add(enemy);
        unitControllerList.add(new EnemyController(enemy));
    }

    @Override
    public void update(double deltaTime) {
        if (getGlobalKeyManager().isKeyPressed("back"))
            getGlobalScreenManager().goTo("home");

        unitControllerList.forEach(unitController -> unitController.update(deltaTime));
        unitList.forEach(unit -> unit.update(deltaTime));
        skillMissileManager.update(deltaTime);

        camera.update(deltaTime);
    }

    @Override
    public void render(Graphics graphics) {
        unitList.forEach(unit -> unit.render(graphics, screenLocation));
        skillMissileManager.render(graphics, screenLocation);

        Color swordColor = new Color(206, 150, 150);
        SkillSet swordSkillSet = player.getSkillManager().getSkillSetMap().get("skill-sword");

        Color sphereColor = new Color(150, 192, 206);
        SkillSet sphereSkillSet = player.getSkillManager().getSkillSetMap().get("skill-sphere");

        Color shieldColor = new Color(165, 206, 150);
        SkillSet shieldSkillSet = player.getSkillManager().getSkillSetMap().get("skill-shield");

        renderSkillSet(graphics, 1, swordColor, swordSkillSet.getTier(), swordSkillSet.getTierCurrent());
        renderSkillSet(graphics, 2, sphereColor, sphereSkillSet.getTier(), sphereSkillSet.getTierCurrent());
        renderSkillSet(graphics, 3, shieldColor, shieldSkillSet.getTier(), shieldSkillSet.getTierCurrent());

        graphics.setColor(WHITE);
        graphics.drawString("Battle", 30, 30);
        graphics.drawString("Press [ESCAPE] to go back", 30, 45);
    }

    private void renderSkillSet(Graphics graphics, int setCount, Color color, int tier, int tierCurrent) {
        int pointWidth = 30;
        int pointHeight = 10;

        int gaugeWidth = pointWidth + 3;
        int gaugeHeight = (pointHeight * tier) + (tier + 2);

        int gaugeX = 10 + (gaugeWidth * (setCount - 1)) + (3 * (setCount - 1));
        int gaugeY = SCREEN_HEIGHT - 10 - gaugeHeight;

        graphics.setColor(color);
        graphics.drawRect(gaugeX, gaugeY, gaugeWidth, gaugeHeight);

        for (int i = 0; i < tierCurrent; i++)
            graphics.fillRect(gaugeX + 2, SCREEN_HEIGHT - 10 - ((pointHeight + 1) * (i + 1)), pointWidth, pointHeight);
    }
}
