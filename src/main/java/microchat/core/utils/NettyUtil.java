package microchat.core.utils;

import io.netty.channel.Channel;

import java.net.SocketAddress;

/**
 * Netty工具类
 *
 * @author qiang
 * @since 2022/3/19
 */
public class NettyUtil {

    /**
     * 获取Channel的远程IP地址
     *
     * @param channel
     * @return
     */
    public static String parseChannelRemoteAddr(Channel channel) {
        if (null == channel) {
            return "";
        }
        SocketAddress remote = channel.remoteAddress();
        String addr = remote != null ? remote.toString() : "";

        if (addr.length() > 0) {
            int index = addr.lastIndexOf("/");
            if (index >= 0) {
                return addr.substring(0, index + 1);
            }
            return addr;
        }

        return "";
    }
}
