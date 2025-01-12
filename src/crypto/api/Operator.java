package crypto.api;

public interface Operator {
    String read(String resource);
    void write(String resource, String text);
}
