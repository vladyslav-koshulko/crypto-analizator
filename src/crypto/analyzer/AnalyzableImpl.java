package crypto.analyzer;

import crypto.CesarCrypto;
import crypto.analyzer.api.Analyzable;
import crypto.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class AnalyzableImpl implements Analyzable {

    private CesarCrypto crypto = new CesarCrypto();


    @Override
    public List<String> analyze(String analyzed) {
        return bruteForce(analyzed);
    }

    private List<String> bruteForce(String input) {
        List<String> result = new ArrayList<>();
        for (int shift = 0; shift < Constants.CHAR_RANGE.length(); shift++) {
            String decrypted = crypto.toCrypt(input, shift, true);
            result.add(decrypted);
        }

        return result;
    }
}
