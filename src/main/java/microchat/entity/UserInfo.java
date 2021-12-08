package microchat.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 用户信息，与用户表对应
 *
 * @author qiang
 * @since 2021/12/8
 */
@Entity(name = "chat_user")
public class UserInfo {
    @Id
    private String userId;

    private String userName;

    private String userAccount; // 账号

    private String password; // 密码

    private String headImageUrl; // 头像地址

    public UserInfo() {

    }

    public UserInfo(String userName, String userAccount, String password, String headImageUrl) {
        this.userId = userAccount;
        this.userName = userName;
        this.userAccount = userAccount;
        this.password = password;
        this.headImageUrl = headImageUrl;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserAcount() {
        return userAccount;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }

    public String getHeadImageUrl() {
        return headImageUrl;
    }
}
