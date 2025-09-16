package ch.supsi.connectfour.frontend.model;

import ch.supsi.connectfour.backend.model.Match;
import ch.supsi.connectfour.backend.model.MatchInterface;

public interface GameModelInterface {

    public void reset();
    public int isFinished();
    public void playerMoved(int x, int y, boolean firstPlayerTurn);
    public MatchInterface getReadMatch();
    public void loadMatch(Match match);
    public boolean isGameInProgress();
}
