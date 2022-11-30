package microchat.controller;

import microchat.exception.UserException;
import microchat.service.FriendService;
import microchat.service.UserInfoService;
import microchat.utils.Result;
import microchat.utils.VoUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 好友关系控制器
 *
 * @author qiang
 * @since 2022/3/20
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/friend")
public class FriendController {
    @Autowired
    private FriendService friendService;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 主要是按前端特定结构而返回的
     *
     * @param userId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/friends")
    public String findFriends(String userId) throws UserException {
        return VoUtils.getFriendAndGroupVo(userInfoService.get(userId), friendService.findAllByUserId(userId));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/search")
    public Result search(String friendIdOrAccount) throws UserException {
        return new Result(200, "查询好友成功", userInfoService.get(friendIdOrAccount));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/add")
    public Result add(String userId, String friendId) throws UserException {
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(friendId)) {
            return new Result(400, "userId or friendId不能为空", null);
        }
        friendService.add(userId, friendId);
        return new Result(200, "添加好友成功", null);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/delete")
    public Result delete(String userId, String friendId) {
        friendService.remove(userId, friendId);
        return new Result(200, "删除好友成功", null);
    }

}
