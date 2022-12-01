package microchat.service;

import microchat.entity.UserInfo;
import microchat.exception.FriendException;
import microchat.exception.UserException;

import java.util.List;

/**
 * 好友管理接口
 *
 * @author qiang
 * @since 2022/3/19
 */
public interface FriendService {
    void add(String userId, String friendId) throws UserException, FriendException;

    void remove(String userId, String friendId);

    List<UserInfo> findAllByUserId(String userId);

}
