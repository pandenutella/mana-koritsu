package com.pdn.game.actual.effect;

import com.pdn.game.actual.common.Location;
import lombok.NoArgsConstructor;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class FootMarkManager {
    private static FootMarkManager instance;

    private final List<FootMark> footMarkList = new ArrayList<>();

    public static void initialize() {
        instance = new FootMarkManager();
    }

    public static FootMarkManager getGlobalFootMarkManager() {
        return instance;
    }

    public void add(FootMark footMark) {
        footMarkList.add(footMark);
    }

    public void update(double deltaTime) {
        footMarkList.forEach(footMark -> footMark.update(deltaTime));
        footMarkList.removeIf(FootMark::isExpired);
    }

    public void render(Graphics graphics, Location screenLocation) {
        try {
            footMarkList.forEach(footMark -> footMark.render(graphics, screenLocation));
        } catch (ConcurrentModificationException ignored) {
        }
    }
}
