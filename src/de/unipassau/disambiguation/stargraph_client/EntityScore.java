package de.unipassau.disambiguation.stargraph_client;

public final class EntityScore {

    public Entity entity;
    public double score;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityScore)) return false;

        EntityScore that = (EntityScore) o;

        if (Double.compare(that.score, score) != 0) return false;
        return entity.equals(that.entity);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = entity.hashCode();
        temp = Double.doubleToLongBits(score);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "EntityScore{" +
                "entity=" + entity +
                ", score=" + score +
                '}';
    }
}
