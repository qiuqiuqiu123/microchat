package microchat.service.impl;

import microchat.entity.UserInfo;
import microchat.repository.FriendRepository;
import microchat.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 好友管理的实现
 *
 * @author qiang
 * @since 2022/3/19
 */
public class FriendServiceImpl implements FriendService {
    @Autowired
    FriendRepository friendRepository;


    @Override
    public void addFriend(String userId, String friendId) {

    }

    @Override
    public void removeFriend(String userId, String friendId) {

    }

    @Override
    public List<UserInfo> findAllFriend(String userId) {
        return null;
    }
}
