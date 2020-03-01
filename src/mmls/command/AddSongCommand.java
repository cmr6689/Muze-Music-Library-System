package mmls.command;

import Database.Song;
import mmls.library.Library;

import java.text.ParseException;

public class AddSongCommand extends AddCommand {
    public AddSongCommand(Library library, Song newItem) {
        super(library, newItem);
    }

    public AddSongCommand(Library library, Song newItem, String acquisitionDate) throws ParseException {
        super(library, newItem, acquisitionDate);
    }

    @Override
    public void executeCommand() {
        library.addSong((Song)newItem, this.acquisitionDate);
    }
}
