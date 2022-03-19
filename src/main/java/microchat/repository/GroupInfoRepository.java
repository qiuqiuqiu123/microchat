package microchat.repository;

import microchat.entity.GroupInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 群组信息查询
 *
 * @author qiang
 * @since 2022/3/19
 */
public interface GroupInfoRepository extends JpaRepository<GroupInfo, String> {
    GroupInfo findAllByGroupId(String groupId);
}
