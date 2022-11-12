package com.pdn.game.engine.ui;

public class ScreenManager {
    private static ScreenManager instance;

    private final ScreenFactory screenFactory;
    private Screen screen;

    private ScreenManager(ScreenFactory screenFactory) {
        this.screenFactory = screenFactory;
    }

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

    public Screen getScreen() {
        return screen;
    }
}
