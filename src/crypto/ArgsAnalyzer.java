package crypto;

import crypto.api.Analyzable;
import crypto.api.Crypto;
import crypto.api.Operator;

import java.nio.file.Path;

import static crypto.CryptoFileSuffixState.DECRYPTED;
import static crypto.CryptoFileSuffixState.ENCRYPTED;

public class ArgsAnalyzer {

    public static final String DECRYPTED_FILE_LOCATION_MASSAGE = "Decrypted file located is: %s";

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
        return new ArgsAnalyzer(
                args,
                new CesarCrypto(),
                new FileOperator(),
                new AnalyzableImpl());
    }

    private void crypt(int key, String filename, boolean isDecrypt) {
        String suffix = isDecrypt ? DECRYPTED.name() : ENCRYPTED.name();
        String plainText = operator.read(filename);
        String processed = (isDecrypt) ? crypto.decrypt(key, plainText) : crypto.encrypt(key, plainText);
        Path written = operator.write(processed, filename, suffix);
        System.out.printf((DECRYPTED_FILE_LOCATION_MASSAGE) + "%n", written);
    }

    public void analyze() {
        checkArguments();
        Operation operationEnum = checkAndGetOperation();
        String filename = checkAndGetFilename();
        if (!operationEnum.getOperation().equals(Operation.ANALYZE)) {
            int key = checkAndGetKey();
            switch (operationEnum) {
                case ENCRYPT -> crypt(key, filename, false);
                case DECRYPT -> crypt(key, filename, true);
            }
        } else {
            analyzable.analyze(filename);
        }
    }

    private void checkArguments() {
        if (args.length < 2) {
            throw new IllegalArgumentException("Missing arguments");
        }
        if (args[0] == null || args[1] == null) {
            throw new IllegalArgumentException("Missing arguments");
        }
    }

    private Operation checkAndGetOperation() {
        String stringOperation = args[0];
        if (stringOperation != null && !stringOperation.isEmpty()) {
            try {
                return Operation.valueOf(stringOperation);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid operation: " + stringOperation);
            }
        }
        throw new IllegalArgumentException("Missing argument: wrong operation '" + stringOperation + "'");
    }

    private String checkAndGetFilename() {
        String stringFilename = args[1];
        if (stringFilename != null && !stringFilename.isEmpty()) {
            return stringFilename;
        }
        throw new IllegalArgumentException("Missing argument: specify filename");
    }

    private Integer checkAndGetKey() {
        String stringKey = args[2];
        if (stringKey != null && !stringKey.isEmpty()) {
            try {
                return Integer.valueOf(stringKey);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid key: '" + stringKey + "'. It should be an integer");
            }
        }
        throw new IllegalArgumentException("Missing argument: specify key");
    }
}
