package com.pdn.game;

import com.pdn.game.engine.Game;
import com.pdn.game.engine.key.KeyManager;
import com.pdn.game.engine.ui.Painter;
import com.pdn.game.engine.ui.Window;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();

        Painter painter = new Painter();
        painter.setGame(game);

        Map<Integer, String> keyCodeNameMap = new HashMap<>();
        keyCodeNameMap.put(KeyEvent.VK_SPACE, "next");
        keyCodeNameMap.put(KeyEvent.VK_ESCAPE, "back");

        KeyManager keyManager = new KeyManager(keyCodeNameMap);

        Window window = new Window("mana koritsu by pandenutella");
        window.setPainter(painter);
        window.setKeyManager(keyManager);

        game.setWindow(window);
        game.start();
    }
}
