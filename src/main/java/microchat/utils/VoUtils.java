package microchat.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import microchat.entity.UserInfo;
import microchat.vo.FriendVo;
import microchat.vo.GroupVo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 把数据结构转换为前端所需要的结构
 *
 * @author qiang
 * @since 2022/3/20
 */
@Component
@Slf4j
public class VoUtils {
    public static String getFriendAndGroupVo(UserInfo userInfo, List<UserInfo> friends) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("msg", "获取成功");
        Map<String, Object> data = new HashMap<>();
        data.put("mine", new FriendVo(userInfo.getName(), userInfo.getId(), "online", "该用户很懒，暂未签名", "https://img2.baidu.com/it/u=1790834130,1952230725&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"));
        List<FriendVo> friendVos = new ArrayList<>();
        for (UserInfo friend : friends) {
            friendVos.add(new FriendVo(friend.getName(), friend.getId(), "online", "该用户很懒，暂未签名", "https://img2.baidu.com/it/u=1790834130,1952230725&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"));
        }
        GroupVo groupVo = new GroupVo("好友", 1, 2, friendVos);
        List<GroupVo> groups = new ArrayList<>();
        groups.add(groupVo);
        data.put("friend", groups);
        result.put("data", data);
        return JSON.toJSONString(result);
    }
}
