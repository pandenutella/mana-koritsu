package com.pdn.game.engine.ui;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ScreenManager {
    private static ScreenManager instance;

    private final ScreenFactory screenFactory;

    @Getter
    private Screen screen;

    public static void initialize(ScreenFactory screenFactory) {
        if (instance != null)
            return;

        instance = new ScreenManager(screenFactory);
    }

    public static ScreenManager getGlobalScreenManager() {
        return instance;
    }

    public void goTo(String screenName) {
        screen = screenFactory.getScreen(screenName);
    }
}
