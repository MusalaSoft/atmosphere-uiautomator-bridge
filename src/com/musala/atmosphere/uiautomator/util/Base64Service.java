package com.musala.atmosphere.uiautomator.util;

import java.util.HashMap;

/**
 * Utility class responsible for encoding and decoding data to and from Base64.
 * 
 * @author georgi.gaydarov
 * 
 */
public class Base64Service {
    private static final char[] encryptionMap = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2',
            '3', '4', '5', '6', '7', '8', '9', '+', '/'};

    private static HashMap<Character, Byte> decryptionMap;
    static {
        decryptionMap = new HashMap<Character, Byte>();
        decryptionMap.put('=', (byte) 0);
        for (byte b = 0; b < 64; b++) {
            decryptionMap.put(encryptionMap[b], b);
        }
    }

    private static char[] encodeTriplet(byte[] triplet) {
        byte[] fixedIn = new byte[] {triplet[0], triplet.length > 1 ? triplet[1] : 0,
                triplet.length > 2 ? triplet[2] : 0};
        char[] result = new char[] {'=', '=', '=', '='};

        switch (triplet.length) {
            case 3:
                result[3] = encryptionMap[fixedIn[2] & 0x3f];
            case 2:
                result[2] = encryptionMap[(fixedIn[1] & 0xf) << 2 | (fixedIn[2] & 0xc0) >> 6];
            case 1:
                result[1] = encryptionMap[(fixedIn[0] & 0x3) << 4 | (fixedIn[1] & 0xf0) >> 4];
                result[0] = encryptionMap[(fixedIn[0] & 0xfc) >> 2];
                break;
        }

        return result;
    }

    private static byte[] decodeTriplet(char[] enc) {
        byte[] result = new byte[3 - (enc[2] == '=' ? 1 : 0) - (enc[3] == '=' ? 1 : 0)];

        int sequence = decryptionMap.get(enc[0]) << 18 | decryptionMap.get(enc[1]) << 12 | decryptionMap.get(enc[2]) << 6
                | decryptionMap.get(enc[3]);

        switch (result.length) {
            case 3:
                result[2] = (byte) (sequence & 0xff);
            case 2:
                result[1] = (byte) ((sequence & 0xff00) >> 8);
            case 1:
                result[0] = (byte) ((sequence & 0xff0000) >> 16);
        }

        return result;
    }

    /**
     * Encodes data into a Base64 string.
     * 
     * @param data
     *        - the data to be encoded.
     * @return the encoded data as a string.
     */
    public static String encode(byte[] data) {
        int resultSize = (int) Math.ceil(data.length / 3.0) * 4;

        char[] encoded = new char[resultSize];
        int outPointer = 0;

        for (int i = 0; i < data.length; i += 3) {
            int toCopy = Math.min(3, data.length - i);
            byte[] in = new byte[toCopy];
            for (int j = 0; j < toCopy; j++) {
                in[j] = data[i + j];
            }

            char[] result = encodeTriplet(in);
            for (int j = 0; j < 4; j++) {
                encoded[outPointer++] = result[j];
            }
        }

        return new String(encoded);
    }

    /**
     * Decodes Base64 encrypted data.
     * 
     * @param data
     *        - the data to be decoded.
     * @return the decoded byte array.
     */
    public static byte[] decode(String data) {
        int resultSize = (int) Math.ceil(data.length() / 4.0) * 3;
        byte[] decoded = new byte[resultSize];

        int outPointer = 0;
        for (int i = 0; i < data.length(); i += 4) {
            char[] in = new char[4];
            for (int j = 0; j < 4; j++) {
                in[j] = data.charAt(i + j);
            }

            byte[] result = decodeTriplet(in);
            for (int j = 0; j < result.length; j++) {
                decoded[outPointer++] = result[j];
            }
        }

        return decoded;
    }
}
