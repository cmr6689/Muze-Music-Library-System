package mmls.command;

import Database.Database;
import mmls.library.Library;

public interface Factory {
    Command createCommand(String request);
}
