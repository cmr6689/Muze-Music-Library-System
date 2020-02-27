package mmls.command;

public interface Factory {
    Command createRequest(String request);
}
