package crypto.api;

import java.nio.file.Path;

public interface Operator {
    String read(String resource);

    Path write(String text, String resource, String... suffixes);
}
