package microchat.core.chatServer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;

/**
 * 真正的聊天服务
 *
 * @author qiang
 * @since 2021/12/8
 */
@Slf4j
@Service
public class MicroChatServer extends BaseChatServer {

    @Override
    public void start() {
        b.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .localAddress(new InetSocketAddress(port))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(defaultEventLoopGroup, new HttpServerCodec(), // 请求解码器
                                new HttpObjectAggregator(65536), // 将多个消息转换成单一的消息对象
                                new ChunkedWriteHandler(), // 支持异步发送大的码流，一般用于文件发送
                                new IdleStateHandler(60, 0, 0) // 检测链路是否空闲
                        );
                    }
                });
        try {
            cf = b.bind().sync();
            InetSocketAddress address = (InetSocketAddress) cf.channel().localAddress();
            log.info("webscoket start success,port is {}", address.getPort());
        } catch (Exception ex) {
            log.error("websocket start fail");
        }
    }

    @Override
    public void shutdown() {
        super.shutdown();
    }
}
