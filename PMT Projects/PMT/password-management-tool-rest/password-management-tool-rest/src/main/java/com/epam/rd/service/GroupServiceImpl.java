package com.epam.rd.service;

import com.epam.rd.bean.AccountBean;
import com.epam.rd.bean.GroupBean;
import com.epam.rd.bean.UserBean;
import com.epam.rd.dao.interfaces.GroupDaoWrapper;
import com.epam.rd.exception.DuplicateGroupException;
import com.epam.rd.exception.GroupDoesNotExistException;
import com.epam.rd.service.interfaces.AccountService;
import com.epam.rd.service.interfaces.GroupService;
import com.epam.rd.service.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("groupService")
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDaoWrapper groupDaoWrapper;
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;

    private static final Logger logger
            = LoggerFactory.getLogger(GroupServiceImpl.class);
    @Override
    public void saveGroup(GroupBean groupBean) throws DuplicateGroupException {
        logger.info("saving the group bean");
        groupBean.setUser(getUserBean());
        groupBean.setCreatedAt(new Date());
        groupBean.setLastModifiedAt(new Date());
        groupDaoWrapper.saveGroup(groupBean , getUserBean());
        logger.info("group saved successfully");
    }

    @Override
    public List<GroupBean> getAllGroups()  {
        logger.info("group bean saved successfully");
        List<GroupBean> allGroups = groupDaoWrapper.getAllGroups(getUserBean());
        List<AccountBean> unAssignedAccounts = accountService.getUnassignedAccounts();
        GroupBean groupBean = new GroupBean("un_assigned");
        groupBean.setAccounts(unAssignedAccounts);
        allGroups.add(groupBean);
        return allGroups;
    }

    @Override
    public GroupBean getGroupById(int groupId) throws GroupDoesNotExistException {
        logger.info("getting group bean by id");
        return groupDaoWrapper.getGroupById(groupId , getUserBean());
    }

    private UserBean getUserBean()  {
        logger.info("getting user bean");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByUserName(authentication.getName());
    }

    @Override
    public void updateGroupById(int groupId, GroupBean groupBean) throws DuplicateGroupException, GroupDoesNotExistException {
        logger.info("updating the group");
        groupDaoWrapper.updateGroupsById(groupId , groupBean , getUserBean());
        logger.info("group updated successfully");
    }

    @Override
    public void deleteGroupById(int groupId) throws GroupDoesNotExistException {
        groupDaoWrapper.deleteGroupById(groupId , getUserBean());
    }
}
