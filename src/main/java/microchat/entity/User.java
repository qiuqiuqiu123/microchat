package microchat.entity;

import io.netty.channel.Channel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 在连接中的用户，并不对应实体表
 *
 * @author qiang
 * @since 2021/12/8
 */
@Setter
@Getter
@NoArgsConstructor
public class User {
    private boolean isAuth = false; // 是否认证

    private long time = 0;

    private String userId; // UID

    private String userName; // 昵称

    private String ipAddr; // IP地址

    private Channel channel; // 通道

    public User(boolean isAuth, long time, String userId, String userName, String ipAddr, Channel channel) {
        this.isAuth = isAuth;
        this.time = time;
        this.userId = userId;
        this.userName = userName;
        this.ipAddr = ipAddr;
        this.channel = channel;
    }
}
