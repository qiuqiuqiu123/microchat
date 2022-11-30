package microchat.repository;

import microchat.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 用户信息查询
 *
 * @author qiang
 * @since 2022/3/19
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    Optional<UserInfo> findById(String id);

    Optional<UserInfo> findByAccount(String account);
}
