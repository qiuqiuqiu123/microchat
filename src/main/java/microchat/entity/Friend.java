package microchat.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

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

    public Friend(String id, String userId, String friendId) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.friendId = friendId;
    }

}
