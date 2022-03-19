package microchat.service;

import microchat.entity.Group;
import microchat.entity.GroupInfo;
import microchat.entity.UserInfo;

import java.util.List;

/**
 * 群组管理接口
 *
 * @author qiang
 * @since 2022/3/19
 */
public interface GroupService {

    GroupInfo createGroup(String creatorId, String groupName);

    void removeGroup(String groupId);

    void joinGroup(String groupId, String userId);

    void outGroup(String groupId, String userId);

    List<Group> findAllGroupByUserId(String userId);

    GroupInfo getGroupInfo(String groupId);

    List<UserInfo> findMembersByGroupId(String groupId);
}
