package crypto;

import crypto.api.Crypto;
import crypto.exceotions.CryptoInvalidKeyException;

import static crypto.utils.Constants.*;


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
