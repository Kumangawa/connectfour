package ch.supsi.connectfour.backend.utility;


public final class Move{
    public final int x;
    public final int y;
    public final int player;

    public Move(){
        this.x = 0;
        this.y = 0;
        this.player = 0;
    }
    public Move(int x, int y, int player) {
        this.x = x;
        this.y = y;
        this.player = player;
    }

}
