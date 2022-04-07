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

    /**
     * @param creatorId 创建者id
     * @param name
     * @return
     */
    GroupInfo create(String creatorId, String name);

    /**
     * @param groupId 群id
     */
    void delete(String groupId);

    /**
     * @param groupId 群id
     * @param userId
     */
    void join(String groupId, String userId);

    /**
     * @param groupId 群id
     * @param userId
     */
    void quit(String groupId, String userId);

    /**
     * @param userId 用户id
     * @return
     */
    List<Group> findAllByUserId(String userId);

    /**
     * @param groupId 群id
     * @return
     */
    GroupInfo get(String groupId);

    /**
     * @param groupId 群id
     * @return
     */
    List<UserInfo> findMembersByGroupId(String groupId);
}
