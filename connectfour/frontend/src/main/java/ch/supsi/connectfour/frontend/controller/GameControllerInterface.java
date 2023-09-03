package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.backend.model.Match;
import ch.supsi.connectfour.backend.model.PreferenceInterface;
import ch.supsi.connectfour.backend.model.MatchInterface;
import ch.supsi.connectfour.frontend.model.GameModelInterface;
import ch.supsi.connectfour.frontend.view.GameViewInterface;
import ch.supsi.connectfour.frontend.view.InfoBarViewInterface;

public interface GameControllerInterface {

    public void initializeExplicit(PreferenceInterface readPreference, GameViewInterface gameView, GameModelInterface gameModel, InfoBarViewInterface infoBarView);

    public MatchInterface getReadMatch();
    public void loadMatch(Match match);
    public void newGame();
}
