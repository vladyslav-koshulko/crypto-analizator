package crypto.utils;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final String DECRYPTED_FILE_LOCATION_MASSAGE = "Decrypted file located is: %s";

    public static final String EXCEPTION_FILE_NOT_READABLE_MESSAGE = "File is not readable";
    public static final String EXCEPTION_FILE_NOT_WRITABLE_MESSAGE = "File is not readable";
    public static final String EXCEPTION_PATH_NOT_EXISTS_MESSAGE = "The given path does not exist";

    public static final int RANGE = 92;
    public static final char FROM_POSITION = '!';
    public static final String MISS_CHARTERS = " !";

    public enum CryptoFileSuffixState {
        DECRYPTED,
        ENCRYPTED,
        BRUTE_FORCE
    }



}
