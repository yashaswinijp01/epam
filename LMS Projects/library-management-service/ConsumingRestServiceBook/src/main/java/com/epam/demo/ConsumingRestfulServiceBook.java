package com.epam.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ConsumingRestfulServiceBook implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ConsumingRestfulServiceBook.class);

    @Autowired
    private BookRestClient bookRestClient;

    @Autowired
    private UserRestClient userRestClient;

    @Autowired
    private LibraryRestClient libraryRestClient;


    public static void main(String[] args) {
        SpringApplication.run(ConsumingRestfulServiceBook.class,args);

    }

    @Override
    public void run(String... args) throws Exception {

        //For Book Client

        //addBook
        //bookRestClient.addBookDetails();

        //View all books
        //bookRestClient.getAllBooksDetails();

        //Edit book details
        //bookRestClient.editBookDetails();

        //delete the book details
        //bookRestClient.deleteBook();
        //bookRestClient.getAllBooksDetails();

        //bookRestClient.getBookById();


        //For User Client

        //addUser
        //userRestClient.addUserDetails();

        //View all users
        //userRestClient.getAllUsersDetails();

        //Edit user details
        //userRestClient.editUserDetails();

        //delete the user details
        //userRestClient.deleteUser();
        //userRestClient.getAllUsersDetails();

        //userRestClient.getUserByUserName();



        libraryRestClient.getAllBooksDetails();
        libraryRestClient.getBookByBookId();
        libraryRestClient.addBook();
        libraryRestClient.updateBookById();
        libraryRestClient.deleteBookById();

        libraryRestClient.getUsers();
        libraryRestClient.getUserByName();
        libraryRestClient.addUser();
        libraryRestClient.updateUserByUsername();
        libraryRestClient.deleteUser();

        libraryRestClient.issueBookToUser();
        libraryRestClient.deleteBookForUser();
    }


    @Bean
    public RestTemplate getTemplate(){
        return new RestTemplate();
    }
}
