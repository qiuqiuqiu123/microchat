package microchat.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author qiang
 * @since 2022/3/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupVo {
    String groupname;

    int id;

    int online;

    List<FriendVo> list;
}
