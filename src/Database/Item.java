package Database;

import java.io.Serializable;

public abstract class Item implements RatableObject, Serializable {
    private String guid;

    public Item(String id){
        guid = id;
    }

    public String getGuid() {
        return guid;
    }
}
