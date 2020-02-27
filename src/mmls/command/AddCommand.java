package mmls.command;

import Database.Item;
import mmls.library.Library;
import mmls.library.LibraryItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AddCommand implements Command {
    protected Library library;
    protected Item newItem;
    protected Date acquisitionDate;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

    public AddCommand(Library library, Item newItem) {
        this.library = library;
        this.newItem = newItem;
        this.acquisitionDate = new Date();
    }

    public AddCommand(Library library, Item newItem, String acquisitionDate) throws ParseException {
        this.library = library;
        this.newItem = newItem;
        this.acquisitionDate = dateFormat.parse(acquisitionDate);
    }

    @Override
    public abstract void executeCommand();
}
