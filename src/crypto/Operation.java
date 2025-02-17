package crypto;

public enum Operation {
    ENCRYPT("ENCRYPT"),
    DECRYPT("DECRYPT"),
    ANALYZE("ANALYZE"),
    ;

    private final String operation;

    Operation(String operation) {
        this.operation = operation;
    }

    public Operation getOperation() {
        return this;
    }
}
