package crypto;

import crypto.analyzer.AnalyzableImpl;
import crypto.analyzer.api.Analyzable;
import crypto.api.Crypto;
import crypto.api.Operator;

import java.nio.file.Path;

import static crypto.utils.Constants.DECRYPTED_FILE_LOCATION_MASSAGE;
import static crypto.utils.Constants.CryptoFileSuffixState.*;

public class ArgsAnalyzer {
    private final String[] args;
    private final Crypto crypto;
    private final Operator operator;
    private final Analyzable analyzable;

    public ArgsAnalyzer(String[] args, Crypto crypto, Operator operator, Analyzable analyzable) {
        this.args = args;
        this.crypto = crypto;
        this.operator = operator;
        this.analyzable = analyzable;
    }

    public static ArgsAnalyzer getDefault(String[] args) {
        return new ArgsAnalyzer(args, new CesarCrypto(), new FileOperator(), new AnalyzableImpl());
    }

    private void crypt(String key, String filename, boolean isDecrypt) {
        String suffix = isDecrypt ? DECRYPTED.name() : ENCRYPTED.name();
        String plainText = operator.read(filename);
        String processed = (isDecrypt) ? crypto.decrypt(key, plainText) : crypto.encrypt(key, plainText);
        Path written = operator.write(processed, filename, suffix);
        System.out.printf((DECRYPTED_FILE_LOCATION_MASSAGE) + "%n", written);
    }

    public void analyze() {
        String operation = args[0];
        Operation operationEnum = Operation.valueOf(operation);
        String filename = args[1];
        String key = args[2];
        switch (operationEnum) {
            case ENCRYPT -> crypt(key, filename, false);
            case DECRYPT -> crypt(key, filename, true);
            case ANALYZE -> analyzable.analyze(filename);
        }
    }
}
