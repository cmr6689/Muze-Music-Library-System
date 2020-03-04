package Database;

import java.io.Serializable;
import java.util.Date;

/**
 * This abstract class provides the getters and setters of all possible attributes for an item in
 * database/library (Song, Artist, Release)
 * @author Shane Burke, Ryan Borger, Jarred Moyer
 */
public abstract class Item implements Serializable {
    private String guid;
    private String name;
    private double rating;
    private Date Releasedate;
    private Date AquisitionDate;

    /**
     * Create an item
     * @param id the GUID
     */
    public Item(String id){
        guid = id;
    }

    /**
     * Get the GUID of an item
     * @return GUID
     */
    public String getGuid() {
        return guid;
    }

    /**
     * Get the name of an item
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of an item
     * @param name : new name of the item
     */
    protected void setName(String name) {
        this.name = name;
    }

    /**
     * Get the rating of the item
     * @return rating double
     */
    public double getRating() {
        return this.rating;
    }

    /**
     * Set the rating of the item
     * @param rating : new rating of an item
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Set the date when the release was published
     * @param date : Java Date class object
     */
    private void setReleaseDate(Date date) {
        this.Releasedate = date;
    }

    /**
     * Get the release date of a release
     * @return release date
     */
    public Date getReleaseDate() {
        return this.Releasedate;
    }

    /**
     * Set the acquisition date of an item
     * @param date : date item was acquired
     */
    private void setAquisitionDate(Date date) {
        this.AquisitionDate = date;
    }

    /**
     * Get the acquisition date of the item
     * @return date item was acquired
     */
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

