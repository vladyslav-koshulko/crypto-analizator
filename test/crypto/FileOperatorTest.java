package crypto;

import crypto.api.Operator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileOperatorTest {
    private final Operator fileOperator = new FileOperator();

   @TempDir
   Path tempDir;

   @BeforeEach
    void setUp() throws IOException {
        Files.createDirectories(tempDir);
    }

    @AfterEach
    void tearDown() {
        tempDir.toFile().deleteOnExit();
    }

    @Test
    void read() throws IOException {
        Path resolve = Files.createFile(tempDir.resolve("tempFile.txt"));
        Files.writeString(resolve, "Hello World");
        assertEquals("Hello World", fileOperator.read(tempDir.resolve("tempFile.txt").toString()));
    }

    @Test
    void write() {
        String file = "tempFile.txt";
        String content = "Hello World";
        String suffix = "ENCRYPTED";
        try {
            Path written = fileOperator.write(content, tempDir.resolve(file).toString(), suffix);
            assertTrue(Files.exists(written));

            String read = Files.readString(written).replaceAll("[\\r\\n\\t]+", "");
            assertEquals(content, read);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}