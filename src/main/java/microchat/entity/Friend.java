package microchat.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 好友，对应好友关系表
 *
 * @author qiang
 * @since 2021/12/8
 */
@Entity(name = "friend")
public class Friend {
    @Id
    private String id;

    private String userId;

    private String friendId;

    public Friend() {

    }

    public Friend(String id,String userId,String friendId) {
        this.id = id;
        this.userId = userId;
        this.friendId = friendId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getFriendId() {
        return friendId;
    }
}
