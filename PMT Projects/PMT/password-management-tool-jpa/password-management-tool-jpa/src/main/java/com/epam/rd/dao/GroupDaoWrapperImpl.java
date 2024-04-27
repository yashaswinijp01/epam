package com.epam.rd.dao;

import com.epam.rd.bean.GroupBean;
import com.epam.rd.bean.UserBean;
import com.epam.rd.dao.interfaces.GroupDao;
import com.epam.rd.dao.interfaces.GroupDaoWrapper;
import com.epam.rd.exception.DuplicateGroupException;
import com.epam.rd.exception.GroupDoesNotExistException;
import com.epam.rd.entity.Group;
import com.epam.rd.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository("groupDaoWrapper")
public class GroupDaoWrapperImpl implements GroupDaoWrapper {
    @Autowired
    private GroupDao groupDao;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveGroup(GroupBean groupBean, UserBean userBean) throws DuplicateGroupException {
        User user = getUserFromBean(userBean);
        Group group = modelMapper.map(groupBean, Group.class);
        checkGorDuplicate(user, group.getGroupName());
        group.setGroupId(0);
        groupDao.save(group);
    }

    private User getUserFromBean(UserBean userBean) {
        return modelMapper.map(userBean, User.class);
    }

    private void checkGorDuplicate(User user, String groupName) throws DuplicateGroupException {
        if(groupDao.countByGroupNameAndUser(groupName , user)>0){
            throw new DuplicateGroupException("Group already Exist");
        }
    }

    @Override
    public List<GroupBean> getAllGroups(UserBean userBean) {
        User user = getUserFromBean(userBean);
        List<Group> groups = groupDao.findByUser(user);
        List<GroupBean> groupBeans = new ArrayList<>();
        groups.forEach(group -> groupBeans.add(modelMapper.map(group , GroupBean.class)));
        return groupBeans;
    }

    @Override
    public GroupBean getGroupById(int groupId, UserBean userBean) throws GroupDoesNotExistException {
        User user = getUserFromBean(userBean);
        List<Group> groups = groupDao.findByGroupIdAndUser(groupId , user);
        if(groups.isEmpty()){
            throw new GroupDoesNotExistException("Group Does not Exist");
        }
        return modelMapper.map(groups.get(0) , GroupBean.class);
    }

    @Override
    public void updateGroupsById(int groupId, GroupBean groupBean, UserBean userBean) throws GroupDoesNotExistException, DuplicateGroupException {
        User user = getUserFromBean(userBean);
        GroupBean groupToUpdate = getGroupById(groupId , userBean);
        if(!groupToUpdate.getGroupName().equals(groupBean.getGroupName())){
            checkGorDuplicate(user , groupBean.getGroupName());
        }
        groupToUpdate.setGroupName(groupBean.getGroupName());
        groupToUpdate.setLastModifiedAt(new Date());
        Group group = modelMapper.map(groupToUpdate , Group.class);
        groupDao.save(group);
    }

    @Override
    public void deleteGroupById(int groupId, UserBean userBean) throws GroupDoesNotExistException {
        GroupBean groupBean = getGroupById(groupId , userBean);
        Group group = modelMapper.map(groupBean, Group.class);
        groupDao.delete(group);

    }
}
