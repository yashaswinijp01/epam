package com.epam.demo;

import com.epam.bean.UserBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class UserRestClient {

    private  final String BASE_URI="http://localhost:9092/UsersPortal/user";

    @Autowired
    private RestTemplate template;

    private static final Logger log = LoggerFactory.getLogger(ConsumingRestfulServiceBook.class);

    public void addUserDetails(){
        UserBean bean = new UserBean("Sri lakshmi","Lakshmi","lakshmi1234@gmail.com");
        String str = template.postForObject(BASE_URI+"/addUser",bean,String.class);
        log.info(str);
    }

    public void getAllUsersDetails(){
        List<UserBean> list =template.getForObject(BASE_URI+"/getDetails",List.class);
        log.info(list.toString());
    }

    public void editUserDetails(){
        UserBean bean = new UserBean("yashaswini","yashu","yashu1234@gmail.com");
        template.put(BASE_URI+"/editUser/yashaswini",bean);
        log.info(bean.toString());
    }

    public void getUserByUserName(){
        UserBean bean = template.getForObject(BASE_URI+"/getDetails/Sri lakshmi",UserBean.class);
        log.info(bean.toString());
    }

    public void deleteUser() {
        template.delete(BASE_URI + "/deleteUser/ArunRaj");
    }
}
