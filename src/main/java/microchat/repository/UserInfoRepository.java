package microchat.repository;

import microchat.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 用户信息查询
 *
 * @author qiang
 * @since 2022/3/19
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    Optional<UserInfo> findById(String id);

    UserInfo findByAccount(String account);
}
