package ch.supsi.connectfour.backend.utility;

import java.io.Serializable;

public final class Move implements Serializable {
    public final int x;
    public final int y;
    public final int player;

    public Move(int x, int y, int player) {
        this.x = x;
        this.y = y;
        this.player = player;
    }
}
