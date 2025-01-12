package crypto.api;

public interface Crypto {
    String encrypt(String key, String plainText);
    String decrypt(String key, String plainText);
}
