package crypto;

import crypto.api.Analyzable;

import java.util.ArrayList;
import java.util.List;

import static crypto.CesarCrypto.CHAR_RANGE;

public class AnalyzableImpl implements Analyzable {

    private final CesarCrypto crypto = new CesarCrypto();


    @Override
    public List<String> analyze(String analyzed) {
        return bruteForce(analyzed);
    }

    private List<String> bruteForce(String input) {
        List<String> result = new ArrayList<>();
        for (int shift = 0; shift < CHAR_RANGE.length(); shift++) {
            String decrypted = crypto.toCrypt(input, shift, true);
            result.add(decrypted);
        }

        return result;
    }
}
