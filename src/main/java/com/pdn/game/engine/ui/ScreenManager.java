package com.pdn.game.engine.ui;

public class ScreenManager {
    private final ScreenFactory screenFactory;
    private Screen screen;

    public ScreenManager(ScreenFactory screenFactory) {
        this.screenFactory = screenFactory;
    }

    public void goTo(String screenName) {
        screen = screenFactory.getScreen(screenName);
    }

    public Screen getScreen() {
        return screen;
    }
}
