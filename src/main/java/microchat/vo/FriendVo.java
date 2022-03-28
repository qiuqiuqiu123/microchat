package microchat.vo;

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
    String userName;

    String id;

    String status;

    String sign;

    String avatar;
}
