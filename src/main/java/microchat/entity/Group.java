package microchat.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/**
 * 群组关系
 *
 * @author qiang
 * @since 2021/12/8
 */
@Entity(name = "chat_group")
public class Group {
    @Id
    private String id;

    private String groupId;

    private String userId;

    public Group() {

    }

    public Group(String groupId,String userId) {
        id = UUID.randomUUID().toString();
        this.groupId = groupId;
        this.userId = userId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
