package com.epam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    final static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Entering application.");
        System.out.println("_____________________HI_________");
        logger.info("Exiting application.");
    }
}
