package com.captainredbeard.xor;

/**
 * Base64 implementation based on MiGBase64.
 * Note: Line separation has been removed.
 *
 * @author captain-redbeard
 * @version 1.00
 * @since 28/12/16
 */
public class Base64 {
    //AZaz09+/=
    public static final char[] BASE_PAD = {
            0x0041, 0x0042, 0x0043, 0x0044, 0x0045, 0x0046, 0x0047, 0x0048, 0x0049, 0x004a, 0x004b, 0x004c, 0x004d,
            0x004e, 0x004f, 0x0050, 0x0051, 0x0052, 0x0053, 0x0054, 0x0055, 0x0056, 0x0057, 0x0058, 0x0059, 0x005a,
            0x0061, 0x0062, 0x0063, 0x0064, 0x0065, 0x0066, 0x0067, 0x0068, 0x0069, 0x006a, 0x006b, 0x006c, 0x006d,
            0x006e, 0x006f, 0x0070, 0x0071, 0x0072, 0x0073, 0x0074, 0x0075, 0x0076, 0x0077, 0x0078, 0x0079, 0x007a,
            0x0030, 0x0031, 0x0032, 0x0033, 0x0034, 0x0035, 0x0036, 0x0037, 0x0038, 0x0039, 0x002b, 0x002f, 0x003d
    };

    //AZaz09-_=
    public static final char[] SAFE_PAD = {
            0x0041, 0x0042, 0x0043, 0x0044, 0x0045, 0x0046, 0x0047, 0x0048, 0x0049, 0x004a, 0x004b, 0x004c, 0x004d,
            0x004e, 0x004f, 0x0050, 0x0051, 0x0052, 0x0053, 0x0054, 0x0055, 0x0056, 0x0057, 0x0058, 0x0059, 0x005a,
            0x0061, 0x0062, 0x0063, 0x0064, 0x0065, 0x0066, 0x0067, 0x0068, 0x0069, 0x006a, 0x006b, 0x006c, 0x006d,
            0x006e, 0x006f, 0x0070, 0x0071, 0x0072, 0x0073, 0x0074, 0x0075, 0x0076, 0x0077, 0x0078, 0x0079, 0x007a,
            0x0030, 0x0031, 0x0032, 0x0033, 0x0034, 0x0035, 0x0036, 0x0037, 0x0038, 0x0039, 0x002d, 0x005f, 0x003d
    };

    public static final int[] BASE_DECODE_PAD = {
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55,
            56, 57, 58, 59, 60, 61, -1, -1, -1, 0, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
            16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35,
            36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1
    };

    public static final int[] SAFE_DECODE_PAD = {
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55,
            56, 57, 58, 59, 60, 61, -1, -1, -1, 0, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
            16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35,
            36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1
    };

    /**
     * Encode the specified data into Base64.
     * This method returns the a byte[] with the SAFE_PAD. (-_ instead of +/)
     *
     * @param data - Data to be encoded
     * @return byte[]
     */
    public static byte[] encode(byte[] data) {
        return encodeByte(data, 1);
    }

    /**
     * Encode the specified data into Base64.
     *
     * @param data - Data to be encoded
     * @param pad - 0 BASE_PAD | 1 SAFE_PAD
     * @return byte[]
     */
    public static byte[] encodeByte(byte[] data, int pad) {
        return encodeToByte(data, pad == 0 ? BASE_PAD : SAFE_PAD);
    }

    /**
     * Decode the specified data from Base64.
     * This method returns the a byte[] with the SAFE_DECODE_PAD. (-_ instead of +/)
     *
     * @param data - Data to be decoded
     * @return byte[]
     */
    public static byte[] decode(byte[] data) {
        return decodeByte(data, 1);
    }

    /**
     * Decode the specified data from Base64.
     *
     * @param data - Data to be decoded
     * @param pad - 0 BASE_PAD | 1 SAFE_PAD
     * @return byte[]
     */
    public static byte[] decodeByte(byte[] data, int pad) {
        return decodeToByte(data, pad == 0 ? BASE_DECODE_PAD : SAFE_DECODE_PAD);
    }

