package crypto;

import crypto.api.Crypto;


public class CesarCrypto implements Crypto {

    static String CHAR_RANGE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!.?";

    @Override
    public String encrypt(int offset, String plainText) {
        return toCrypt(plainText, offset, false);
    }

    @Override
    public String decrypt(int offset, String plainText) {
        return toCrypt(plainText, offset, true);
    }

    public String toCrypt(String message, int offset, boolean toDecrypt) {
        StringBuilder encryptedResult = new StringBuilder();
        for (char symbol : message.toCharArray()) {
            if (CHAR_RANGE.contains(String.valueOf(symbol))) {
                int newAlphabetPosition = (CHAR_RANGE.indexOf(symbol) + offset) % CHAR_RANGE.length();
                if (toDecrypt) {
                    newAlphabetPosition = (CHAR_RANGE.indexOf(symbol) - offset) % CHAR_RANGE.length();
                    if (newAlphabetPosition < 0) newAlphabetPosition += CHAR_RANGE.length();
                }
                encryptedResult.append(CHAR_RANGE.charAt(newAlphabetPosition));
            } else {
                encryptedResult.append(symbol);
            }
        }
        return encryptedResult.toString();
    }


}
