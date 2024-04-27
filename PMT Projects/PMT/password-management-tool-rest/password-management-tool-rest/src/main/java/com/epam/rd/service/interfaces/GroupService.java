package com.epam.rd.service.interfaces;
import com.epam.rd.bean.GroupBean;
import com.epam.rd.exception.DuplicateGroupException;
import com.epam.rd.exception.GroupDoesNotExistException;
import java.util.List;

public interface GroupService {

    void saveGroup(GroupBean groupBean) throws DuplicateGroupException;
    List<GroupBean> getAllGroups() ;
    GroupBean getGroupById(int groupId) throws GroupDoesNotExistException;
    void updateGroupById(int groupId , GroupBean groupBean) throws DuplicateGroupException, GroupDoesNotExistException;
    void deleteGroupById(int groupId) throws GroupDoesNotExistException;
}
