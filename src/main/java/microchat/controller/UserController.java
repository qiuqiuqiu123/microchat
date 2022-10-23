package microchat.controller;

import microchat.utils.Result;
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

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public Result login(String userName, String password) {
        return new Result<>(200, "登录成功", userName);
    }
}
