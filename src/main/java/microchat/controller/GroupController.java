package microchat.controller;

import microchat.service.GroupService;
import microchat.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author qiang
 * @since 2022/3/20
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/group")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public Result create(@RequestParam(value = "creator", required = false) String creator, @RequestParam(value = "name", required = false) String name) {
        groupService.create(creator, name);
        return new Result(200, "创建成功", null);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/join")
    public Result join(String userId, String groupId) {
        groupService.join(userId, groupId);
        return new Result(200, "加入成功", null);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/quit")
    public Result quit(String userId, String groupId) {
        groupService.quit(userId, groupId);
        return new Result(200, "退出成功", null);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public Result delete(String groupId) {
        groupService.delete(groupId);
        return new Result(200, "删除成功", null);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public Result get(String groupId) {
        return new Result(200, "获取成功", groupService.get(groupId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAllByUserIds")
    public Result findAllByUserId(String userId) {
        return new Result(200, "获取成功", groupService.findAllByUserId(userId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAllMembers")
    public Result getAllMembers(String groupId) {
        return new Result(200, "获取成功", groupService.findMembersByGroupId(groupId));
    }
}


