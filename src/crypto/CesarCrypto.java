package crypto;

import crypto.api.Crypto;
import crypto.exceotions.CryptoInvalidKeyException;


public class CesarCrypto implements Crypto {


    @Override
    public String encrypt(String offset, String plainText) {
        try {
            int digitOffset = Integer.parseInt(offset);
            return toCrypt(plainText, digitOffset, false);
        } catch (NumberFormatException e) {
            throw new CryptoInvalidKeyException(e);
        }
    }

    @Override
    public String decrypt(String offset, String plainText) {
        try {
            int digitOffset = Integer.parseInt(offset);
            return toCrypt(plainText, digitOffset, true);
        } catch (NumberFormatException e) {
            throw new CryptoInvalidKeyException(e);
        }
    }

    private String toCrypt(String message, int offset, boolean toDecrypt) {
        StringBuilder encryptedResult = new StringBuilder();
        for (char symbol : message.toCharArray()) {
            if (symbol != ' ') {
                int originAlphabetPosition = symbol - '!';
                int newAlphabetPosition = (originAlphabetPosition + offset) % 93;
                if (toDecrypt) {
                    newAlphabetPosition = (originAlphabetPosition - offset) % 93;
                }
                encryptedResult.append((char) ('!' + newAlphabetPosition));

            } else {
                encryptedResult.append(symbol);
            }
        }
        return encryptedResult.toString();
    }
}
