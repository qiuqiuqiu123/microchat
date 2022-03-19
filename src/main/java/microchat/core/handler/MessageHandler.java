package microchat.core.handler;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import microchat.core.message.ChatMessage;
import microchat.core.service.UserManage;
import microchat.entity.User;
import microchat.exception.UserChannelException;
import microchat.utils.SpringUtil;

/**
 * 消息处理器
 *
 * @author qiang
 * @since 2021/12/8
 */
@Slf4j
public class MessageHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private UserManage userManage = (UserManage) SpringUtil.getBean("userManage");

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        ChatMessage chatMessage = JSON.parseObject(textWebSocketFrame.text(), ChatMessage.class);
        // handle
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

    private void handlerMessage(ChatMessage chatMessage) {
        switch (chatMessage.getType()) {
            case ChatMessage.HEART_BEAT: // 心跳消息
//                handlerHeartMessage(message)
        }
    }

    /**
     * 处理心跳消息：不处理，直接发回去，保持连接
     * @param message
     * @throws UserChannelException
     */
    private void handlerHeartMessage(ChatMessage message) throws UserChannelException {
        String receiverId = message.getReceiverId();
        User user = userManage.getUser(receiverId);
        if (user == null || !user.isAuth() || !user.getChannel().isActive()) {
            log.error("目标用户不在线或连接异常");
            throw new UserChannelException("目标用户不在线或连接异常");
        }
        Channel channel = user.getChannel();
        channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
        log.info("{} send to {} success", message.getSenderId(), message.getReceiverId());
    }

    private void handlerSingleMessage(ChatMessage message) {

    }
}
