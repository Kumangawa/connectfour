package ch.supsi.connectfour.backend.model;

import ch.supsi.connectfour.backend.logic.Logic;
import ch.supsi.connectfour.backend.utility.Move;

import java.util.ArrayList;

public class Match implements MatchInterface {
    private ArrayList<Move> moves=new ArrayList<Move>();

    private Logic logic = new Logic();

    public Match() {}

    public Match(ArrayList<Move> moves) {
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

    public int isFinished() {
        return logic.isFinished(this.getMoves());
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
