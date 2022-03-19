package microchat.repository;

import microchat.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 群组关系查询
 *
 * @author qiang
 * @since 2022/3/19
 */
public interface GroupRepository extends JpaRepository<Group, String> {
    List<Group> findAllByGroupId(String groupId);

    Group findByGroupIdAndUserId(String groupId, String userId);

    List<Group> findAllByUserId(String userId);
}
