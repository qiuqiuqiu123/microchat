package microchat.controller;

import microchat.service.FriendService;
import microchat.service.UserInfoService;
import microchat.utils.Result;
import microchat.utils.VoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 好友关系控制器
 *
 * @author qiang
 * @since 2022/3/20
 */
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
    public String findFriends(String userId) {
        return VoUtils.getFriendAndGroupVo(userInfoService.get(userId), friendService.findAllByUserId(userId));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/search")
    public Result search(String friendIdOrAccount) {
        return new Result(2000, "查询好友成功", userInfoService.get(friendIdOrAccount));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/add")
    public Result add(String userId, String friendId) {
        friendService.add(userId, friendId);
        return new Result(2000, "添加好友成功", null);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/delete")
    public Result delete(String userId, String friendId) {
        friendService.remove(userId, friendId);
        return new Result(2000, "删除好友成功", null);
    }

}
