package crypto;

import crypto.api.Crypto;
import crypto.exceotions.CryptoInvalidKeyException;


public class CesarCrypto implements Crypto {

    private static final int RANGE = 93;
    private static final char FROM_POSITION = '!';
    private static final String MISS_CHARTERS = " ";

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
            if (!MISS_CHARTERS.contains(String.valueOf(symbol))) {
                int originAlphabetPosition = symbol - FROM_POSITION;
                int newAlphabetPosition = (originAlphabetPosition + offset) % RANGE;
                if (toDecrypt) {
                    newAlphabetPosition = (originAlphabetPosition - offset) % RANGE;
                }
                encryptedResult.append((char) (FROM_POSITION + newAlphabetPosition));

            } else {
                encryptedResult.append(symbol);
            }
        }
        return encryptedResult.toString();
    }
}
