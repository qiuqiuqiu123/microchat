package microchat.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import microchat.utils.UUIDUtil;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 用户信息，与用户表对应
 *
 * @author qiang
 * @since 2021/12/8
 */
@Entity(name = "chat_user")
@Data
@NoArgsConstructor
public class UserInfo implements Serializable {
    @Id
    private String id;

    private String name;

    private String account; // 账号

    private String password; // 密码

    private String headImageUrl; // 头像地址

    public UserInfo(String name, String account, String password, String headImageUrl) {
        this.id = UUIDUtil.getUUID();
        this.name = name;
        this.account = account;
        this.password = password;
        this.headImageUrl = headImageUrl;
    }

}
