package microchat.repository;

import microchat.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 好友关系
 *
 * @author qiang
 * @since 2022/3/19
 */
@Repository
public interface FriendRepository extends JpaRepository<Friend, String> {
    @Transactional
    int deleteByUserIdAndFriendId(String userId, String friendId);

    Optional<Friend> findByUserIdAndFriendId(String userId, String friendId);

    List<Friend> findAllByUserId(String userId);
}
