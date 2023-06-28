package ch.supsi.connectfour.backend.utility;

import java.util.Random;

public class ReactiveAI implements AI {
    @Override
    public Move iaMove(Match match) {
        Random random=new Random();
        int randomX;
        int randomY;
        int[][] grid=new int[6][7];
        for (Move m:match.getMoves()) {
            grid[m.x][m.y]=m.player;
        }
        do{
            randomX=random.nextInt(6);
            randomY=random.nextInt(7);
        } while(grid[randomX][randomY]!=0);

        return new Move(randomX ,randomY, 2);


    }
}
