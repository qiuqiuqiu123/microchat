package microchat.entity;

import io.netty.channel.Channel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 在连接中的用户，并不对应实体表
 *
 * @author qiang
 * @since 2021/12/8
 */
@Data
@NoArgsConstructor
public class User {
    private boolean isAuth = false; // 是否认证

    private long time = 0;

    private String id; // UID

    private String name; // 昵称

    private String ipAddr; // IP地址

    private Channel channel; // 通道

    public User(boolean isAuth, long time, String id, String name, String ipAddr, Channel channel) {
        this.isAuth = isAuth;
        this.time = time;
        this.id = id;
        this.name = name;
        this.ipAddr = ipAddr;
        this.channel = channel;
    }
}
