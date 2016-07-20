package de.unipassau.disambiguation.stargraph_client;

public final class Entity {

    public String id;
    public EntityType type;
    public String value;
    public boolean literal;


    @Override
    public String toString() {
        return "Entity{" +
                "Id='" + id + '\'' +
                ", value='" + value + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entity entity = (Entity) o;

        if (!id.equals(entity.id)) return false;
        if (type != entity.type) return false;
        return value != null ? value.equals(entity.value) : entity.value == null;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

}
