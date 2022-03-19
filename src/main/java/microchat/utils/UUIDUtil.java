package microchat.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * UUID生成器
 *
 * @author qiang
 * @since 2022/3/20
 */
@Component
public class UUIDUtil {
    public static String getUUID() {
        // 随机生成一位整数
        int random = (int) (Math.random() * 9 + 1);
        String str = String.valueOf(random);
        // 生成uuid的hashCode值
        int hashCode = UUID.randomUUID().toString().hashCode();
        // 可能为负数
        if (hashCode < 0) {
            hashCode = -hashCode;
        }
        return str + String.format("%015d", hashCode);
    }
}