    /**
     * Implementation based on MiGBase64.
     * MiGBase64 home: http://migbase64.sourceforge.net/
     *
     * @param data - Data to be encoded
     * @param pad - Pad to use
     * @return char[]
     */
    private static byte[] encodeToByte(byte[] data, char[] pad) {
        //Assign lengths
        int dataLength = data.length;
        int evenLength = (dataLength / 3) * 3;
        int characterCount = ((dataLength -1) / 3 + 1) << 2;

        //Create empty array
        byte[] baseData = new byte[characterCount];

        //Encode data
        for (int d = 0, b = 0; d < evenLength;) {
            //Copy next three bytes into lower 24 bits of int
            int bits = (data[d++] & 0xff) << 16 | (data[d++] & 0xff) << 8 | (data[d++] & 0xff);

            //Encode the int into four chars
            baseData[b++] = (byte) pad[(bits >>> 18) & 0x3f];
            baseData[b++] = (byte) pad[(bits >>> 12) & 0x3f];
            baseData[b++] = (byte) pad[(bits >>> 6) & 0x3f];
            baseData[b++] = (byte) pad[bits & 0x3f];
        }

        //Add padding
        int left = dataLength - evenLength;
        if(left > 0) {
            int bits = ((data[evenLength] & 0xff) << 10) | (left == 2 ? ((data[dataLength - 1] & 0xff) << 2) : 0);

            //Set last four characters
            baseData[characterCount - 4] = (byte) pad[bits >> 12];
            baseData[characterCount - 3] = (byte) pad[(bits >>> 6) & 0x3f];
            baseData[characterCount - 2] = left == 2 ? (byte) pad[bits & 0x3f] : (byte) pad[64];
            baseData[characterCount - 1] = (byte) pad[64];
        }

        //Return encoded data
        return baseData;
    }

    /**
     * Implementation based on MiGBase64.
     * MiGBase64 home: http://migbase64.sourceforge.net/
     *
     * @param data - Data to be encoded
     * @param pad - Pad to use
     * @return char[]
     */
    private static byte[] decodeToByte(byte[] data, int[] pad) {
        //Assign lengths
        int dataLength = data.length;
        int startIndex = 0;
        int endIndex = dataLength - 1;

        // Trim illegal chars from start
        while (startIndex < endIndex && pad[data[startIndex] & 0xff] < 0) {
            startIndex++;
        }

        // Trim illegal chars from end
        while (endIndex > 0 && pad[data[endIndex] & 0xff] < 0) {
            endIndex--;
        }

        int paddingLength = data[endIndex] == '=' ? (data[endIndex - 1] == '=' ? 2 : 1) : 0;
        int characterCount = endIndex - startIndex + 1;
        int length = (characterCount * 6 >> 3) - paddingLength;
        int evenLength = (length / 3) * 3;
        int d, b = 0;

        //Create empty array
        byte[] rawData = new byte[length];

        //Encode data
        for (d = 0; d < evenLength;) {
            //Assemble three bytes into an int from four valid characters
            int bits = pad[data[b++]] << 18 |
                    pad[data[b++]] << 12 |
                    pad[data[b++]] << 6 |
                    pad[data[b++]];

            //Add bytes
            rawData[d++] = (byte) (bits >> 16);
            rawData[d++] = (byte) (bits >> 8);
            rawData[d++] = (byte) bits;
        }
        //Decode last 1-3 bytes
        if (d < length) {
            int i = 0;
            for (int j = 0; b <= endIndex - paddingLength; j++) {
                i |= pad[data[b++]] << (18 - j * 6);
            }

            for (int r = 16; d < length; r -= 8) {
                rawData[d++] = (byte) (i >> r);
            }
        }

        //Return encoded data
        return rawData;
    }
}
