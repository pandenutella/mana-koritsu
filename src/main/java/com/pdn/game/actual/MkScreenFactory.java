package com.pdn.game.actual;

import com.pdn.game.actual.screen.BattleScreen;
import com.pdn.game.actual.screen.HomeScreen;
import com.pdn.game.engine.key.KeyManager;
import com.pdn.game.engine.ui.Screen;
import com.pdn.game.engine.ui.ScreenFactory;
import com.pdn.game.engine.ui.ScreenManager;

public class MkScreenFactory implements ScreenFactory {
    private ScreenManager screenManager;
    private KeyManager keyManager;

    @Override
    public Screen getScreen(String name) {
        switch (name) {
            case "home":
                return new HomeScreen(screenManager, keyManager);
            case "battle":
                return new BattleScreen(screenManager, keyManager);
            default:
                return null;
        }
    }

    public void setScreenManager(ScreenManager screenManager) {
        this.screenManager = screenManager;
    }

    public void setKeyManager(KeyManager keyManager) {
        this.keyManager = keyManager;
    }
}
