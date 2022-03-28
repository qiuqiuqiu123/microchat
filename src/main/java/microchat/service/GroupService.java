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

    GroupInfo create(String creatorId, String groupName);

    void delete(String groupId);

    void join(String groupId, String userId);

    void quit(String groupId, String userId);

    List<Group> findAllByUserId(String userId);

    GroupInfo get(String groupId);

    List<UserInfo> findMembersByGroupId(String groupId);
}
