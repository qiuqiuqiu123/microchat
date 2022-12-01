package microchat.controller;

import microchat.entity.UserInfo;
import microchat.exception.UserException;
import microchat.service.UserInfoService;
import microchat.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiang
 * @since 2022/6/26
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public Result login(String userName, String password) throws UserException {
        UserInfo userInfo = userInfoService.get(userName);

        return new Result<>(200, "登录成功", userInfo.getId());
    }
}
