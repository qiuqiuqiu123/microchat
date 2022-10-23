package microchat.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import microchat.utils.UUIDUtil;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 群组关系
 *
 * @author qiang
 * @since 2021/12/8
 */
@Entity(name = "chat_group")
@Data
@NoArgsConstructor
public class Group {
    @Id
    private String id;

    private String groupId;

    private String userId;

    public Group(String groupId, String userId) {
        this.id = UUIDUtil.getUUID();
        this.groupId = groupId;
        this.userId = userId;
    }
}
