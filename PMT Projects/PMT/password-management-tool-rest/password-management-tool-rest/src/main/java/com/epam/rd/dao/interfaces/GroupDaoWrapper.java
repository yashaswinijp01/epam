package com.epam.rd.dao.interfaces;

import com.epam.rd.bean.GroupBean;
import com.epam.rd.bean.UserBean;
import com.epam.rd.exception.DuplicateGroupException;
import com.epam.rd.exception.GroupDoesNotExistException;
import java.util.List;

public interface GroupDaoWrapper {
    void saveGroup(GroupBean group , UserBean userBean) throws DuplicateGroupException;
    List<GroupBean> getAllGroups(UserBean userBean) ;
    GroupBean getGroupById(int groupId , UserBean userBean) throws GroupDoesNotExistException;
    void updateGroupsById(int groupId , GroupBean groupBean , UserBean userBean) throws GroupDoesNotExistException, DuplicateGroupException;

    void deleteGroupById(int groupId, UserBean userBean) throws GroupDoesNotExistException;
}
