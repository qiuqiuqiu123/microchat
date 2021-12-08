package microchat.core.chatServer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 基本聊天服务器，里面包含了基本的参数
 *
 * @author qiang
 * @since 2021/12/5
 */
@Slf4j
public abstract class BaseChatServer implements ChatServer {
    protected String host = "localhost";

    protected int port = 9688;

    protected DefaultEventLoopGroup defaultEventLoopGroup;

    protected NioEventLoopGroup bossGroup;

    protected NioEventLoopGroup workGroup;

    protected NioServerSocketChannel ssch;

    protected ChannelFuture cf;

    protected ServerBootstrap b;

    public void init() {
        defaultEventLoopGroup = new DefaultEventLoopGroup(8, new ThreadFactory() {
            private AtomicInteger index = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "DEFAULTEVENTLOOPGROUP_" + index.incrementAndGet());
            }
        });
        bossGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors(), new ThreadFactory() {
            private AtomicInteger index = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "BOSS_" + index.incrementAndGet());
            }
        });
        workGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors() * 10, new ThreadFactory() {
            private AtomicInteger index = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "WORK_" + index.incrementAndGet());
            }
        });

        b = new ServerBootstrap();
    }

    @Override
    public void shutdown() {
        if (defaultEventLoopGroup != null) {
            defaultEventLoopGroup.shutdownGracefully();
        }
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
        log.info("关闭聊天服务器");
    }

}
