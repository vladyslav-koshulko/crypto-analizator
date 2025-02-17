package crypto.api;

public interface Crypto {
    String encrypt(int key, String plainText);
    String decrypt(int key, String plainText);
}
