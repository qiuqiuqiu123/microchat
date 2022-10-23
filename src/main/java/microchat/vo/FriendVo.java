package microchat.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qiang
 * @since 2022/3/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendVo {
    @JSONField(name="username")
    private String userName;

    private String id;

    private String status;

    private String sign;

    private String avatar;
}
