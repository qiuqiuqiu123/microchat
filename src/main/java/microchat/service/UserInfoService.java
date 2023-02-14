package microchat.service;

import com.alicp.jetcache.anno.Cached;
import microchat.entity.UserInfo;
import microchat.exception.UserException;

/**
 * 用户信息管理
 *
 * @author qiang
 * @since 2022/3/19
 */
public interface UserInfoService {
    UserInfo create(String userName, String userAccount, String imageUrl, String password);

    @Cached(name="UserInfoService.get", expire = 3600)
    UserInfo get(String userIdOrUserName) throws UserException;

    boolean login(String userAccount, String password);
}
