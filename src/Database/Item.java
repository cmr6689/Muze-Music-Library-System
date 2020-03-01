package Database;

public abstract class Item implements RatableObject {
    private String guid;
    private String name;

    public Item(String id){
        guid = id;
    }

    public String getGuid() {
        return guid;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }
}
