package com.pdn.game.actual;

import com.pdn.game.actual.screen.BattleScreen;
import com.pdn.game.actual.screen.HomeScreen;
import com.pdn.game.engine.ui.Screen;
import com.pdn.game.engine.ui.ScreenFactory;

public class MkScreenFactory implements ScreenFactory {
    @Override
    public Screen getScreen(String name) {
        switch (name) {
            case "home":
                return new HomeScreen();
            case "battle":
                return new BattleScreen();
            default:
                return null;
        }
    }
}
