package com.epam.bean;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component
public class UserBean {

	private String userName;

	private String name;

	private String email;

	public UserBean(String userName, String name, String email) {
		this.userName = userName;
		this.name = name;
		this.email = email;
	}
}
