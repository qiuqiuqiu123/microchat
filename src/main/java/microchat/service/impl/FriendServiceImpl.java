package microchat.service.impl;

import lombok.extern.slf4j.Slf4j;
import microchat.entity.Friend;
import microchat.entity.UserInfo;
import microchat.exception.FriendException;
import microchat.exception.UserException;
import microchat.repository.FriendRepository;
import microchat.repository.UserInfoRepository;
import microchat.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 好友管理的实现
 *
 * @author qiang
 * @since 2022/3/19
 */
@Slf4j
@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public void add(String userId, String friendId) throws UserException, FriendException {
        log.info("[FriendService] add Friend start");
        userInfoRepository.findById(userId).orElseThrow(() -> new UserException("本用户不存在"));
        userInfoRepository.findById(friendId).orElseThrow(() -> new UserException("该用户不存在"));
        if (friendRepository.findByUserIdAndFriendId(userId, friendId).isPresent()) {
            throw new FriendException("该用户已经是你的好友");
        }
        // 加好友是双向的
        Friend myFriend = new Friend(userId, friendId);
        Friend frFriend = new Friend(friendId, userId);
        friendRepository.save(myFriend);
        friendRepository.save(frFriend);
        log.info("[FriendService] add Friend end");
    }

    @Override
    public void remove(String userId, String friendId) {
        log.info("[FriendService] {} remove Friend {} start",userId,friendId);
        // 删除也是双向的
        friendRepository.deleteByUserIdAndFriendId(userId, friendId);
        friendRepository.deleteByUserIdAndFriendId(friendId, userId);
        log.info("[FriendService] remove Friend end");
    }

    @Override
    public List<UserInfo> findAllByUserId(String userId) {
        log.info("[FriendService] {} find all Friend start", userId);
        List<Friend> friends = friendRepository.findAllByUserId(userId);
        List<UserInfo> userInfos = new ArrayList<>();
        friends.forEach(friend -> {
            log.info("{}'s friend {}", userId, friend                                                        );
            userInfos.add(userInfoRepository.findById(friend.getFriendId()).get());
        });
        log.info("[FriendService] find all Friend end");
        return userInfos;
    }
}
