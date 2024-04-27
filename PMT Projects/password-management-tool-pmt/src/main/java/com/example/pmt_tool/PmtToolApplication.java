package com.example.pmt_tool;

import com.example.pmt_tool.entities.Account;
import com.example.pmt_tool.entities.Group;
import com.example.pmt_tool.entities.User;
import com.example.pmt_tool.repositories.AccountRepo;
import com.example.pmt_tool.repositories.GroupRepo;
import com.example.pmt_tool.repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PmtToolApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmtToolApplication.class, args);

	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}


}
