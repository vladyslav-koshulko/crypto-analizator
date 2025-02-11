package crypto;


import crypto.analyzer.AnalyzableImpl;
import crypto.analyzer.api.Analyzable;
import crypto.exceotions.CryptoInvalidKeyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CesarCryptoTest {
    private final CesarCrypto crypto = new CesarCrypto();

    private final Analyzable analyzable = new AnalyzableImpl();

    @org.junit.jupiter.api.Test
    void encrypt() {
        String offset = "5";
        String plaintext = "Hello World!";
        String expected = "Mjqqt \\twqi&";
        String encryptedResult = crypto.encrypt(offset, plaintext);
        Assertions.assertEquals(expected, encryptedResult);
    }

    @org.junit.jupiter.api.Test
    void decrypt() {
        String offset = "5";
        String plaintext = "Mjqqt \\twqi&";
        String expected = "Hello World!";
        String decryptResult = crypto.decrypt(offset, plaintext);
        Assertions.assertEquals(expected, decryptResult);
    }

    @Test
    void passWrongOffset() {
        String offset = "5asdf";
        String plaintextEncrypt = "Hello World!";
        String plaintextDecrypt = "Mjqqt \\twqi&";
        Assertions.assertThrows(CryptoInvalidKeyException.class, () -> crypto.encrypt(offset, plaintextEncrypt));
        Assertions.assertThrows(CryptoInvalidKeyException.class, () -> crypto.decrypt(offset, plaintextDecrypt));
    }


    @Test
    void brutForceTest() {
        String offset = "5";
        String plaintext = "Mjqqt \\twqi&";
        String expected = "Hello World!";
        String analyze = analyzable.analyze(plaintext);
        Assertions.assertEquals(expected, analyze);

    }
}