package microchat.service;

import microchat.entity.UserInfo;

import java.util.List;

/**
 * 好友管理接口
 *
 * @author qiang
 * @since 2022/3/19
 */
public interface FriendService {
    void addFriend(String userId, String friendId);

    void removeFriend(String userId, String friendId);

    List<UserInfo> findAllFriend(String userId);

}
