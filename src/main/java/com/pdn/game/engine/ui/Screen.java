package com.pdn.game.engine.ui;

import java.awt.Graphics;

public interface Screen {
    void update(double deltaTime);

    void render(Graphics graphics);
}
