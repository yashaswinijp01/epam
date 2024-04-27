package com.epam.passwordmanagementtool.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private static final int USER_NAME_MIN_SIZE=3;
	private static final int USER_NAME_MAX_SIZE=20;
	
	private int id;
	
	@NotNull
	@Size(min=USER_NAME_MIN_SIZE,max=USER_NAME_MAX_SIZE,message="Username should boe of min 3 characters and max 20 characters")
	private String userName;
	
	@NotNull
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$", message = "Password must have minimum 8 characters and contain at least 1 UPPERCASE, 1 lower case, 1 number, 1 special character.")
	private String password;
	

}
