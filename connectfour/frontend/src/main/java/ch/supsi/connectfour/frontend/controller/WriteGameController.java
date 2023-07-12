package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.backend.utility.Match;
import ch.supsi.connectfour.backend.utility.ReadMatch;
import ch.supsi.connectfour.backend.utility.ReadPreference;
import ch.supsi.connectfour.frontend.controller.observer.WriteObserver;
import ch.supsi.connectfour.frontend.model.ReadAndWriteGameModel;
import ch.supsi.connectfour.frontend.view.WriteGameView;

public interface WriteGameController {

    public void initializeExplicit(ReadPreference readPreference, WriteGameView gameView, ReadAndWriteGameModel gameModel, WriteObserver observer);

    public ReadMatch getReadMatch();
    public void loadMatch(Match match);
    public void newGame();
}
