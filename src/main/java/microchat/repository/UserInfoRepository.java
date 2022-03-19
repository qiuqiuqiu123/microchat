package microchat.repository;

import microchat.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户信息查询
 *
 * @author qiang
 * @since 2022/3/19
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    UserInfo findByUserId(String userId);

    UserInfo findByUserAccount(String userAccount);

}
