package crypto.analyzer;

import crypto.CesarCrypto;
import crypto.analyzer.api.Analyzable;
import crypto.utils.Constants;

import java.util.HashMap;
import java.util.Map;

public class AnalyzableImpl implements Analyzable {

    private CesarCrypto crypto = new CesarCrypto();


    @Override
    public String analyze(String analyzed) {
        return bruteForce(analyzed);
    }

    private String bruteForce(String input) {

        Double minDifference = Double.MAX_VALUE;
        int targetShift = 0;
        for (int shift = 0; shift < Constants.RANGE; shift++) {
            String result = crypto.toCrypt(input, shift, true);

            Map<Character, Double> expectedFrequencyMap = Utils.getFrequencyMap();
            Map<Character, Double> currentFrequencyMap = Utils.analyzeFrequency(result);

            Double difference = Utils.getMapDifference(expectedFrequencyMap, currentFrequencyMap);
            if (difference < minDifference && difference > 0) {
                minDifference = difference;
                targetShift = shift;
            }
        }

        return crypto.toCrypt(input, targetShift, true);
    }


    private static final class Utils {
        private static final Map<Character, Double> frequencyMap = new HashMap<>() {{
            put('e', 13.89);
            put('l', 8.33);
            put('s', 6.94);
            put('a', 6.94);
            put('i', 5.56);
            put('o', 5.56);
            put('n', 5.56);
            put('t', 4.17);
            put('c', 4.17);
            put('u', 4.17);
            put('r', 4.17);
            put('b', 4.17);
            put('y', 2.78);
            put('m', 2.78);
            put('f', 2.78);
            put('g', 2.78);
            put('h', 1.39);
            put('d', 1.39);
            put('q', 1.39);
            put('v', 1.39);
        }};

        private static Map<Character, Double> getFrequencyMap() {
            return frequencyMap;
        }

        private static Map<Character, Double> analyzeFrequency(String text) {
            Map<Character, Double> frequencyMap = new HashMap<>();

            for (char c : text.toLowerCase().toCharArray()) {
                if (Character.isLetter(c)) {
                    frequencyMap.put(c, frequencyMap.getOrDefault(c, 0.0) + 1);
                }
            }

            for (Map.Entry<Character, Double> characterDoubleEntry : frequencyMap.entrySet()) {
                characterDoubleEntry.setValue(frequencyMap.get(characterDoubleEntry.getKey()) / text.length());
            }
            return frequencyMap;
        }

        private static Double getMapDifference(Map<Character, Double> expected, Map<Character, Double> actual) {
            double totalDifference = 0.0;

            for (Map.Entry<Character, Double> actualFrequencyMap : actual.entrySet()) {
                Character key = actualFrequencyMap.getKey();
                Double actualValue = actualFrequencyMap.getValue();

                Double expectedValue = expected.get(key.toString().toLowerCase().charAt(0));
                if (expectedValue != null) {
                    totalDifference += expectedValue - actualValue;
                }
            }
            return totalDifference;
        }

    }
}
