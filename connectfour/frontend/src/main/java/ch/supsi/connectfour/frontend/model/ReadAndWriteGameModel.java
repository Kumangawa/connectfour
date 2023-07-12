package ch.supsi.connectfour.frontend.model;

import ch.supsi.connectfour.backend.utility.Match;
import ch.supsi.connectfour.backend.utility.ReadMatch;

public interface ReadAndWriteGameModel {

    public void reset();
    public int isFinished();
    public void playerMoved(int x, int y, boolean firstPlayerTurn);
    public ReadMatch getReadMatch();
    public void loadMatch(Match match);
    public boolean isGameInProgress();
}
