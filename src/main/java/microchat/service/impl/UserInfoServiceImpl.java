package microchat.service.impl;

import lombok.extern.slf4j.Slf4j;
import microchat.entity.UserInfo;
import microchat.repository.UserInfoRepository;
import microchat.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户信息管理
 *
 * @author qiang
 * @since 2022/3/20
 */
@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    public UserInfo createUser(String userName, String userAccount, String imageUrl, String password) {
        log.info("[UserInfoService] create user");
        UserInfo userInfo = new UserInfo(userName, userAccount, password, imageUrl);
        userInfoRepository.save(userInfo);
        return userInfo;
    }

    @Override
    public UserInfo getUser(String userIdORUserAccount) {
        UserInfo userInfo = userInfoRepository.findByUserId(userIdORUserAccount);
        if (userInfo == null) {
            userInfo = userInfoRepository.findByUserAccount(userIdORUserAccount);
        }
        return userInfo;
    }

    @Override
    public boolean login(String userAccount, String password) {
        // todo 主要是用于权限控制，但是权限控制暂时未做
        return true;
    }
}