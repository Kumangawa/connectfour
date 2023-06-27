package ch.supsi.connectfour.frontend.controller;


import ch.supsi.connectfour.backend.utility.Move;
import ch.supsi.connectfour.backend.utility.ReadMatch;
import ch.supsi.connectfour.backend.utility.ReadPreference;
import ch.supsi.connectfour.frontend.model.GameModel;
import ch.supsi.connectfour.backend.utility.Match;
import ch.supsi.connectfour.frontend.view.GameView;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;


import java.util.ArrayList;

public class GameController {
    private boolean initialized=false;
    private ReadPreference readPreference;
    private GameModel gameModel;
    private GameView gameView;
    // Grid Buttons
    public Button   b00, b01, b02,
            b10, b11, b12,
            b20, b21, b22;

    private boolean endGame;
    private int buttonFree = 9;
    private boolean newGame = false;

    //constructor

    public GameController() {
    }

    //static

    //private
    @FXML
    private void playerMove(ActionEvent e) {
        Move iaMove = null;
        Button clickedButton = (Button) e.getSource();
        if (!clickedButton.isDisabled()) {
            gameView.showMove(clickedButton, trasformColor(readPreference.getColorPlayer()), readPreference.getSimbolPlayer());
            int[] coordinates= fromButtonToCoordinates(clickedButton);
            iaMove=gameModel.playerMoved(coordinates[0],coordinates[1]);
            switch (gameModel.isFinished()){
                //draw
                case -1:
                    endGame(gameView.showPopUpEnd( -1));
                    break;
                case 0:
                    Button aiClickedButton=fromCoordinatesToButton(iaMove.x,iaMove.y);
                    if (aiClickedButton!=null)
                        gameView.showMove(aiClickedButton,
                                trasformColor(readPreference.getColorAI()), readPreference.getSimbolAI());
                    break;
                //win player
                case 1:
                    endGame(gameView.showPopUpEnd( 1));
                    break;
                //win AI
                case 2:
                    endGame(gameView.showPopUpEnd( 2));
                    break;
            }
        }
    }

    @FXML
    private void endGame(boolean choice){
        if (choice){
            // if the player want to play again
            endGame = false;
            newGame();
        } else {
            // if the player want to exit
            Platform.exit();
        }
    }

    private Color trasformColor(String color) {
        Color colore;

        try {
            // Trasformazione della stringa in un colore
            colore = Color.web(color);
        } catch (IllegalArgumentException e) {
            // Gestione dell'eccezione nel caso in cui la stringa non rappresenti un colore valido
            colore = Color.BLACK; // colore di default
        }

        return colore;
    }

    /**
     * b00, b01, b02,
     * b10, b11, b12,
     * b20, b21, b22;
     * */
    private int[] fromButtonToCoordinates(Button b){
        int[] r=new int[2];
        r[0]=Integer.parseInt(b.getId().substring(1,2));
        r[1]=Integer.parseInt(b.getId().substring(2,3));
        return r;
    }
    private Button fromCoordinatesToButton(int x, int y){
        if (x==0&&y==0 && !b00.isDisabled()) {
            return b00;
        } else if ( x==0&&y==1 && !b01.isDisabled()) {
            return b01;
        } else if (x==0&&y==2 && !b02.isDisabled()) {
            return b02;
        } else if (x==1&&y==0 && !b10.isDisabled()) {
            return b10;
        } else if (x==1&&y==1 && !b11.isDisabled()) {
            return b11;
        } else if (x==1&&y==2 && !b12.isDisabled()) {
            return b12;
        } else if (x==2&&y==0 && !b20.isDisabled()) {
            return b20;
        } else if (x==2&&y==1 && !b21.isDisabled()) {
            return b21;
        } else if (x==2&&y==2 && !b22.isDisabled()){
            return b22;
        }
        return null;
    }
    private boolean loadMoves(ArrayList<Move> moves){
        if (moves==null) return false;
        Button button;
        for (Move move: moves) {
            button=fromCoordinatesToButton(move.x, move.y);
            if (button==null) return false;
            if(move.player==1){
                gameView.showMove(button,
                        trasformColor(readPreference.getColorPlayer()), readPreference.getSimbolPlayer());
            }else{
                gameView.showMove(button,
                        trasformColor(readPreference.getColorAI()), readPreference.getSimbolAI());
            }
        }
        return true;
    }
    private void resetBoard(){
        b00.setDisable(false);
        b00.setText("");
        b01.setDisable(false);
        b01.setText("");
        b02.setDisable(false);
        b02.setText("");
        b10.setDisable(false);
        b10.setText("");
        b11.setDisable(false);
        b11.setText("");
        b12.setDisable(false);
        b12.setText("");
        b20.setDisable(false);
        b20.setText("");
        b21.setDisable(false);
        b21.setText("");
        b21.setDisable(false);
        b21.setText("");
        b22.setDisable(false);
        b22.setText("");
    }

    //public
    public void initializeExplicit(ReadPreference readPreference, GameView gameView, GameModel gameModel) {
        this.readPreference = readPreference;
        this.gameView=gameView;
        this.gameModel=gameModel;
        this.initialized=true;
    }

    public ReadMatch getReadMatch(){
        return this.gameModel.getReadMatch();
    }
    public void loadMatch(Match match){
        this.gameModel.loadMatch(match);
        this.resetBoard();
        this.loadMoves(match.getMoves());
    }
    public void newGame() {
        if(this.gameModel.isGameInProgress() && endGame){
            if(gameView.showPopUpNewGame()){
                this.gameModel.reset();
                this.resetBoard();
            }
        } else {
            endGame = true;
            this.gameModel.reset();
            this.resetBoard();
        }

    }
}
