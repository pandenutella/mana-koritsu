package com.pdn.game.actual.utility;

import com.pdn.game.actual.common.Direction;
import lombok.NoArgsConstructor;

import static com.pdn.game.actual.common.Direction.DOWN;
import static com.pdn.game.actual.common.Direction.LEFT;
import static com.pdn.game.actual.common.Direction.RIGHT;
import static com.pdn.game.actual.common.Direction.UP;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class DirectionUtility {
    public static Direction getLeftOf(Direction direction) {
        switch (direction) {
            case UP:
                return LEFT;
            case RIGHT:
                return UP;
            case DOWN:
                return RIGHT;
            case LEFT:
                return DOWN;
            default:
                return null;
        }
    }

    public static Direction getRightOf(Direction direction) {
        switch (direction) {
            case UP:
                return RIGHT;
            case RIGHT:
                return DOWN;
            case DOWN:
                return LEFT;
            case LEFT:
                return UP;
            default:
                return null;
        }
    }
}
