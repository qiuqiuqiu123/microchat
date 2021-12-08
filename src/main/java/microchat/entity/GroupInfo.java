package microchat.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 群组信息
 *
 * @author qiang
 * @since 2021/12/8
 */
@Entity(name = "chat_group_info")
public class GroupInfo {
    @Id
    private String groupId;

    private String groupName;

    private String imageUrl;

    private String creatorId;

    public GroupInfo() {

    }

    public GroupInfo(String groupId,String groupName,String imageUrl,String creatorId) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.imageUrl = imageUrl;
        this.creatorId = creatorId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorId() {
        return creatorId;
    }
}
