package com.epam.passwordmanagementtool.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AccountDto {
	
	private static final int ACCOUNT_NAME_MIN_SIZE=3;
	private static final int ACCOUNT_NAME_MAX_SIZE=10;
	private static final int ACCOUNT_USERNAME_MIN_SIZE=5;
	private static final int ACCOUNT_USERNAME_MAX_SIZE=20;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Size(min = ACCOUNT_NAME_MIN_SIZE, max = ACCOUNT_NAME_MAX_SIZE, message = "length must be min of 3 chars and max of 10 chars")
	@Pattern(regexp = "^[a-z_]+$", message = "Account name can only have small alphabets and underscore is allowed")
	private String accountName;

	@NotNull
	@Size(min = ACCOUNT_USERNAME_MIN_SIZE, max = ACCOUNT_USERNAME_MAX_SIZE, message = "username should be of min 5 characters and max of 20 chars")
	private String accountUserName;
    
	
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$", message = "Password must have minimum 8 characters and contain at least 1 UPPERCASE, 1 lower case, 1 number, 1 special character. ")
	private String accountPassword;

	
	private String url;
	
	
	 @NonNull
	 private CategoryDto category;
	 
	 
	
	 

	
}
