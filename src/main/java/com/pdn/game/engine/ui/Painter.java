package com.pdn.game.engine.ui;

import com.pdn.game.engine.Game;

import javax.swing.JPanel;
import java.awt.Graphics;

public class Painter {
    private PainterPanel component;

    public void setGame(Game game) {
        component = new PainterPanel(game);
    }

    public PainterPanel getComponent() {
        return component;
    }

    private static class PainterPanel extends JPanel {
        private final Game game;

        public PainterPanel(Game game) {
            this.game = game;
        }

        @Override
        protected void paintComponent(Graphics g) {
            game.render(g);
        }
    }
}
