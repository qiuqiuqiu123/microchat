package microchat.service;

import microchat.entity.UserInfo;

/**
 * 用户信息管理
 *
 * @author qiang
 * @since 2022/3/19
 */
public interface UserInfoService {
    UserInfo createUser(String userName, String userAccount, String imageUrl, String password);

    UserInfo getUser(String userIdORUserName);

    boolean login(String userAccount, String password);
}