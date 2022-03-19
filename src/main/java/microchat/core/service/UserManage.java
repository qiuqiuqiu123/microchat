package microchat.core.service;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import microchat.core.utils.NettyUtil;
import microchat.entity.User;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用户管理，主要是指用户的channel
 *
 * @author qiang
 * @since 2021/12/8
 */
@Slf4j
@Service
public class UserManage {

    private static final AtomicInteger userOnlineNum = new AtomicInteger(0);

    // 用户ID与在线用户绑定
    private ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();

    // 一个通道和一个用户绑定
    private ConcurrentHashMap<Channel, String> channels = new ConcurrentHashMap<>();

    public User getUser(String userId) {
        // 不存在就返回空指针
        return users.getOrDefault(userId, null);
    }

    /**
     * 添加一个用户，同时绑定到频道上
     *
     * @param channel
     * @param userId
     * @param userName
     */
    public void addChannel(Channel channel, String userId, String userName) {
        log.info("[UserManage] add channel start");
        String address = NettyUtil.parseChannelRemoteAddr(channel);
        if (!channel.isActive()) {
            log.error("[UserManage] channel is active,address is : {}", address);
            return;
        }
        userOnlineNum.incrementAndGet();
        User user = new User(true, System.currentTimeMillis(), userId, userName, address, channel);
        users.put(userId, user);
        channels.put(channel, userId);
        log.info("[UserManage] add channel end");
    }

    /**
     * 移除一个用户，同时移除频道
     *
     * @param userId
     */
    public void removeChannel(String userId) {
        log.info("[UserManage] remove channel start");

        User user = getUser(userId);

        if (user == null || !user.getChannel().isActive()) {
            log.error("[UserManage] channel is active,address is : {}", user.getIpAddr());
            return;
        }
        channels.remove(user.getChannel());
        users.remove(userId);
        log.info("[UserManage] remove channel end");
    }

    public void removeChannel(Channel channel) {
        String userId = getUserIdByChannel(channel);
        removeChannel(userId);
    }

    /**
     * 通过频道获取用户id
     *
     * @param channel
     * @return
     */
    public String getUserIdByChannel(Channel channel) {
        return channels.get(channel);
    }
}
