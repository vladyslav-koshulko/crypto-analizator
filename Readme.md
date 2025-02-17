## Cesar Crypto Analyzing

### Operations:

    Encrypt - encrypt file content with provided shuffle digit key.
    Decrypt - decrypt file content with provided shuffle digit key.
    Analyze - analyze to decipher file encrypted content.

### Commands
    
    java -jar <PATH_TO_JAR>/cesar-cypher.jar <OPERATION [ENCRYPT|DECRYPT|ANALYZE]> <FILEPATH> <DIGIT_SHUFFLE>

#### Examples

    command: java -jar <PATH_TO_JAR>/crypto-analizator-1.0.1.jar ENCRYPT ./test.txt 5
    output: File located is: ./_ENCRYPTED_test.txt

    command: java -jar <PATH_TO_JAR>/crypto-analizator-1.0.1.jar DECRYPT ./test.txt 5
    output: File located is: ./_DECRYPTED_test.txt
    
    command: java -jar <PATH_TO_JAR>/crypto-analizator-1.0.1.jar ANALYZE ./test.txt 5
    output: File located is: ./_DECRYPTED_test.txt
    

    