package crypto.utils;


public class Constants {
    public static final String DECRYPTED_FILE_LOCATION_MASSAGE = "Decrypted file located is: %s";

    public static final String EXCEPTION_FILE_NOT_READABLE_MESSAGE = "File is not readable";
    public static final String EXCEPTION_FILE_NOT_WRITABLE_MESSAGE = "File is not readable";
    public static final String EXCEPTION_PATH_NOT_EXISTS_MESSAGE = "The given path does not exist";

    public static String CHAR_RANGE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!.?";

    public enum CryptoFileSuffixState {
        DECRYPTED,
        ENCRYPTED,
        BRUTE_FORCE

    }



}
