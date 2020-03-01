package Database;

import java.io.Serializable;
import java.util.Date;

public abstract class Item implements Serializable {
    private String guid;
    private String name;
    private double rating;
    private Date date;


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

    public double getRating() {
        return this.rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    private void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return this.date;
    }
}

