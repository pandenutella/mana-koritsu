package com.pdn.game.actual.screen;

import com.pdn.game.actual.camera.Camera;
import com.pdn.game.actual.camera.FocusedCamera;
import com.pdn.game.actual.common.Location;
import com.pdn.game.actual.controller.EnemyController;
import com.pdn.game.actual.controller.PlayerController;
import com.pdn.game.actual.skill.SkillMissileManager;
import com.pdn.game.actual.unit.Unit;
import com.pdn.game.actual.unit.UnitController;
import com.pdn.game.engine.ui.Screen;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import static com.pdn.game.engine.key.KeyManager.getGlobalKeyManager;
import static com.pdn.game.engine.ui.ScreenManager.getGlobalScreenManager;
import static java.awt.Color.BLACK;

public class BattleScreen implements Screen {

    private final Camera camera;
    private final Location screenLocation = new Location(0, 0);

    private final List<Unit> unitList = new ArrayList<>();
    private final List<UnitController> unitControllerList = new ArrayList<>();

    private final SkillMissileManager skillMissileManager = new SkillMissileManager();

    public BattleScreen() {
        Unit player = new Unit("Mk", new Location(100, 325), skillMissileManager);
        unitList.add(player);
        unitControllerList.add(new PlayerController(player));

        Unit enemy = new Unit("eMk", new Location(850, 325), skillMissileManager);
        unitList.add(enemy);
        unitControllerList.add(new EnemyController(enemy));

        camera = new FocusedCamera(screenLocation, player);
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
        graphics.setColor(BLACK);
        graphics.drawString("Battle", 30, 30);
        graphics.drawString("Press [ESCAPE] to go back", 30, 45);

        unitList.forEach(unit -> unit.render(graphics, screenLocation));
        skillMissileManager.render(graphics, screenLocation);
    }
}
