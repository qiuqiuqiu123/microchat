package microchat.core.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 消息体
 *
 * @author qiang
 * @since 2022/3/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    public static final int HEART_BEAT = 0;

    public static final int PRIVATE = 1;

    public static final int GROUP = 2;

    public static final int BIND = 3;

    private int type; // 0是心跳，1是单聊，2是群聊，3认证消息，用于登录绑定channel

    private String senderId;

    private String senderName;

    private String receiverId;

    private String receiverName;

    private String content;

    private long time;

    private String extern;
}
