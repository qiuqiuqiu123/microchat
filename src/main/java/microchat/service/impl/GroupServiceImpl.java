package microchat.service.impl;

import lombok.extern.slf4j.Slf4j;
import microchat.entity.Group;
import microchat.entity.GroupInfo;
import microchat.entity.UserInfo;
import microchat.repository.GroupInfoRepository;
import microchat.repository.GroupRepository;
import microchat.repository.UserInfoRepository;
import microchat.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiang
 * @since 2022/3/20
 */
@Slf4j
@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupInfoRepository groupInfoRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    @Transactional
    public GroupInfo create(String creatorId, String groupName) {
        log.info("[GroupService] create group");
        String imageUrl = "";
        GroupInfo groupInfo = new GroupInfo(groupName, imageUrl, creatorId);
        groupInfoRepository.save(groupInfo);
        Group group = new Group(groupInfo.getId(), creatorId);
        groupRepository.save(group);
        if (true) {
            throw new RuntimeException("test");
        }
        return groupInfo;
    }

    @Override
    public void delete(String groupId) {
        log.info("[GroupService] remove group");
        groupInfoRepository.deleteById(groupId);
        groupRepository.deleteAllByGroupId(groupId);
    }

    @Override
    public void join(String groupId, String userId) {
        log.info("[GroupService] join group");
        Group group = new Group(groupId, userId);
        groupRepository.save(group);
    }

    @Override
    public void quit(String groupId, String userId) {
        log.info("[GroupService] join group");
        Group group = groupRepository.findByGroupIdAndUserId(groupId, userId);
        groupRepository.delete(group);
    }

    @Override
    public List<Group> findAllByUserId(String userId) {
        log.info("[GroupService] find all group");
        List<Group> groups = groupRepository.findAllByUserId(userId);
        return new ArrayList<>(groups);
    }

    @Override
    public GroupInfo get(String groupId) {
        log.info("[GroupService] find all group");
        return groupInfoRepository.findAllById(groupId);
    }

    @Override
    public List<UserInfo> findMembersByGroupId(String groupId) {
        log.info("[GroupService] find all groupMember");
        List<Group> groups = groupRepository.findAllByGroupId(groupId);
        List<UserInfo> userInfos = new ArrayList<>();
        groups.forEach(group -> {
            userInfos.add(userInfoRepository.findById(group.getUserId()).get());
        });
        return userInfos;
    }
}
