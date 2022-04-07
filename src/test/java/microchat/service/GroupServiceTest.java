package microchat.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author qiang
 * @since 2022/4/7
 */
@SpringBootTest
public class GroupServiceTest {

    @Autowired
    private GroupService groupService;

    @Test
    void create() {
        groupService.create("test", "test");
    }

    @Test
    void delete() {
    }

    @Test
    void join() {
    }

    @Test
    void quit() {
    }

    @Test
    void findAllByUserId() {
    }

    @Test
    void get() {
    }

    @Test
    void findMembersByGroupId() {
    }
}