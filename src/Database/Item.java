package Database;

public abstract class Item implements RatableObject {
    private String guid;

    public Item(String id){
        guid = id;
    }

    public String getGuid() {
        return guid;
    }
}
