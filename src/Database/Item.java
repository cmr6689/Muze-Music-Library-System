package Database;

public abstract class Item implements RatableObject {
    private String guid;

    public String getGuid() {
        return guid;
    }
}
