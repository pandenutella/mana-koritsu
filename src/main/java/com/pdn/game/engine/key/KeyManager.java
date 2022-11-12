package com.pdn.game.engine.key;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import static java.util.Comparator.comparingDouble;

public class KeyManager {
    private static KeyManager INSTANCE;

    protected final Map<String, KeyPress> keyPressMap;
    private final KeyManagerListener listener;

    private KeyManager(Map<Integer, String> keyCodeNameMap) {
        keyPressMap = new HashMap<>();
        keyCodeNameMap.forEach((code, name) -> keyPressMap.put(name, new KeyPress(name)));

        listener = new KeyManagerListener(keyCodeNameMap, keyPressMap);
    }

    public static void initialize(Map<Integer, String> keyCodeNameMap) {
        if (INSTANCE != null)
            return;

        INSTANCE = new KeyManager(keyCodeNameMap);
    }

    public static KeyManager getGlobalKeyManager() {
        return INSTANCE;
    }

    public String getLatestPressedKey(String partialName) {
        return keyPressMap.values().stream()
                .filter(KeyPress::isPressed)
                .filter(keyPress -> keyPress.getName().contains(partialName))
                .sorted(comparingDouble(KeyPress::getLastInteraction).reversed())
                .map(KeyPress::getName)
                .findFirst()
                .orElse(null);
    }

    public boolean isKeyPressed(String name) {
        KeyPress keyPress = keyPressMap.get(name);
        if (keyPress == null)
            return false;

        return keyPress.isPressed();
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
