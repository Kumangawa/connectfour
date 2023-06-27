package ch.supsi.connectfour.frontend.model;

import ch.supsi.connectfour.backend.utility.*;


public class GameModel {
    private Match match;
    private AI ai;
    //constructor
    public GameModel(){
        this.ai =new RandomAI();
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
    public Move playerMoved(int x, int y){
        match.addMove(new Move(x, y, 1));
        int t=-1;
        Move iaMove;
        t=match.isFinished();

        //match finished
        if (t!=0){
            return null;
        }else {
            iaMove= ai.iaMove(match);
            match.addMove(iaMove);
        }
        return iaMove;
    }

    public ReadMatch getReadMatch() { return match; }

    public void loadMatch(Match match) {
        this.match.copy(match);
    }

    public boolean isGameInProgress() {
        return !(this.match.getMoves().size() == 0);
    }
}
