package ch.supsi.connectfour.frontend.model;

import ch.supsi.connectfour.backend.model.Match;
import ch.supsi.connectfour.backend.model.MatchInterface;
import ch.supsi.connectfour.backend.utility.*;

public class GameModel implements GameModelInterface {
    private Match match;
    //constructor
    public GameModel(){
        this.match=new Match();
    }
    //static

    //private

    //public
    public void reset(){
        this.match.copy(new Match());
    }
    public int isFinished(){
        return this.match.isFinished();
    }
    public void playerMoved(int x, int y, boolean firstPlayerTurn){
        if (firstPlayerTurn){
            match.addMove(new Move(x, y, 1));
        } else {
            match.addMove(new Move(x, y, 2));
        }
    }

    public MatchInterface getReadMatch() { return match; }

    public void loadMatch(Match match) {
        this.match.copy(match);
    }

    public boolean isGameInProgress() {
        return !(this.match.getMoves().size() == 0);
    }
}
