package Database;

import java.io.Serializable;
import java.util.Date;

public abstract class Item implements Serializable {
    private String guid;
    private String name;
    private double rating;
    private Date Releasedate;
    private Date AquisitionDate;


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

    private void setReleaseDate(Date date) {
        this.Releasedate = date;
    }

    public Date getReleaseDate() {
        return this.Releasedate;
    }

    private void setAquisitionDate(Date date) {
        this.AquisitionDate = date;
    }

    public Date getAquisitionDate() {
        return this.AquisitionDate;
    }

    @Override
    public int hashCode() {
        return guid.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (other instanceof Item) {
            Item otherItem = (Item) other;
            return this.guid.equals(otherItem.guid);
        }
        return false;
    }
}

