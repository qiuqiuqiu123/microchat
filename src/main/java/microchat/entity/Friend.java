package microchat.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import microchat.utils.UUIDUtil;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 好友，对应好友关系表
 *
 * @author qiang
 * @since 2021/12/8
 */
@Entity(name = "friend")
@Setter
@Getter
@NoArgsConstructor
public class Friend {
    @Id
    private String id;

    private String userId;

    private String friendId;

    public Friend(String userId, String friendId) {
        this.id = UUIDUtil.getUUID();
        this.userId = userId;
        this.friendId = friendId;
    }

}
