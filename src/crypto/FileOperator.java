package crypto;

import crypto.api.Operator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.stream.Stream;

import static java.nio.file.Files.isReadable;
import static java.nio.file.Files.isWritable;

public class FileOperator implements Operator {


    @Override
    public String read(String resource) {
        return readFile(resource);
    }

    @Override
    public Path write(String text, String resource, String... suffixes) {
        return writeFile(text, resource, suffixes);
    }

    private String readFile(String resource) {
        Path path = checkForReading(resource);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(path.toFile()))) {
            while (fileReader.ready()) {
                sb.append(fileReader.readLine()).append("\r\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    private Path writeFile(String text, String resource, String... suffixes) {
        String resourceWithSuffix = addSuffixes(resource, suffixes);
        Path path = checkForWriting(resourceWithSuffix);
        try {
            return Files.write(path, Collections.singleton(text));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String addSuffixes(String path, String... suffixes) {
        return path.replace(
                Path.of(path).toFile().getName(),
                Stream.of(suffixes).reduce("", (a, b) -> a + "_" + b)) + "_" + Path.of(path).toFile().getName();
    }

    private Path checkForReading(String resource) {
        return checkReadable(
                checkExistingFile(
                        getPath(resource)));
    }

    private Path checkForWriting(String resource) {
        return checkWritable(
                createIfNotExist(
                        getPath(resource)));
    }

    private Path checkReadable(Path path) {
        if (isReadable(path)) {
            return path;
        }
        throw new IllegalArgumentException(ExceptionMessage.EXCEPTION_FILE_NOT_READABLE_MESSAGE);
    }

    private Path checkWritable(Path path) {
        if (isWritable(path)) {
            return path;
        }
        throw new IllegalArgumentException(ExceptionMessage.EXCEPTION_FILE_NOT_WRITABLE_MESSAGE);
    }

    private Path checkExistingFile(Path path) {
        if (!Files.notExists(path)) {
            return path;
        }
        throw new IllegalArgumentException(ExceptionMessage.EXCEPTION_PATH_NOT_EXISTS_MESSAGE);
    }

    private Path createIfNotExist(Path path) {
        try {
            return Files.exists(path) ? path : Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Path getPath(String resource) {
        try {
            return Path.of(resource);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static final class ExceptionMessage {
        private static final String EXCEPTION_FILE_NOT_READABLE_MESSAGE = "File is not readable";
        private static final String EXCEPTION_FILE_NOT_WRITABLE_MESSAGE = "File is not readable";
        private static final String EXCEPTION_PATH_NOT_EXISTS_MESSAGE = "The given path does not exist";
    }
}
