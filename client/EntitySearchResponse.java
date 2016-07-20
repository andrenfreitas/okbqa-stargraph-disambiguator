package net.stargraph.client;

import java.util.ArrayList;
import java.util.List;

public final class EntitySearchResponse {
    public List<EntityScore> entityScores = new ArrayList<>();

    @Override
    public String toString() {
        return "EntitySearchResponse{" +
                "entityScores=" + entityScores +
                '}';
    }
}
