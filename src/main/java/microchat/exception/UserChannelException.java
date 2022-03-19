package microchat.exception;

/**
 * 用户连接异常
 *
 * @author qiang
 * @since 2022/3/20
 */
public class UserChannelException extends Exception {
    public UserChannelException(String message) {
        super(message);
    }
}
