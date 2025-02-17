package crypto;


import crypto.api.Analyzable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


class CesarCryptoTest {
    private final CesarCrypto crypto = new CesarCrypto();

    private final Analyzable analyzable = new AnalyzableImpl();

    @org.junit.jupiter.api.Test
    void encrypt() {
        int offset = 5;
        String plaintext = "Hello World!";
        String expected = "Mjqqt btwqiC";
        String encryptedResult = crypto.encrypt(offset, plaintext);
        Assertions.assertEquals(expected, encryptedResult);
    }

    @org.junit.jupiter.api.Test
    void decrypt() {
        int offset = 5;
        String plaintext = "Mjqqt btwqiC";
        String expected = "Hello World!";
        String decryptResult = crypto.decrypt(offset, plaintext);
        Assertions.assertEquals(expected, decryptResult);
    }


    @Test
    void brutForceTest() {
        int offset = 5;
        String plaintext = "Mjqqt btwqiC";
        String expected = "Hello World!";
        List<String> analyze = analyzable.analyze(plaintext);
        Assertions.assertTrue(analyze.contains(expected));
        Assertions.assertEquals(expected, crypto.decrypt(offset, plaintext));
    }
}