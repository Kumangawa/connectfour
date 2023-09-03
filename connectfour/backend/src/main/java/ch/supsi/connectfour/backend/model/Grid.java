package ch.supsi.connectfour.backend.model;

public class Grid {
    private final int height = 6;
    private final int width = 7;
    private int[][] grid; // Matrice 6x7 per il gioco di Forza 4

    public Grid(){
        this.grid = new int[height][width];
    }
    public int getFromGrid(int i, int y) {
        return grid[i][y];
    }

    public void setInGrid(int i, int y, int data) {
        grid[i][y] = data;
    }

    public int[][] getGrid() {
        return grid;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth(){
        return width;
    }
}
