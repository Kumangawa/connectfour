package ch.supsi.connectfour.frontend.controller;


import ch.supsi.connectfour.backend.utility.Move;
import ch.supsi.connectfour.backend.utility.ReadMatch;
import ch.supsi.connectfour.backend.utility.ReadPreference;
import ch.supsi.connectfour.frontend.controller.observer.WriteObserver;
import ch.supsi.connectfour.backend.utility.Match;
import ch.supsi.connectfour.frontend.model.ReadAndWriteGameModel;

import ch.supsi.connectfour.frontend.view.WriteGameView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;


import java.util.ArrayList;
import java.util.Objects;

public class GameController implements WriteGameController{
    private boolean initialized=false;
    private ReadPreference readPreference;
    private ReadAndWriteGameModel gameModel;
    private WriteGameView gameView;
    private WriteObserver observer;
    // Grid Buttons
    public Button   b00, b01, b02, b03, b04, b05, b06,
                    b10, b11, b12, b13, b14, b15, b16,
                    b20, b21, b22, b23, b24, b25, b26,
                    b30, b31, b32, b33, b34, b35, b36,
                    b40, b41, b42, b43, b44, b45, b46,
                    b50, b51, b52, b53, b54, b55, b56;

    private boolean endGame;
    private boolean firstPlayerTurn = true;

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
            if (firstPlayerTurn){
                observer.changeTurnToPlayerOne();
            } else {
                observer.changeTurnToPlayerTwo();
            }
        }
    }

    private Button getBottomButtonInColumn(int columnIndex) {
        if(columnIndex == 0) {
            if (Objects.equals(b50.getText(), "")) {
                return b50;
            } else if (Objects.equals(b40.getText(), "")) {
                return b40;
            } else if (Objects.equals(b30.getText(), "")) {
                return b30;
            } else if (Objects.equals(b20.getText(), "")) {
                return b20;
            } else if (Objects.equals(b10.getText(), "")) {
                return b10;
            } else {
                return b00;
            }
        } else if (columnIndex == 1) {
            if (Objects.equals(b51.getText(), "")) {
                return b51;
            } else if (Objects.equals(b41.getText(), "")) {
                return b41;
            } else if (Objects.equals(b31.getText(), "")) {
                return b31;
            } else if (Objects.equals(b21.getText(), "")) {
                return b21;
            } else if (Objects.equals(b11.getText(), "")) {
                return b11;
            } else {
                return b01;
            }
        } else if (columnIndex == 2) {
            if (Objects.equals(b52.getText(), "")) {
                return b52;
            } else if (Objects.equals(b42.getText(), "")) {
                return b42;
            } else if (Objects.equals(b32.getText(), "")) {
                return b32;
            } else if (Objects.equals(b22.getText(), "")) {
                return b22;
            } else if (Objects.equals(b12.getText(), "")) {
                return b12;
            } else {
                return b02;
            }
        } else if (columnIndex == 3) {
            if (Objects.equals(b53.getText(), "")) {
                return b53;
            } else if (Objects.equals(b43.getText(), "")) {
                return b43;
            } else if (Objects.equals(b33.getText(), "")) {
                return b33;
            } else if (Objects.equals(b23.getText(), "")) {
                return b23;
            } else if (Objects.equals(b13.getText(), "")) {
                return b13;
            } else {
                return b03;
            }
        } else if (columnIndex == 4) {
            if (Objects.equals(b54.getText(), "")) {
                return b54;
            } else if (Objects.equals(b44.getText(), "")) {
                return b44;
            } else if (Objects.equals(b34.getText(), "")) {
                return b34;
            } else if (Objects.equals(b24.getText(), "")) {
                return b24;
            } else if (Objects.equals(b14.getText(), "")) {
                return b14;
            } else {
                return b04;
            }
        } else if (columnIndex == 5) {
            if (Objects.equals(b55.getText(), "")) {
                return b55;
            } else if (Objects.equals(b45.getText(), "")) {
                return b45;
            } else if (Objects.equals(b35.getText(), "")) {
                return b35;
            } else if (Objects.equals(b25.getText(), "")) {
                return b25;
            } else if (Objects.equals(b15.getText(), "")) {
                return b15;
            } else {
                return b05;
            }
        } else {
            if (Objects.equals(b56.getText(), "")) {
                return b56;
            } else if (Objects.equals(b46.getText(), "")) {
                return b46;
            } else if (Objects.equals(b36.getText(), "")) {
                return b36;
            } else if (Objects.equals(b26.getText(), "")) {
                return b26;
            } else if (Objects.equals(b16.getText(), "")) {
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
    private int[] fromButtonToCoordinates(Button b){
        int[] r=new int[2];
        r[0]=Integer.parseInt(b.getId().substring(1,2));
        r[1]=Integer.parseInt(b.getId().substring(2,3));
        return r;
    }
    private Button fromCoordinatesToButton(int x, int y){
        if (x==0&&y==0 && Objects.equals(b00.getText(), "")) {
            return b00;
        } else if (x==0&&y==1 && Objects.equals(b01.getText(), "")) {
            return b01;
        } else if (x==0&&y==2 && Objects.equals(b02.getText(), "")) {
            return b02;
        } else if (x==0&&y==3 && Objects.equals(b03.getText(), "")) {
            return b03;
        } else if (x==0&&y==4 && Objects.equals(b04.getText(), "")) {
            return b04;
        } else if (x==0&&y==5 && Objects.equals(b05.getText(), "")) {
            return b05;
        } else if (x==0&&y==6 && Objects.equals(b06.getText(), "")) {
            return b06;

        } else if (x==1&&y==0 && Objects.equals(b10.getText(), "")){
            return b10;
        } else if (x==1&&y==1 && Objects.equals(b11.getText(), "")) {
            return b11;
        } else if (x==1&&y==2 && Objects.equals(b12.getText(), "")) {
            return b12;
        } else if (x==1&&y==3 && Objects.equals(b13.getText(), "")) {
            return b13;
        } else if (x==1&&y==4 && Objects.equals(b14.getText(), "")) {
            return b14;
        } else if (x==1&&y==5 && Objects.equals(b15.getText(), "")) {
            return b15;
        } else if (x==1&&y==6 && Objects.equals(b16.getText(), "")) {
            return b16;

        } else if (x==2&&y==0 && Objects.equals(b20.getText(), "")) {
            return b20;
        } else if (x==2&&y==1 && Objects.equals(b21.getText(), "")){
            return b21;
        } else if (x==2&&y==2 && Objects.equals(b22.getText(), "")) {
            return b21;
        } else if (x==2&&y==3 && Objects.equals(b23.getText(), "")) {
            return b23;
        } else if (x==2&&y==4 && Objects.equals(b24.getText(), "")) {
            return b24;
        } else if (x==2&&y==5 && Objects.equals(b25.getText(), "")) {
            return b25;
        } else if (x==2&&y==6 && Objects.equals(b26.getText(), "")) {
            return b26;

        } else if (x==3&&y==0 && Objects.equals(b30.getText(), "")) {
            return b30;
        } else if (x==3&&y==1 && Objects.equals(b31.getText(), "")) {
            return b31;
        } else if (x==3&&y==2 && Objects.equals(b32.getText(), "")){
            return b32;
        } else if (x==3&&y==3 && Objects.equals(b33.getText(), "")) {
            return b33;
        } else if (x==3&&y==4 && Objects.equals(b34.getText(), "")) {
            return b34;
        } else if (x==3&&y==5 && Objects.equals(b35.getText(), "")) {
            return b35;
        } else if (x==3&&y==6 && Objects.equals(b36.getText(), "")) {
            return b36;

        } else if (x==4&&y==0 && Objects.equals(b40.getText(), "")) {
            return b40;
        } else if (x==4&&y==1 && Objects.equals(b41.getText(), "")) {
            return b41;
        } else if (x==4&&y==2 && Objects.equals(b42.getText(), "")) {
            return b42;
        } else if (x==4&&y==3 && Objects.equals(b43.getText(), "")){
            return b43;
        } else if (x==4&&y==4 && Objects.equals(b44.getText(), "")) {
            return b44;
        } else if (x==4&&y==5 && Objects.equals(b45.getText(), "")) {
            return b45;
        } else if (x==4&&y==6 && Objects.equals(b46.getText(), "")){
            return b46;

        } else if (x==5&&y==0 && Objects.equals(b50.getText(), "")) {
            return b50;
        } else if (x==5&&y==1 && Objects.equals(b51.getText(), "")) {
            return b51;
        } else if (x==5&&y==2 && Objects.equals(b52.getText(), "")) {
            return b52;
        } else if (x==5&&y==3 && Objects.equals(b53.getText(), "")){
            return b53;
        } else if (x==5&&y==4 && Objects.equals(b54.getText(), "")) {
            return b54;
        } else if (x==5&&y==5 && Objects.equals(b55.getText(), "")) {
            return b55;
        } else if (x==5&&y==6 && Objects.equals(b56.getText(), "")){
            return b56;
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
            button.setText("");
        }
    }

    private void setDisable(){
        b00.setDisable(true); b01.setDisable(true); b02.setDisable(true); b03.setDisable(true); b04.setDisable(true); b05.setDisable(true); b06.setDisable(true);
        b10.setDisable(true); b11.setDisable(true); b12.setDisable(true); b13.setDisable(true); b14.setDisable(true); b15.setDisable(true); b16.setDisable(true);
        b20.setDisable(true); b21.setDisable(true); b22.setDisable(true); b23.setDisable(true); b24.setDisable(true); b25.setDisable(true); b26.setDisable(true);
        b30.setDisable(true); b31.setDisable(true); b32.setDisable(true); b33.setDisable(true); b34.setDisable(true); b35.setDisable(true); b36.setDisable(true);
        b40.setDisable(true); b41.setDisable(true); b42.setDisable(true); b43.setDisable(true); b44.setDisable(true); b45.setDisable(true); b46.setDisable(true);
        b50.setDisable(true); b51.setDisable(true); b52.setDisable(true); b53.setDisable(true); b54.setDisable(true); b55.setDisable(true); b56.setDisable(true);
    }

    //public
    public void initializeExplicit(ReadPreference readPreference, WriteGameView gameView, ReadAndWriteGameModel gameModel, WriteObserver observer) {
        this.readPreference = readPreference;
        this.gameView=gameView;
        this.gameModel=gameModel;
        this.initialized=true;
        this.observer=observer;
        setDisable();
    }

    public ReadMatch getReadMatch(){
        return this.gameModel.getReadMatch();
    }
    public void loadMatch(Match match){
        this.gameModel.loadMatch(match);
        this.resetBoard();
        this.loadMoves(match.getMoves());
        observer.loadMatch();

        if (match.getMoves().size() % 2 == 0){
            observer.changeTurnToPlayerOne();
            firstPlayerTurn = true;
        } else {
            observer.changeTurnToPlayerTwo();
            firstPlayerTurn = false;
        }
    }
    public void newGame() {
        if(this.gameModel.isGameInProgress() && endGame){
            if(gameView.showPopUpNewGame()){
                this.gameModel.reset();
                this.resetBoard();
                observer.changeTurnToPlayerOne();
            }
        } else {
            endGame = true;
            this.gameModel.reset();
            this.resetBoard();
            observer.changeTurnToPlayerOne();
        }

    }
}
