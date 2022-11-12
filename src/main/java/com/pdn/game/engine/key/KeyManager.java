package com.pdn.game.engine.key;

import com.pdn.game.engine.key.KeyPress;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import static java.util.Comparator.comparingDouble;

public class KeyManager {
    protected final Map<String, KeyPress> keyPressMap;
    private final KeyManagerListener listener;

    public KeyManager(Map<Integer, String> keyCodeNameMap) {
        keyPressMap = new HashMap<>();
        keyCodeNameMap.forEach((code, name) -> keyPressMap.put(name, new KeyPress(name)));

        listener = new KeyManagerListener(keyCodeNameMap, keyPressMap);
    }

    public String getLatestPressedKey(String partialName) {
        return keyPressMap.values().stream()
                .filter(KeyPress::isPressed)
                .filter(keyPress -> keyPress.getName().contains(partialName))
                .sorted(comparingDouble(KeyPress::getLastInteraction))
                .map(KeyPress::getName)
                .findFirst()
                .orElse(null);
    }

    public KeyManagerListener getListener() {
        return listener;
    }

    private static class KeyManagerListener implements KeyListener {
        private final Map<Integer, String> keyCodeNameMap;
        private final Map<String, KeyPress> keyPressMap;

        private KeyManagerListener(Map<Integer, String> keyCodeNameMap, Map<String, KeyPress> keyPressMap) {
            this.keyCodeNameMap = keyCodeNameMap;
            this.keyPressMap = keyPressMap;
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            String name = keyCodeNameMap.get(e.getKeyCode());
            if (name == null)
                return;

            keyPressMap.get(name).press();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            String name = keyCodeNameMap.get(e.getKeyCode());
            if (name == null)
                return;

            keyPressMap.get(name).release();
        }
    }
}
