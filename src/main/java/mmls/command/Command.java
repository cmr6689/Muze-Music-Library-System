package mmls.command;

/**
 * This interface is used by all Commands and helps follow the Command pattern
 * @author Shane Burke
 */
public interface Command {
    /**
     * This method allows a single command to be executed no matter which command it is
     */
    public void executeCommand();
}
