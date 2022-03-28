package microchat.controller;

import lombok.extern.slf4j.Slf4j;
import microchat.core.chatServer.MicroChatServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * 主要做一些启动时的初始化工作+
 * @author qiang
 * @since 2022/3/29
 */
@RestController
@Slf4j
public class InitController {

    @Autowired
    private MicroChatServer microChatServer;

    @PostConstruct
    public void init() {
        // 初始化时候的工作
        microChatServer.init();
        microChatServer.start();
        log.info("聊天系统初始化完成");
    }
}
