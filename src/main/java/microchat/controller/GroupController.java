package microchat.controller;

import microchat.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiang
 * @since 2022/3/20
 */
@RestController
@RequestMapping(value = "/group")
public class GroupController {
    @Autowired
    private GroupService groupService;


}
