package com.pdn.game;

import com.pdn.game.actual.MkScreenFactory;
import com.pdn.game.engine.Game;
import com.pdn.game.engine.key.KeyManager;
import com.pdn.game.engine.ui.Painter;
import com.pdn.game.engine.ui.ScreenManager;
import com.pdn.game.engine.ui.Window;

import java.util.HashMap;
import java.util.Map;

import static com.pdn.game.engine.key.KeyManager.getGlobalKeyManager;
import static com.pdn.game.engine.ui.ScreenManager.getGlobalScreenManager;
import static java.awt.event.KeyEvent.VK_A;
import static java.awt.event.KeyEvent.VK_D;
import static java.awt.event.KeyEvent.VK_E;
import static java.awt.event.KeyEvent.VK_ESCAPE;
import static java.awt.event.KeyEvent.VK_J;
import static java.awt.event.KeyEvent.VK_K;
import static java.awt.event.KeyEvent.VK_L;
import static java.awt.event.KeyEvent.VK_Q;
import static java.awt.event.KeyEvent.VK_S;
import static java.awt.event.KeyEvent.VK_SPACE;
import static java.awt.event.KeyEvent.VK_W;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();

        Painter painter = new Painter();
        painter.setGame(game);

        Map<Integer, String> keyCodeNameMap = new HashMap<>();
        keyCodeNameMap.put(VK_SPACE, "next");
        keyCodeNameMap.put(VK_ESCAPE, "back");
        keyCodeNameMap.put(VK_W, "move-up");
        keyCodeNameMap.put(VK_D, "move-right");
        keyCodeNameMap.put(VK_S, "move-down");
        keyCodeNameMap.put(VK_A, "move-left");
        keyCodeNameMap.put(VK_Q, "peek-left");
        keyCodeNameMap.put(VK_E, "peek-right");
        keyCodeNameMap.put(VK_J, "skill-sword");
        keyCodeNameMap.put(VK_K, "skill-sphere");
        keyCodeNameMap.put(VK_L, "skill-shield");

        KeyManager.initialize(keyCodeNameMap);

        Window window = new Window("mana koritsu by pandenutella");
        window.setPainter(painter);
        window.setKeyManager(getGlobalKeyManager());

        ScreenManager.initialize(new MkScreenFactory());
        getGlobalScreenManager().goTo("home");

        game.setWindow(window);
        game.start();
    }
}
