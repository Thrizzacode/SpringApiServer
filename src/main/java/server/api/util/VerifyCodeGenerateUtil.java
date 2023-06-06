package server.api.util;

import java.security.SecureRandom;
import java.util.Random;
/**
 * 驗證碼生成工具
 */
public class VerifyCodeGenerateUtil {

    private static final String numbers = "0123456789";
    private static final Random random = new SecureRandom();

    public static String generateVerifyCode() {
        var sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            sb.append(numbers.charAt(random.nextInt(numbers.length())));
        }
        return sb.toString();
    }
}
