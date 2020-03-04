package mmls.command;

import Database.Database;
import mmls.library.Library;

/**
 *Interface used for the CommandFactory class
 * @author Shanke Burke, Cameron Riu
 */
public interface Factory {
    /**
     * Determines which command to create
     * @param request : inputted by the user
     * @return concrete command to be called
     */
    Command createCommand(String request);
}
