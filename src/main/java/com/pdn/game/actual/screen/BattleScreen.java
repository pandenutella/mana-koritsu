package com.pdn.game.actual.screen;

import com.pdn.game.actual.Camera;
import com.pdn.game.actual.Location;
import com.pdn.game.actual.Unit;
import com.pdn.game.actual.UnitController;
import com.pdn.game.actual.camera.FocusedCamera;
import com.pdn.game.engine.ui.Screen;

import java.awt.Graphics;

import static com.pdn.game.engine.key.KeyManager.getGlobalKeyManager;
import static com.pdn.game.engine.ui.ScreenManager.getGlobalScreenManager;
import static java.awt.Color.BLACK;

public class BattleScreen implements Screen {

    private final Unit player;
    private final UnitController playerController;
    private final Camera camera;

    private final Location screenLocation = new Location(0, 0);

    public BattleScreen() {
        String playerName = "Mk";
        Location playerLocation = new Location(100, 100);

        player = new Unit(playerName, playerLocation);
        playerController = new UnitController(player);
        camera = new FocusedCamera(screenLocation, player);
    }

    @Override
    public void update(double deltaTime) {
        if (getGlobalKeyManager().isKeyPressed("back"))
            getGlobalScreenManager().goTo("home");

        playerController.update();
        player.update(deltaTime);

        camera.update(deltaTime);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(BLACK);
        graphics.drawString("Battle", 30, 30);
        graphics.drawString("Press [ESCAPE] to go back", 30, 45);

        player.render(graphics, screenLocation);
    }
}
