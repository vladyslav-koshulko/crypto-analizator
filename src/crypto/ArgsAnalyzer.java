package crypto;

import crypto.analyzer.api.Analyzable;
import crypto.api.Crypto;
import crypto.api.Operator;

import java.nio.file.Path;

public class ArgsAnalyzer {
    private String[] args;
    private Crypto crypto;
    private Operator operator;
    private Analyzable analyzable;

    public ArgsAnalyzer(String[] args, Crypto crypto, Operator operator, Analyzable analyzable) {
        this.args = args;
        this.crypto = crypto;
        this.operator = operator;
        this.analyzable = analyzable;
    }

    public static ArgsAnalyzer getDefault(String[] args) {
        return new ArgsAnalyzer(args, new CesarCrypto(), new FileOperator(), analyzed -> "");
    }

    private void crypt(String key, String filename, boolean isDecrypt) {
        String suffix = isDecrypt ? "DECRYPTED" : "ENCRYPTED";
        String plainText = operator.read(filename);
        String processed = (isDecrypt) ? crypto.decrypt(key, plainText) : crypto.encrypt(key, plainText);
        Path written = operator.write(processed, filename, suffix);
        System.out.println("Decrypted file located is: " + written);
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
