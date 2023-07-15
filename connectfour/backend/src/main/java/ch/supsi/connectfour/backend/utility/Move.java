package ch.supsi.connectfour.backend.utility;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class Move{
    public final int x;
    public final int y;
    public final int player;

    @JsonCreator
    public Move(@JsonProperty("x") int x, @JsonProperty("y") int y, @JsonProperty("player") int player) {
        this.x = x;
        this.y = y;
        this.player = player;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

    public static Move fromJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Move.class);
    }
}
