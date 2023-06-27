package ch.supsi.connectfour.backend.utility;

import java.util.Random;

public class RandomAI implements AI {
    @Override
    public Move iaMove(final Match match) {
        Random random=new Random();
        int randomX;
        int randomY;
        int[][] grid=new int[3][3];
        for (Move m:match.getMoves()) {
            grid[m.x][m.y]=m.player;
        }
        do{
            randomX=random.nextInt(3);
            randomY=random.nextInt(3);
        } while(grid[randomX][randomY]!=0);

        return new Move(randomX ,randomY, 2);
    }
}
