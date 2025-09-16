package ch.supsi.connectfour.backend.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Player {
    private String currentSymbol;
    private String currentColor;
    private String nextSymbol;
    private String nextColor;

    // Costruttore predefinito senza argomenti
    public Player() {
        this.currentSymbol = null;
        this.currentColor = null;
        this.nextSymbol = null;
        this.nextColor = null;
    }

    public Player(String symbol, String color) {
        this.currentSymbol = symbol;
        this.currentColor = color;
        this.nextSymbol = this.currentSymbol;
        this.nextColor = this.currentColor;
    }

    public String getCurrentSymbol() {
        return currentSymbol;
    }

    public String getNextSymbol() {
        return nextSymbol;
    }

    public void setNextSymbol(String nextSymbol) {
        this.nextSymbol = nextSymbol;
    }

    public String getCurrentColor() {
        return currentColor;
    }

    public String getNextColor() {
        return nextColor;
    }
    public void setNextColor(String color) {
        this.nextColor = color;
    }

    public void setCurrentSymbol(String nextSymbol) {
        this.currentSymbol = nextSymbol;
    }

    public void setCurrentColor(String nextColor) {
        this.currentColor = nextColor;
    }
}
