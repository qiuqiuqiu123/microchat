package microchat.core.chatServer;

/**
 * 聊天服务器接口，聊天服务器需实现这个接口
 *
 * @author qiang
 * @since 2021/12/2
 */
public interface ChatServer {
    void start();

    void shutdown();
}
