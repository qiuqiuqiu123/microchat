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
    public static final int HEART_BEAT = 1;

    public static final int PRIVATE = 2;

    public static final int GROUP = 3;

    public static final int BIND = 4;

    private int type; // 1是心跳，2是单聊，3是群聊，4认证消息，用于登录绑定channel

    private String senderId;

    private String senderName;

    private String receiverId;

    private String receiverName;

    private String context;

    private long time;

    private String extern;
}
