package microchat.core.handler;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.*;
import lombok.extern.slf4j.Slf4j;
import microchat.core.message.ChatMessage;
import microchat.core.service.UserManage;
import microchat.core.utils.Constants;
import microchat.utils.SpringUtil;

/**
 * @author qiang
 * @since 2022/6/26
 */
@Slf4j
public class UserAuthHandler extends SimpleChannelInboundHandler<Object> {
    private WebSocketServerHandshaker handshaker;

    private UserManage userManage = (UserManage) SpringUtil.getBean("userManage");


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof FullHttpRequest) {
            handleHttpRequeust(ctx, (FullHttpRequest) msg);
        } else if (msg instanceof WebSocketFrame) {
            handleWebSocket(ctx, (WebSocketFrame) msg);
        }
    }

    private void handleHttpRequeust(ChannelHandlerContext ctx, FullHttpRequest request) {
        // 请求不成功或是websocket请求是直接返回
        if (!request.decoderResult().isSuccess() || !"websocket".equals(request.headers().get("upgrade"))) {
            log.info("protobuf don't support websocket");
            ctx.channel().close();
            return;
        }
        WebSocketServerHandshakerFactory handshakerFactory = new WebSocketServerHandshakerFactory(Constants.WEBSOCKET_URL, null, true);
        handshaker = handshakerFactory.newHandshaker(request);
        if (handshakerFactory == null) {
            // 发送不支持的响应
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            // 动态加入websocket的编码处理
            handshaker.handshake(ctx.channel(), request);
        }
    }

    private void handleWebSocket(ChannelHandlerContext ctx, WebSocketFrame frame) {
        // 判断链路是否关闭指令
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            userManage.removeChannel(ctx.channel());
        }
        // 判断是否是ping消息
        if (frame instanceof PingWebSocketFrame) {
            log.info("ping message : {}", frame.content().retain());
            ctx.writeAndFlush(new PongWebSocketFrame(frame.content().retain()));
        }
        // 判断是都pong消息
        if (frame instanceof PongWebSocketFrame) {
            log.info("pong message : {}", frame.content().retain());
            ctx.writeAndFlush(new PongWebSocketFrame(frame.content().retain()));
        }
        // 目前只支持文本消息
        if (!(frame instanceof TextWebSocketFrame)) {
            throw new UnsupportedOperationException(frame.getClass().getName() + "frame type not support");
        }
        String message = ((TextWebSocketFrame) frame).text();
        log.info("message str : {}", message);
        ChatMessage chatMessage = JSON.parseObject(message, ChatMessage.class);
        log.info("chat message : {}", chatMessage.toString());
        if (chatMessage.getType() == ChatMessage.BIND) {
            // 加入频道
            userManage.addChannel(ctx.channel(), chatMessage.getSenderId(), chatMessage.getSenderName());
        }
        // 后续消息交给MessageHandlerCHU
        ctx.fireChannelRead(frame.retain());
    }

}
