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
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;


import java.util.ArrayList;

public class GameController {
    private boolean initialized=false;
    private ReadPreference readPreference;
    private GameModel gameModel;
    private GameView gameView;
    // Grid Buttons
    public Button   b00, b01, b02, b03, b04, b05, b06,
                    b10, b11, b12, b13, b14, b15, b16,
                    b20, b21, b22, b23, b24, b25, b26,
                    b30, b31, b32, b33, b34, b35, b36,
                    b40, b41, b42, b43, b44, b45, b46,
                    b50, b51, b52, b53, b54, b55, b56;

    private boolean endGame;
    private boolean firstPlayerTurn = true;
    private Button[][] buttonGrid = {
            {b00, b01, b02, b03, b04, b05, b06},
            {b10, b11, b12, b13, b14, b15, b16},
            {b20, b21, b22, b23, b24, b25, b26},
            {b30, b31, b32, b33, b34, b35, b36},
            {b40, b41, b42, b43, b44, b45, b46},
            {b50, b51, b52, b53, b54, b55, b56}
    };

    //constructor

    public GameController() {
    }

    //static

    //private
    @FXML
    private void playerMove(ActionEvent e) {
        Button clickedButton = (Button) e.getSource();
        if (!clickedButton.isDisabled()) {
            int columnIndex = getColumnIndex(clickedButton);
            Button targetButton = getBottomButtonInColumn(columnIndex);
            if (firstPlayerTurn){
                gameView.showMove(targetButton, trasformColor(readPreference.getColorPlayerFirst()), readPreference.getSimbolPlayerFirst());
            } else {
                gameView.showMove(targetButton, trasformColor(readPreference.getColorPlayerSecond()), readPreference.getSimbolPlayerSecond());
            }
            int[] coordinates= fromButtonToCoordinates(targetButton);
            gameModel.playerMoved(coordinates[0],coordinates[1], firstPlayerTurn);
            firstPlayerTurn = !firstPlayerTurn;
            switch (gameModel.isFinished()){
                //draw
                case -1:
                    endGame(gameView.showPopUpEnd( -1));
                    break;
                case 0:
                    // The game is still on
                    break;
                //win playerFirst
                case 1:
                    endGame(gameView.showPopUpEnd( 1));
                    break;
                //win playerSecond
                case 2:
                    endGame(gameView.showPopUpEnd( 2));
                    break;
            }
        }
    }

    private Button getBottomButtonInColumn(int columnIndex) {
        System.out.println("L indice e' " + columnIndex);

        if(columnIndex == 0) {
            if (!b50.isDisabled()) {
                return b50;
            } else if (!b40.isDisabled()) {
                return b40;
            } else if (!b30.isDisabled()) {
                return b30;
            } else if (!b20.isDisabled()) {
                return b20;
            } else if (!b10.isDisabled()) {
                return b10;
            } else {
                return b00;
            }
        } else if (columnIndex == 1) {
            if (!b51.isDisabled()) {
                return b51;
            } else if (!b41.isDisabled()) {
                return b41;
            } else if (!b31.isDisabled()) {
                return b31;
            } else if (!b21.isDisabled()) {
                return b21;
            } else if (!b11.isDisabled()) {
                return b11;
            } else {
                return b01;
            }
        } else if (columnIndex == 2) {
            if (!b52.isDisabled()) {
                return b52;
            } else if (!b42.isDisabled()) {
                return b42;
            } else if (!b32.isDisabled()) {
                return b32;
            } else if (!b22.isDisabled()) {
                return b22;
            } else if (!b12.isDisabled()) {
                return b12;
            } else {
                return b02;
            }
        } else if (columnIndex == 3) {
            if (!b53.isDisabled()) {
                return b53;
            } else if (!b43.isDisabled()) {
                return b43;
            } else if (!b33.isDisabled()) {
                return b33;
            } else if (!b23.isDisabled()) {
                return b23;
            } else if (!b13.isDisabled()) {
                return b13;
            } else {
                return b03;
            }
        } else if (columnIndex == 4) {
            if (!b54.isDisabled()) {
                return b54;
            } else if (!b44.isDisabled()) {
                return b44;
            } else if (!b34.isDisabled()) {
                return b34;
            } else if (!b24.isDisabled()) {
                return b24;
            } else if (!b14.isDisabled()) {
                return b14;
            } else {
                return b04;
            }
        } else if (columnIndex == 5) {
            if (!b55.isDisabled()) {
                return b55;
            } else if (!b45.isDisabled()) {
                return b45;
            } else if (!b35.isDisabled()) {
                return b35;
            } else if (!b25.isDisabled()) {
                return b25;
            } else if (!b15.isDisabled()) {
                return b15;
            } else {
                return b05;
            }
        } else {
            if (!b56.isDisabled()) {
                return b56;
            } else if (!b46.isDisabled()) {
                return b46;
            } else if (!b36.isDisabled()) {
                return b36;
            } else if (!b26.isDisabled()) {
                return b26;
            } else if (!b16.isDisabled()) {
                return b16;
            } else {
                return b06;
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

    private int getColumnIndex(Button button) {
        Integer columnIndex = GridPane.getColumnIndex(button);
        return columnIndex != null ? columnIndex : -1;
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
        if (x >= 0 && x < buttonGrid.length && y >= 0 && y < buttonGrid[x].length) {
            Button button = buttonGrid[x][y];
            if (!button.isDisabled()) {
                return button;
            }
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
                        trasformColor(readPreference.getColorPlayerFirst()), readPreference.getSimbolPlayerFirst());
            }else{
                gameView.showMove(button,
                        trasformColor(readPreference.getColorPlayerSecond()), readPreference.getSimbolPlayerSecond());
            }
        }
        return true;
    }
    private void resetBoard(){
        Button[] buttonsToDisable = {
                b00, b01, b02, b03, b04, b05, b06,
                b10, b11, b12, b13, b14, b15, b16,
                b20, b21, b22, b23, b24, b25, b26,
                b30, b31, b32, b33, b34, b35, b36,
                b40, b41, b42, b43, b44, b45, b46,
                b50, b51, b52, b53, b54, b55, b56
        };

        for (Button button : buttonsToDisable) {
            button.setDisable(false);
            button.setText("");
        }
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
