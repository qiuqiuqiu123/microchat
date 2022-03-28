package microchat.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import microchat.utils.UUIDUtil;

import javax.persistence.Entity;
import javax.persistence.Id;

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
    private String id;

    private String name;

    private String imageUrl;

    private String creatorId;

    public GroupInfo(String groupName, String imageUrl, String creatorId) {
        this.id = UUIDUtil.getUUID();
        this.name = groupName;
        this.imageUrl = imageUrl;
        this.creatorId = creatorId;
    }

}
