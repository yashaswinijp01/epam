package com.epam.demo;

import com.epam.bean.BookBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Component
public class BookRestClient {

    private  final String BASE_URI="http://localhost:9090/BooksPortal";

    @Autowired
    private RestTemplate template;

    private static final Logger log = LoggerFactory.getLogger(ConsumingRestfulServiceBook.class);

    public void addBookDetails(){
        BookBean bean = new BookBean("java","LakshmiBookDepo","anand");
        String str = template.postForObject(BASE_URI+"/addBook",bean,String.class);
        log.info(str);
    }

    public void getAllBooksDetails(){
        List<BookBean> list =template.getForObject(BASE_URI+"/books",List.class);
        log.info(list.toString());
    }

    public void editBookDetails(){
        BookBean bean = new BookBean(3,"Java Basics","Goshal","Rajeev");
        template.put(BASE_URI+"/editBook",bean);
        log.info(bean.toString());
    }

    public void getBookById(){
        BookBean bean = template.getForObject(BASE_URI+"/getDetails/3",BookBean.class);
        log.info(bean.toString());
    }

    public void deleteBook() {
        template.delete(BASE_URI+"/deleteBook/4");

        template.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                boolean bool =false;
                if(response.getStatusCode()== HttpStatus.INTERNAL_SERVER_ERROR){
                    bool= true;
                }
                return bool;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                throw new RuntimeException("please give valid id  "+response.getStatusCode());
            }
        });
    }
}
