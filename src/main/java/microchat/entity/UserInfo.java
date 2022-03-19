package microchat.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import microchat.utils.UUIDUtil;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/**
 * 用户信息，与用户表对应
 *
 * @author qiang
 * @since 2021/12/8
 */
@Entity(name = "chat_user")
@Setter
@Getter
@NoArgsConstructor
public class UserInfo {
    @Id
    private String userId;

    private String userName;

    private String userAccount; // 账号

    private String password; // 密码

    private String headImageUrl; // 头像地址

    public UserInfo(String userName, String userAccount, String password, String headImageUrl) {
        this.userId = UUIDUtil.getUUID();
        this.userName = userName;
        this.userAccount = userAccount;
        this.password = password;
        this.headImageUrl = headImageUrl;
    }

}
