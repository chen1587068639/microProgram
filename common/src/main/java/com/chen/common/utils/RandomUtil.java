package com.chen.common.utils;

import java.util.Random;
import java.util.UUID;

public class RandomUtil {

    private static final char[] FIX_STR = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
            'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final String STR = new String(FIX_STR);

    public static String strToSplitHex16() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "") + new Random().nextInt(10);
        StringBuilder shortBuffer = new StringBuilder();
        //我们这里想要保证券码为11位，所以32位uuid加了一位随机数，再分成11等份；（如果是8位券码，则32位uuid分成8等份进行计算即可）
        for (int i = 0; i < 11; i++) {
            String str = uuid.substring(i * 3, i * 3 + 3);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(FIX_STR[x % FIX_STR.length]);
        }
        return shortBuffer.toString();
    }

}
