package ch.supsi.connectfour.backend.utility;

import java.io.Serializable;
import java.util.ArrayList;

public class Match implements Serializable ,ReadMatch{
    private ArrayList<Move> moves=new ArrayList<Move>();

    public Match() {}
    public Match( ArrayList<Move> moves) {
        this.moves = moves;
    }
    public Match(Match match) {
        this.moves = (ArrayList<Move>) match.moves.clone();
    }
    public ArrayList<Move> getMoves() {
        return (ArrayList<Move>) moves.clone();
    }
    public void addMove(Move move){
        this.moves.add(move);
    }
    public void copy(Match match){
        try {
            this.moves= (ArrayList<Move>) match.moves.clone();
        }catch (ClassCastException e){
            this.moves=new ArrayList<>();
        }
    }
    public int isFinished(){
        int[][] grid=new int[3][3];
        for (Move m:this.getMoves()) {
            grid[m.x][m.y]=m.player;
        }
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (grid[row][0]!=0 && grid[row][0] == grid[row][1] && grid[row][1] == grid[row][2]) {
                return grid[row][0];
            }
        }
        // Check columns
        for (int col = 0; col < 3; col++) {
            if (grid[0][col]!=0 && grid[0][col] == grid[1][col] && grid[1][col] == grid[2][col]) {
                return grid[0][col];
            }
        }
        // Check diagonals
        if ((grid[0][0]!=0 && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) ||
                (grid[0][2]!=0 && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0])) {
            return grid[1][1];
        }

        //there are free cell
        for (int[] row : grid)
            for (int element : row) if(element==0) return 0;
        //its a draw
        return -1;
    }

    @Override
    public Match readMatch() {
        return new Match(this);
    }
    @Override
    public boolean equals(Match match) {
        return false;
    }
}
