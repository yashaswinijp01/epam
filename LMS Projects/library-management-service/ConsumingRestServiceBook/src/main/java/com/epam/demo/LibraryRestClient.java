package com.epam.demo;

import com.epam.bean.BookBean;
import com.epam.bean.LibraryBean;
import com.epam.bean.UserBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class LibraryRestClient {

    private static final String BASE_URI="http://localhost:9098/LibraryPortal";
    @Autowired
    private RestTemplate template;

    private static final Logger log = LoggerFactory.getLogger(ConsumingRestfulServiceBook.class);

    public void getAllBooksDetails(){
        List<BookBean> list =template.getForObject(BASE_URI+"/library/books",List.class);
        log.info(list.toString());
    }

    public void getBookByBookId() {
        BookBean bean = template.getForObject(BASE_URI+"/library/books/2",BookBean.class);
        log.info(bean.toString());
    }

    public void addBook(){
        BookBean bean = new BookBean("java","LakshmiBookDepo","anand");
        String str = template.postForObject(BASE_URI+"/library/books",bean,String.class);
        log.info(str);
    }

    public void updateBookById(){
        BookBean bean = new BookBean(3,"Java Basics","Goshal","Rajeev");
        template.put(BASE_URI+"/library/books/3",bean);
        log.info(bean.toString());
    }

    public void deleteBookById(){
        template.delete(BASE_URI+"/library/books/2");
    }


    public void getUsers(){
        List<UserBean> list =template.getForObject(BASE_URI+"/library/users",List.class);
        log.info(list.toString());
    }

    public void getUserByName(){
        UserBean bean = template.getForObject(BASE_URI+"/library/users/Sri lakshmi",UserBean.class);
        log.info(bean.toString());
    }

    public void addUser(){
        UserBean bean = new UserBean("Sri lakshmi","Lakshmi","lakshmi1234@gmail.com");
        String str = template.postForObject(BASE_URI+"/library/users",bean,String.class);
        log.info(str);
    }

    public void deleteUser() {
        template.delete(BASE_URI + "/library/users/Sri Lakshmi");
    }

    public void updateUserByUsername(){
        UserBean bean = new UserBean("Sri Lakshmi","yashu","lakshmi1234@gmail.com");
        template.put(BASE_URI+"/library/users/Sri Lakshmi",bean);
        log.info(bean.toString());
    }

    public void issueBookToUser(){
        LibraryBean libraryBean=new LibraryBean(2,"yashu",401);
        String str=template.postForObject(BASE_URI+"/library/users/Sri Lakshmi/2",libraryBean,String.class);
        log.info(str);
    }

    public void deleteBookForUser(){
        template.delete(BASE_URI + "/library/users/yashu/books/401");
    }

}
