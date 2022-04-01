package microchat.core.handler;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import microchat.core.message.ChatMessage;
import microchat.core.service.UserManage;
import microchat.entity.Group;
import microchat.entity.User;
import microchat.exception.UserChannelException;
import microchat.repository.GroupRepository;
import microchat.utils.SpringUtil;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 消息处理器
 *
 * @author qiang
 * @since 2021/12/8
 */
@Slf4j
public class MessageHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private UserManage userManage = (UserManage) SpringUtil.getBean("userManage");

    private GroupRepository groupRepository = (GroupRepository) SpringUtil.getBean("groupRepository");

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        ChatMessage chatMessage = JSON.parseObject(textWebSocketFrame.text(), ChatMessage.class);
        handlerGroupMessage(chatMessage);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) {
        String userId = userManage.getUserIdByChannel(ctx.channel());
        log.info("{} 断开连接", userId);
        userManage.removeChannel(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        String userId = userManage.getUserIdByChannel(ctx.channel());
        log.error("{} 连接异常", userId);
        userManage.removeChannel(ctx.channel());
    }

    private void handlerMessage(ChatMessage message) throws UserChannelException {
        switch (message.getType()) {
            case ChatMessage.HEART_BEAT: // 心跳消息
                handlerHeartMessage(message);
                break;
            case ChatMessage.PRIVATE: // 单聊消息
                handlerSingleMessage(message);
                break;
            case ChatMessage.GROUP: // 群聊消息
                handlerGroupMessage(message);
                break;
            case ChatMessage.BIND: // 绑定消息，认证时需要处理
                break;
            default:
                log.warn("未知类型消息:{}", message);
        }
    }

    /**
     * 处理心跳消息：不处理，直接发回去，保持连接
     *
     * @param message
     * @throws UserChannelException
     */
    private void handlerHeartMessage(ChatMessage message) throws UserChannelException {
        String receiverId = message.getReceiverId();
        User user = userManage.getUser(receiverId);
        if (user == null || !user.isAuth() || !user.getChannel().isActive()) {
            log.error("{} 目标用户不在线或连接异常", receiverId);
            throw new UserChannelException("目标用户不在线或连接异常");
        }
        Channel channel = user.getChannel();
        channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
        log.info("{} send to {} success", message.getSenderId(), message.getReceiverId());
    }

    /**
     * 处理单聊消息
     *
     * @param message
     * @throws UserChannelException
     */
    private void handlerSingleMessage(ChatMessage message) throws UserChannelException {
        String receiverId = message.getReceiverId();
        User user = userManage.getUser(receiverId);
        if (user == null || !user.isAuth() || !user.getChannel().isActive()) {
            log.error("{} 目标用户不在线或连接异常", receiverId);
            throw new UserChannelException("目标用户不在线或连接异常");
        }
        Channel channel = user.getChannel();
        channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
        log.info("{} send to {} success", message.getSenderId(), message.getReceiverId());
    }

    /**
     * 处理群聊消息，这里不抛出异常的原因，如果某个用户有问题，不影响群组其他用户的消息发送
     *
     * @param message
     */
    private void handlerGroupMessage(ChatMessage message) {
        // 处理群i西澳西是，接收者id是群id，然后去数据库查询出群组，判断一下是在线，在线就触发
        String groupId = message.getReceiverId();
        // 去数据库查询
        List<Group> groups = groupRepository.findAllByGroupId(groupId);
        for (Group group : groups) {
            User user = userManage.getUser(group.getUserId());
            if (user == null || !user.isAuth() || !user.getChannel().isActive()) {
                log.error("{} 目标用户不在线或连接异常", group.getUserId());
            }
            user.getChannel().writeAndFlush(JSON.toJSONString(message));
        }
    }
}
