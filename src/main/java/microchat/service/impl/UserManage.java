package microchat.service.impl;

import io.netty.channel.Channel;
import microchat.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用户管理，主要是指用户的channel
 *
 * @author qiang
 * @since 2021/12/8
 */
public class UserManage {

    private ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();

    private ConcurrentHashMap<Channel,String> channels = new ConcurrentHashMap<>();

    private AtomicInteger userOnline = new AtomicInteger(0);

    @Autowired


}
