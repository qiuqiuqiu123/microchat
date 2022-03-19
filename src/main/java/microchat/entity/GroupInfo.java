package microchat.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/**
 * 群组信息
 *
 * @author qiang
 * @since 2021/12/8
 */
@Entity(name = "chat_group_info")
@Setter
@Getter
@NoArgsConstructor
public class GroupInfo {
    @Id
    private String groupId;

    private String groupName;

    private String imageUrl;

    private String creatorId;

    public GroupInfo(String groupId, String groupName, String imageUrl, String creatorId) {
        this.groupId = UUID.randomUUID().toString();
        this.groupName = groupName;
        this.imageUrl = imageUrl;
        this.creatorId = creatorId;
    }

}
