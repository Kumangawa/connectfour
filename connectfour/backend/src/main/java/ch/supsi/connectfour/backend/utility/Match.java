package ch.supsi.connectfour.backend.utility;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class Match implements ReadMatch{
    private ArrayList<Move> moves=new ArrayList<Move>();

    public Match() {}
    @JsonCreator
    public Match(@JsonProperty("moves") ArrayList<Move> moves) {
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
        int[][] grid = new int[6][7]; // Matrice 6x7 per il gioco di Forza 4

        // Popolare la matrice con le mosse effettuate
        for (Move m : this.getMoves()) {
            grid[m.x][m.y] = m.player;
        }

        // Controllare le righe
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 4; col++) {
                int player = grid[row][col];
                if (player != 0 && player == grid[row][col+1] && player == grid[row][col+2] && player == grid[row][col+3]) {
                    return player; // C'è un vincitore
                }
            }
        }

        // Controllare le colonne
        for (int col = 0; col < 7; col++) {
            for (int row = 0; row < 3; row++) {
                int player = grid[row][col];
                if (player != 0 && player == grid[row+1][col] && player == grid[row+2][col] && player == grid[row+3][col]) {
                    return player; // C'è un vincitore
                }
            }
        }

        // Controllare le diagonali principali (da sinistra a destra)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 4; col++) {
                int player = grid[row][col];
                if (player != 0 && player == grid[row+1][col+1] && player == grid[row+2][col+2] && player == grid[row+3][col+3]) {
                    return player; // C'è un vincitore
                }
            }
        }

        // Controllare le diagonali secondarie (da destra a sinistra)
        for (int row = 0; row < 3; row++) {
            for (int col = 3; col < 7; col++) {
                int player = grid[row][col];
                if (player != 0 && player == grid[row+1][col-1] && player == grid[row+2][col-2] && player == grid[row+3][col-3]) {
                    return player; // C'è un vincitore
                }
            }
        }

        // Se non ci sono mosse disponibili, è un pareggio
        for (int[] row : grid) {
            for (int element : row) {
                if (element == 0) {
                    return 0; // Ci sono ancora mosse disponibili
                }
            }
        }

        // È un pareggio
        return -1;
    }

    @Override
    public Match readMatch() {
        return new Match(this);
    }
    @Override
    public boolean equals(Match match) {
        return false;
    }
    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

    public static Match fromJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Match.class);
    }
}
