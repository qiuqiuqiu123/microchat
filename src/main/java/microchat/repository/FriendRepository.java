package microchat.repository;

import microchat.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 好友关系查询
 *
 * @author qiang
 * @since 2022/3/19
 */
public interface FriendRepository extends JpaRepository<Friend, String> {
    void deleteByUserIdAndFriendId(String userId, String friendId);

    Friend findByUserIdAndFriendId(String userId, String friendId);

    List<Friend> findAllByUserId(String userId);
}
