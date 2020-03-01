package mmls.command;

import Database.Release;
import Database.Song;
import mmls.library.Library;

import java.text.ParseException;

public class AddReleaseCommand extends AddCommand {
    public AddReleaseCommand(Library library, String guid) {
        super(library, guid);
    }
//    public AddReleaseCommand(Library library, Release newItem) {
//        super(library, newItem);
//    }
//    public AddReleaseCommand(Library library, Song newItem, String acquisitionDate) throws ParseException {
//        super(library, newItem, acquisitionDate);
//    }


    @Override
    public void executeCommand() {
//        library.addRelease((Release)newItem, this.acquisitionDate);
    }
}
