package com.pdn.game.engine.ui;

import com.pdn.game.engine.key.KeyManager;

import javax.swing.JFrame;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Window {
    private final JFrame jFrame;

    public Window(String title) {
        jFrame = new JFrame(title);

        jFrame.setSize(1300, 900);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void setPainter(Painter painter) {
        jFrame.getContentPane().add(painter.getComponent());
    }

    public void setKeyManager(KeyManager keyManager) {
        jFrame.addKeyListener(keyManager.getListener());
    }

    public void display() {
        jFrame.setVisible(true);
    }

    public void repaint() {
        jFrame.repaint();
    }
}
