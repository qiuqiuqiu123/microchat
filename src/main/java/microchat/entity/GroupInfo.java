package microchat.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import microchat.utils.UUIDUtil;

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

    public GroupInfo(String groupName, String imageUrl, String creatorId) {
        this.groupId = UUIDUtil.getUUID();
        this.groupName = groupName;
        this.imageUrl = imageUrl;
        this.creatorId = creatorId;
    }

}
