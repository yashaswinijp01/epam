package com.epam.rd.dao;

import com.epam.rd.bean.UserBean;
import com.epam.rd.dao.interfaces.UserDao;
import com.epam.rd.dao.interfaces.UserDaoWrapper;
import com.epam.rd.exception.DuplicateUserException;
import com.epam.rd.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDaoWrapper")
public class UserDaoWrapperImpl implements UserDaoWrapper {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveUser(UserBean userBean) throws DuplicateUserException {
        User user = modelMapper.map(userBean , User.class);
        if(userDao.countByUserName(user.getUserName())>0){
            throw new DuplicateUserException("User with this UserName already Exist");
        }
        user.setUserId(0);
        userDao.save(user);
    }

    @Override
    public UserBean getUserByUserName(String userName) {
        List<User> user = userDao.findByUserName(userName);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("Invalid UserName / Password");
        }
        return modelMapper.map(user.get(0) , UserBean.class);
    }
}
