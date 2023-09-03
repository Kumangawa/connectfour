package ch.supsi.connectfour.backend.logic;

import ch.supsi.connectfour.backend.model.Grid;
import ch.supsi.connectfour.backend.utility.Move;

import java.util.ArrayList;

public class Logic {
    private Grid grid;

    public int isFinished(ArrayList<Move> moves){
        grid = new Grid();

        // Popolare la matrice con le mosse effettuate
        for (Move m : moves) {
            grid.setInGrid(m.x, m.y, m.player);
        }

        // Controllare le righe
        for (int row = 0; row < grid.getHeight(); row++) {
            for (int col = 0; col < 4; col++) {
                int player = grid.getFromGrid(row, col);
                if (player != 0 && player == grid.getFromGrid(row, col+1) && player == grid.getFromGrid(row, col+2) && player == grid.getFromGrid(row, col+3)) {
                    return player; // C'è un vincitore
                }
            }
        }

        // Controllare le colonne
        for (int col = 0; col < grid.getWidth(); col++) {
            for (int row = 0; row < 3; row++) {
                int player = grid.getFromGrid(row, col);
                if (player != 0 && player == grid.getFromGrid(row+1, col) && player == grid.getFromGrid(row+2, col)&& player == grid.getFromGrid(row+3, col)) {
                    return player; // C'è un vincitore
                }
            }
        }

        // Controllare le diagonali principali (da sinistra a destra)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 4; col++) {
                int player = grid.getFromGrid(row, col);
                if (player != 0 && player == grid.getFromGrid(row+1, col+1) && player == grid.getFromGrid(row+2, col+2) && player == grid.getFromGrid(row+3, col+3)) {
                    return player; // C'è un vincitore
                }
            }
        }

        // Controllare le diagonali secondarie (da destra a sinistra)
        for (int row = 0; row < 3; row++) {
            for (int col = 3; col < 7; col++) {
                int player = grid.getFromGrid(row, col);
                if (player != 0 && player == grid.getFromGrid(row+1, col-1) && player == grid.getFromGrid(row+2, col-2) && player == grid.getFromGrid(row+3, col-3)) {
                    return player; // C'è un vincitore
                }
            }
        }

        // Se non ci sono mosse disponibili, è un pareggio
        for (int[] row : grid.getGrid()) {
            for (int element : row) {
                if (element == 0) {
                    return 0; // Ci sono ancora mosse disponibili
                }
            }
        }

        // È un pareggio
        return -1;
    };
}
